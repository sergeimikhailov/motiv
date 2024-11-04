package nz.mikhailov.motiv.data.photo

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.provider.MediaStore.EXTRA_OUTPUT
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.FileProvider.getUriForFile
import java.io.File
import javax.inject.Inject

typealias TakePictureContract = ActivityResultContract<Void?, Bitmap?>

class PhotoRepository @Inject constructor(
    private val dataSource: LocalPhotoDataSource,
) {

    private val targetEnvelopeWidth = 1024
    private val targetEnvelopeHeight = 1024

    fun takePictureContract() = object : TakePictureContract() {

        private val authority = "nz.mikhailov.motiv.fileprovider"
        private val file by lazy { dataSource.createNewFile() }

        override fun createIntent(context: Context, input: Void?) =
            Intent(ACTION_IMAGE_CAPTURE)
                .putExtra(EXTRA_OUTPUT, getUriForFile(context, authority, file))

        override fun parseResult(resultCode: Int, intent: Intent?) =
            when (resultCode) {
                RESULT_OK -> file.resizedBitmap()
                else -> null
            }
    }

    private fun File.resizedBitmap(): Bitmap? {
        val bounds = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, this)
        }
        val newBounds = resizeOptions(
            width = bounds.outWidth,
            height = bounds.outHeight,
        )
        return BitmapFactory.decodeFile(path, newBounds)
    }

    private fun resizeOptions(width: Int, height: Int): BitmapFactory.Options {
        if (pictureFitsEnvelope(width, height)) {
            return BitmapFactory.Options()
        }

        val slightlyLargerThanEnvelopeSampleSize =
            listOf(2, 4, 8, 16).fold(initial = 1) { currentSampleSize, nextSampleSize ->
                when (sampledPictureFitsEnvelope(width, height, nextSampleSize)) {
                    true -> currentSampleSize
                    else -> nextSampleSize
                }
            }

        return BitmapFactory.Options().apply {
            inScaled = true
            inSampleSize = slightlyLargerThanEnvelopeSampleSize
            inDensity = if (width > height) width else height
            inTargetDensity = if (width > height) targetEnvelopeWidth else targetEnvelopeHeight
        }
    }

    private fun pictureFitsEnvelope(width: Int, height: Int) =
        sampledPictureFitsEnvelope(width, height, sampleSize = 1)

    private fun sampledPictureFitsEnvelope(width: Int, height: Int, sampleSize: Int) =
        width / sampleSize < targetEnvelopeWidth && height / sampleSize < targetEnvelopeHeight
}
