package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.PowerRate

object Cards {
    val weakCards = FighterCards.weakFighters.map {
        it.copy(powerRate = PowerRate.normal)
    } + ArcherCards.weakArchers.map {
        it.copy(powerRate = PowerRate.normal)
    } + MageCards.weakWizards.map {
        it.copy(powerRate = PowerRate.normal)
    } + DefenceCards.weakDefenceCards.map {
        it.copy(powerRate = PowerRate.normal)
    }

    val midCards = FighterCards.midFighters.map {
        it.copy(powerRate = PowerRate.strong)
    } + ArcherCards.midArchers.map {
        it.copy(powerRate = PowerRate.strong)
    } + MageCards.midWizards.map {
        it.copy(powerRate = PowerRate.strong)
    } + DefenceCards.midDefenceCards.map {
        it.copy(powerRate = PowerRate.strong)
    }
    val strongCards = FighterCards.strongFighters.map {
        it.copy(powerRate = PowerRate.epic)
    } + ArcherCards.strongArchers.map {
        it.copy(powerRate = PowerRate.epic)
    } + MageCards.strongWizards.map {
        it.copy(powerRate = PowerRate.epic)
    } + DefenceCards.strongDefenceCards.map {
        it.copy(powerRate = PowerRate.epic)
    }
    val masterCards = FighterCards.masterFighters.map {
        it.copy(powerRate = PowerRate.legendary)
    } + ArcherCards.masterArchers.map {
        it.copy(powerRate = PowerRate.legendary)
    } + MageCards.masterWizards.map {
        it.copy(powerRate = PowerRate.legendary)
    } + DefenceCards.masterDefenceCards.map {
        it.copy(powerRate = PowerRate.legendary)
    }

    val strongSpecialCards = FighterCards.strongSpecialFighters.map {
        it.copy(powerRate = PowerRate.specialEpic)
    } + ArcherCards.specialStrongArchers.map {
        it.copy(powerRate = PowerRate.specialEpic)
    } + MageCards.SpecialStrongWizards.map {
        it.copy(powerRate = PowerRate.specialEpic)
    }

    val masterSpecialCards = FighterCards.masterSpecialFighters.map {
        it.copy(powerRate = PowerRate.specialLegendary)
    } + ArcherCards.specialMasterArchers.map {
        it.copy(powerRate = PowerRate.specialLegendary)
    } + MageCards.SpecialMasterWizards.map {
        it.copy(powerRate = PowerRate.specialLegendary)
    }

    val abilityCards = AbilityCards.abilityCards.map {
        it.copy(powerRate = PowerRate.epic)
    }
}