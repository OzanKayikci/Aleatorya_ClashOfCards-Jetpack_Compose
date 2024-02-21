package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util

import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card

object Characters {
    //close range

    private val mountedWarrior = Card(
        id = 0,
        imageUrl = R.drawable.mounted_warrior_spear,
        power = 10,
        defence = 30,
        type = CardType.CloseRange,
        title = "Mount Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val lionWarrior = Card(
        id = 1,
        imageUrl = R.drawable.lion_warrior,
        power = 10,
        defence = 30,
        type = CardType.CloseRange,
        title = "Flame Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val spearman = Card(
        id = 2,
        imageUrl = R.drawable.spearman,
        power = 20,
        defence = 20,
        type = CardType.CloseRange,
        title = "Spearman",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val twoSwordWarrior = Card(
        id = 3,
        imageUrl = R.drawable.two_sword_warrior,
        power = 20,
        defence = 20,
        type = CardType.CloseRange,
        title = "Two Sword Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val natureWarrior = Card(
        id = 4,
        imageUrl = R.drawable.nature_warrior,
        power = 20,
        defence = 40,
        type = CardType.CloseRange,
        title = "Nature Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val shadowFighter = Card(
        id = 5,
        imageUrl = R.drawable.shadow_fighter,
        power = 30,
        defence = 30,
        type = CardType.CloseRange,
        title = "Shadow Fighter",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val swordMaster = Card(
        id = 6,
        imageUrl = R.drawable.sword_master,
        power = 30,
        defence = 30,
        type = CardType.CloseRange,
        title = "Sword Master",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val giant = Card(
        id = 7,
        imageUrl = R.drawable.giant,
        power = 30,
        defence = 50,
        type = CardType.CloseRange,
        title = "Giant",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val darkKnight = Card(
        id = 8,
        imageUrl = R.drawable.dark_knight,
        power = 30,
        defence = 50,
        type = CardType.CloseRange,
        title = "Dark Knight",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val flameWarrior = Card(
        id = 9,
        imageUrl = R.drawable.flame_warrior,
        power = 40,
        defence = 40,
        type = CardType.CloseRange,
        title = "Flame Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    //special
    private val flameWarriorSpecial = Card(
        id = 10,
        imageUrl = R.drawable.flame_warrior,
        power = 60,
        defence = 40,
        type = CardType.CloseRange,
        title = "Special Flame Warrior",
        desc = "",
        isSpecial = CardSpecialityType.AttackSpecial
    )
    private val darkKnightSpecial = Card(
        id = 11,
        imageUrl = R.drawable.dark_knight,
        power = 50,
        defence = 50,
        type = CardType.CloseRange,
        title = "Special Dark Knight",
        desc = "",
        isSpecial = CardSpecialityType.AttackSpecial
    )
    private val giantSpecial = Card(
        id = 13,
        imageUrl = R.drawable.giant,
        power = 40,
        defence = 60,
        type = CardType.CloseRange,
        title = "Special Giant",
        desc = "",
        isSpecial = CardSpecialityType.DefenceSpecial
    )

    val fighters = listOf(
        mountedWarrior,
        lionWarrior,
        spearman,
        twoSwordWarrior,
        natureWarrior,
        shadowFighter,
        swordMaster,
        giant,
        darkKnight,
        flameWarrior
    )

    val specialFighters = listOf(darkKnightSpecial, flameWarriorSpecial, giantSpecial)
}