package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller

import android.annotation.SuppressLint
import android.util.Log

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.bounceClick
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.Constants

import com.laivinieks.aleatorya_clashofcards.ui.theme.*
import kotlinx.coroutines.delay


@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeDetermineDialog(
    modifier: Modifier = Modifier,
    callback: (
        showDialog: Boolean,
        luckNumber: Int,
    ) -> Unit
) {
    val rollerItems = Constants.rollerItems

    var selectedNumbers by remember {
        mutableStateOf(mutableListOf(0, 0))
    }
    var clickedTheButton by remember {
        mutableStateOf(false)
    }

    var totalLuck by remember {
        mutableIntStateOf(0)
    }
    var isRolling by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(totalLuck) {
        if (totalLuck > 0) {
            delay(2000)
            callback(false, totalLuck)
        }


    }
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = {}
    ) {

        Text(
            text = "Earn Your Luck",
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

                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(size = 16.dp)
                    )

                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$totalLuck",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = getLuckColor(totalLuck),
                        fontSize = 100.sp
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DiceRoller(modifier = Modifier.size(150.dp), items = rollerItems, selected = {
                        selectedNumbers[0] = it
                        isRolling = false
                    }, isRolling = isRolling)
                    DiceRoller(items = rollerItems, selected = {
                        selectedNumbers[1] = it
                        isRolling = false
                        totalLuck = selectedNumbers[0] + selectedNumbers[1]

                    }, isRolling = isRolling)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Roll",
                    modifier = Modifier.bounceClick(isEnabled = !clickedTheButton, onAnimationFinished = {
                        clickedTheButton = true
                        isRolling = true
                    }),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = strongAttackSpecialColor,
                        fontSize = 64.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

            }

        }
    }
}

private fun getLuckColor(number: Int): Color {
    return when {
        number < 20 -> {
            normalCardColor
        }

        number < 40 -> {
            strongDefenceSpecialColor

        }

        number < 60 -> {
            strongAttackSpecialColor
        }

        number < 80 -> {
            masterDefenceSpecialColor
        }

        else -> {
            masterAttackSpecialColor
        }
    }
}