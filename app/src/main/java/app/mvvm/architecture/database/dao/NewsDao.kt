package app.mvvm.architecture.database.dao

import androidx.room.*
import app.mvvm.architecture.model.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsItems: List<NewsItem>)

    @Query("SELECT * FROM NewsItem")
    fun getAll(): List<NewsItem>

    @Query("SELECT * FROM NewsItem")
    fun getAllFlow(): Flow<List<NewsItem>>

    @Query("SELECT * FROM NewsItem WHERE id = :id")
    fun getFlow(id: String): Flow<NewsItem>

    @Query("DELETE FROM NewsItem")
    suspend fun clear()

    @Transaction
    suspend fun replaceAll(newsItems: List<NewsItem>) {
        clear()
        insertAll(newsItems)
    }
}



