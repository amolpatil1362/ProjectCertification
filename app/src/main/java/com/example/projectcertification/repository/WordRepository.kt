package com.example.projectcertification.repository

import androidx.annotation.WorkerThread
import com.example.projectcertification.database.WordDao
import com.example.projectcertification.model.Words
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDoa: WordDao) {
    val allWords: Flow<List<Words>> = wordDoa.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Words) {
        wordDoa.add(word)
    }

    suspend fun deleteAll() {
        wordDoa.deleteAll()
    }
}