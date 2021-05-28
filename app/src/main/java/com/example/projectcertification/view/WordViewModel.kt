package com.example.projectcertification

import androidx.lifecycle.*
import com.example.projectcertification.model.Words
import com.example.projectcertification.repository.WordRepository

import kotlinx.coroutines.launch


class WordViewModel(private val repository: WordRepository) : ViewModel() {

    private var _allWord = MutableLiveData<List<Words>>()

    lateinit var allWord : LiveData<List<Words>>

    fun getAllWords(): LiveData<List<Words>> {

        _allWord = repository.allWords.asLiveData() as MutableLiveData<List<Words>>

         allWord = _allWord

        return allWord

    }

    fun insertWord(word: Words) = viewModelScope.launch {
        repository.insert(word)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}