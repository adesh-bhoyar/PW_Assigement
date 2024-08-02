package com.example.pw_assigement.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val abilities: List<AbilitySlot>,
    val stats: List<Stats>,
    val types: List<Ability>,
    val weight: Int
)


data class AbilitySlot(
    val ability: Ability, val is_hidden: Boolean, val slot: Int
)

data class Ability(
    val name: String, val url: String
)

data class Stat(
    var name: String? = null, var url: String? = null
)

data class Stats(

    @SerializedName("base_stat") var baseStat: Int? = null,
    @SerializedName("effort") var effort: Int? = null,
    @SerializedName("stat") var stat: Stat? = Stat()

)