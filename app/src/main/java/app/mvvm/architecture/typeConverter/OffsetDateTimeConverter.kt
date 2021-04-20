package app.mvvm.architecture.typeConverter

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.OffsetDateTime


object OffsetDateTimeConverter {
    @TypeConverter
    @FromJson
    @JvmStatic
    fun toDate(dateString: String?): OffsetDateTime? {
        return if (dateString == null) {
            null
        } else {
            OffsetDateTime.parse(dateString)
        }
    }

    @TypeConverter
    @ToJson
    @JvmStatic
    fun toDateString(date: OffsetDateTime?): String? {
        return date?.toString()
    }
}