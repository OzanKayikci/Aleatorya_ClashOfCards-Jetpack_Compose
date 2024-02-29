package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards

import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType

object ArcherCards {

    private val pirateArcher = Card(
        id = 200,
        imageUrl = R.drawable.pirate_archer,
        power = 20,
        defence = 20,
        type = CardType.Archer,
        title = "Pirate Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val natureWindArcher = Card(
        id = 201,
        imageUrl = R.drawable.nature_wind_archer,
        power = 20,
        defence = 20,
        type = CardType.Archer,
        title = "Wind Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val crossbowDwarf = Card(
        id = 202,
        imageUrl = R.drawable.crossbow_dworf,
        power = 30,
        defence = 10,
        type = CardType.Archer,
        title = "Crossbow Dwarf",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val blackSwan = Card(
        id = 203,
        imageUrl = R.drawable.black_swan_archer,
        power = 30,
        defence = 10,
        type = CardType.Archer,
        title = "Black Swan Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val highTechArcher = Card(
        id = 204,
        imageUrl = R.drawable.high_tech_archer,
        power = 30,
        defence = 30,
        type = CardType.Archer,
        title = "High Tech Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val invisibleArcher = Card(
        id = 205,
        imageUrl = R.drawable.invisible_archer,
        power = 30,
        defence = 30,
        type = CardType.Archer,
        title = "Invisible Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val crystalArcher = Card(
        id = 206,
        imageUrl = R.drawable.crystal_archer,
        power = 40,
        defence = 20,
        type = CardType.Archer,
        title = "Crystal Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )


    private val flameArcher = Card(
        id = 207,
        imageUrl = R.drawable.flame_archer,
        power = 40,
        defence = 20,
        type = CardType.Archer,
        title = "Flame Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val mountainArcher = Card(
        id = 208,
        imageUrl = R.drawable.time_controller_archer,
        power = 40,
        defence = 40,
        type = CardType.Archer,
        title = "Mountain Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val electricArcher = Card(
        id = 209,
        imageUrl = R.drawable.electri_archer,
        power = 50,
        defence = 30,
        type = CardType.Archer,
        title = "Electric Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val MagicalArcher = Card(
        id = 210,
        imageUrl = R.drawable.magical_archer,
        power = 50,
        defence = 30,
        type = CardType.Archer,
        title = "Magical Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )


    private val mysticArcher = Card(
        id = 211,
        imageUrl = R.drawable.mystic_angry_archer,
        power = 60,
        defence = 40,
        type = CardType.Archer,
        title = "Mystic Angry Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )


    private val timeControllerArcher = Card(
        id = 212,
        imageUrl = R.drawable.time_controller_archer,
        power = 70,
        defence = 30,
        type = CardType.Archer,
        title = "Time Controller Archer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )


    //special
    private val electricArcherSpecial = Card(
        id = 213,
        imageUrl = R.drawable.electri_archer,
        power = 80,
        defence = 30,
        type = CardType.Archer,
        title = "Special Electric Archer",
        desc = "",
        isSpecial = CardSpecialityType.StrongAttackSpecial
    )

    private val MagicalArcherSpecial = Card(
        id = 214,
        imageUrl = R.drawable.magical_archer,
        power = 70,
        defence = 40,
        type = CardType.Archer,
        title = "Special Magical Archer",
        desc = "",
        isSpecial = CardSpecialityType.StrongDefenceSpecial
    )


    private val mysticArcherSpecial = Card(
        id = 215,
        imageUrl = R.drawable.mystic_angry_archer,
        power = 80,
        defence = 50,
        type = CardType.Archer,
        title = "Special Mystic Angry Archer",
        desc = "",
        isSpecial = CardSpecialityType.MasterDefenceSpecial
    )


    private val timeControllerArcherSpecial = Card(
        id = 216,
        imageUrl = R.drawable.time_controller_archer,
        power = 90,
        defence = 40,
        type = CardType.Archer,
        title = "Special Time Controller Archer",
        desc = "",
        isSpecial = CardSpecialityType.MasterAttackSpecial
    )

    val weakArchers = listOf(
        pirateArcher,
        blackSwan,
        natureWindArcher,
        crossbowDwarf,
    )
    val midArchers = listOf(
        highTechArcher,
        invisibleArcher,
        crystalArcher,
        flameArcher
    )
    val strongArchers = listOf(
        electricArcher,
        MagicalArcher,
        mountainArcher
    )

    val masterArchers = listOf(
        mysticArcher,
        timeControllerArcher
    )
    val specialStrongArchers = listOf(MagicalArcherSpecial, electricArcherSpecial)
    val specialMasterArchers = listOf(timeControllerArcherSpecial, mysticArcherSpecial)

}