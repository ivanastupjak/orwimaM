package com.ivansertic.livemtg.data

import androidx.lifecycle.LiveData

class MatchRepository(private val matchDao: MatchDao) {

    val readAllData: LiveData<List<Match>> = matchDao.readAllData()

    suspend fun addMatch(match: Match){
        matchDao.addMatch(match)
    }

    suspend fun update(score: String){
        matchDao.updateLatest(score)
    }

    suspend fun getLatestScore(): String{

        return matchDao.getLatest()
    }


}