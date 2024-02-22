package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.cards.FighterCards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.GameBoardFeatureScreen

@Composable
fun GameBoardScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    cardId: Int
) {

    var playerTurn by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier
            .fillMaxWidth(0.82f)
            .fillMaxHeight()) {
            AISide(modifier = modifier.weight(10f), playerTurn = playerTurn)

            Box(modifier = Modifier.weight(1f))

            /** player area*/
            PlayerSide(
                modifier = modifier.weight(10f),
                playerTurn = playerTurn,
                goBackClick = { navHostController.navigate(GameBoardFeatureScreen.PickCardScreen.route) }
            )
        }
    }

}

@Composable
fun PlayerSide(modifier: Modifier = Modifier, playerTurn: Boolean, goBackClick: () -> Unit) {
    Column(modifier = modifier) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            //contentPadding = PaddingValues(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            val fighter = FighterCards.masterFighters.plus(FighterCards.strongSpecialFighters + FighterCards.masterSpecialFighters)

            items(fighter, key = { it.id!! }) { char ->
                CardItem(card = char, sizeMultiplier = 1f)
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(FighterCards.weakFighters.subList(0, 4)) {
                    CardItem(card = it, sizeMultiplier = 0.5f)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (!playerTurn) 0f else 1f), contentAlignment = Alignment.Center
        ) {
            OpenDeckButton(sm = 0.4f) {
                goBackClick()

            }

        }

    }
}

@Composable
fun AISide(modifier: Modifier = Modifier, playerTurn: Boolean) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (playerTurn) 0f else 1f), contentAlignment = Alignment.Center
        ) {
            OpenDeckButton(sm = 0.4f)

        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(FighterCards.weakFighters.subList(0, 4)) {
                    CardItem(card = it, sizeMultiplier = 0.5f)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            // contentPadding = PaddingValues(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            val fighter =
                FighterCards.weakFighters.subList(0, 2).plus(FighterCards.midFighters.subList(0, 2) + FighterCards.strongFighters.subList(0, 2))
            items(fighter, key = { it.id!! }) { char ->
                CardItem(card = char, sizeMultiplier = 1f)
            }

        }
    }
}

//@Preview(showSystemUi = true)
//
//@Composable
//fun SelectCardScreenPreview() {
//    GameBoardScreen()
//}