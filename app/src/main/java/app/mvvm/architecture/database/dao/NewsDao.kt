package app.mvvm.architecture.database.dao

import androidx.room.*
import app.mvvm.architecture.model.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<NewsItem>)

    @Query("SELECT * FROM NewsItem")
    fun getAll(): List<NewsItem>

    @Query("SELECT * FROM NewsItem")
    fun getAllFlow(): Flow<List<NewsItem>>

    @Query("DELETE FROM NewsItem")
    suspend fun clear()

    @Transaction
    suspend fun replaceAll(news: List<NewsItem>) {
        clear()
        insertAll(news)
    }
}



