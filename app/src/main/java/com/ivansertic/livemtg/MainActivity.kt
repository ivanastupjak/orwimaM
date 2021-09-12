package com.ivansertic.livemtg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ivansertic.livemtg.data.Match
import com.ivansertic.livemtg.data.MatchViewModel



class MainActivity : AppCompatActivity() {
    private lateinit var bDraft: Button
    private lateinit var bSealed: Button
    private lateinit var bStandard: Button
    private lateinit var bModern: Button
    private lateinit var tPlayerOne: TextView
    private lateinit var player1Name: EditText
    private lateinit var tPlayerTwo: TextView
    private lateinit var player2Name: EditText
    private lateinit var bStartGame: Button
    private lateinit var mMatchViewModel: MatchViewModel
    private lateinit var bSkip: FloatingActionButton
    private var format: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMatchViewModel=ViewModelProvider(this).get(MatchViewModel::class.java)

        this.bDraft = findViewById(R.id.bDraft)
        this.bSealed=findViewById(R.id.bSealed)
        this.bStandard=findViewById(R.id.bStandard)
        this.bModern=findViewById(R.id.bModern)
        this.tPlayerOne=findViewById(R.id.tPlayerOne)
        this.player1Name=findViewById(R.id.player1Name)
        this.tPlayerTwo=findViewById(R.id.tPlayerTwo)
        this.player2Name=findViewById(R.id.player2Name)
        this.bStartGame=findViewById(R.id.bStartGame)
        this.bSkip=findViewById(R.id.floatingActionButton)
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        bDraft.setOnClickListener{
           changeVisibility()
            format="Draft"
        }

        bSealed.setOnClickListener{
            changeVisibility()
            format="Sealed"
        }

        bStandard.setOnClickListener{
            changeVisibility()
            format="Standard"
        }

        bModern.setOnClickListener{
            changeVisibility()
            format="Modern"
        }

        bStartGame.setOnClickListener{
            insertDataToDatabase()
            val playerOneName=player1Name.text.toString()
            val playerTwoName=player2Name.text.toString()
            val intent = Intent(this, LifeTracker::class.java)
            intent.putExtra("playerOneName", playerOneName)
            intent.putExtra("playerTwoName", playerTwoName)
            startActivity(intent)
        }

        bSkip.setOnClickListener{
            val intent = Intent(this, ScoreBoard::class.java)
            startActivity(intent)
        }

    }

    private fun insertDataToDatabase() {
        val playerOneName = player1Name.text.toString()
        val playerTwoName = player2Name.text.toString()


        if(inputCheck(playerOneName, playerTwoName)){
            val match = Match(0,format,playerOneName, playerTwoName, "0-0")

            mMatchViewModel.addMatch(match)
        }
    }

    private fun inputCheck(playerOneName: String, playerTwoName: String): Boolean{
        return !(TextUtils.isEmpty(playerOneName) && TextUtils.isEmpty(playerTwoName))
    }

    private fun changeVisibility(){
        bDraft.visibility= View.GONE
        bSealed.visibility=View.GONE
        bStandard.visibility=View.GONE
        bModern.visibility=View.GONE
        tPlayerOne.visibility=View.VISIBLE
        player1Name.visibility=View.VISIBLE
        tPlayerTwo.visibility=View.VISIBLE
        player2Name.visibility=View.VISIBLE
        bStartGame.visibility=View.VISIBLE
    }
}