package com.amonteiro.a2022_supvinciparisandroid.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {
    val data: MutableLiveData<PokemonBean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    val threadRunning: MutableLiveData<Boolean> = MutableLiveData(false)

    fun loadData() {
        //reset des données
        errorMessage.postValue(null)
        data.postValue(null)
        threadRunning.postValue(true)
        thread {
            try {
                //CA MARCHE
                //Charger les données
                data.postValue(RequestUtils.loadWeather("Toulouse"))
            } catch (e: Exception) {
                e.printStackTrace()
                //CA NE MARCHE PAS
                errorMessage.postValue(e.message)
            }
            threadRunning.postValue(false)
        }
    }

}