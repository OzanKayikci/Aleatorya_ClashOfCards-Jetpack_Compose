package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util

enum class LuckyRate(val maxRate: Int, val weakCard: Int, val midCard: Int, val strongCard: Int) {
 RONE(maxRate = 10, weakCard = 6, midCard = 0, strongCard = 0),
 RTWO(maxRate = 20, weakCard = 4, midCard = 2, strongCard = 0),
 RTHREE(maxRate = 40, weakCard = 3, midCard = 2, strongCard = 1),
 RFOUR(maxRate = 60, weakCard = 2, midCard = 2, strongCard = 2),
 RFIVE(maxRate = 70, weakCard = 1, midCard = 2, strongCard = 3),
 RSIX(maxRate = 70, weakCard = 0, midCard = 2, strongCard = 4)
}
