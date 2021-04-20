package app.mvvm.architecture.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.time.OffsetDateTime

@Entity(primaryKeys= [ "author", "title" ])
@Parcelize
data class NewsItem (
        val author: String,
        val title: String,
        val description: String?,
        val url: String,
        val urlToImage: String?,
        val publishedAt: OffsetDateTime,
        val content: String?
): Parcelable