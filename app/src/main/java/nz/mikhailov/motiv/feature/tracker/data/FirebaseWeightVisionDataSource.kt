package nz.mikhailov.motiv.feature.tracker.data

import com.google.firebase.functions.HttpsCallableReference
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class FirebaseWeightVisionDataSource @Inject constructor(
) {
    private val function: HttpsCallableReference = Firebase
        .functions("australia-southeast1")
        .getHttpsCallable("weight")

    @Suppress("UNCHECKED_CAST")
    suspend fun recognize(base64EncodedImage: String) = try {
        val response = function.call(mapOf("image" to base64EncodedImage)).await()
        val result by response.data as Map<String, Double?>
        val error by response.data as Map<String, String?>

        val weight = result
        val reason = error ?: "Something went wrong!"

        when {
            weight != null -> success(weight)
            else -> failure(Exception(reason))
        }
    } catch (e: Throwable) {
        failure(Exception("Something went wrong!", e))
    }
}
