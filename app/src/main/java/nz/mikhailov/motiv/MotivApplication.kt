package nz.mikhailov.motiv

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.appCheck
import com.google.firebase.remoteconfig.remoteConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MotivApplication: Application() {

    @Inject lateinit var appCheckProviderFactory: AppCheckProviderFactory

    override fun onCreate() {
        super.onCreate()
        Firebase.appCheck.installAppCheckProviderFactory(appCheckProviderFactory)
        Firebase.remoteConfig.fetchAndActivate()
    }
}
