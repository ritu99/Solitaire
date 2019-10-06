package Models

import GUI.Display
import Utils.CardDisplayHelper

/**
 * Created by Ritvik on 9/29/2019.
 */
data class Card (val suit: Suit, val rank: Rank){
    val display : Display = CardDisplayHelper.getCard(suit, rank)
    val topDisplay : Display = CardDisplayHelper.getTopOfCard(suit, rank)
}
