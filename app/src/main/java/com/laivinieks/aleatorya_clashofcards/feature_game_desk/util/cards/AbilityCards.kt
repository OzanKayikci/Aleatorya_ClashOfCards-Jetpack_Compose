package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.cards

import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.AbilityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.CardType

object AbilityCards {
    private val ivyWallAbility = Card(
        id = 500,
        imageUrl = R.drawable.ivy_ability,
        power = 0,
        defence = 0,
        type = CardType.Ability(AbilityType.BLOCK_DEFENCE),
        title = "Ivy Wall",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val icyGroundAbility = Card(
        id = 501,
        imageUrl = R.drawable.ice_ability,
        power = 0,
        defence = 0,
        type = CardType.Ability(AbilityType.BLOCK_WARRIOR),
        title = "Icy Ground",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val fogAbility = Card(
        id = 502,
        imageUrl = R.drawable.fog_ability,
        power = 0,
        defence = 0,
        type = CardType.Ability(AbilityType.BLOCK_WIZARD),
        title = "Fog",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val rainAbility = Card(
        id = 503,
        imageUrl = R.drawable.rain_ability,
        power = 0,
        defence = 0,
        type = CardType.Ability(AbilityType.BLOCK_WIZARD),
        title = "Rain",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    val abilityCards = listOf(ivyWallAbility, rainAbility, fogAbility, icyGroundAbility)
}