package nz.mikhailov.motiv.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase.CONFLICT_NONE
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val MIGRATION_1_2 = object : Migration(1, 2) {

    val dateTimeFormatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm")
        .withZone(ZoneId.systemDefault())

    override fun migrate(database: SupportSQLiteDatabase) = with(database) {
        // SQLite bundled in API 26 doesn't support rename column
        execSQL("ALTER TABLE transactions RENAME TO transactions_1")
        execSQL("""
            CREATE TABLE transactions (
                date INTEGER NOT NULL PRIMARY KEY, 
                amount INTEGER NOT NULL)
            """.trimIndent())
        query("SELECT date, amount FROM transactions_1").use { cursor ->
            while (cursor.moveToNext()) {
                val date = dateTimeFormatter.parse(cursor.getString(0), Instant::from)
                val amount = cursor.getInt(1)
                database.insert("transactions", CONFLICT_NONE, ContentValues().apply {
                    put("date", date.toEpochMilli())
                    put("amount", amount)
                })
            }
        }
        execSQL("DROP TABLE transactions_1")
    }
}