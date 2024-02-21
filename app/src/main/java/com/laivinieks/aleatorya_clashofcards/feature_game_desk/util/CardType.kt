package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.DoubleArrow
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.ShieldMoon
import androidx.compose.material.icons.rounded.SpeakerGroup
import androidx.compose.material.icons.rounded.Whatshot
import androidx.compose.ui.graphics.vector.ImageVector
import com.laivinieks.aleatorya_clashofcards.R

enum class CardType(name: String, icon: Int, description: String) {

    CloseRange(name = "Close Range", icon = R.drawable.two_sword_icon, description = ""),
    Mage(name = "Mage", icon = R.drawable.two_sword_icon, description = ""),
    Archer(name = "Archer", icon = R.drawable.two_sword_icon, description = ""),
    Shield(name = "Shield", icon = R.drawable.two_sword_icon, description = ""),
    MageShield(name = "MageShield", icon = R.drawable.two_sword_icon, description = ""),
    Armor(name = "Armor", icon = R.drawable.two_sword_icon, description = ""),
    Special(name = "Special", icon = R.drawable.two_sword_icon, description = ""),

}