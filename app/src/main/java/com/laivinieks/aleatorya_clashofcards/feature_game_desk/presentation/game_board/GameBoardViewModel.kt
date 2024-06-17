package com.laivinieks.aleatorya_clashofcards.feature_game_desk.presentation.game_board

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Card
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.model.Stats
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.AILogic
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.GetLuckRate
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.use_case.GetRandomDeck
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.PowerRate
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.cards.Cards
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.data.util.CardType
import com.laivinieks.aleatorya_clashofcards.feature_game_desk.util.RoundParts

class GameDeskViewModel : ViewModel() {

    private val _playerCards = mutableStateOf<List<Card>>(emptyList())
    val playerCards: MutableState<List<Card>> = _playerCards

    private val _AICards = mutableStateOf<List<Card>>(emptyList())
    val AICards: MutableState<List<Card>> = _AICards

    private val _newCard = mutableStateOf<Card?>(null)
    val newCard: MutableState<Card?> = _newCard

    private val _isPlayerTurn = mutableStateOf<Boolean?>(true)
    val isPlayerTurn: MutableState<Boolean?> = _isPlayerTurn

    private val _newDeckForPick = mutableStateOf<List<Card>>(emptyList())
    val newDeckForPick: MutableState<List<Card>> = _newDeckForPick

    private val _remainingRetryLockCount = mutableIntStateOf(2)
    val remainingRetryLockCount: MutableIntState = _remainingRetryLockCount

    private val _remainingClosedCardCount = mutableIntStateOf(2)
    val remainingClosedCardCount: MutableIntState = _remainingClosedCardCount

    private val _playerStats = mutableStateOf(Stats())
    val playerStats: MutableState<Stats> = _playerStats

    private val _AIStats = mutableStateOf(Stats())
    val AIStats: MutableState<Stats> = _AIStats

    private val _playerLuckForNewDeck = mutableIntStateOf(0)
    val playerLuckForNewDeck: MutableState<Int> = _playerLuckForNewDeck

    private val _playerLuckForSpecialCard = mutableIntStateOf(0)
    val playerLuckForSpecialCard: MutableState<Int> = _playerLuckForSpecialCard


    private val _roundPart = mutableStateOf<RoundParts>(RoundParts.CHOOSE_STARTER)
    val roundPart: MutableState<RoundParts> = _roundPart

    private val _showTurnText = mutableStateOf(false)
    val showTurnText: MutableState<Boolean> = _showTurnText


    init {
        val playerCards =
            Cards.strongCards.shuffled().filter { it.type::class != CardType.Archer::class }.take(2) + Cards.masterSpecialCards.shuffled()
                .filter { it.type::class != CardType.Archer::class }.take(4) + Cards.midCards.shuffled()
                .filter { it.type::class != CardType.Archer::class }.take(2)
        val aiCards = Cards.strongCards.shuffled().take(2) + Cards.masterSpecialCards.shuffled().take(4) + Cards.midCards.shuffled().take(2)

        _playerCards.value = playerCards
        _AICards.value = aiCards

    }

    fun onEvent(event: GameBoardEvent) {
        when (event) {
            is GameBoardEvent.ChangeAIStats -> {
                _AIStats.value = event.stats
            }

            is GameBoardEvent.ChangeClosedCardCount -> {
                _remainingClosedCardCount.intValue = event.count
            }

            is GameBoardEvent.ChangeLuckForNewCards -> {
                _playerLuckForNewDeck.intValue = event.luck
                val luckRate = GetLuckRate().invoke(event.luck)
                _newDeckForPick.value = GetRandomDeck().invoke(luckRate)
            }

            is GameBoardEvent.ChangePlayerLuckForRetry -> TODO()
            is GameBoardEvent.ChangePlayerLuckForSpecialCard -> {

                val oldCard = newDeckForPick.value.filter { it.id == event.oldCardId }.first()
                val newCard = getNewCard(event.luck > 50, oldCard.powerRate!!)

                newCard?.let {
                    val cards = newDeckForPick.value.toMutableList()
                    cards[cards.indexOf(oldCard)] = it
                    _newDeckForPick.value = cards

                }
            }

            is GameBoardEvent.ChangePlayerStats -> {
                _playerStats.value = playerStats.value.copy(
                    attack = playerStats.value.attack + event.stats.attack,
                    defence = playerStats.value.defence + event.stats.defence
                )
            }

            is GameBoardEvent.ChangePlayerTurn -> {
                _isPlayerTurn.value = event.isPlaying
            }

            is GameBoardEvent.ChangeRoundPart -> {
                _roundPart.value = event.round
            }

            is GameBoardEvent.NewCard -> {
                var card = newDeckForPick.value.find { it.id == event.cardId }!!
                _newCard.value = if (card.type::class == CardType.Ability::class) card.copy(isCardClose = true) else card

                var cards = playerCards.value.toMutableList()
                newCard.value?.let { cards.add(it) }

                _playerCards.value = cards
                _playerLuckForNewDeck.intValue = 0

            }

            is GameBoardEvent.RetryLuckCount -> TODO()
            is GameBoardEvent.ShowTurnText -> {
                _showTurnText.value = event.show

            }
        }
    }

    private fun getNewCard(isLucky: Boolean, powerRate: PowerRate): Card? {

        if (!isLucky) {
            return Cards.midCards.random()
        }

        return when (powerRate) {
            PowerRate.legendary -> {
                Cards.masterSpecialCards.random()
            }

            PowerRate.epic -> {
                Cards.strongSpecialCards.random()
            }

            else -> {
                null
            }
        }
    }

    fun playAI() {
        _newCard.value = null

        var newAICard = AILogic().invoke(playerCards.value)
        newAICard = if (newAICard.type::class == CardType.Ability::class) newAICard.copy(isCardClose = true) else newAICard
        var cards = AICards.value.toMutableList()
        cards.add(newAICard)
        _AICards.value = cards

        _AIStats.value = AIStats.value.copy(
            attack = AIStats.value.attack + newAICard.power,
            defence = AIStats.value.defence + newAICard.defence
        )
    }

}