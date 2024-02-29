package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laivinieks.aleatorya_clashofcards.R
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.bounceClick
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Utils.m

@Composable
fun OpenDeckButton(
    modifier: Modifier = Modifier, sm: Float = 0.4f,
    onclick: ((Boolean) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .requiredHeightIn(min = 60.dp)
            .padding(16.dp)
            .padding(start = 30.dp)
            .bounceClick(onAnimationFinished  = {
                if (onclick != null) {
                    onclick(true)
                }
            })


    ) {
        for (index in 0..5) {

            val rotateVal = (-25 + (10 * index)).toFloat()
            Box(
                modifier
                    .rotate(rotateVal)
                    .padding(horizontal = (6 * index).dp)
                    .clip(RoundedCornerShape(4.dp))
                    .size((80 * sm).dp, 120.m(sm).dp)
                    .requiredWidthIn(maxOf(80.m(sm)).dp)
                    .background(Color.White)
                    .padding(1.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .paint(painter = painterResource(id = R.drawable.card_bg), contentScale = ContentScale.FillHeight)

            ) {

            }
        }


    }

}


//@Preview(showSystemUi = true)
//@Composable
//private fun preview() {
//    OpenDeckButton()
//}