package nz.mikhailov.motiv

import android.app.Application
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MotivApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseRemoteConfig.getInstance().fetchAndActivate()
    }
}
