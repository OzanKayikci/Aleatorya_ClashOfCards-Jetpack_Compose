package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.pick_card_stage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.pick_card_stage.AISide
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.pick_card_stage.PlayerSide

@Composable
fun PickCardsStage (
    modifier: Modifier = Modifier,
    aiDeck:List<Card>,
    playerDeck:List<Card>,
    isPlayerTurn:Boolean,
    cardId:Int,
    playerStats: Stats,
    aiStats:Stats,
    navigateToCardPick:()->Unit
    ){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AISide(
            modifier = Modifier.weight(20f),
            deck = aiDeck,
            playerTurn = isPlayerTurn,
            newCardId = cardId,
            stats= aiStats
        )
        Box(modifier = Modifier.weight(0.5f))
        /** player area*/
        /** player area*/
        PlayerSide(
            modifier = Modifier.weight(20f),
            deck = playerDeck,
            playerTurn = isPlayerTurn,
            onClick =  navigateToCardPick ,
            newCardId = cardId,
            stats = playerStats
        )
    }
}