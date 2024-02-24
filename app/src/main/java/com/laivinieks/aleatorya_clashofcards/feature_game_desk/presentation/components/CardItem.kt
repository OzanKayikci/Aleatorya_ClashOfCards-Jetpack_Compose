package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.CardSpecialityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.CardType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Utils.m

@Composable
fun CardItem(modifier: Modifier = Modifier, card: Card, sizeMultiplier: Float = 1f, isClickable: Boolean = true) {

    val sm = sizeMultiplier

    val cardColor = card.isSpecial.color.copy(
        if (!card.isSpecial.isSpecial)
            0.2f else 0.5f
    )
    val brush = Brush.radialGradient(listOf(cardColor, Color.Black.copy(alpha = 0.2f)), radius = (300.m(sm)))
    Box(
        modifier = modifier
            .size(80.m(sm).dp, 120.m(sm).dp)
            .requiredWidthIn(maxOf(80.m(sm)).dp)
            .clip(RoundedCornerShape(8.m(sm).dp))
            .background(
                brush
            ).shadow(20.dp)
            .clickable(enabled = isClickable) {

            }
    )
    {

        Box(
            modifier = modifier
                .size(80.m(sm).dp, 120.m(sm).dp)
                .padding(2.m(sm).dp)
                .clip(RoundedCornerShape(6.m(sm).dp))
                .paint(
                    // Replace with your image id
                    painterResource(id = card.imageUrl),
                    contentScale = ContentScale.FillHeight
                )

        ) {

            Column(
                modifier = Modifier

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


//@Preview(showSystemUi = true)
//@Composable
//fun Preview() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .paint(painter = painterResource(id = R.drawable.background3), contentScale = ContentScale.Crop)
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        CardItem(
//            sizeMultiplier = 1,
//        )
//    }
//}