package app.mvvm.architecture.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.mvvm.architecture.database.dao.NewsDao
import app.mvvm.architecture.typeConverter.OffsetDateTimeConverter
import app.mvvm.architecture.model.NewsItem
import splitties.init.appCtx

@Database(
    exportSchema = false,
    version = Migrations.DB_VERSION,
    entities = [
        NewsItem::class
    ]
)
@TypeConverters(
    OffsetDateTimeConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        private const val dbName = "news.db"

        val instance: AppDatabase by lazy {
            Room.databaseBuilder(
                appCtx,
                AppDatabase::class.java,
                dbName
            )
                .addMigrations(*Migrations.ALL)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}