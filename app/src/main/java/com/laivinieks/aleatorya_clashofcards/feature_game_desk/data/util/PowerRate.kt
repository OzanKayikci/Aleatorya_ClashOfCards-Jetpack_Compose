package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util

import androidx.compose.ui.graphics.Color
import com.laivinieks.aleatorya_clashofcards.ui.theme.*

enum class PowerRate(val title:String,val color: Color) {
    normal(title="Normal",color = normalCardColor),
    strong(title="Strong",color = strongDefenceSpecialColor),
    epic(title="Epic",color = masterAttackSpecialColor),
    legendary(title="Legendary",color = masterDefenceSpecialColor),
    specialEpic(title="Special Epic",color = masterAttackSpecialColor),
    specialLegendary(title="Special Legendary",color = masterDefenceSpecialColor),
}