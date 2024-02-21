package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util

import androidx.compose.ui.graphics.Color

sealed class CardSpecialityType(val isSpecial: Boolean, val isAttackSpecial: Boolean? = null, val color: Color) {
     object NotSpecial : CardSpecialityType(false, color = Color.White)
     object AttackSpecial : CardSpecialityType(false, true, color = Color.Red)
     object DefenceSpecial : CardSpecialityType(false, false, color = Color.Blue)


}