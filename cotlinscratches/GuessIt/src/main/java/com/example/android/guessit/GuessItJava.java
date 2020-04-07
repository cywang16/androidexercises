package com.example.android.guessit;

import java.util.Random;
import java.util.Scanner;

public class GuessItJava {
    public static void main(String args[]) {
        int computerNumber;
        int yourGuess;
        Random myRandom = new Random();
        Scanner myScanner = new Scanner(System.in);

        // get the computer's number between 1 and 10
        computerNumber = myRandom.nextInt(10) + 1;
        System.out.println("I'm thinking of a number between 1 and 10.");

        // get your guess
        System.out.println("What do you think it is?");
        yourGuess = myScanner.nextInt();

        // analyze guess and print results
        if (yourGuess == computerNumber) {
            // you got it
            System.out.println("You got it.");
        } else if (yourGuess < computerNumber) {
            // too low
            System.out.println("You are too low!! My number was " + computerNumber);
        } else {
            // too high
            System.out.println("You are too high !! My number was " + computerNumber);
        }
    }
}
