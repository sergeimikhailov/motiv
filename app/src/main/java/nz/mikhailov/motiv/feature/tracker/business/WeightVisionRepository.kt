package nz.mikhailov.motiv.feature.tracker.business

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat.JPEG
import android.util.Base64
import nz.mikhailov.motiv.feature.tracker.data.FirebaseWeightVisionDataSource
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class WeightVisionRepository @Inject constructor(
    private val dataSource: FirebaseWeightVisionDataSource,
) {
    suspend fun readScale(image: Bitmap): Result<Double> {
        val jpgImage = ByteArrayOutputStream().use {
            image.compress(JPEG, 90, it)
            it.toByteArray()
        }
        return dataSource.recognize(Base64.encodeToString(jpgImage, Base64.NO_WRAP))
    }
}
