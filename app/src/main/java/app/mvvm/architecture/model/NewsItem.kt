package app.mvvm.architecture.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.OffsetDateTime
import java.util.*

@Entity
@Parcelize
data class NewsItem(
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: OffsetDateTime,
    val content: String?,
    // NOTE: This project intends to show the android project architecture and setup, not the news
    // api. Since an article of the news api doesn't have an id, we simply generate one for local
    // use.
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
): Parcelable {
}