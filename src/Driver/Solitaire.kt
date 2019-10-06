package Driver

import GUI.BoardPresenter
import GUI.Display
import Models.Column
import Models.Foundation
import Models.Suit
import Utils.CardDisplayHelper
import java.util.*

/**
 * Created by Ritvik on 9/29/2019.
 */

val boardPresenter = BoardPresenter()
val input = Scanner(System.`in`);

fun main(args: Array<String>) {

    printDisplay()
    println("Welcome to Solitaire!")
    println("")
    printMenu()
    while(input.hasNext()){
        val option = input.next()[0]
        val validMove: Boolean =
                when(option){
                    'F', 'f' -> {moveToFoundation(); true}
                    'C', 'c' -> boardPresenter.deckToColumn(getColumnNumber())
                    'T', 't' -> boardPresenter.columnToFoundation(getColumnNumber())
                    'R', 'r' -> boardPresenter.columnToColumn(
                            getColumnNumber("From which column?"), getColumnNumber("To which column?"))
                    'D', 'd' -> {boardPresenter.flipCard(); true}
                    'Q' -> {quit(); true}
                    else -> false
                }
        clear()
        printDisplay()
        if(!validMove){
            println("That wasn't a valid move!")
        }
        printMenu()

        if(boardPresenter.hasWon){
            println("CONGRATULATIONS! You've won!")
        }
    }
}

fun clear(){
    println("\u001Bc")}

fun printDisplay(){
    println(createDisplay().toString())
}

fun quit(){
    println("See ya!")
    System.exit(0)
}

fun getColumnNumber(message: String = "Which column?") : Int{
    println(message)
    while(true){
        try{
            return Integer.parseInt(input.next())
        }catch(e : NumberFormatException){
            println("That isn't a number")
        }
    }
}

fun moveToFoundation(){
    if(boardPresenter.deckToFoundation())
    else
        println("That wasn't a valid move!")
}

fun printMenu(){
    println("Enter a command to continue")
    println("\t F - Move deck to a foundation")
    println("\t C - Move deck to a column")
    println("\t T - Move from column to foundation")
    println("\t R - Move from column to column")
    println("\t D - Flip a new card from the stack")
    println("\t Q - Quit")
}

fun createDisplay(): Display{
    val display = createFoundations(boardPresenter.foundations)

    val buffer = Display()
    buffer.setWidth(CardDisplayHelper.cardWidth)
    display.concatHorizontally(buffer)
    display.concatHorizontally(boardPresenter.faceUpDeck.topCardDisplay())
    display.concatHorizontally(boardPresenter.deck.display)
    display.concatVertically(createColumns(boardPresenter.columns))
    return display
}

fun createFoundations(foundations: Map<Suit, Foundation>) : Display{
    val display = Display()
    display.concatHorizontally(foundations[Suit.HEARTS]!!.display())
    display.concatHorizontally(foundations[Suit.SPADES]!!.display())
    display.concatHorizontally(foundations[Suit.DIAMONDS]!!.display())
    display.concatHorizontally(foundations[Suit.CLUBS]!!.display())

    return display
}

fun createColumns(columns: Array<Column>) : Display {
    val display = Display()
    for(columnNumber in 0 until columns.size){
        val column = Display.fromString("    $columnNumber    ")
        column.concatVertically(columns[columnNumber].display())
        display.concatHorizontally(column)
    }

    return display
}

