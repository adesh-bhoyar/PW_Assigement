package com.example.pw_assigement.model

import com.google.gson.annotations.SerializedName


data class PokemonListResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Pokemon> = arrayListOf()

)

data class Pokemon(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)