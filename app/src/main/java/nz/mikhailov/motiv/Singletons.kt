package nz.mikhailov.motiv

import android.app.Application
import androidx.room.RoomDatabase
import nz.mikhailov.motiv.database.MotivRoomDatabase

interface Singletons {
    companion object {

        private lateinit var context: Application

        fun init(context: Application) {
            this.context = context
        }

        val roomDatabase: MotivRoomDatabase
            get() = MotivRoomDatabase.getDatabase(context = context) as MotivRoomDatabase
    }
}
