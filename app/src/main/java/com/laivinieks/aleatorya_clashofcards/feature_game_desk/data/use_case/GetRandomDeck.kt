package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.LuckyRate
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.Cards

class GetRandomDeck {
    operator fun invoke(luckyRate: LuckyRate): List<Card> {
        val shuffledWeakCards = Cards.weakCards.shuffled()
        val shuffledMidCards = Cards.midCards.shuffled()
        val shuffledStrongCards = Cards.strongCards.shuffled()
        val shuffledMasterCards = Cards.masterCards.shuffled()
        val shuffledAbilityCards = Cards.abilityCards.shuffled()

        val strongCardsList = (
                shuffledStrongCards +
                        shuffledMasterCards +
                        shuffledAbilityCards
                ).shuffled()

        return when (luckyRate) {
            LuckyRate.RONE -> {
                shuffledWeakCards.take(luckyRate.weakCard) +
                        shuffledMidCards.take(luckyRate.midCard) +
                        strongCardsList.take(luckyRate.strongCard)
            }

            LuckyRate.RTWO -> {
                shuffledWeakCards.take(luckyRate.weakCard) +
                        shuffledMidCards.take(luckyRate.midCard) +
                        strongCardsList.take(luckyRate.strongCard)
            }

            LuckyRate.RTHREE -> {
                shuffledWeakCards.take(luckyRate.weakCard) +
                        shuffledMidCards.take(luckyRate.midCard) +
                        strongCardsList.take(luckyRate.strongCard)

            }

            LuckyRate.RFOUR -> {
                shuffledWeakCards.take(luckyRate.weakCard) +
                        shuffledMidCards.take(luckyRate.midCard) +
                        strongCardsList.take(luckyRate.strongCard)
            }

            LuckyRate.RFIVE -> {
                shuffledWeakCards.take(luckyRate.weakCard) +
                        shuffledMidCards.take(luckyRate.midCard) +
                        strongCardsList.take(luckyRate.strongCard)
            }

            LuckyRate.RSIX -> {
                shuffledWeakCards.take(luckyRate.weakCard) +
                        shuffledMidCards.take(luckyRate.midCard) +
                        strongCardsList.take(luckyRate.strongCard)
            }
        }
    }
}