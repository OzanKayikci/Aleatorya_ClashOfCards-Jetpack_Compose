package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.PowerRate
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.Cards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.AbilityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Constants

class AILogic() {
    operator fun invoke(playerDeck: List<Card>): Card {
        /** set ai deck to pick a card*/
        val randomDeck = getRandomDeck(getLuckNumber())
        val (legendaryCards, epicCards) = getLegendaryAndEpicCards(randomDeck)

        /**grouping ai cards*/
        val abilityCards = getAbilityCards(randomDeck)

        val legendaryDefence = getDefenceStrongCards(legendaryCards)
        val epicDefence = getDefenceStrongCards(epicCards)

        val legendaryAttack = legendaryCards.subtract(legendaryDefence.toSet()).toList()
        val epicAttack = epicCards.subtract(epicDefence.toSet()).toList()

        var nonStrongCards = getNonStrongCards(randomDeck)


        //this cards after trying chance for the special cards
        val newLegendarySpecialCards = tryForSpecial(legendaryAttack)
        val newEpicSpecialCards = tryForSpecial(epicAttack)


        /** compare player cards for the best choice */
        val strongestCardOfPlayer = playerDeck.let { deck ->
            val playerDeckWithoutAbilityCards = excludeAbilityCardsFromPlayerDec(deck)
            legendaryOrEpicCardsOfPlayer(playerDeckWithoutAbilityCards)
        }


        /**get strongest card of ai*/
        val specialLegendaryStrongestCard = getPowerfulCardAfterSpecialTry(newLegendarySpecialCards)
        val specialEpicStrongestCard = getPowerfulCardAfterSpecialTry(newEpicSpecialCards)
        val strongestCard = specialLegendaryStrongestCard?.let {
            if (it.powerRate!! != PowerRate.strong) it else null
        } ?: specialEpicStrongestCard?.let {
            if (it.powerRate!! != PowerRate.strong) it else null
        }

        if (legendaryAttack.isNotEmpty() || epicAttack.isNotEmpty()){
            /** add new nonStrong cards to deck*/
            //that mean there isn't any strong card after trying special card luck
            val newMidCards = if ( strongestCard == null) {
                listOf(specialLegendaryStrongestCard, specialEpicStrongestCard)
            } else null

            nonStrongCards.plus(newMidCards)
        }


        /** get cards for play*/
        val abilityCardToPlay = chooseAnAbilityCardToPlay(strongestCardOfPlayer, abilityCards)
        val legendaryDefenceToPlay = chooseStrongDefenceCards(playerDeck,legendaryDefence)
        val epicDefenceToPlay = chooseStrongDefenceCards(playerDeck,epicDefence)
        val normalCardToPlay = chooseNonStrongCard(nonStrongCards)


        return strongestCard ?: abilityCardToPlay ?: legendaryDefenceToPlay ?: epicDefenceToPlay?: normalCardToPlay
    }

    /**preparing of ai deck*/
    private fun getLuckNumber(): Int {
        return Constants.rollerItems.random() + Constants.rollerItems.random()
    }

    private fun getRandomDeck(luckNumber: Int): List<Card> {
        val luckRate = GetLuckRate().invoke(luckNumber)
        return GetRandomDeck().invoke(luckRate)
    }

    private fun tryForSpecial(cards: List<Card>): List<Card> {
        val newCards = cards.map { card ->
            var newCard: Card = card

            if (getRandomChoose()) {
                getNewCardForSpecial(getLuckNumber() > 50, card.powerRate!!)?.let {
                    newCard = it
                }
            }
            newCard
        }
        return newCards
    }


    private fun getNewCardForSpecial(isLucky: Boolean, powerRate: PowerRate): Card? {

        if (!isLucky) {
            return Cards.midCards.random()
        }

        return when (powerRate) {
            PowerRate.legendary -> {
                Cards.masterSpecialCards.random()
            }

            PowerRate.epic -> {
                Cards.strongSpecialCards.random()
            }

            else -> {
                null
            }
        }
    }

    /**grouping of ai deck*/
    private fun getLegendaryAndEpicCards(deck: List<Card>): Pair<List<Card>, List<Card>> {

        val legendaryCards = deck.filter { it.powerRate!! == PowerRate.legendary }
        val epicCards = deck.filter { it.powerRate!! == PowerRate.epic }
        return Pair(legendaryCards, epicCards)
    }

    private fun getDefenceStrongCards(deck: List<Card>): List<Card> {
        return deck.filter { it.defence > it.power }
    }

    private fun getNonStrongCards(deck: List<Card>): List<Card> {
        return deck.asSequence().filter {
            it.powerRate!= PowerRate.legendary
        }.filter { it.powerRate != PowerRate.epic }.toList()
    }

    private fun getAbilityCards(deck: List<Card>): List<Card> {
        return deck.filter { card -> card.type::class == CardType.Ability::class }
    }

    private fun getPowerfulCardAfterSpecialTry(cards: List<Card>): Card? {
        if (cards.isEmpty()) return null
        return cards.filter { card -> card.powerRate == PowerRate.specialLegendary}.shuffled().firstOrNull()
            ?: cards.filter {card -> card.powerRate == PowerRate.specialEpic}.shuffled().firstOrNull()
            ?: cards.filter {card -> card.powerRate == PowerRate.legendary}.shuffled().firstOrNull()
            ?: cards.filter {card -> card.powerRate == PowerRate.epic}.shuffled().firstOrNull()
            ?:cards.filter {card -> card.powerRate == PowerRate.strong}.shuffled().firstOrNull()

    }

    /**find best card to play*/
    private fun chooseAnAbilityCardToPlay(playerCard: Card?, abilityCards: List<Card>): Card? {
        if (playerCard == null) return null

        return when (playerCard.type::class) {
            CardType.Mage::class -> {
                abilityCards.filter { card -> card.type.abilityType!!::class == AbilityType.BLOCK_WIZARD::class }
            }

            CardType.Archer::class -> {
                abilityCards.filter { card -> card.type.abilityType!!::class == AbilityType.BLOCK_ARCHER::class }
            }

            CardType.CloseRange::class -> {
                abilityCards.filter { card -> card.type.abilityType!!::class == AbilityType.BLOCK_WARRIOR::class }
            }

            CardType.Shield::class -> {
                abilityCards.filter { card -> card.type.abilityType!!::class == AbilityType.BLOCK_DEFENCE::class }
            }

            CardType.SpellShield::class -> {
                abilityCards.filter { card -> card.type.abilityType!!::class == AbilityType.BLOCK_DEFENCE::class }
            }

            else -> {
                null
            }
        }?.firstOrNull()
    }

    private fun chooseStrongDefenceCards(playerDeck: List<Card>, cards: List<Card>): Card? {
        if (playerDeck.isEmpty()) return null

        val fighterAttack = playerDeck.fold(0) { total, playerCard ->
            if (playerCard.type::class == CardType.CloseRange::class) {
                total + playerCard.power
            } else {
                total
            }
        }
        val archerAttack = playerDeck.fold(0) { total, playerCard ->
            if (playerCard.type::class == CardType.Archer::class) {
                total + playerCard.power
            } else {
                total
            }
        }
        val mageAttack = playerDeck.fold(0) { total, playerCard ->
            if (playerCard.type::class == CardType.Mage::class) {
                total + playerCard.power
            } else {
                total
            }
        }

        val attackValues = listOf(fighterAttack, archerAttack, mageAttack)
        val maxAttackValue = attackValues.maxOrNull()

        return when (maxAttackValue) {
            fighterAttack -> cards.filter { it.type::class == CardType.Shield::class }
            archerAttack -> cards.filter { it.type::class == CardType.Helmet::class }
            mageAttack -> cards.filter { it.type::class == CardType.SpellShield::class }
            else -> null
        }?.firstOrNull()
    }

    private fun chooseNonStrongCard(cards: List<Card>): Card {
        //choose card in order as -> strong attack -> strong defence -> normal attack -> normal defence
        return cards.filter { it.powerRate!! == PowerRate.strong && it.power > it.defence }.shuffled().firstOrNull()
            ?: cards.filter { it.powerRate!! == PowerRate.strong&& it.defence > it.power }.shuffled().firstOrNull()
            ?: cards.filter { it.powerRate!! == PowerRate.normal&& it.power > it.defence }.shuffled().firstOrNull()
            ?: cards.filter { it.powerRate!! == PowerRate.normal && it.defence > it.power }.shuffled().first()
    }
    private fun legendaryOrEpicCardsOfPlayer(playerDeck: List<Card>): Card? {

        return playerDeck.filter { card ->
            PowerRate.specialLegendary == card.powerRate!!
        }.shuffled().firstOrNull()
            ?: playerDeck.filter { card ->
                PowerRate.specialEpic == card.powerRate!!
            }.shuffled().firstOrNull()
            ?: playerDeck.filter { card ->
                PowerRate.legendary == card.powerRate!!
            }.shuffled().firstOrNull()
            ?: playerDeck.filter { card ->
                PowerRate.epic == card.powerRate!!
            }.shuffled().firstOrNull()
    }
    private fun getRandomChoose(): Boolean = listOf(false, true).random()


    /** operations for the player deck*/
    private fun excludeAbilityCardsFromPlayerDec(playerDeck: List<Card>): List<Card> {
        return playerDeck.filter { it.type::class != CardType.Ability::class }
    }


}