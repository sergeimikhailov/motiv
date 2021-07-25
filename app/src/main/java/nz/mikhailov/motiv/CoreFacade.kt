package nz.mikhailov.motiv

import android.app.Application
import androidx.room.RoomDatabase

interface CoreFacade {
    companion object {

        private lateinit var context: Application

        fun init(context: Application) {
            this.context = context
        }

        val roomDatabase: RoomDatabase
            get() = MotivRoomDatabase.getDatabase(context = context)
    }
}
