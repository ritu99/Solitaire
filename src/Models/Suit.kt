package Models

/**
 * Created by Ritvik on 9/29/2019.
 */
enum class Suit (val stringRepresentation: String, val color: Color){
    HEARTS ("\u2661",   Color.RED),
    SPADES ("\u2660",   Color.BLACK),
    CLUBS ("\u2663",    Color.BLACK),
    DIAMONDS ("\u2662", Color.RED);

    override fun toString() : String{
        return stringRepresentation
    }

    fun toEasyString() : String{
        return when(this){
            HEARTS -> "H"
            SPADES -> "S"
            CLUBS -> "C"
            DIAMONDS -> "D"
        }
    }

    enum class Color{
        RED,
        BLACK
    }
}
