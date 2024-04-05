package nz.mikhailov.motiv.util

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import javax.inject.Inject

class FirebaseRemoteConfig @Inject constructor() {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    val autofillEnabled: Boolean
        get() = remoteConfig.getBoolean("weight_autofill")
}
