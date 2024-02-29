package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.RoundParts

sealed class GameBoardEvent {
    data class ChangePlayerTurn(val isPlaying:Boolean):GameBoardEvent()
    data class NewCard(val cardId:Int, val isClose:Boolean = false):GameBoardEvent()

//    data class ChangePlayerCards(val cards:List<Card>):GameBoardEvent()
//    data class ChangeAICards(val cards:List<Card>):GameBoardEvent()
    data class ChangePlayerLuckForRetry(val luck:Int):GameBoardEvent()
    data class ChangeLuckForNewCards(val luck:Int):GameBoardEvent()
    data class ChangePlayerLuckForSpecialCard(val oldCardId:Int,val luck:Int):GameBoardEvent()
    //data class GetNewCardsForPick(val isNewCards:Boolean):GameBoardEvent()
    data class RetryLuckCount(val count:Int):GameBoardEvent()

    data class ChangeClosedCardCount(val count:Int):GameBoardEvent()
    data class ChangePlayerStats(val stats:Stats):GameBoardEvent()
    data class ChangeAIStats(val stats:Stats):GameBoardEvent()
    data class ChangeRoundPart(val round:RoundParts):GameBoardEvent()
    class ShowTurnText(val show: Boolean = false) : GameBoardEvent()
}