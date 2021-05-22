package ge.llortkipanidze.RpsGame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private var isGameOn = false
    private lateinit var rockButton : Button
    private lateinit var paperButton: Button
    private lateinit var scissorsButton : Button
    private lateinit var computerPointsView : TextView
    private lateinit var playerPointsView : TextView
    private lateinit var playerImage : ImageView
    private lateinit var computerImage : ImageView
    private lateinit var startTextView: TextView
    private val ROCK = 0
    private val PAPER = 1
    private val SCISSORS = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getComponents()
        addButtonListeners()
    }

    private fun addButtonListeners() {
        rockButton.setOnClickListener {
            handleButtonEvent(ROCK)
        }

        paperButton.setOnClickListener {
            handleButtonEvent(PAPER)
        }
        scissorsButton.setOnClickListener {
            handleButtonEvent(SCISSORS)
        }

    }

    private fun handleButtonEvent(playerMove: Int){
        if(!isGameOn){
            startGame()
        }
        var computerMove = generateComputerMove()
        setComputerImage(computerMove)
        setPlayerImage(playerMove)
        handleResult(playerMove, computerMove)
    }

    private fun setComputerImage(computerMove: Int) {
        when (computerMove){
            ROCK -> computerImage.setImageResource(R.drawable.rock)
            PAPER -> computerImage.setImageResource(R.drawable.paper)
            else -> computerImage.setImageResource(R.drawable.scissors)

        }

    }

    private fun setPlayerImage(playerMove: Int){
        when (playerMove){
            ROCK -> playerImage.setImageResource(R.drawable.rock)
            PAPER -> playerImage.setImageResource(R.drawable.paper)
            else -> playerImage.setImageResource(R.drawable.scissors)

        }
    }

    private fun startGame() {
        isGameOn = true
        startTextView.visibility = View.INVISIBLE
    }

    private fun getComponents(){
        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        scissorsButton = findViewById(R.id.scissorsButton)
        computerPointsView = findViewById(R.id.computerPoints)
        playerPointsView = findViewById(R.id.playerPoints)
        playerImage = findViewById(R.id.playerImage)
        computerImage = findViewById(R.id.computerImage)
        startTextView = findViewById(R.id.startTextView)

    }

    private fun generateComputerMove() : Int {
        return (ROCK..SCISSORS).random()
    }

    private fun handleResult(playerMove : Int, computerMove : Int){
        if(playerMove == computerMove) {
            handleTie()
        }
        else if((playerMove == ROCK && computerMove == PAPER) ||
                (playerMove == PAPER && computerMove == SCISSORS) ||
                (playerMove == SCISSORS &&  computerMove == ROCK )) {
            handlePlayerLose()

        }
        else {
            handlePlayerWin()
        }


    }

    private fun handleTie(){
        computerPointsView.setTextColor(ContextCompat.getColor(this, R.color.tieColor))
        playerPointsView.setTextColor(ContextCompat.getColor(this, R.color.tieColor))

    }
    private fun handlePlayerWin(){
        computerPointsView.setTextColor(ContextCompat.getColor(this, R.color.loserColor))
        val lastPlayerPoints = Integer.parseInt(playerPointsView.text.toString())
        playerPointsView.text = "" + (lastPlayerPoints + 1)
        playerPointsView.setTextColor(ContextCompat.getColor(this, R.color.winnerColor))


    }
    private fun handlePlayerLose(){
        playerPointsView.setTextColor(ContextCompat.getColor(this, R.color.loserColor))
        val lastComputerPoints = Integer.parseInt(computerPointsView.text.toString())
        computerPointsView.text = "" + (lastComputerPoints + 1)
        computerPointsView.setTextColor(ContextCompat.getColor(this, R.color.winnerColor))

    }

}