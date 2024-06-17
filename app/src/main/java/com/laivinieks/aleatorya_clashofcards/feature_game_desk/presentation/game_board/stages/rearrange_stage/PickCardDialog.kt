package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.Cards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem
import com.laivinieks.aleatorya_clashofcards.ui.theme.diceTextColor
import com.laivinieks.aleatorya_clashofcards.ui.theme.masterAttackSpecialColor
import com.laivinieks.aleatorya_clashofcards.ui.theme.strongAttackSpecialColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickCardDialog(modifier: Modifier = Modifier, abilityCard: Card?, cards: List<Card>?, selectedCard: (Card?) -> Unit, isPlayerTurn: Boolean) {

    LaunchedEffect(cards) {
        if (cards != null && cards.isEmpty()) {
            delay(3000)
            selectedCard(null)
        }
    }

    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = {}
    ) {

        Text(
            text = "Destroy Enemy Card",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = masterAttackSpecialColor,
                fontSize = 48.sp
            ),
            textAlign = TextAlign.Center
        )


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Blurred background
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    // .offset { IntOffset(0, 120.dp.roundToPx()) }
                    .scale(scaleX = 1f, scaleY = 1f)

                    .blur(
                        radius = 100.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    )
                    .background(Color.Black.copy(alpha = 0.8f))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(size = 16.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (abilityCard != null) {

                    CardItem(card = abilityCard, sizeMultiplier = 1f)

                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = if (isPlayerTurn) "Select Card To Destroy" else "Rival Select Card To Destroy.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = strongAttackSpecialColor,
                            fontSize = 32.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                }
                if (cards != null) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(),
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        itemsIndexed(cards) { index, card ->
                            if (isPlayerTurn)
                                CardItem(card = card, sizeMultiplier = 1.5f, clickedForPreview = selectedCard)
                            else
                                CardItem(card = card, sizeMultiplier = 1.5f, clickedForPreview = {})


                        }

                    }
                    if (cards.isEmpty()) {
                        Text(
                            text = "No card to destroy",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = diceTextColor,
                                fontSize = 32.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }

}

//@Preview(showSystemUi = true)
//@Composable
//private fun Preview() {
//
//    val cards = Cards.abilityCards.take(3)
//    PickCardDialog(isAbility = true, cards = cards)
//}