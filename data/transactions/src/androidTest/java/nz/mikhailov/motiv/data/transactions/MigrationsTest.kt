package nz.mikhailov.motiv.data.transactions

import androidx.core.database.getStringOrNull
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertArrayEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate.parse
import java.time.ZoneId

const val TEST_DB = "migration-test"

@RunWith(AndroidJUnit4::class)
class MigrationsTest {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        TransactionsDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migrate1to2() = with(helper) {
        createDatabase(TEST_DB, 1).use {
            it.execSQL("""
                INSERT INTO transactions(date, amount) VALUES
                    ('2021-06-12 17:59', 1),
                    ('2021-06-12 18:02', -11)
            """.trimIndent())
        }
        runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2).use {
            val offsetMillis =
                ZoneId.systemDefault().rules.getOffset(parse("2021-06-12").atStartOfDay()).totalSeconds * 1000
            val result = mutableListOf<Pair<Long, Int>>()
            it.query("SELECT date, amount FROM transactions").use { cursor ->
                while (cursor.moveToNext()) {
                    val date = cursor.getLong(0)
                    val amount = cursor.getInt(1)
                    result.add(Pair(date, amount))
                }
            }
            assertArrayEquals(
                arrayOf(
                    Pair(1623520740000 - offsetMillis, 1),
                    Pair(1623520920000 - offsetMillis, -11)),
                result.toTypedArray())
        }
    }

    @Test
    fun migrate3to4(): Unit = with(helper) {
        data class TransactionV4(val date: Long, val amount: Int, val activity: String?, val balance: Int)
        createDatabase(TEST_DB, 3).use {
            it.execSQL("""
                INSERT INTO transactions(date, amount, activity) VALUES
                    (1636099861309, 1, 'Filled.DeveloperMode'),
                    (1636100097890, 2, 'Filled.School'),
                    (1636099861818, 1, 'Filled.FitnessCenter'), -- intentionally wrong order
                    (1636100190452, -2, null)
            """.trimIndent())
        }
        runMigrationsAndValidate(TEST_DB, 4, true, MIGRATION_3_4).use {
            val result = mutableListOf<TransactionV4>()
            it.query("SELECT * FROM transactions").use { cursor ->
                while (cursor.moveToNext()) {
                    result.add(TransactionV4(
                        date = cursor.getLong(cursor.getColumnIndexOrThrow("date")),
                        amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount")),
                        activity = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("activity")),
                        balance = cursor.getInt(cursor.getColumnIndexOrThrow("balance")),
                    ))
                }
            }
            assertThat(result).containsExactlyInAnyOrder(
                TransactionV4(date = 1636099861309, amount = 1, balance = 1, activity = "Filled.DeveloperMode"),
                TransactionV4(date = 1636099861818, amount = 1, balance = 2, activity = "Filled.FitnessCenter"),
                TransactionV4(date = 1636100097890, amount = 2, balance = 4, activity = "Filled.School"),
                TransactionV4(date = 1636100190452, amount = -2, balance = 2, activity = null),
            )
        }
    }
}