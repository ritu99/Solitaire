package Models

import GUI.Display
import Utils.CardDisplayHelper

/**
 * Created by Ritvik on 9/29/2019.
 */
class Foundation(val suit: Suit){
    private var card: Card = Card(suit, Rank.ACE)

    val complete = card.rank == Rank.KING

    val display
        get() = {
            CardDisplayHelper.getCard(card.suit, card.rank)
        }

    fun addCard(card: Card){
        if(!canAddCard(card)) throw IllegalArgumentException()

        this.card = card
    }

    fun canAddCard(toAdd: Card): Boolean{
        return card.rank.getNext() == toAdd.rank && toAdd.suit == suit
    }
}
