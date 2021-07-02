package app.mvvm.architecture.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.time.OffsetDateTime

@Entity(primaryKeys= ["author", "title"])
@Parcelize
data class NewsItem(
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: OffsetDateTime,
    val content: String?
): Parcelable {
    val id: CompositeKey
        get() = CompositeKey(author, title)

    data class CompositeKey(val author: String, val title: String) {
        val identifier
            get() = listOf(author, title).joinToString(SEPARATOR)

        companion object {
            private const val SEPARATOR = "__"

            fun fromIdentifier(identifier: String): CompositeKey {
                val fields = identifier.split(SEPARATOR)
                require(fields.size == 2)
                return CompositeKey(fields[0], fields[1])
            }
        }
    }
}