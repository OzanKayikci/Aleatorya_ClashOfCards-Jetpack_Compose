package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.LuckyRate

class GetLuckRate {
    operator fun invoke(totalNumber: Int): LuckyRate {
      return  when{
            totalNumber< 10 -> {
              LuckyRate.RONE
            }
          totalNumber < 30 ->{
              LuckyRate.RTWO
          }
          totalNumber < 50 -> {
              LuckyRate.RTHREE
          }
          totalNumber < 70 -> {
              LuckyRate.RFOUR
          }
          totalNumber < 90 -> {
              LuckyRate.RFIVE
          }
          else -> {
              LuckyRate.RSIX
          }
        }
    }
}