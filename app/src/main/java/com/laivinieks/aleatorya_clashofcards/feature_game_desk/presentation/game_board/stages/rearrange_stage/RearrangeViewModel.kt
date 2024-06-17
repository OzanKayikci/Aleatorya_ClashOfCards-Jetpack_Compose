package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board.stages.rearrange_stage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.StatsForBattle
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.rearrange_part.DestroyPlayerCard
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.rearrange_part.RemoveCardFromRearrangeCards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.rearrange_part.SplitCards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.AbilityType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RearrangeViewModel() : ViewModel() {

    /** player cards*/
    private val _playerCards = mutableStateOf<RearrangeCards>(RearrangeCards())
    val playerCards: MutableState<RearrangeCards> = _playerCards

    private val _aiCards = mutableStateOf<RearrangeCards>(RearrangeCards())
    val aiCards: MutableState<RearrangeCards> = _aiCards


    /**stats*/
    private val _playerBattleStats = mutableStateOf<StatsForBattle>(StatsForBattle())
    val playerBattleStats: MutableState<StatsForBattle> = _playerBattleStats
    private val _playerTotalStats = mutableStateOf<Stats>(Stats())
    val playerTotalStats: MutableState<Stats> = _playerTotalStats

    private val _aiBattleStats = mutableStateOf<StatsForBattle>(StatsForBattle())
    val aiBattleStats: MutableState<StatsForBattle> = _aiBattleStats
    private val _aiTotalStats = mutableStateOf(Stats())
    val aiTotalStats: MutableState<Stats> = _aiTotalStats

    private val _isPlayerTurn = mutableStateOf<Boolean>(true)
    val isPlayerTurn: MutableState<Boolean> = _isPlayerTurn

    //on start animations
    private val _animatePlayerCards = mutableStateOf<Boolean?>(null)
    val animatePlayerCards: MutableState<Boolean?> = _animatePlayerCards

    // use ability card in dialog part
    private val _isDialogOpen = mutableStateOf(false)
    val isDialogOpen: MutableState<Boolean> = _isDialogOpen

    private val _selectedAbilityCard = mutableStateOf<Card?>(null)
    val selectedAbilityCard: MutableState<Card?> = _selectedAbilityCard

    private val _destroyableCards = mutableStateOf<List<Card>?>(emptyList())
    val destroyableCards: MutableState<List<Card>?> = _destroyableCards

    var isStart = mutableStateOf(true)

    var isPlayerAbilityCardsCompleted = mutableStateOf(false)
    var isAiAbilityCardsCompleted = mutableStateOf(false)

    private val _startedRowAnimationForPlayer = mutableStateOf<List<Boolean>>(listOf(false, false, false, false, false))
    val startToRowAnimationForPlayer: MutableState<List<Boolean>> = _startedRowAnimationForPlayer

    private val _startedRowAnimationForAI = mutableStateOf<List<Boolean>>(listOf(false, false, false, false, false))
    val startToRowAnimationForAI: MutableState<List<Boolean>> = _startedRowAnimationForAI
    fun onEvent(event: RearrangeEvent) {
        when (event) {

            is RearrangeEvent.GetAiCards -> {
                viewModelScope.launch {
                    val cards = event.cards.map {
                        val card = if (it.isCardClose) it.copy(isCardClose = false) else it
                        card
                    }
                    val splitCards = SplitCards().invoke(cards)

                    _aiCards.value = splitCards

                    delay(400)
                    _animatePlayerCards.value = false
                }

            }

            is RearrangeEvent.GetPlayerCards -> {
                viewModelScope.launch {
                    val cards = event.cards.map {
                        val card = if (it.isCardClose) it.copy(isCardClose = false) else it
                        card
                    }
                    val splitCards = SplitCards().invoke(cards)

                    _playerCards.value = splitCards
                    delay(400)
                    _animatePlayerCards.value = true
                }

            }

            is RearrangeEvent.ChangeBattleStats -> TODO()
            is RearrangeEvent.ChangeTotalStats -> TODO()
            is RearrangeEvent.ChangePlayerTurn -> {
                _isPlayerTurn.value = event.isPlayerTurn
            }

            is RearrangeEvent.OpenPickCardDialog -> {
                viewModelScope.launch {
                    val deck = if (isPlayerTurn.value) playerCards.value else aiCards.value

                    val abilityCard = deck.abilityCards!!.let {
                        val cards = (it.closeRange + it.archer + it.mage + it.defence)
                        if (cards.isNotEmpty()) cards.random() else null
                    }
                    if (abilityCard == null) {
                        _isDialogOpen.value = false
                        if (isPlayerTurn.value) isPlayerAbilityCardsCompleted.value = true else isAiAbilityCardsCompleted.value = true
                        return@launch
                    }
                    _selectedAbilityCard.value = abilityCard
                    _destroyableCards.value = findDestroyableCards()
                    delay(2000)
                    _isDialogOpen.value = true
                    if (!isPlayerTurn.value) {
                        playAi()
                    }
                }

            }

            is RearrangeEvent.SelectedEnemyCard -> {
                val defenceDeck = if (isPlayerTurn.value) _aiCards else _playerCards
                val attackDeck = if (isPlayerTurn.value) _playerCards else _aiCards

                if (event.card != null) {

                    val newDec = RemoveCardFromRearrangeCards().invoke(event.card, defenceDeck.value)
                    defenceDeck.value = newDec
                }

                val removedAbilityDec = RemoveCardFromRearrangeCards().invoke(selectedAbilityCard.value!!, attackDeck.value)
                attackDeck.value = removedAbilityDec

            }

            is RearrangeEvent.ShowNextRowAnim -> {
                val list = if (animatePlayerCards.value!!) startToRowAnimationForPlayer.value.toMutableList() else startToRowAnimationForAI.value.toMutableList()

                val startedRowAnimation = if (animatePlayerCards.value!!) _startedRowAnimationForPlayer else _startedRowAnimationForAI
                when(event.row){
                    RowByCardType.ABILITY -> {
                        list[0] = true
                        startedRowAnimation.value = list
                    }
                    RowByCardType.MAGE -> {
                        list[1] = true
                        startedRowAnimation.value = list
                    }

                    RowByCardType.ARCHER ->{
                        list[2] = true
                        startedRowAnimation.value = list
                    }
                    RowByCardType.WARRIOR -> {
                        list[3] = true
                        startedRowAnimation.value = list
                    }
                    RowByCardType.DEFENCE -> {
                        list[4] = true
                        startedRowAnimation.value = list
                    }


                }
            }
        }
    }

    private fun playAi() {
        viewModelScope.launch {
            delay(2000)
            onEvent(RearrangeEvent.SelectedEnemyCard(DestroyPlayerCard().invoke(destroyableCards.value)))

        }
    }


    private fun findDestroyableCards(): List<Card>? {
        val deck = if (isPlayerTurn.value) aiCards.value else playerCards.value
        return when (selectedAbilityCard.value
            ?.type
            ?.abilityType) {
            AbilityType.BLOCK_DEFENCE -> {
                return deck.defenceCards!!.archerDefence + deck.defenceCards.mageDefence + deck.defenceCards.mageDefence
            }

            AbilityType.BLOCK_WARRIOR -> {
                return deck.fighterCards
            }

            AbilityType.BLOCK_ARCHER -> {
                return deck.archerCards
            }

            AbilityType.BLOCK_WIZARD -> {
                return deck.mageCards
            }

            null -> {
                return null
            }
        }
    }


}



