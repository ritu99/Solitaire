package Utils

import GUI.Display
import Models.Rank
import Models.Suit

/**
 * Created by Ritvik on 9/29/2019.
 */

object CardDisplayHelper{
    private val cardToDisplay : MutableMap<Rank, String> = mutableMapOf()
    private const val two   = " _ _ _ _\n|2*     |\n|   *   |\n|       |\n|   *   |\n|     2*|\n|_ _ _ _|\n"
    private const val three = " _ _ _ _\n|3*     |\n|   *   |\n|       |\n|  * *  |\n|     3*|\n|_ _ _ _|\n"
    private const val four  = " _ _ _ _\n|4*     |\n|  * *  |\n|       |\n|  * *  |\n|     4*|\n|_ _ _ _|\n"
    private const val five  = " _ _ _ _\n|5*     |\n|  * *  |\n|   *   |\n|  * *  |\n|     5*|\n|_ _ _ _|\n"
    private const val six   = " _ _ _ _\n|6*     |\n|  * *  |\n|  * *  |\n|  * *  |\n|     6*|\n|_ _ _ _|\n"
    private const val seven = " _ _ _ _\n|7*     |\n|  * *  |\n| * * * |\n|  * *  |\n|     7*|\n|_ _ _ _|\n"
    private const val eight = " _ _ _ _\n|8*     |\n| * * * |\n| *   * |\n| * * * |\n|     8*|\n|_ _ _ _|\n"
    private const val nine  = " _ _ _ _\n|9*     |\n| * * * |\n| * * * |\n| * * * |\n|     9*|\n|_ _ _ _|\n"
    private const val ten   = " _ _ _ _\n|10*    |\n| * * * |\n|* * * *|\n| * * * |\n|    10*|\n|_ _ _ _|\n"
    private const val jack  = " _ _ _ _\n|J*     |\n|    *  |\n|  * *  |\n|  ***  |\n|     J*|\n|_ _ _ _|\n"
    private const val queen = " _ _ _ _\n|Q*     |\n| * * * |\n| *   * |\n| * * **|\n|     Q*|\n|_ _ _ _|\n"
    private const val king  = " _ _ _ _\n|K*     |\n|  * *  |\n|  **   |\n|  * *  |\n|     K*|\n|_ _ _ _|\n"
    private const val ace   = " _ _ _ _\n|A*     |\n|  ***  |\n| ***** |\n| *   * |\n|     A*|\n|_ _ _ _|\n"
    private const val blank = " _ _ _ _\n|       |\n|       |\n|       |\n|       |\n|       |\n|_ _ _ _|\n"
    const val cardWidth = 9
    const val cardHeight = 7

    var easyRead = false

    init {
        cardToDisplay[Rank.TWO]   = two
        cardToDisplay[Rank.THREE] = three
        cardToDisplay[Rank.FOUR]  = four
        cardToDisplay[Rank.FIVE]  = five
        cardToDisplay[Rank.SIX]   = six
        cardToDisplay[Rank.SEVEN] = seven
        cardToDisplay[Rank.EIGHT] = eight
        cardToDisplay[Rank.NINE]  = nine
        cardToDisplay[Rank.TEN]   = ten
        cardToDisplay[Rank.JACK]  = jack
        cardToDisplay[Rank.QUEEN] = queen
        cardToDisplay[Rank.KING]  = king
        cardToDisplay[Rank.ACE]   = ace
    }

    fun getCard(suit: Suit, rank: Rank) : Display{
        return Display.fromString(unicodeCard(suit, rank))
    }

    private fun unicodeCard(suit: Suit, rank: Rank): String{
        return cardToDisplay.getValue(rank).replaceFirst("*", if (easyRead) suit.toEasyString() else suit.toString())
                .reversed().replaceFirst("*", if (easyRead) suit.toEasyString() else suit.toString()).reversed()
    }

    fun getBlankCard() : Display{
        return Display.fromString(blank)
    }

    fun getTopOfBlankCard() : Display{
        return getBlankCard().subset(0, 2)
    }

    fun getTopOfCard(suit: Suit, rank: Rank) : Display{
        return getCard(suit, rank).subset(0, 2)
    }
}