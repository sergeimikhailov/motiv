package nz.mikhailov.motiv

import android.app.Application
import nz.mikhailov.motiv.repository.TransactionRepository

class MotivApplication: Application() {

    val database by lazy { MotivRoomDatabase.getDatabase(this) }
    val transactionRepository by lazy { TransactionRepository((database as MotivRoomDatabase).transactionRecordDao()) }
}