package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.pick_card

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.CardItem
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.cards.FighterCards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.GameBoardFeatureScreen
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.cards.MageCards
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PickCardScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val cards = MageCards.SpecialStrongWizards + MageCards.SpecialMasterWizards


    val pagerState = rememberPagerState(pageCount = { cards.size })
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pick_card_background),
                contentScale = ContentScale.Crop
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.weight(1f))
        CardPager(modifier = Modifier
            .fillMaxHeight()
            .weight(8f), cards = cards, pagerState = pagerState)

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.5f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            MiniCardDeck(cards = cards, currentCardIndex = pagerState.currentPage) {
                navHostController.navigate(GameBoardFeatureScreen.GameBoardScreen.route + "?pickedCardId={0}") {
                    popUpTo(0)

                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardPager(modifier: Modifier = Modifier, cards: List<Card>, pagerState: PagerState) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    )
    {
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
        ) { page ->
            val pageOffset = pagerState.calculateCurrentOffsetForPage(page)

            Box(
                Modifier
                    .graphicsLayer {
                        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
                        // translate the contents by the size of the page, to prevent the pages from sliding in from left or right and stays in the center
                        val scale = lerp(1f, 1.75f, pageOffset)
                        // apply an alpha to fade the current page in and the old page out
                        alpha = 1 - pageOffset.absoluteValue*1.8f
                        scaleX = scale
                        scaleY = scale
                    }
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CardItem(card = cards[pagerState.currentPage], sizeMultiplier = 3.5f)

            }
        }
    }

}

// extension method for current page offset
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}