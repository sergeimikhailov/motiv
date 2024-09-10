package nz.mikhailov.motiv.data.transactions

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase.CONFLICT_NONE
import androidx.core.database.getStringOrNull
import androidx.room.DeleteTable
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val MIGRATION_1_2 = object : Migration(1, 2) {

    val dateTimeFormatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm")
        .withZone(ZoneId.systemDefault())

    override fun migrate(db: SupportSQLiteDatabase) = with(db) {
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
                db.insert("transactions", CONFLICT_NONE, ContentValues().apply {
                    put("date", date.toEpochMilli())
                    put("amount", amount)
                })
            }
        }
        execSQL("DROP TABLE transactions_1")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {

    override fun migrate(db: SupportSQLiteDatabase) = with(db) {
        // SQLite doesn't support add/remove NOT NULL constraint
        // using temporary table strategy
        execSQL("""
            CREATE TABLE transactions_4 (
                date INTEGER NOT NULL PRIMARY KEY,
                amount INTEGER NOT NULL,
                activity TEXT,
                balance INTEGER NOT NULL)
            """.trimIndent())
        query("SELECT * FROM transactions ORDER BY date ASC").use { cursor ->
            var runningBalance = 0
            while (cursor.moveToNext()) {
                val date = cursor.getLong(cursor.getColumnIndexOrThrow("date"))
                val amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount"))
                val activity = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("activity"))
                runningBalance += amount
                db.insert("transactions_4", CONFLICT_NONE, ContentValues().apply {
                    put("date", date)
                    put("amount", amount)
                    put("activity", activity)
                    put("balance", runningBalance)
                })
            }
        }
        execSQL("DROP TABLE transactions")
        execSQL("ALTER TABLE transactions_4 RENAME TO transactions")
    }
}

@DeleteTable.Entries(DeleteTable(tableName = "weight"))
internal class Migration5To6: AutoMigrationSpec
