package nz.mikhailov.motiv.database

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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
        MotivRoomDatabase::class.java.canonicalName,
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
}