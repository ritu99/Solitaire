package Models

/**
 * Created by Ritvik on 9/29/2019.
 */
enum class Rank (private val stringRepresentation : String, private val position : Int){
    ACE ("A", 0),
    TWO ("2", 1),
    THREE ("3", 2),
    FOUR ("4", 3),
    FIVE ("5", 4),
    SIX ("6", 5),
    SEVEN ("7", 6),
    EIGHT ("8", 7),
    NINE ("9", 8),
    TEN ("10", 9),
    JACK ("J", 10),
    QUEEN ("Q", 11),
    KING ("K", 12);

    override fun toString(): String {
        return stringRepresentation
    }

    fun hasNext(): Boolean{
        return position != 12
    }

    fun getNext(): Rank{
        return Rank.values()[position + 1]
    }
}
