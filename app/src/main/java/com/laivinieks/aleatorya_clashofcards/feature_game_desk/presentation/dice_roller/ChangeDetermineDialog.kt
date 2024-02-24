package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.laivinieks.aleatorya_clashofcards.ui.theme.strongAttackSpecialColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeDetermineDialog(
    modifier: Modifier = Modifier,
    callback: (
        closeDialog: Boolean,
    ) -> Unit
) {
    val rollerItems = listOf(0..50).flatMap { it.toList() }
    var selectedNumbers by remember {
        mutableStateOf(mutableListOf(0,0))
    }
    var isRolling by remember {
        mutableStateOf(false)
    }
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = { callback(true) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Blurred background
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6f))
                    .fillMaxSize()
                    .clickable {
                        callback(true)
                    },
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DiceRoller(modifier = Modifier.size(150.dp),items = rollerItems, selected = {
                        selectedNumbers[0] = it
                        isRolling = false
                    }, isRolling = isRolling)
                    DiceRoller(items = rollerItems, selected = {
                        selectedNumbers[1] = it
                        isRolling = false
                    }, isRolling = isRolling)
                }
                Text(
                    text = "Roll",
                    modifier = Modifier.clickable {
                        isRolling = true
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = strongAttackSpecialColor,
                        fontSize = 32.sp
                    )
                )
                Text(text = "${selectedNumbers[0]} - ${selectedNumbers[1]}")
            }

        }
    }
}