package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.cards

object Cards {
    val weakCards = FighterCards.weakFighters + ArcherCards.weakArchers + MageCards.weakWizards + DefenceCards.weakDefenceCards
    val midCards = FighterCards.midFighters + ArcherCards.midArchers + MageCards.midWizards + DefenceCards.midDefenceCards
    val strongCards = FighterCards.strongFighters + ArcherCards.strongArchers + MageCards.strongWizards + DefenceCards.strongDefenceCards
    val masterCards = FighterCards.masterFighters + ArcherCards.masterArchers + MageCards.masterWizards + DefenceCards.masterDefenceCards
    val abilityCards = AbilityCards.abilityCards
}