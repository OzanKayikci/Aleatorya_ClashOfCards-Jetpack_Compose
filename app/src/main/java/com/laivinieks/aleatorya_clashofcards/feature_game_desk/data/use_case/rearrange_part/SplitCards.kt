package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.rearrange_part

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.AbilityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.AbilityCardByType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.DefenceCardTypes
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.RearrangeCards

class SplitCards {
    operator fun invoke (cards: List<Card>):RearrangeCards {

        val abilityCards = cards.filter { it.type::class == CardType.Ability::class }
        val nonAbilityCards = cards.filter { it.type::class != CardType.Ability::class }

        val shields = nonAbilityCards.filter { it.type::class == CardType.Shield::class }
        val helmets = nonAbilityCards.filter { it.type::class == CardType.Helmet::class }
        val spellShields = nonAbilityCards.filter { it.type::class == CardType.SpellShield::class }

        val fighters = nonAbilityCards.filter { it.type::class == CardType.CloseRange::class }
        val archers = nonAbilityCards.filter { it.type::class == CardType.Archer::class }
        val mages = nonAbilityCards.filter { it.type::class == CardType.Mage::class }

        val abilityForDefence = abilityCards.filter { it.type.abilityType!! == AbilityType.BLOCK_DEFENCE }
        val abilityForFighter = abilityCards.filter { it.type.abilityType!! == AbilityType.BLOCK_WARRIOR }
        val abilityForArcher = abilityCards.filter { it.type.abilityType!! == AbilityType.BLOCK_ARCHER }
        val abilityForMage = abilityCards.filter { it.type.abilityType!! == AbilityType.BLOCK_WIZARD }

        val splittedCards = RearrangeCards(
            fighterCards = fighters,
            archerCards = archers,
            mageCards = mages,
            defenceCards = DefenceCardTypes(
                fighterDefence = shields,
                archerDefence = helmets,
                mageDefence = spellShields
            ),
            abilityCards = AbilityCardByType(
                closeRange = abilityForFighter,
                archer = abilityForArcher,
                mage = abilityForMage,
                defence = abilityForDefence
            )
        )

        return  splittedCards


    }
}