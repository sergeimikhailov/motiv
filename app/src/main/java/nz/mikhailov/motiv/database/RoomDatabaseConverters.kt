package nz.mikhailov.motiv.database

import androidx.room.TypeConverter
import java.time.Instant

class RoomDatabaseConverters {
    @TypeConverter
    fun toInstant(value: Long?) = value?.let {
        Instant.ofEpochMilli(it)
    }

    @TypeConverter
    fun fromInstant(instant: Instant?) = instant?.toEpochMilli()
}
