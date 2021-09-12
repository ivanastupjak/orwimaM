package com.ivansertic.livemtg.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMatch(match: Match)

    @Query("SELECT * FROM match_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Match>>

    @Query("Update match_table SET score = :score WHERE id = (Select Max(id) from match_table)")
    fun updateLatest(score: String)

    @Query("Select score from match_table where id = (Select Max(id) from match_table)")
    fun getLatest(): String

}