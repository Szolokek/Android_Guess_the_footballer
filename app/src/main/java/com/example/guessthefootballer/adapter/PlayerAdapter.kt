package com.example.guessthefootballer.adapter

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getResourceId
import androidx.recyclerview.widget.RecyclerView
import com.example.guessthefootballer.R
import com.example.guessthefootballer.data.FootballPlayer
import com.example.guessthefootballer.databinding.FootballPlayerListBinding

class PlayerAdapter(private val listener: FootballPlayerClickListener) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private val guessedPlayers = mutableListOf<FootballPlayer>()
    private lateinit var hiddenPlayer: FootballPlayer
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayerViewHolder(
        FootballPlayerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val footballPlayer = guessedPlayers[position]

        holder.binding.playerName.text = footballPlayer.name
        holder.binding.playerPosition.text = footballPlayer.position
        holder.binding.playerAge.text = footballPlayer.age.toString()
        holder.binding.playerKitnum.text = footballPlayer.kitnum.toString()

        if(holder.binding.playerName.text.equals(hiddenPlayer.name)){
            holder.binding.playerName.setTextColor(Color.GREEN)
        }
        if(footballPlayer.nationality.equals(hiddenPlayer.nationality)){
            holder.binding.playerNationality.setImageResource(getImageNationalityHit(footballPlayer.nationality))
        } else{
            holder.binding.playerNationality.setImageResource(getImageNationality(footballPlayer.nationality))
        }
        if(footballPlayer.league.equals(hiddenPlayer.league)){
            holder.binding.playerLeague.setImageResource(getImageLeagueHit(footballPlayer.league))
        } else{
            holder.binding.playerLeague.setImageResource(getImageLeague(footballPlayer.league))
        }

        if(footballPlayer.club.equals(hiddenPlayer.club)){
            holder.binding.playerClub.setImageResource(getImageClubHit(footballPlayer.club))
        } else{
            holder.binding.playerClub.setImageResource(getImageClub(footballPlayer.club))
        }

        if(holder.binding.playerPosition.text.equals(hiddenPlayer.position)){
            holder.binding.playerPosition.setTextColor(Color.GREEN)
        }
        if(holder.binding.playerAge.text.equals(hiddenPlayer.age.toString())){
            holder.binding.playerAge.setTextColor(Color.GREEN)
        }
        if(holder.binding.playerKitnum.text.equals(hiddenPlayer.kitnum.toString())){
            holder.binding.playerKitnum.setTextColor(Color.GREEN)
        }

    }

    fun initGameStart(footballPlayer: FootballPlayer){
        hiddenPlayer = footballPlayer
    }

    override fun getItemCount(): Int = guessedPlayers.size

    fun update(footballPlayers: List<FootballPlayer>) {
        guessedPlayers.clear()
        guessedPlayers.addAll(footballPlayers)
        notifyDataSetChanged()
    }

    interface FootballPlayerClickListener {
        fun onPlayerGuess(footballPlayer: FootballPlayer)
    }

    inner class PlayerViewHolder(val binding: FootballPlayerListBinding) : RecyclerView.ViewHolder(binding.root)

    fun getImageClub(club :String) :Int{
        return when (club){
            "Manchester United" -> R.drawable.manchester_united_logo
            "Real Madrid" -> R.drawable.real_madrid_logo
            else -> R.drawable.manchester_united_logo_green
        }
    }

    fun getImageClubHit(club: String): Int{
        return when (club){
            "Manchester United" -> R.drawable.manchester_united_logo_green
            "Real Madrid" -> R.drawable.real_madrid_logo_green
            else -> R.drawable.manchester_united_logo
        }
    }

    fun getImageNationality(nat :String) :Int{
        return when (nat){
            "Argentina" -> R.drawable.argentina_flag
            "Brazil" -> R.drawable.brazil_flag
            "Denmark" -> R.drawable.denmark_flag
            "England" -> R.drawable.england_flag
            "France" -> R.drawable.france_flag
            "Netherlands" -> R.drawable.netherlands_flag
            "Portugal" -> R.drawable.portugal_flag
            "Scotland" -> R.drawable.scotland_flag
            "Spain" -> R.drawable.spain_flag
            "Sweden" -> R.drawable.sweden_flag
            "Austria" -> R.drawable.austria_flag
            "Croatia" -> R.drawable.croatia_flag
            "Germany" -> R.drawable.germany_flag
            "Belgium" -> R.drawable.belgium_flag
            else -> R.drawable.manchester_united_logo_green
        }
    }

    fun getImageNationalityHit(nat: String): Int{
        return when (nat){
            "Argentina" -> R.drawable.argentina_flag_green
            "Brazil" -> R.drawable.brazil_flag_green
            "Denmark" -> R.drawable.denmark_flag_green
            "England" -> R.drawable.england_flag_green
            "France" -> R.drawable.france_flag_green
            "Netherlands" -> R.drawable.netherlands_flag_green
            "Portugal" -> R.drawable.portugal_flag_green
            "Scotland" -> R.drawable.scotland_flag_green
            "Spain" -> R.drawable.spain_flag_green
            "Sweden" -> R.drawable.sweden_flag_green
            "Austria" -> R.drawable.austria_flag_green
            "Croatia" -> R.drawable.croatia_flag_green
            "Germany" -> R.drawable.germany_flag_green
            "Belgium" -> R.drawable.belgium_flag_green
            else -> R.drawable.manchester_united_logo_green
        }
    }

    fun getImageLeague(league :String) :Int{
        return when (league){
            "Premier League" -> R.drawable.premier_league_logo
            "La Liga" -> R.drawable.la_liga_logo
            else -> R.drawable.manchester_united_logo_green
        }
    }
    fun getImageLeagueHit(league :String) :Int{
        return when (league){
            "Premier League" -> R.drawable.premier_league_logo_green
            "La Liga" -> R.drawable.la_liga_logo_green
            else -> R.drawable.manchester_united_logo_green
        }
    }
}