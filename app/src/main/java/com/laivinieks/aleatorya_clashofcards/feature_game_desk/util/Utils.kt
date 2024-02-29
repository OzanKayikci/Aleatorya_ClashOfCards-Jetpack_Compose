package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util

import android.content.Context
import android.content.res.Configuration
import android.util.Log

object Utils {

    fun Int.m(multiplayer: Float): Float = this * multiplayer

    fun sizeConvertorFromDimension(configuration: Configuration): Float {
        val screenHeight = configuration.screenHeightDp
        return when {
            screenHeight <= 685 -> 0.80f
            screenHeight <= 720 -> 0.85f
            screenHeight <= 770 -> 0.90f
            screenHeight <= 820 -> 0.95f
            else -> 1f

        }
    }
}
