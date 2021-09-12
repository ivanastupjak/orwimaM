package com.ivansertic.livemtg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivansertic.livemtg.data.MatchViewModel

class ScoreBoard : AppCompatActivity() {

    private lateinit var mMatchViewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)
    }
}