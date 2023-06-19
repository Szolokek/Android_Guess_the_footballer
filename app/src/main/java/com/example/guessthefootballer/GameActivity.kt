package com.example.guessthefootballer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessthefootballer.adapter.PlayerAdapter
import com.example.guessthefootballer.data.FootballPlayer
import com.example.guessthefootballer.data.FootballPlayerDatabase
import com.example.guessthefootballer.databinding.ActivityGameBinding
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random.Default.nextInt

class GameActivity : AppCompatActivity(), PlayerAdapter.FootballPlayerClickListener {
    private lateinit var binding: ActivityGameBinding
    private lateinit var database: FootballPlayerDatabase
    private lateinit var adapter: PlayerAdapter
    private val players = mutableListOf<FootballPlayer>()
    private val names = mutableListOf<String>()
    private val playersGuessed = mutableListOf<FootballPlayer>()
    private lateinit var hiddenPlayer: FootballPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = FootballPlayerDatabase.getDatabase(applicationContext)
        initRecyclerView()
        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        binding.btnGiveUp.setOnClickListener {
            playersGuessed.add(players[names.indexOf(hiddenPlayer.name)])
            adapter.update(playersGuessed)
            val alertbox: AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(this)
            gameOver()
            alertbox.setMessage("You gave up.\nCheck the solution in the list.\nGame Over!")
            alertbox.setNeutralButton("Ok",
                DialogInterface.OnClickListener { arg0, arg1 ->
                })
            alertbox.show()
        }
        binding.actv.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names))

        binding.actv.setOnItemClickListener{parent, view, position, id ->
            playersGuessed.add(players[names.indexOf(binding.actv.text.toString())])
            adapter.update(playersGuessed)
            val alertbox: AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(this)
            binding.guessLeft.text = (binding.guessLeft.text.toString().toInt()-1).toString() //Guess csökkentése 1-el
            if(binding.guessLeft.text.toString().toInt() == 0){
                gameOver()
                alertbox.setMessage("You ran out of guesses.\nGame Over!")
                alertbox.setNeutralButton("Ok",
                    DialogInterface.OnClickListener { arg0, arg1 ->
                    })
                alertbox.show()
            }

            if(playersGuessed[playersGuessed.size-1] == hiddenPlayer){
                gameOver()
                alertbox.setMessage("Congratulations you win!")
                alertbox.setNeutralButton("Ok",
                    DialogInterface.OnClickListener { arg0, arg1 ->
                    })
                alertbox.show()
            }
        }


    }

    private fun addnewplayer(newplayer: FootballPlayer){
        thread{
            database.footballPlayerDao().insert(newplayer)
        }
    }
    override fun onPlayerGuess(footballPlayer: FootballPlayer){
        thread{
            database.footballPlayerDao().update(footballPlayer)
            Log.d("GameActivity", "Players update was successful")
        }
    }

    private fun initRecyclerView() {
        adapter = PlayerAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }


    private fun loadItemsInBackground() {
        thread {
            players.addAll(database.footballPlayerDao().getAll())
            if(players.size == 0){
                firstLoad()
                players.addAll(database.footballPlayerDao().getAll())
            }
            names.addAll(database.footballPlayerDao().getAllNames())
            val rand = Random()
            hiddenPlayer = players[rand.nextInt(players.size)]
            adapter.initGameStart(hiddenPlayer)
        }
    }

    override fun onBackPressed() {
        val alertbox: AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(this)
        alertbox.setMessage("Do you really want to quit?\n" +
                "Your progress will be lost!")
        alertbox.setNeutralButton("Ok",
            DialogInterface.OnClickListener { arg0, arg1 ->
                super.onBackPressed()
            })
        alertbox.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { arg0, arg1 ->

            })
        alertbox.show()
    }

    fun gameOver(){
        binding.actv.isFocusable = false
        binding.btnGiveUp.isEnabled = false
    }

    fun firstLoad(){
        //Manchester United
        addnewplayer(FootballPlayer(1,"David de Gea","Spain", "Premier League","Manchester United", "GK", 31, 1))
        addnewplayer(FootballPlayer(2,"Diogo Dalot","Portugal", "Premier League","Manchester United", "DF", 23, 20))
        addnewplayer(FootballPlayer(3,"Victor Lindelof","Sweden", "Premier League","Manchester United", "DF", 28, 2))
        addnewplayer(FootballPlayer(4,"Harry Maguire","England", "Premier League","Manchester United", "DF", 29, 5))
        addnewplayer(FootballPlayer(5,"Tyrell Malacia","Netherlands", "Premier League","Manchester United", "DF", 23, 12))
        addnewplayer(FootballPlayer(6,"Lisandro Martinez", "Argentina" ,"Premier League","Manchester United", "DF",24,6))
        addnewplayer(FootballPlayer(7,"Luke Shaw","England", "Premier League","Manchester United", "DF", 27, 23))
        addnewplayer(FootballPlayer(8,"Raphael Varane","France", "Premier League","Manchester United", "DF", 24, 29))
        addnewplayer(FootballPlayer(9,"Casemiro","Brazil", "Premier League","Manchester United", "MF", 30, 18))
        addnewplayer(FootballPlayer(10,"Christian Eriksen","Denmark", "Premier League","Manchester United", "MF", 30, 14))
        addnewplayer(FootballPlayer(11,"Bruno Fernandes","Portugal", "Premier League","Manchester United", "MF", 28, 8))
        addnewplayer(FootballPlayer(12,"Fred","Brazil", "Premier League","Manchester United", "MF", 29, 17))
        addnewplayer(FootballPlayer(13,"Scott McTominay","Scotland", "Premier League","Manchester United", "MF", 25, 39))
        addnewplayer(FootballPlayer(14,"Donny van de Beek","Netherlands", "Premier League","Manchester United", "MF", 25, 34))
        addnewplayer(FootballPlayer(15,"Antony","Brazil", "Premier League","Manchester United", "FW", 22, 21))
        addnewplayer(FootballPlayer(16,"Anthony Elanga","Sweden", "Premier League","Manchester United", "FW", 20, 36))
        addnewplayer(FootballPlayer(17,"Alejandro Garnacho","Argentina", "Premier League","Manchester United", "FW", 18, 49))
        addnewplayer(FootballPlayer(18,"Anthony Martial","France", "Premier League","Manchester United", "FW", 26, 9))
        addnewplayer(FootballPlayer(19,"Marcus Rashford","England", "Premier League","Manchester United", "FW", 25, 10))
        addnewplayer(FootballPlayer(20,"Cristiano Ronaldo","Portugal", "Premier League","Manchester United", "FW", 37, 7))
        addnewplayer(FootballPlayer(21,"Jadon Sancho","England", "Premier League","Manchester United", "FW", 22, 25))

        //Real Madrid
        addnewplayer(FootballPlayer(22, "David Alaba", "Austria", "La Liga", "Real Madrid", "DF", 30, 4))
        addnewplayer(FootballPlayer(23, "Courtois Thibaut", "Belgium", "La Liga", "Real Madrid", "GK", 30, 1))
        addnewplayer(FootballPlayer(24, "Daniel Carvajal", "Spain", "La Liga", "Real Madrid", "DF", 30, 20))
        addnewplayer(FootballPlayer(25, "Nacho Fernandez", "Spain", "La Liga", "Real Madrid", "DF", 32, 6))
        addnewplayer(FootballPlayer(26, "Ferland Mendy", "France", "La Liga", "Real Madrid", "DF", 27, 23))
        addnewplayer(FootballPlayer(27, "Eder Militao", "Brazil", "La Liga", "Real Madrid", "DF", 24, 14))
        addnewplayer(FootballPlayer(28, "Antonio Rüdiger", "Germany", "La Liga", "Real Madrid", "DF", 29, 2))
        addnewplayer(FootballPlayer(29, "Eduardo Camavinga", "France", "La Liga", "Real Madrid", "MF", 20, 25))
        addnewplayer(FootballPlayer(30, "Tony Kroos", "Germany", "La Liga", "Real Madrid", "MF", 32, 8))
        addnewplayer(FootballPlayer(31, "Luka Modric", "Croatia", "La Liga", "Real Madrid", "MF", 37, 10))
        addnewplayer(FootballPlayer(32, "Aurelien Tchouameni", "France", "La Liga", "Real Madrid", "MF", 22, 8))
        addnewplayer(FootballPlayer(33, "Marco Asensio", "Spain", "La Liga", "Real Madrid", "FW", 26, 10))
        addnewplayer(FootballPlayer(34, "Karim Benzema", "France", "La Liga", "Real Madrid", "FW", 34, 19))
        addnewplayer(FootballPlayer(35, "Eden Hazard", "Belgium", "La Liga", "Real Madrid", "FW", 31, 10))
        addnewplayer(FootballPlayer(36, "Rodrygo", "Brazil", "La Liga", "Real Madrid", "FW", 21, 21))
        addnewplayer(FootballPlayer(37, "Vinicius Junior", "Brazil", "La Liga", "Real Madrid", "FW", 22, 20))
    }
}