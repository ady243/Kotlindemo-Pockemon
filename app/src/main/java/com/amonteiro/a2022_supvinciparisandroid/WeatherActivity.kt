package com.amonteiro.a2022_supvinciparisandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a2022_supvinciparisandroid.databinding.ActivityWeatherBinding
import com.amonteiro.a2022_supvinciparisandroid.model.WeatherViewModel
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {


    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    //Data
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Observe les changements sur une donnée
        model.data.observe(this) {
            if (it != null){
                binding.pokemonName.text = it.name.uppercase()
                Picasso.get().load(it.sprites.front_default).into(binding.pokemonPicture)
            }

        }

        model.errorMessage.observe(this) {
            binding.tvError.isVisible = it != null
            binding.tvError.text = it ?: ""
        }

        model.threadRunning.observe(this) {
            binding.progressBar.isVisible = it
        }


        //Callback du clic du bouton version lambda
        binding.btLoad.setOnClickListener {
            //Recherche des données
            model.loadData()
        }
    }
}
