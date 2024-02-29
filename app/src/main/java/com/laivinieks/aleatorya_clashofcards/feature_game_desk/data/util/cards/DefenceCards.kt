package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards

import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType

object DefenceCards {

    private val woodenHelmet = Card(
        id = 400,
        imageUrl = R.drawable.wooden_helmet,
        power = 0,
        defence = 40,
        type = CardType.Helmet,
        title = "Wooden Helmet",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val ironHelmet = Card(
        id = 401,
        imageUrl = R.drawable.iron_helmet,
        power = 0,
        defence = 50,
        type = CardType.Helmet,
        title = "Iron Helmet",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val goldHelmet = Card(
        id = 402,
        imageUrl = R.drawable.gold_helmet,
        power = 0,
        defence = 60,
        type = CardType.Helmet,
        title = "Gold Helmet",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val diamondHelmet = Card(
        id = 403,
        imageUrl = R.drawable.diaomond_helmet,
        power = 0,
        defence = 80,
        type = CardType.Helmet,
        title = "Diamond Helmet",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val woodenShield = Card(
        id = 404,
        imageUrl = R.drawable.wooden_shield,
        power = 0,
        defence = 30,
        type = CardType.Shield,
        title = "Wooden Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val ironShield = Card(
        id = 405,
        imageUrl = R.drawable.iron_shield,
        power = 0,
        defence = 40,
        type = CardType.Shield,
        title = "Iron Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val goldShield = Card(
        id = 406,
        imageUrl = R.drawable.gold_shield,
        power = 0,
        defence = 50,
        type = CardType.Shield,
        title = "Gold Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val diamondShield = Card(
        id = 407,
        imageUrl = R.drawable.diamond_shield,
        power = 0,
        defence = 70,
        type = CardType.Shield,
        title = "Diamond Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val weakSpellShield = Card(
        id = 408,
        imageUrl = R.drawable.weak_spell_shield,
        power = 0,
        defence = 50,
        type = CardType.SpellShield,
        title = "Weak Spell Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val normalSpellShield = Card(
        id = 409,
        imageUrl = R.drawable.normal_spell_shield,
        power = 0,
        defence = 60,
        type = CardType.SpellShield,
        title = "Normal Spell Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val strongSpellShield = Card(
        id = 410,
        imageUrl = R.drawable.strong_spell_shield,
        power = 0,
        defence = 70,
        type = CardType.SpellShield,
        title = "Strong Spell Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val epicSpellShield = Card(
        id = 411,
        imageUrl = R.drawable.epic_spell_shield,
        power = 0,
        defence = 80,
        type = CardType.SpellShield,
        title = "Epic Spell Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val legendarySpellShield = Card(
        id = 412,
        imageUrl = R.drawable.legendary_spell_shield,
        power = 0,
        defence = 100,
        type = CardType.SpellShield,
        title = "Legendary Spell Shield",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )



    val weakDefenceCards = listOf(
        woodenHelmet,
        woodenShield,
        weakSpellShield,
        normalSpellShield,
    )
    val midDefenceCards = listOf(
        ironHelmet,
        ironShield,
        strongSpellShield,
    )
    val strongDefenceCards = listOf(
        goldHelmet,
        goldShield,
        epicSpellShield,
    )
    val masterDefenceCards = listOf(
        diamondHelmet,
        diamondShield,
        legendarySpellShield
    )
}