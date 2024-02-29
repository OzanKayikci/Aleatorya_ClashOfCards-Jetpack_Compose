package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.PowerRate
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType

data class Card(
    val id: Int? = null,
    val imageUrl: Int,
    val power: Int,
    val defence: Int,
    val type: CardType,
    val title: String,
    val desc: String,
    val isSpecial: CardSpecialityType,
    val powerRate:PowerRate? = null,
    val isCardClose:Boolean = false,
)
