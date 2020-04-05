package com.example.android.kotlinscratches
import java.util.*

public class KotlinDraft {
    fun feedTheFish() {
        val day = randomDay()
        val food = "pellets"
        println ("Today is $day and the fish eat $food")
    }

    fun randomDay() : String {
        val week = arrayOf ("Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday", "Sunday")
        return week[Random().nextInt(week.size)]
    }
}

fun main(args: Array<String>) {
    // val testclass = KotlinDraft()
    // testclass.feedTheFish()
}
