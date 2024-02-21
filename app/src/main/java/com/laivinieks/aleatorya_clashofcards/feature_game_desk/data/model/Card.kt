package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.CardType

data class Card(
    val id: Int? = null,
    val imageUrl: Int,
    val power: Int,
    val defence: Int,
    val type: CardType,
    val title: String,
    val desc: String,
    val isSpecial:CardSpecialityType
)
