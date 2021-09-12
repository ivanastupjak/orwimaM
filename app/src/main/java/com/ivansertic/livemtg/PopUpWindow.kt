package com.ivansertic.livemtg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.ivansertic.livemtg.data.MatchViewModel

class PopUpWindow : AppCompatActivity() {

    private lateinit var mMatchViewModel: MatchViewModel
    private lateinit var bPlayerOneWinner: Button
    private lateinit var bPlayerTwoWinner: Button
    private var winsPlayerOne: Int=0
    private var winsPlayerTwo: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        setContentView(R.layout.activity_pop_up_window)

        mMatchViewModel= ViewModelProvider(this).get(MatchViewModel::class.java)

        val playerOneName=intent.getStringExtra("playerOneName")
        val playerTwoName=intent.getStringExtra("playerTwoName")


        this.bPlayerOneWinner=findViewById(R.id.bWinner1)
        this.bPlayerTwoWinner=findViewById(R.id.bWinner2)
        setOnClickListeners()

        bPlayerOneWinner.text=playerOneName.toString()
        bPlayerTwoWinner.text=playerTwoName.toString()
    }



    private fun setOnClickListeners() {
        bPlayerOneWinner.setOnClickListener {
            val latestScore = mMatchViewModel.getLatest()
            mMatchViewModel.score.observe(this,{score -> score?.let{
                val scoreArray = score.split("-")

                val scorePlayerOne = scoreArray[0].toInt()
                val scorePlayerTwo = scoreArray[1].toInt()
                mMatchViewModel.updateLatest("${scorePlayerOne + 1}-${scorePlayerTwo}")
            }})

            finish()

        }
        bPlayerTwoWinner.setOnClickListener{
            val latestScore = mMatchViewModel.getLatest()
            mMatchViewModel.score.observe(this,{score -> score?.let{
                val scoreArray = score.split("-")

                val scorePlayerOne = scoreArray[0].toInt()
                val scorePlayerTwo = scoreArray[1].toInt()
                mMatchViewModel.updateLatest("${scorePlayerOne}-${scorePlayerTwo+1}")
            }})

            finish()
        }

    }
}