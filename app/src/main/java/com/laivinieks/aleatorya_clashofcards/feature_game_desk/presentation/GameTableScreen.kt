package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.OpenDeckButton
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Characters

@Composable
fun SelectCardScreen() {

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

        Column(modifier = Modifier.fillMaxWidth(0.82f)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(if (playerTurn) 0f else 1f), contentAlignment = Alignment.Center
            ) {
                OpenDeckButton(sm = 0.4f)

            }

            Box(modifier = Modifier.weight(1f))



            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(Characters.fighters.subList(0, 4)) {
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
                items(Characters.fighters.subList(2, 10), key = { it.id!! }) { char ->
                    CardItem(card = char, sizeMultiplier = 1f)
                }

            }
            Box(modifier = Modifier.weight(10f))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                //contentPadding = PaddingValues(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                items(Characters.fighters.subList(0, 8), key = { it.id!! }) { char ->
                    CardItem(card = char, sizeMultiplier = 1f)
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(Characters.fighters.subList(0, 4)) {
                        CardItem(card = it, sizeMultiplier = 0.5f)
                    }
                }
            }
            Box(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .fillMaxWidth()
                .alpha(if (!playerTurn) 0f else 1f), contentAlignment = Alignment.Center) {
                OpenDeckButton(sm = 0.4f)

            }


        }
    }

}


@Preview(showSystemUi = true)

@Composable
fun SelectCardScreenPreview() {
    SelectCardScreen()
}