package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.components.bounceClick
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.dice_roller.DiceRoller
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.GameBoardEvent
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.GameDeskViewModel
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.RoundParts
import com.laivinieks.aleatorya_clashofcards.ui.theme.diceTextColor

import com.laivinieks.aleatorya_clashofcards.ui.theme.masterAttackSpecialColor
import com.laivinieks.aleatorya_clashofcards.ui.theme.strongAttackSpecialColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindWhoStartStage(
    modifier: Modifier,
    onEvents: (
        changePlayerTurn: GameBoardEvent.ChangePlayerTurn,
        changeRoundPart: GameBoardEvent.ChangeRoundPart
    ) -> Unit
) {
    val scope = rememberCoroutineScope()

    val rollerItems = listOf(0, 1)

    var clickedTheButton by remember {
        mutableStateOf(false)
    }

    var isUserWin by remember {
        mutableStateOf(false)
    }
    var userChoice by remember {
        mutableIntStateOf(-1)
    }
    var isRolling by remember {
        mutableStateOf(false)
    }

    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = {}
    ) {

        Text(
            text = "Who Will Start",
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DiceRoller(modifier = Modifier.size(150.dp), items = rollerItems, selected = {
                        isUserWin = userChoice == it
                        scope.launch {
                            delay(2000)
                            onEvents(
                                GameBoardEvent.ChangePlayerTurn(isUserWin),
                                GameBoardEvent.ChangeRoundPart(RoundParts.CARD_PICK)
                            )

                        }
                        isRolling = false
                    }, isRolling = isRolling)

                }
                Spacer(modifier = Modifier.height(32.dp))
                Box(modifier = Modifier.fillMaxHeight(0.5f)) {


                    this@Column.AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = (userChoice == -1),
                        enter = fadeIn() + scaleIn(animationSpec = tween(500)),
                        exit = fadeOut() + scaleOut(animationSpec = tween(500))
                    ) {
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Choose One",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = diceTextColor,
                                    fontSize = 70.sp
                                ),
                            )
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                                for (i in rollerItems) {
                                    Text(
                                        text = i.toString(),
                                        modifier = Modifier.bounceClick(isEnabled = !clickedTheButton, onAnimationFinished = {
                                            userChoice = i
                                            clickedTheButton = true
                                            isRolling = true
                                        }),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = strongAttackSpecialColor,
                                            fontSize = 100.sp
                                        )
                                    )
                                }

                            }
                        }
                    }
                    this@Column.AnimatedVisibility(
                        visible = (userChoice != -1 && !isRolling),
                        enter = fadeIn() + scaleIn(animationSpec = tween(500)),
                        exit = fadeOut() + scaleOut(animationSpec = tween(500))
                    ) {
                        val text = if (isUserWin) "You \n\n\n Will Start" else "Rival \n\n\n Will Start"

                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = strongAttackSpecialColor,
                                fontSize = 64.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
    }
}
