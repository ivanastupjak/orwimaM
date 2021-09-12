package com.ivansertic.livemtg.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Match>>
    var score: MutableLiveData<String> = MutableLiveData()

    private val repository: MatchRepository

    init {
        val matchDao = MatchDatabase.getDatabase(application).matchDao()
        repository = MatchRepository(matchDao)
        readAllData= repository.readAllData
    }

    fun addMatch(match: Match){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMatch(match)
        }
    }

    fun updateLatest(score: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(score)
        }
    }

    fun getLatest(){
        viewModelScope.launch(Dispatchers.IO) {
            val oldScore:String = repository.getLatestScore()
            withContext(Main){
                score.value = oldScore
            }
        }
    }



}