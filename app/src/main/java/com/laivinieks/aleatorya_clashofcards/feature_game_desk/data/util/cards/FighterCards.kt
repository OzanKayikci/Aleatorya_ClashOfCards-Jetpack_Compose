package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards

import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType

object FighterCards {
    //close range

    private val mountedWarrior = Card(
        id = 100,
        imageUrl = R.drawable.mounted_warrior_spear,
        power = 10,
        defence = 30,
        type = CardType.CloseRange,
        title = "Mount Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val lionWarrior = Card(
        id = 101,
        imageUrl = R.drawable.lion_warrior,
        power = 10,
        defence = 30,
        type = CardType.CloseRange,
        title = "Flame Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val spearman = Card(
        id = 102,
        imageUrl = R.drawable.spearman,
        power = 20,
        defence = 20,
        type = CardType.CloseRange,
        title = "Spearman",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val twoSwordWarrior = Card(
        id = 103,
        imageUrl = R.drawable.two_sword_warrior,
        power = 20,
        defence = 20,
        type = CardType.CloseRange,
        title = "Two Sword Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val natureWarrior = Card(
        id = 104,
        imageUrl = R.drawable.nature_warrior,
        power = 20,
        defence = 40,
        type = CardType.CloseRange,
        title = "Nature Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val shadowFighter = Card(
        id = 105,
        imageUrl = R.drawable.shadow_fighter,
        power = 20,
        defence = 40,
        type = CardType.CloseRange,
        title = "Shadow Fighter",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val vikingFighter = Card(
        id = 106,
        imageUrl = R.drawable.viking_fighter,
        power = 30,
        defence = 30,
        type = CardType.CloseRange,
        title = "Viking",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val swordMaster = Card(
        id = 107,
        imageUrl = R.drawable.sword_master,
        power = 30,
        defence = 30,
        type = CardType.CloseRange,
        title = "Sword Master",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val darkKnight = Card(
        id = 108,
        imageUrl = R.drawable.dark_knight,
        power = 30,
        defence = 50,
        type = CardType.CloseRange,
        title = "Dark Knight",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val berserk = Card(
        id = 109,
        imageUrl = R.drawable.berserker_fighting,
        power = 40,
        defence = 40,
        type = CardType.CloseRange,
        title = "Berserk",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val flameWarrior = Card(
        id = 110,
        imageUrl = R.drawable.flame_warrior,
        power = 40,
        defence = 40,
        type = CardType.CloseRange,
        title = "Flame Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val giant = Card(
        id = 111,
        imageUrl = R.drawable.giant,
        power = 50,
        defence = 60,
        type = CardType.CloseRange,
        title = " Giant",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val electricWarrior = Card(
        id = 112,
        imageUrl = R.drawable.electric_warrior,
        power = 60,
        defence = 50,
        type = CardType.CloseRange,
        title = "Electric Warrior",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val flameWarriorSpecial = Card(
        id = 113,
        imageUrl = R.drawable.flame_warrior,
        power = 70,
        defence = 40,
        type = CardType.CloseRange,
        title = "Special Flame Warrior",
        desc = "",
        isSpecial = CardSpecialityType.StrongAttackSpecial
    )
    private val darkKnightSpecial = Card(
        id = 114,
        imageUrl = R.drawable.dark_knight,
        power = 60,
        defence = 50,
        type = CardType.CloseRange,
        title = "Special Dark Knight",
        desc = "",
        isSpecial = CardSpecialityType.StrongDefenceSpecial
    )
    private val electricWarriorSpecial = Card(
        id = 115,
        imageUrl = R.drawable.electric_warrior,
        power = 80,
        defence = 50,
        type = CardType.CloseRange,
        title = "Electric Warrior",
        desc = "",
        isSpecial = CardSpecialityType.MasterAttackSpecial
    )
    private val giantSpecial = Card(
        id = 116,
        imageUrl = R.drawable.giant,
        power = 70,
        defence = 60,
        type = CardType.CloseRange,
        title = "Special Giant",
        desc = "",
        isSpecial = CardSpecialityType.MasterDefenceSpecial
    )
    val weakFighters = listOf(
        mountedWarrior,
        lionWarrior,
        spearman,
        twoSwordWarrior,
        )
    val midFighters = listOf(
        natureWarrior,
        shadowFighter,
        vikingFighter,
        swordMaster,
        )
    val strongFighters = listOf(
        berserk,
        darkKnight,
        flameWarrior,
        )
    val masterFighters = listOf(
        electricWarrior,
        giant,
        )
    val strongSpecialFighters = listOf(darkKnightSpecial, flameWarriorSpecial)
    val masterSpecialFighters = listOf(electricWarriorSpecial, giantSpecial)
}