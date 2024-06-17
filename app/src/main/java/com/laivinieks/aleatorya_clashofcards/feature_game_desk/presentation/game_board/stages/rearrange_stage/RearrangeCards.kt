package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card

data class RearrangeCards(
    val fighterCards: List<Card> = emptyList(),
    val archerCards: List<Card> = emptyList(),
    val mageCards: List<Card> = emptyList(),
    val defenceCards: DefenceCardTypes? = null,
    val abilityCards: AbilityCardByType? = null,
)

data class DefenceCardTypes(
    val fighterDefence:List<Card> = emptyList(),
    val archerDefence:List<Card> = emptyList(),
    val mageDefence:List<Card> = emptyList()
)
data class AbilityCardByType(
    val closeRange: List<Card> = emptyList(),
    val archer: List<Card> = emptyList(),
    val mage: List<Card> = emptyList(),
    val defence: List<Card> = emptyList()
)
