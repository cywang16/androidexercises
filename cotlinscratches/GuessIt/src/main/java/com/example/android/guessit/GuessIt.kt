package com.example.android.guessit

import java.util.*
import kotlin.random.Random

fun main() {
    // get the computer's number between 1 and 10
    var computerNumber: Int = Random.nextInt(10)
    val myScanner = Scanner(System.`in`)
    println("I'm thinking of a number between 1 and 10 using kotlin.")

    // get your guess
    println("What do you think it is?")
    var yourGuess: Int = myScanner.nextInt()

    var result = if (yourGuess == computerNumber) {
        "You got it."
    } else if (yourGuess < computerNumber) {
        "You are too low!! My number was " + computerNumber
    } else {
        "You are too high !! My number was " + computerNumber
    }

    println(result)
}

public class GuessIt {
}