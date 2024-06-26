package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.build_deck_stage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.OpenDeckButton
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import com.laivinieks.aleatorya_clashofcards.ui.theme.masterAttackSpecialColor
import com.laivinieks.aleatorya_clashofcards.ui.theme.masterDefenceSpecialColor
import kotlinx.coroutines.delay


@Composable
fun AISide(
    modifier: Modifier = Modifier,
    deck: List<Card> = emptyList(),
    playerTurn: Boolean,
    newCardId: Int,
    stats: Stats
) {
    val normalCards = if (deck.isNotEmpty()) deck.filter { it.type::class != CardType.Ability::class } else emptyList()
    val abilityCards = if (deck.isNotEmpty()) deck.filter { it.type::class == CardType.Ability::class } else emptyList()

    var showNewCard by remember {
        mutableStateOf(false)
    }
    var cardForPreview by remember {
        mutableStateOf<Card?>(null)
    }
    LaunchedEffect(newCardId) {
        if (newCardId > 0) {
            delay(600)
            showNewCard = true
        }
    }



    Column(modifier = modifier.padding(bottom = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .rotate(180f), contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.damage_icon), contentDescription = "card style",
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(0.3f)
                    )
                    Text(
                        text = stats.attack.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = masterAttackSpecialColor,
                            fontSize = 60.sp,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false,
                            ),
                        ),
                        modifier = Modifier.offset(y = (-6).dp)

                    )
                }
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.shield_icon), contentDescription = "card style",
                        modifier = Modifier
                            .size(100.dp)
                            .alpha(0.3f)
                    )
                    Text(
                        text = stats.defence.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = masterDefenceSpecialColor,
                            fontSize = 60.sp,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false,
                            ),
                        ),
                        modifier = Modifier.offset(y = (-8).dp)
                    )
                }
            }

            OpenDeckButton(modifier = Modifier.alpha(if (playerTurn) 0f else 1f), sm = 0.4f)

        }
        //Ability cards
        AbilityCardsPart(
            modifier = Modifier
                .rotate(180f)
                .fillMaxWidth(0.82f),
            cards = abilityCards,
            showNewCard = showNewCard,
            newCardId = newCardId
        )
        Spacer(modifier = Modifier.weight(0.5f))
        //Normal Cards Part
        NormalCardsPart(
            modifier = Modifier
                .rotate(180f)
                .fillMaxWidth(0.82f),
            cards = normalCards,
            showNewCard = showNewCard,
            newCardId = newCardId,
            cardForPreview = {
                cardForPreview = it
            }
        )
    }
    /** to preview card*/
    AnimatedVisibility(
        visible = cardForPreview != null,
        enter = scaleIn(animationSpec = tween(500)),
        exit = scaleOut(animationSpec = tween(500))
    ) {
        if (cardForPreview != null) {

            PreviewCard(
                selectedCard = cardForPreview!!,
                onDismissRequest = { cardForPreview = null }
            )

        }
    }
}