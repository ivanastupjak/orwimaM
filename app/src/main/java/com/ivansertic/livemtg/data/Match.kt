package com.ivansertic.livemtg.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="match_table")
data class Match(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val format: String,
    val playerOneName: String,
    val playerTwoName: String,
    val score: String
): Parcelable