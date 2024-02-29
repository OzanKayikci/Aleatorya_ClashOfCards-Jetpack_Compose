package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller.ChangeDetermineDialog
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.GameBoardEvent
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.GameBoardFeatureScreen
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.GameDeskViewModel
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card.components.CardPager
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card.components.MiniCardDeck
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card.components.TakeChanceButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PickCardScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: GameDeskViewModel
) {
    val coroutineScope = rememberCoroutineScope()


    val cards by remember(viewModel.newDeckForPick) { viewModel.newDeckForPick }
    val pagerState = rememberPagerState(pageCount = { cards.size })
    var takeChanceForSpecial by remember {
        mutableStateOf(false)
    }
    var showLuckDialog by remember {
        mutableStateOf(false)
    }
    var chanceForSpecial by remember {
        mutableIntStateOf(0)
    }
    var selectedCard by remember {
        mutableIntStateOf(0)
    }
    var isAddedNewCard by remember {
        mutableStateOf(false)
    }
    var isNavigateToBack by remember {
        mutableStateOf(false)
    }
    var showingCardId by remember {
        mutableIntStateOf(0)
    }


    LaunchedEffect(chanceForSpecial) {
        if (chanceForSpecial > 0) {
            isAddedNewCard = true
            delay(200)
            viewModel.onEvent(
                GameBoardEvent.ChangePlayerLuckForSpecialCard(oldCardId = cards[pagerState.currentPage].id!!, luck = chanceForSpecial)
            )
        }
        chanceForSpecial = 0
    }

    LaunchedEffect(isAddedNewCard) {
        if (isAddedNewCard) {
            delay(400)
        }
        isAddedNewCard = false
    }

    LaunchedEffect(isNavigateToBack) {
        if (isNavigateToBack) {
            if (selectedCard > 0) {
                viewModel.onEvent(GameBoardEvent.NewCard(selectedCard))
            }
            navHostController.navigate(GameBoardFeatureScreen.GameBoardScreen.route + "?pickedCardId=${selectedCard}") {
                popUpTo(0)
            }
            isNavigateToBack = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pick_card_background),
                contentScale = ContentScale.Crop
            ),
    )
    {
        Box(modifier = modifier
            .offset(x = 8.dp, y = 16.dp)
            .size(60.dp)
            .clickable {
                isNavigateToBack = true
            }) {
            Image(painter = painterResource(id = R.drawable.back_icon), contentDescription = "go back")
        }
        if (takeChanceForSpecial) {
            //try luck for special card
            TakeChanceButton(
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = 50.dp, y = 130.dp)
                    .zIndex(3f)
            ) {
                showLuckDialog = true
            }
        }

        if (showLuckDialog) {
            ChangeDetermineDialog(callback = { showDialog, luckNumber ->
                showLuckDialog = showDialog
                chanceForSpecial = luckNumber
            })
        }
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.weight(1f))

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(8f),
                visible = !showLuckDialog,
                enter = fadeIn() + scaleIn(animationSpec = tween(500)),
                exit = fadeOut() + scaleOut(animationSpec = tween(500))
            ) {

                CardPager(
                    cards = cards,
                    pagerState = pagerState,
                    isStrongShowing = { takeChanceForSpecial = it },
                    currentCardId = { showingCardId = it },
                    onClick = {
                        selectedCard = cards.find { it.id == showingCardId }!!.id!!
                        isNavigateToBack = true
                    }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                MiniCardDeck(cards = cards, currentCardIndex = pagerState.currentPage, changeCurrentCard = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                })

            }
        }
    }
}

