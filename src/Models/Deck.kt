package Models

import Utils.CardDisplayHelper

/**
 * Created by Ritvik on 9/29/2019.
 * A deck is represented as a list with index 0 being the bottom
 * and deck.size being the top
 */
class Deck private constructor(){
    private val deck : MutableList<Card> = mutableListOf<Card>()

    val display
        get() = CardDisplayHelper.getBlankCard()

    init{
        for(rank in Rank.values())
            for(suite in Suit.values())
                deck.add(Card(suite, rank))
        shuffle()
    }

    val topCardDisplay
        get() =
            {
                if(deck.size == 0) CardDisplayHelper.getBlankCard()
                else CardDisplayHelper.getCard(deck.last().suit, deck.last().rank)
            }

    fun shuffle(){
        deck.shuffle()
    }

    fun drawCard() : Card{
        return deck.removeAt(deck.lastIndex)
    }

    fun isEmpty() : Boolean{
        return deck.isEmpty()
    }

    fun emptyDeck(){
        deck.clear()
    }

    fun topCard() : Card{
        return deck.last()
    }

    fun addCardToTop(card: Card){
        deck.add(card)
    }

    fun addCardToBottom(card: Card){
        deck.add(0, card)
    }

    fun removeCards(vararg cards: Card) : Boolean {
        return deck.removeAll(cards)
    }

    companion object {
        fun createDeck() : Deck{
            return Deck()
        }

        fun createEmptyDeck() : Deck{
            val deck = Deck()
            deck.emptyDeck()
            return deck;
        }
    }
}
