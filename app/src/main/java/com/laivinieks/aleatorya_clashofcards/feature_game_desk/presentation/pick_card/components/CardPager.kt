package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.PowerRate
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.bounceClick
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import com.laivinieks.aleatorya_clashofcards.ui.theme.strongAttackSpecialColor
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardPager(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    pagerState: PagerState,
    isStrongShowing: (Boolean) -> Unit,
    currentCardId: (Int) -> Unit,
    onClick: (Boolean) -> Unit
) {

    val verticalState = rememberPagerState(pageCount = {
        cards.count()
    })
    val verticalStateTop = rememberPagerState(pageCount = {
        cards.count()
    })
    val canBeSpecialCardTypes = listOf(CardType.Mage, CardType.CloseRange, CardType.Archer)
    val canBeSpecialPowerRates = listOf(PowerRate.epic, PowerRate.legendary)


    LaunchedEffect(Unit) {
        snapshotFlow {
            Pair(
                pagerState.currentPage,
                pagerState.currentPageOffsetFraction
            )
        }.collect { (page, offset) ->
            verticalState.scrollToPage(page, offset)
            verticalStateTop.scrollToPage(page, offset)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            contentAlignment = Alignment.BottomCenter
        ) {
            //Black background for text
            Box(
                modifier = Modifier
                    .size(300.dp, 50.dp)
                    .scale(scaleX = 1f, scaleY = 1f)
                    .blur(
                        radius = 60.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    )
                    .background(Color.Black.copy(alpha = 1f))
            )

            VerticalPager(
                reverseLayout = true,
                state = verticalStateTop,
                userScrollEnabled = false
            ) { page ->
                val card = cards[page]
                val pageOffset = verticalStateTop.calculateCurrentOffsetForPage(page)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(1 - pageOffset.absoluteValue * 1.8f), contentAlignment = Alignment.BottomCenter
                ) {

                    Text(
                        text = card.powerRate!!.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = card.powerRate.color, fontSize = 36.sp
                        )
                    )

                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .zIndex(2f),
            contentAlignment = Alignment.Center
        )
        {
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
            ) { page ->
                val card = cards[pagerState.currentPage]
                if (canBeSpecialCardTypes.contains(card.type) &&
                    canBeSpecialPowerRates.contains(card.powerRate)
                ) {
                    isStrongShowing(true)
                } else {
                    isStrongShowing(false)
                }
                currentCardId(card.id!!)

                Box(
                    Modifier
                        .graphicsLayer {
                            val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                            // translate the contents by the size of the page, to prevent the pages from sliding in from left or right and stays in the center
                            val scale = lerp(1f, 1.75f, pageOffset)
                            // apply an alpha to fade the current page in and the old page out
                            alpha = 1 - pageOffset.absoluteValue * 1.8f
                            scaleX = scale
                            scaleY = scale
                        }
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CardItem(modifier.bounceClick(onAnimationFinished = { onClick(true) }), card = card, sizeMultiplier = 3.5f)

                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.TopCenter
        ) {
            //Black background for text
            Box(
                modifier = Modifier
                    .size(300.dp, 50.dp)
                    .scale(scaleX = 1f, scaleY = 1f)
                    .blur(
                        radius = 60.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    )
                    .background(Color.Black.copy(alpha = 1f))
            )
            VerticalPager(

                state = verticalState,
                userScrollEnabled = false
            ) { page ->
                val pageOffset = verticalState.calculateCurrentOffsetForPage(page)
                Text(
                    modifier = Modifier.alpha(1 - pageOffset.absoluteValue * 1.8f),
                    text = cards[page].title,
                    style = MaterialTheme.typography.bodyMedium.copy(color = strongAttackSpecialColor, fontSize = 32.sp)
                )
            }
        }
    }

}

// extension method for current page offset
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}