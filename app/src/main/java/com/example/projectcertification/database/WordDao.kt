package com.example.projectcertification.database

import androidx.room.*
import com.example.projectcertification.model.Words
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(word: Words)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAll(listOfWords: List<Words>)

    @Query("DELETE from word_table")
    suspend fun deleteAll()



    @Query("SELECT * FROM word_table ORDER BY words ASC")
    fun getAlphabetizedWords(): Flow<List<Words>>
}