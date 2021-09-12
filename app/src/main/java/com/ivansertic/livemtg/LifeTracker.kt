package com.ivansertic.livemtg

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProvider
import com.ivansertic.livemtg.data.MatchViewModel

class LifeTracker : AppCompatActivity() {

    private lateinit var mMatchViewModel: MatchViewModel

    private lateinit var player1Life: TextView
    private lateinit var player2Life: TextView
    private lateinit var bDeclareWinner: Button
    private lateinit var bGainLife1: FloatingActionButton
    private lateinit var bLoseLife1: FloatingActionButton
    private lateinit var bGainLife2: FloatingActionButton
    private lateinit var bLoseLife2: FloatingActionButton
    private lateinit var tPlayerOneName: TextView
    private lateinit var tPlayerTwoName: TextView
    private lateinit var tPlayerOneScore: TextView
    private lateinit var tPlayerTwoScore: TextView

    private var playerLife1: Int=20
    private var playerLife2: Int=20

    private var scorePlayer1: Int=0
    private var scorePlayer2: Int=0

    companion object{
        const val ACTIVITY_CONSTANT = 1
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_tracker)
        mMatchViewModel= ViewModelProvider(this).get(MatchViewModel::class.java)

        this.player1Life=findViewById(R.id.player1Life)
        player1Life.setText(playerLife1.toString())

        this.player2Life=findViewById(R.id.player2Life)
        player2Life.setText(playerLife2.toString())

        this.bDeclareWinner=findViewById(R.id.bDeclareWinner)
        this.bGainLife1=findViewById(R.id.bGainLife1)
        this.bLoseLife1=findViewById(R.id.loseLife1)
        this.bGainLife2=findViewById(R.id.gainLife2)
        this.bLoseLife2=findViewById(R.id.loseLife2)
        setOnClickListeners()


        val playerOneName=intent.getStringExtra("playerOneName")
        val playerTwoName=intent.getStringExtra("playerTwoName")

        this.tPlayerOneName=findViewById(R.id.tPlayerOneName)
        tPlayerOneName.text=playerOneName.toString()

        this.tPlayerTwoName=findViewById(R.id.tPlayerTwoName)
        tPlayerTwoName.text=playerTwoName.toString()

        this.tPlayerOneScore=findViewById(R.id.tPlayerOneScore)
        tPlayerOneScore.text=scorePlayer1.toString()

        this.tPlayerTwoScore=findViewById(R.id.tPlayerTwoScore)
        tPlayerTwoScore.text=scorePlayer2.toString()

    }

    override fun onResume() {
        super.onResume()

        mMatchViewModel.getLatest()
        mMatchViewModel.score.observe(this ,{score -> score?.let{
            this.tPlayerOneScore.text = score.split('-')[0]
            this.tPlayerTwoScore.text = score.split('-')[1]

            if(this.tPlayerOneScore.text.toString().toInt() == 2 || this.tPlayerTwoScore.text.toString().toInt() == 2){
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }})
    }




    private fun setOnClickListeners() {
        bDeclareWinner.setOnClickListener{
            val playerOneName=intent.getStringExtra("playerOneName")
            val playerTwoName=intent.getStringExtra("playerTwoName")
            val intent= Intent(this, PopUpWindow::class.java)
            intent.putExtra("playerOneName",playerOneName)
            intent.putExtra("playerTwoName",playerTwoName)
            startActivity(intent)
        }

        bGainLife1.setOnClickListener {
            playerLife1++
            player1Life.setText(playerLife1.toString())
        }
        bLoseLife1.setOnClickListener {
            playerLife1--
            player1Life.setText(playerLife1.toString())
        }
        bGainLife2.setOnClickListener {
            playerLife2++
            player2Life.setText(playerLife2.toString())
        }
        bLoseLife2.setOnClickListener {
            playerLife2--
            player2Life.setText(playerLife2.toString())
        }

    }

}