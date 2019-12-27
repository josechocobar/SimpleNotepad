package com.cuty.simplenotepad

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1 , exportSchema = false)
public abstract class DatabaseService: RoomDatabase() {
    //
    abstract fun Dao(): Dao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getDatabase (context: Context, scope: CoroutineScope):DatabaseService{
            return INSTANCE ?: kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }

        }
        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.Dao())
                    }
                }
            }
        }
        suspend fun populateDatabase(wordDao: Dao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.


            var word = Word("Jose",MainActivity.currentDate)
            wordDao.insert(word)
            word = Word("Chocobar",MainActivity.currentDate)
            wordDao.insert(word)
        }

    }


}