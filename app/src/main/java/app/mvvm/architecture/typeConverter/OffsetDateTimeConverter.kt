package app.mvvm.architecture.typeConverter

import androidx.room.TypeConverter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object OffsetDateTimeConverter {
    private val format = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toDate(dateString: String?): OffsetDateTime? {
        return if (dateString == null) {
            null
        } else {
            OffsetDateTime.parse(dateString)
        }
    }

    @TypeConverter
    @JvmStatic
    fun toDateString(date: OffsetDateTime?): String? {
        return date?.let(format::format)
    }

    object Serializer : KSerializer<OffsetDateTime> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OffsetDateTime", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: OffsetDateTime) {
            encoder.encodeString(format.format(value))
        }

        override fun deserialize(decoder: Decoder): OffsetDateTime {
            return OffsetDateTime.parse(decoder.decodeString())
        }
    }
}