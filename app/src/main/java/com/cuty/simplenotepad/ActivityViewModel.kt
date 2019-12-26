package com.cuty.simplenotepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application): AndroidViewModel(application){

    private val repository:AplicationRepository

    val allWords: LiveData<List<Word>>

    init {
        val dao = DatabaseService.getDatabase(application,viewModelScope).Dao()
        repository = AplicationRepository(dao)
        allWords=repository.allWords

    }
    fun insert(word:Word){
        viewModelScope.launch {
            repository.insert(word)
        }
    }
    fun delete(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
    fun deleteWord(deleteWord:String){
        viewModelScope.launch {
            repository.delete(deleteWord)

        }
    }

}