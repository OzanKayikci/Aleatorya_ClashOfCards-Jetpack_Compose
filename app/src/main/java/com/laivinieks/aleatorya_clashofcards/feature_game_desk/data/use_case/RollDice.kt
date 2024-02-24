package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.LuckyRate

class RollDice {
    operator fun invoke(luckyRate: Int): LuckyRate {
        return LuckyRate.entries.filter { item ->
            item.maxRate > luckyRate && item.maxRate - luckyRate < 10
        }[0]
    }
}