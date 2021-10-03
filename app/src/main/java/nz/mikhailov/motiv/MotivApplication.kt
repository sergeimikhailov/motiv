package nz.mikhailov.motiv

import android.app.Application

@Suppress("unused")
class MotivApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Singletons.init(this)
    }
}
