package GUI

import Models.*

/**
 * Created by Ritvik on 9/29/2019.
 */

class BoardPresenter{
    val foundations: Map<Suit, Foundation> =
            mapOf(
                    Pair(Suit.HEARTS, Foundation(Suit.HEARTS)),
                    Pair(Suit.CLUBS, Foundation(Suit.CLUBS)),
                    Pair(Suit.DIAMONDS, Foundation(Suit.DIAMONDS)),
                    Pair(Suit.SPADES, Foundation(Suit.SPADES))
            )

    var deck = Deck.createDeck().apply{
        removeCards(
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.SPADES, Rank.ACE),
                Card(Suit.CLUBS, Rank.ACE),
                Card(Suit.HEARTS, Rank.ACE));

    }
        private set
    var faceUpDeck = Deck.createEmptyDeck()
        private set

    val hasWon : Boolean = foundations.all{
        foundation -> foundation.value.complete
    }

    val columns: Array<Column> =
        Array(COLUMNS_ON_GAME_BOARD, { columnNumber ->
            Column.create(columnNumber, deck)
        })

    fun deckToColumn(columnNumber: Int) : Boolean{
        if(columnNumber >= COLUMNS_ON_GAME_BOARD) return false
        val cardToMove = faceUpDeck.topCard()
        if(!columns[columnNumber].canAddCard(cardToMove)) return false

        columns[columnNumber].addCard(cardToMove)
        faceUpDeck.drawCard()
        return true
    }

    fun deckToFoundation() : Boolean{
        val cardToMove = faceUpDeck.topCard()
        if(!foundations[cardToMove.suit]!!.canAddCard(cardToMove)) return false

        foundations[cardToMove.suit]!!.addCard(cardToMove)
        faceUpDeck.drawCard()
        return true
    }

    fun flipCard(){
        if(deck.isEmpty()) {
            deck = faceUpDeck
        }

        faceUpDeck.addCardToTop(deck.drawCard())
    }

    fun columnToFoundation(columnNumber: Int) : Boolean{
        val cardToMove = columns[columnNumber].topCard()
        if(!foundations[cardToMove.suit]!!.canAddCard(cardToMove)) return false
        if(columns[columnNumber].isEmpty()) return false

        foundations[cardToMove.suit]!!.addCard(columns[columnNumber].takeCard())

        return true
    }

    fun columnToColumn(fromColumn: Int, toColumn: Int) : Boolean{
        val cardToMove = columns[fromColumn].bottomFaceUpCard()

        if(columns[toColumn].canAddCard(cardToMove)){
            val numberCards = columns[fromColumn].numberOfFaceUpCards
            val cards = columns[fromColumn].faceUpCards()
            for(card in cards)
                columns[toColumn].addCard(card)
            for(i in 0 until numberCards)
                columns[fromColumn].takeCard()

            return true
        }

        return false
    }

    companion object{
        const val COLUMNS_ON_GAME_BOARD = 7
    }
}
