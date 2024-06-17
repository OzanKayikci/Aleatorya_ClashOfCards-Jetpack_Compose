package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.GameBoardScreen
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.GameDeskViewModel
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card.PickCardScreen
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.GameBoardFeatureScreen


@Composable
fun FeatureGameDeskNavHost(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val gameDeskViewModel: GameDeskViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = GameBoardFeatureScreen.GameBoardScreen.route,
        modifier = modifier
    ) {
        composable(route = GameBoardFeatureScreen.GameBoardScreen.route +
                "?pickedCardId={pickedCardId}",
            arguments = listOf(
                navArgument(name = "pickedCardId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )) {
            val cardId = it.arguments?.getInt("pickedCardId") ?: -1
            GameBoardScreen(navHostController = navController, cardId = cardId, viewModel = gameDeskViewModel)
        }
        composable(route = GameBoardFeatureScreen.PickCardScreen.route) {

            PickCardScreen(navHostController = navController, viewModel = gameDeskViewModel)
        }
    }
}