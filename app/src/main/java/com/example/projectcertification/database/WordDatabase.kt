package com.example.projectcertification.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projectcertification.model.Words
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Words::class],version = 1,exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDatabase(context: Context, applicationScope: CoroutineScope): WordDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(applicationScope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    // Delete all content here.
                 //   wordDao.deleteAll()

                    // Add sample words.
                    var word = Words("Hello")
                    wordDao.add(word)
                    word = Words("World!")
                    wordDao.add(word)

                    // TODO: Add your own words!
                    word = Words("TODO!")
                    wordDao.add(word)

                    Log.d("DB Created","DONE")
                }
            }
        }
    }
}