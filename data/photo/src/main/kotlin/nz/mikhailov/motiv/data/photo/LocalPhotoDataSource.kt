package nz.mikhailov.motiv.data.photo

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.File.createTempFile
import javax.inject.Inject

private const val DIRECTORY = "images"
private const val PREFIX = "motiv-"

class LocalPhotoDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val storageDirectory by lazy {
        File(context.filesDir, DIRECTORY).apply {
            mkdirs()
        }
    }

    fun createNewFile(): File = createTempFile(PREFIX, null, storageDirectory)
}
