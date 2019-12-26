package com.cuty.simplenotepad

import androidx.lifecycle.LiveData

class AplicationRepository(private val dao:Dao){
    // esta variable aloja los objetos Words que hicimos antes
    val allWords : LiveData<List<Word>> = dao.getAlphabetizeWords()

    suspend fun insert(word:Word){
        dao.insert(word)
    }
    suspend fun delete(deletedWord:String){
        dao.deleteWord(deletedWord)

    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }

}