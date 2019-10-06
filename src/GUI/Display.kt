package GUI

import kotlin.math.max

/**
 * Created by Ritvik on 9/29/2019.
 */
class Display constructor(private var display : MutableList<String> = mutableListOf<String>()){

    val numberOfLines
        get() = display.size

    private var maxWidth = 0

    fun concatHorizontally(other: Display, seperator: String = " "){
        if(other.numberOfLines < numberOfLines)
            other.display.addAll(Array<String>(numberOfLines - other.numberOfLines, {""}))
        else
            display.addAll(Array<String>(other.numberOfLines - numberOfLines, {""}))

        equalizeStrings()
        other.equalizeStrings()

        display = display.zip(other.display, { a, b -> "$a$seperator$b" }).toMutableList()

        maxWidth += other.maxWidth + seperator.length
        equalizeStrings()
    }

    fun concatVertically(other: Display){
        display.addAll(other.display)
        maxWidth = max(maxWidth, other.maxWidth)
        equalizeStrings()
    }

    private fun equalizeStrings(){
        display.replaceAll { string -> string.padEnd(maxWidth, ' ') }
    }

    fun appendLine(line: String){
        display.add(line)
        maxWidth = max(maxWidth, line.length)
    }

    fun setWidth(width: Int){
        if(maxWidth > width) return
        maxWidth = width
        equalizeStrings()
    }

    override fun toString(): String{
        return display.joinToString("\n")
    }

    fun subset(start: Int, end: Int): Display{
        return Display(display.subList(start, end))
    }

    companion object {
        fun fromString(string: String) : Display{
            val display = Display()
            for(line in string.split("\n"))
                display.appendLine(line)
            return display
        }
    }
}