package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller.ChangeDetermineDialog
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.FindWhoStartStage
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.pick_card_stage.PickCardsStage
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage.RearrangeStage
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.GameBoardFeatureScreen
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.RoundParts
import com.laivinieks.aleatorya_clashofcards.ui.theme.strongAttackSpecialColor
import kotlinx.coroutines.delay

@Composable
fun GameBoardScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: GameDeskViewModel,
    cardId: Int
) {
    val roundPart = viewModel.roundPart.value
    val playerDeck = viewModel.playerCards.value
    val aiDeck = viewModel.AICards.value
    val newCard = viewModel.newCard.value

    val isPlayerTurn = viewModel.isPlayerTurn.value

    var showLuckDialog by remember {
        mutableStateOf(false)
    }
    var navigateToCardPick by remember {
        mutableStateOf(false)
    }

    val totalCardOnBoard = playerDeck.size + aiDeck.size

    if (showLuckDialog) {
        ChangeDetermineDialog(callback = { showDialog, luckNumber ->
            viewModel.onEvent(GameBoardEvent.ChangeLuckForNewCards(luckNumber))
            showLuckDialog = showDialog
            navigateToCardPick = true
        })
    }

    LaunchedEffect(navigateToCardPick) {
        if (navigateToCardPick) {
            navHostController.navigate(GameBoardFeatureScreen.PickCardScreen.route)
        }
        navigateToCardPick = false
    }

    LaunchedEffect(newCard) {
        if (cardId > 0) {
            viewModel.onEvent(GameBoardEvent.ChangePlayerStats(Stats(newCard!!.power, newCard!!.defence)))
        }
    }
    LaunchedEffect(playerDeck) {
        if (playerDeck.isNotEmpty()) {
            delay(2000)
            viewModel.onEvent(GameBoardEvent.ShowTurnText(true))
            viewModel.onEvent(GameBoardEvent.ChangePlayerTurn(false))
            delay(1000)
            viewModel.onEvent(GameBoardEvent.ShowTurnText(false))
        }

    }

    LaunchedEffect(isPlayerTurn) {
        if ((isPlayerTurn != null) && !isPlayerTurn) {
            delay(3000)
            viewModel.playAI()
            delay(1000)
            viewModel.onEvent(GameBoardEvent.ShowTurnText(true))
            viewModel.onEvent(GameBoardEvent.ChangePlayerTurn(true))
        }
        if (isPlayerTurn!! && viewModel.showTurnText.value){
            delay(1000)
            viewModel.onEvent(GameBoardEvent.ShowTurnText(false))

        }
    }

    LaunchedEffect(totalCardOnBoard){
        if (totalCardOnBoard >= 8){
            viewModel.onEvent(GameBoardEvent.ChangeRoundPart(RoundParts.REARRANGE_CARDS))
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        contentAlignment = Alignment.Center
    ) {
        when (roundPart) {
            RoundParts.CHOOSE_STARTER -> {
                FindWhoStartStage(modifier = Modifier,
                    onEvents = { changePlayerTurn, changeRoundPart ->
                        viewModel.onEvent(changePlayerTurn)
                        viewModel.onEvent(changeRoundPart)
                    })
            }

            RoundParts.CARD_PICK -> PickCardsStage(
                modifier = Modifier,
                playerDeck = playerDeck,
                aiDeck = aiDeck,
                isPlayerTurn = isPlayerTurn!!,
                cardId = cardId,
                playerStats = viewModel.playerStats.value,
                aiStats = viewModel.AIStats.value,
                navigateToCardPick = { if (viewModel.playerLuckForNewDeck.value > 0) navigateToCardPick = true else showLuckDialog = true }
            )

            RoundParts.REARRANGE_CARDS -> {
RearrangeStage()
            }

            RoundParts.BATTLE -> {

            }
        }

        AnimatedVisibility(

            visible = viewModel.showTurnText.value,
            enter = fadeIn() + scaleIn(animationSpec = tween(500)),
            exit = fadeOut() + scaleOut(animationSpec = tween(500))
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(300.dp, 125.dp)
                        .scale(scaleX = 1f, scaleY = 1f)

                        // .offset(x =10.m(sm).dp)
                        .blur(
                            radius = 50.dp,
                            edgeTreatment = BlurredEdgeTreatment.Unbounded,
                        )

                        .background(Color.Black.copy(alpha = 1f))
                )
                Text(
                    text = if (isPlayerTurn!!) "Your Turn" else "The Rival Turn",
                    style = MaterialTheme.typography.bodyMedium.copy(color = strongAttackSpecialColor, fontSize = 64.sp)
                )

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