package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model

data class Stats(
    val attack: Int = 0,
    val defence: Int = 0
)

data class StatsForBattle(
    val fighterAttack: Int = 0,
    val archerAttack: Int = 0,
    val mageAttack: Int = 0,
    val fighterDefence: Int = 0,
    val mageDefence: Int = 0,
    val archerDefence: Int = 0,
)