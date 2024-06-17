package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.rearrange_part

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.AbilityCardByType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.DefenceCardTypes
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.RearrangeCards

class RemoveCardFromRearrangeCards {

    operator fun invoke(cardToRemove: Card, rearrangeCards: RearrangeCards): RearrangeCards {
        val updatedFighterCards = rearrangeCards.fighterCards.minusElement(cardToRemove)
        val updatedArcherCards = rearrangeCards.archerCards.minusElement(cardToRemove)
        val updatedMageCards = rearrangeCards.mageCards.minusElement(cardToRemove)

        val updatedDefenceCards = rearrangeCards.defenceCards?.let { defenceCards ->
            val updatedFighterDefence = defenceCards.fighterDefence.minusElement(cardToRemove)
            val updatedArcherDefence = defenceCards.archerDefence.minusElement(cardToRemove)
            val updatedMageDefence = defenceCards.mageDefence.minusElement(cardToRemove)
            DefenceCardTypes(updatedFighterDefence, updatedArcherDefence, updatedMageDefence)
        }

        val updatedAbilityCards = rearrangeCards.abilityCards?.let { abilityCards ->
            val updatedCloseRange = abilityCards.closeRange.minusElement(cardToRemove)
            val updatedArcher = abilityCards.archer.minusElement(cardToRemove)
            val updatedMage = abilityCards.mage.minusElement(cardToRemove)
            val updatedDefence = abilityCards.defence.minusElement(cardToRemove)
            AbilityCardByType(updatedCloseRange, updatedArcher, updatedMage, updatedDefence)
        }

        return RearrangeCards(
            updatedFighterCards,
            updatedArcherCards,
            updatedMageCards,
            updatedDefenceCards,
            updatedAbilityCards
        )
    }
}