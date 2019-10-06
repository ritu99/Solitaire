package Models

import GUI.Display
import Utils.CardDisplayHelper

/**
 * Created by Ritvik on 9/29/2019.
 */
class Column private constructor(val cards : MutableList<Card>) {
    var numberOfFaceUpCards = 1
        private set
    val numberOfFaceDownCards
            get() = cards.size - numberOfFaceUpCards

    val display
        get() = {
            var display = Display()
            for( card in 1..numberOfFaceDownCards)
                display.concatVertically(CardDisplayHelper.getTopOfBlankCard())

            for( card in 0 until numberOfFaceUpCards-1 )
                display.concatVertically(cards[numberOfFaceDownCards + card].topDisplay)

            if(cards.size == 0)
                display.concatVertically(CardDisplayHelper.getBlankCard())
            else
                display.concatVertically(cards.last().display)

            display
        }

    fun canAddCard(card: Card) : Boolean {
        if(numberOfFaceUpCards == 0) return true
        return cards.last().suit.color != card.suit.color
                && card.rank.hasNext()
                && card.rank.getNext() == cards.last().rank
    }

    fun addCard(card: Card){
        if(!canAddCard(card)) throw IllegalArgumentException()

        cards.add(card)
        numberOfFaceUpCards++
    }

    fun addCards(vararg cards: Card){
        for(card in cards)
            addCard(card)
    }

    fun faceUpCards(): List<Card>{
        return cards.subList(numberOfFaceDownCards, cards.size)
    }

    fun flipCard(){
        if(numberOfFaceDownCards == 0) return
        if(numberOfFaceUpCards > 0) return
        numberOfFaceUpCards = 1
    }

    fun isEmpty() : Boolean {
        return cards.size == 0
    }

    fun topCard() : Card{
        return cards.last()
    }

    fun bottomFaceUpCard() : Card{
        return cards[numberOfFaceDownCards]
    }

    fun takeCard() : Card{
        val card = cards.removeAt(cards.lastIndex)
        numberOfFaceUpCards--
        flipCard()
        return card
    }

    companion object {
        fun create(numberOfCards : Int, deck : Deck) : Column{
            val cards = mutableListOf<Card>()
            for(i in 0..numberOfCards)
                cards.add(deck.drawCard())
            return Column(cards)
        }
    }
}
