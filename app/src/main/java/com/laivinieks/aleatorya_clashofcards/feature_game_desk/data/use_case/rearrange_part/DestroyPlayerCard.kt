package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.rearrange_part

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card

class DestroyPlayerCard {
    operator fun invoke(oppositeCards: List<Card>?): Card? {

        if (oppositeCards == null) {
            return null
        }
        var bestRateCard: Card? = null
        var bestRate = 0

        for (card in oppositeCards) {
            val rate = (card.power + card.defence)
            if (rate > bestRate) {
                bestRate = rate
                bestRateCard = card
            }
        }

        return bestRateCard
    }


}