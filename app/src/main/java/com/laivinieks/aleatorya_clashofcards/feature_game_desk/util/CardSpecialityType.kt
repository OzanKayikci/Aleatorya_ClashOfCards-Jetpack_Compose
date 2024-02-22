package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util

import androidx.compose.ui.graphics.Color
import com.laivinieks.aleatorya_clashofcards.ui.theme.*

sealed class CardSpecialityType(
    val isSpecial: Boolean,
    val isMaster: Boolean? = null,
    val isAttackSpecial: Boolean? = null,
    val color: Color
) {
    object NotSpecial : CardSpecialityType(
        false,
        color = normalCardColor
    )

    object StrongAttackSpecial : CardSpecialityType(
        isSpecial = true,
        isMaster = false,
        isAttackSpecial = true,
        color = strongAttackSpecialColor
    )

    object StrongDefenceSpecial : CardSpecialityType(
        isSpecial = true,
        isMaster = false,
        isAttackSpecial = false,
        color = strongDefenceSpecialColor
    )

    object MasterAttackSpecial : CardSpecialityType(
        isSpecial = true,
        isMaster = true,
        isAttackSpecial = true,
        color = masterAttackSpecialColor
    )

    object MasterDefenceSpecial : CardSpecialityType(
        isSpecial = true,
        isMaster = true,
        isAttackSpecial = false,
        color = masterDeffenceSpecialColor
    )

}