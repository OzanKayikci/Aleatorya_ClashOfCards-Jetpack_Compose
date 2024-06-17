package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.AbilityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.Cards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.round

@Composable
fun RearrangeStage(modifier: Modifier = Modifier, playerDeck: List<Card>, aiDeck: List<Card>) {

    val scope = rememberCoroutineScope()

    val viewModel: RearrangeViewModel = viewModel()

    val playerCards = viewModel.playerCards.value
    val aiCards = viewModel.aiCards.value
    val animatePlayerCards = viewModel.animatePlayerCards.value
    val showDialog = viewModel.isDialogOpen.value

    val startRowAnimationforPlayer = viewModel.startToRowAnimationForPlayer.value
    val startRowAnimationforAI = viewModel.startToRowAnimationForAI.value

    LaunchedEffect(Unit) {
        if (viewModel.isStart.value) {
            viewModel.onEvent(RearrangeEvent.GetPlayerCards(playerDeck))
        }
    }

    LaunchedEffect(viewModel.isPlayerAbilityCardsCompleted.value) {
        if (viewModel.isPlayerAbilityCardsCompleted.value && !viewModel.isAiAbilityCardsCompleted.value) {
            scope.launch {
                viewModel.onEvent(RearrangeEvent.ChangePlayerTurn(false))
                delay(2000)
                viewModel.onEvent(RearrangeEvent.OpenPickCardDialog)
            }

        }
    }
    if (showDialog) {
        PickCardDialog(
            abilityCard = viewModel.selectedAbilityCard.value,
            cards = viewModel.destroyableCards.value,
            isPlayerTurn = viewModel.isPlayerTurn.value,
            selectedCard = {
                scope.launch {
                    delay(500)
                    viewModel.onEvent(RearrangeEvent.SelectedEnemyCard(it))
                    delay(2000)
                }

            })
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        animatePlayerCards?.let {
            if (!animatePlayerCards) {
                // ai side
                ArrangedSide(
                    modifier = Modifier
                        .weight(20f)
                        .rotate(180f),
                    deck = aiCards,
                    startRowAnimation = startRowAnimationforAI,
                    isVisible = {
                        //start to show ability dialog
                        viewModel.onEvent(RearrangeEvent.OpenPickCardDialog)
                    },
                    updateAnimatedRow = { showNextRowAnim ->
                        viewModel.onEvent(showNextRowAnim)
                    }

                )
            } else {
                Box(modifier = Modifier.weight(20f))

            }
            Box(modifier = Modifier.weight(0.5f))
            ArrangedSide(
                modifier = Modifier
                    .weight(20f), deck = playerCards,
                startRowAnimation = startRowAnimationforPlayer,
                isVisible = {
                    //start to animate ai deck
                    if (viewModel.isStart.value) {
                        viewModel.onEvent(RearrangeEvent.GetAiCards(aiDeck))
                        viewModel.isStart.value = false
                    }
                },
                updateAnimatedRow = { showNextRowAnim ->
                    viewModel.onEvent(showNextRowAnim)
                }


            )

        }

    }

}


@Composable
private fun ArrangedSide(
    modifier: Modifier = Modifier,
    deck: RearrangeCards,
    startRowAnimation: List<Boolean>,
    isVisible: (Boolean) -> Unit = {},
    updateAnimatedRow: (RearrangeEvent.ShowNextRowAnim) -> Unit
) {


    val sizeMultiplier = 0.7f

    val abilityCards = deck.abilityCards

    val sortedAbilityCards =
        abilityCards!!.let {
            it.closeRange + it.archer + it.mage + it.defence

        }
    val warriors = deck.fighterCards
    val archers = deck.archerCards
    val mages = deck.mageCards

    val defenceCards = deck.defenceCards!!

    LaunchedEffect(Unit) {

        when {
            sortedAbilityCards.isNotEmpty() -> {
                updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.ABILITY))
                return@LaunchedEffect
            }

            mages.isNotEmpty() -> {
                updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.MAGE))
                return@LaunchedEffect
            }

            archers.isNotEmpty() -> {
                updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.ARCHER))
                return@LaunchedEffect
            }

            warriors.isNotEmpty() -> {
                updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.WARRIOR))
                return@LaunchedEffect
            }

            (defenceCards.archerDefence + defenceCards.mageDefence + defenceCards.fighterDefence).isNotEmpty() -> {
                updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.DEFENCE))
                return@LaunchedEffect
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .weight(8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //defence cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .paint(painter = painterResource(id = R.drawable.defence_shield_icon), contentScale = ContentScale.Inside, alpha = 0.1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (startRowAnimation[4]) {

                    //shields
                    DisplayCards(cards = defenceCards.fighterDefence, sizeMultiplier = sizeMultiplier, updateCount = {
                        isVisible(true)
                    })

                    //helmets
                    DisplayCards(cards = defenceCards.archerDefence, sizeMultiplier = sizeMultiplier, updateCount = {
                        isVisible(true)
                    })

                    //spell shields
                    DisplayCards(cards = defenceCards.mageDefence, sizeMultiplier = sizeMultiplier, updateCount = {
                        isVisible(true)
                    })
                    if ((defenceCards.archerDefence + defenceCards.archerDefence + defenceCards.mageDefence).isEmpty()) {
                        isVisible(true)
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .paint(painter = painterResource(id = R.drawable.two_sword_icon), contentScale = ContentScale.Inside, alpha = 0.1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (startRowAnimation[3]) {     //fighters
                    DisplayCards(cards = warriors, sizeMultiplier = sizeMultiplier, updateCount = {
                        updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.DEFENCE))
                    })
                    if (warriors.isEmpty()) {
                        updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.DEFENCE))
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .paint(painter = painterResource(id = R.drawable.archer_icon), contentScale = ContentScale.Inside, alpha = 0.1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (startRowAnimation[2]) {
                    //archers
                    DisplayCards(cards = archers, sizeMultiplier = sizeMultiplier, updateCount = {
                        updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.WARRIOR))
                    })
                    if (archers.isEmpty() && !startRowAnimation[3] ) {
                        updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.WARRIOR))
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .paint(painter = painterResource(id = R.drawable.wizard_icon), contentScale = ContentScale.Inside, alpha = 0.1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (startRowAnimation[1]) {
                    //mages
                    DisplayCards(cards = mages, sizeMultiplier = sizeMultiplier, updateCount = {
                        updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.ARCHER))
                    })
                    if (mages.isEmpty()&& !startRowAnimation[2]) {
                        updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.ARCHER))
                    }
                }
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.6f)
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //ability cards
            if (startRowAnimation[0]) {
                DisplayCards(cards = sortedAbilityCards, sizeMultiplier = sizeMultiplier * 0.6f, updateCount = {
                    updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.MAGE))

                })
                if (sortedAbilityCards.isEmpty()&& !startRowAnimation[1]) {
                    updateAnimatedRow(RearrangeEvent.ShowNextRowAnim(RowByCardType.MAGE))
                }
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun DisplayCards(cards: List<Card>, sizeMultiplier: Float, updateCount: () -> Unit) {

    if (cards.isNotEmpty()) {
        var currentAnimatedCard by remember {
            mutableIntStateOf(0)
        }
        for (index in cards.indices) {
            AnimatableCard(
                modifier = Modifier.alpha(if (currentAnimatedCard >= index) 1f else 0f),
                card = cards[index],
                sizeMultiplier = sizeMultiplier, isAnimate = currentAnimatedCard == index,
                isAnimationCompleted = {
                    currentAnimatedCard += 1
                    if (currentAnimatedCard >= cards.size) {
                        updateCount()
                    }
                }
            )

        }
    }

}

@Composable
fun AnimatableCard(modifier: Modifier = Modifier, card: Card, sizeMultiplier: Float, isAnimate: Boolean, isAnimationCompleted: () -> Unit) {


    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val coroutineScope = rememberCoroutineScope()

    val offsetY = remember { Animatable(screenHeight) }
    val offsetX = remember { Animatable(screenWidth / 2) }
    val scale = remember { Animatable(1.5f) }

    LaunchedEffect(isAnimate) {
        if (isAnimate) {
            coroutineScope.launch {
                launch {
                    awaitAll(
                        async {
                            offsetX.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(
                                    durationMillis = 200,
                                    easing = CubicBezierEasing(.21f, .64f, .26f, 1.1f)
                                )
                            )
                        },
                        async {
                            offsetY.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(
                                    durationMillis = 200,
                                    easing = CubicBezierEasing(.21f, .64f, .63f, 1.04f)
                                )
                            )
                        },
                        async {
                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(
                                    durationMillis = 200,
                                    easing = CubicBezierEasing(.98f, -1f, .95f, 1.05f)
                                )
                            )
                        }

                    )


                }
            }
        }

    }
    LaunchedEffect(offsetY.isRunning) {
        if (!offsetY.isRunning && offsetY.value == 0f) {
            isAnimationCompleted()
            coroutineScope.cancel()
        }
    }

    Box(
        modifier = modifier
            .offset(y = offsetY.value.dp, x = offsetX.value.dp)
            .scale(scale.value)
    ) {
        CardItem(
            card = card,
            sizeMultiplier = sizeMultiplier,
            isClickable = card.type::class == CardType.Ability::class,
        )
    }

}

