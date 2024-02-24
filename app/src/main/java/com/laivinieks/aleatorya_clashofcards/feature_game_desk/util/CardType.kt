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

sealed class CardType(var cardName: String, var icon: Int, description: String, var abilityType: AbilityType? = null) {

    object CloseRange : CardType(cardName = "Close Range", icon = R.drawable.two_sword_icon, description = "")
    object Mage : CardType(cardName = "Mage", icon = R.drawable.wizard_icon, description = "")
    object Archer : CardType(cardName = "Archer", icon = R.drawable.archer_icon, description = "")
    object Shield : CardType(cardName = "Shield", icon = R.drawable.defence_shield_icon, description = "")
    object SpellShield : CardType(cardName = "SpellShield", icon = R.drawable.defence_magic_icon, description = "")
    object Helmet : CardType(cardName = "Armor", icon = R.drawable.defence_helmet_icon, description = "")
    class Ability(abilityType: AbilityType) :
        CardType(cardName = "Ability", icon = R.drawable.ability_icon, description = "", abilityType = abilityType)

}

enum class AbilityType(var power: Int) {
    BLOCK_DEFENCE(0),
    BLOCK_WARRIOR(0),
    BLOCK_ARCHER(10),
    BLOCK_WIZARD(20)
}