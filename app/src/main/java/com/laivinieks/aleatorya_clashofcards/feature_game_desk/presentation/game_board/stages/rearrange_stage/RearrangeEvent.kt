package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage

import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.StatsForBattle

sealed class RearrangeEvent {

    data class GetPlayerCards(val cards:List<Card>):RearrangeEvent()
    data class GetAiCards(val cards:List<Card>):RearrangeEvent()
    data class ChangeTotalStats(val stats:Stats ):RearrangeEvent()
    data class ChangeBattleStats(val battle: StatsForBattle):RearrangeEvent()
    data object OpenPickCardDialog:RearrangeEvent()
    data class SelectedEnemyCard(val card: Card?):RearrangeEvent()
    data class  ChangePlayerTurn(val isPlayerTurn:Boolean):RearrangeEvent()
    data class ShowNextRowAnim(val row:RowByCardType):RearrangeEvent()
}

enum class RowByCardType{
    DEFENCE,
    WARRIOR,
    ARCHER,
    MAGE,
    ABILITY
}