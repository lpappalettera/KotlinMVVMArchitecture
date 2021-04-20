package app.mvvm.architecture.database

import androidx.room.migration.Migration

class Migrations {
    companion object {
        /**
         * The current database version.
         */
        const val DB_VERSION = 1

        /**
         * A collection of all database migrations.
         */
        val ALL = arrayOf<Migration>()
    }
}