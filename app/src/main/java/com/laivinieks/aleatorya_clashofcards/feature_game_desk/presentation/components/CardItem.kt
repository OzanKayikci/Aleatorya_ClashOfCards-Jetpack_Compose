package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.Cards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.FighterCards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Utils
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Utils.m
import com.laivinieks.aleatorya_clashofcards.ui.theme.strongAttackSpecialColor

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    card: Card,
    sizeMultiplier: Float = 1f,
    isClickable: Boolean = true,
) {

    var configuration = LocalConfiguration.current
    var dimensionSizeMultiplayer = Utils.sizeConvertorFromDimension(configuration)
    val sm = sizeMultiplier * dimensionSizeMultiplayer

    val cardColor = card.isSpecial.color.copy(
        if (!card.isSpecial.isSpecial)
            0f else 0.6f
    )
    // val brush = Brush.radialGradient(listOf(cardColor, Color.Black.copy(alpha = 0.2f)), radius = (300.m(sm)))


    Box(
        modifier = modifier
            .size(80.m(sm).dp, 120.m(sm).dp)
            .requiredWidthIn(maxOf(80.m(sm)).dp)
            //.clip(RoundedCornerShape(8.m(sm).dp))
//            .background(
//                brush
//            )
//            .shadow(20.dp)
            .padding(top = 2.dp)
            .clickable(enabled = isClickable) {

            },
        contentAlignment = Alignment.Center
    )
    {
        if (isClickable && card.isSpecial.isSpecial) {
            Box(
                modifier = Modifier
                    .size(80.m(sm).dp, 120.m(sm).dp)
                    .scale(scaleX = 1f, scaleY = 1f)

                    // .offset(x =10.m(sm).dp)
                    .blur(
                        radius = 5.m(sm).dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded,
                    )
                    .clip(RoundedCornerShape(10.m(sm).dp))

                    .background(cardColor)
            )
        }

        Box(
            modifier = modifier
                .size(80.m(sm).dp, 120.m(sm).dp)

                .clip(RoundedCornerShape(6.m(sm).dp))
                .paint(
                    painterResource(id = if (!card.isCardClose) card.imageUrl else R.drawable.card_bg),
                    contentScale = ContentScale.FillHeight
                )

        ) {

            Column(
                modifier = Modifier
                    .alpha(if (card.isCardClose) 0f else 1f)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.damage_icon), contentDescription = "card style",
                            modifier = Modifier.size(20.m(sm).dp)
                        )
                        Text(
                            text = card.power.toString(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.m(sm).sp,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false,
                                ),
                            )
                        )
                    }
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.shield_icon), contentDescription = "card style",
                            modifier = Modifier.size(20.m(sm).dp)
                        )
                        Text(
                            text = card.defence.toString(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.m(sm).sp,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false,
                                ),
                            )
                        )
                    }

                }
                Image(
                    painter = painterResource(id = card.type.icon), contentDescription = "card style",
                    modifier = Modifier.size(18.m(sm).dp)
                )
            }
        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    var card = Cards.masterSpecialCards[0]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.drawable.pick_card_background), contentScale = ContentScale.Crop)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardItem(
            sizeMultiplier = 3f,
            card = card,
        )
    }
}