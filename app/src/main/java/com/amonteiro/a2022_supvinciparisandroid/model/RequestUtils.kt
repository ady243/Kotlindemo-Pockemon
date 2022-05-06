package com.amonteiro.a2022_supvinciparisandroid.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

private val client = OkHttpClient()
private val gson = Gson()

const val URL_API_WEATHER =
    "https://pokeapi.co/api/v2/pokemon/ditto"

class RequestUtils {

    companion object {
        fun loadWeather(city: String): PokemonBean {
            val json: String = sendGet(URL_API_WEATHER.format(city))

            val weather = gson.fromJson(json, PokemonBean::class.java)

            return weather
        }

        fun sendGet(url: String): String {
            println("url : $url")
            //Création de la requete
            val request = Request.Builder().url(url).build()
            //Execution de la requête
            return client.newCall(request).execute().use {
                //Analyse du code retour
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}")
                }
                //Résultat de la requete
                it.body?.string() ?: ""
            }
        }
    }
}