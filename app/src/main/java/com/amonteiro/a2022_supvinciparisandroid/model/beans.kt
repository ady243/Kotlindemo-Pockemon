package com.amonteiro.a2022_supvinciparisandroid.model

import com.google.gson.annotations.SerializedName

data class PokemonBean (
    var name:String,
    var sprites: SpritesBean
        )
data class SpritesBean (
    var front_default: String
        )