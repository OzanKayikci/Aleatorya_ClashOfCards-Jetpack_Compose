package com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards

import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType

object MageCards {
    private val natureWizard = Card(
        id = 300,
        imageUrl = R.drawable.nature_wizard,
        power = 30,
        defence = 0,
        type = CardType.Mage,
        title = "Nature Wizard",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val waterMage = Card(
        id = 301,
        imageUrl = R.drawable.water_mage,
        power = 30,
        defence = 0,
        type = CardType.Mage,
        title = "Water Mage",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val windChanterSorcerer = Card(
        id = 302,
        imageUrl = R.drawable.wind_chanter_sorcerer,
        power = 40,
        defence = 0,
        type = CardType.Mage,
        title = "Wind Chanter Sorcerer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val windElfWizard = Card(
        id = 303,
        imageUrl = R.drawable.wind_elf_wizard,
        power = 40,
        defence = 0,
        type = CardType.Mage,
        title = "Wind Elf Wizard",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val electricWizard = Card(
        id = 304,
        imageUrl = R.drawable.electric_wizard,
        power = 50,
        defence = 0,
        type = CardType.Mage,
        title = "Electric Wizard",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val flameMage = Card(
        id = 305,
        imageUrl = R.drawable.flame_mage,
        power = 50,
        defence = 0,
        type = CardType.Mage,
        title = "Flame Mage",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val darkSorcerer = Card(
        id = 306,
        imageUrl = R.drawable.dark_sorcerer,
        power = 60,
        defence = 0,
        type = CardType.Mage,
        title = "Dark Sorcerer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val starWizard = Card(
        id = 307,
        imageUrl = R.drawable.star_wizard,
        power = 60,
        defence = 0,
        type = CardType.Mage,
        title = "Star Wizard",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val giantMage = Card(
        id = 308,
        imageUrl = R.drawable.giant_mage,
        power = 70,
        defence = 0,
        type = CardType.Mage,
        title = "Giant Mage",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val spaceWizard = Card(
        id = 309,
        imageUrl = R.drawable.space_wizard,
        power = 80,
        defence = 0,
        type = CardType.Mage,
        title = "Space Wizard",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val viseSorcerer = Card(
        id = 310,
        imageUrl = R.drawable.vise_sorcerer,
        power = 80,
        defence = 0,
        type = CardType.Mage,
        title = "Vise Sorcerer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val unknownSorcerer = Card(
        id = 311,
        imageUrl = R.drawable.powerful_unknown_sorcerer,
        power = 90,
        defence = 0,
        type = CardType.Mage,
        title = "Powerful Unknown Sorcerer",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )
    private val motherOfMages = Card(
        id = 312,
        imageUrl = R.drawable.mother_of_sorcerers,
        power = 100,
        defence = 0,
        type = CardType.Mage,
        title = "Mother Of Sorcerers",
        desc = "",
        isSpecial = CardSpecialityType.NotSpecial
    )

    private val spaceWizardSpecial = Card(
        id = 313,
        imageUrl = R.drawable.space_wizard,
        power = 110,
        defence = 0,
        type = CardType.Mage,
        title = "Special Space Wizard",
        desc = "",
        isSpecial = CardSpecialityType.StrongAttackSpecial
    )

    private val viseSorcererSpecial = Card(
        id = 314,
        imageUrl = R.drawable.vise_sorcerer,
        power = 110,
        defence = 0,
        type = CardType.Mage,
        title = "Special Vise Sorcerer",
        desc = "",
        isSpecial = CardSpecialityType.StrongAttackSpecial
    )
    private val unknownSorcererSpecial = Card(
        id = 315,
        imageUrl = R.drawable.powerful_unknown_sorcerer,
        power = 100,
        defence = 30,
        type = CardType.Mage,
        title = "Special Powerful Unknown Sorcerer",
        desc = "",
        isSpecial = CardSpecialityType.MasterDefenceSpecial
    )
    private val motherOfMagesSpecial = Card(
        id = 316,
        imageUrl = R.drawable.mother_of_sorcerers,
        power = 110,
        defence = 20,
        type = CardType.Mage,
        title = "Special Mother Of Sorcerers",
        desc = "",
        isSpecial = CardSpecialityType.MasterAttackSpecial
    )

    val weakWizards = listOf(
        natureWizard,
        waterMage,
        windChanterSorcerer,
        windElfWizard
    )
    val midWizards = listOf(
        electricWizard,
        flameMage,
        darkSorcerer,
        starWizard
    )

    val strongWizards = listOf(
        spaceWizard,
        giantMage,
        viseSorcerer
    )

    val masterWizards = listOf(
        unknownSorcerer,
        motherOfMages
    )

    val SpecialStrongWizards = listOf(spaceWizardSpecial, viseSorcererSpecial)
    val SpecialMasterWizards = listOf(unknownSorcererSpecial, motherOfMagesSpecial)
}