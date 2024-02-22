package com.laivinieks.aleatorya_clashofcards.feature_game_desk.util

sealed class GameBoardFeatureScreen(val route: String) {

    object GameBoardScreen : GameBoardFeatureScreen("game_board_screen")
    object PickCardScreen : GameBoardFeatureScreen("pick_card_screen")
}