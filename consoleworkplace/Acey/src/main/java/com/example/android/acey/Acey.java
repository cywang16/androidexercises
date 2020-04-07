package com.example.android.acey;

import java.util.Random;

public class Acey
{
    private static void DisplayCard(int V, String C)
    {
        //  Specifies a card value and suit
        String cardLabel = "";
        String cardSuit = "";
        switch (V % 13 + 1)
        {
            case 1:
                cardLabel = "2";
                break;
            case 2:
                cardLabel = "3";
                break;
            case 3:
                cardLabel = "4";
                break;
            case 4:
                cardLabel = "5";
                break;
            case 5:
                cardLabel = "6";
                break;
            case 6:
                cardLabel = "7";
                break;
            case 7:
                cardLabel = "8";
                break;
            case 8:
                cardLabel = "9";
                break;
            case 9:
                cardLabel = "10";
                break;
            case 10:
                cardLabel = "Jack";
                break;
            case 11:
                cardLabel = "Queen";
                break;
            case 12:
                cardLabel = "King";
                break;
            case 13:
                cardLabel = "Ace";
                break;
        }
        switch ((int) (V / 13))
        {
            case 0:
                cardSuit = "Hearts";
                break;
            case 1:
                cardSuit = "Clubs";
                break;
            case 2:
                cardSuit = "Diamonds";
                break;
            case 3:
                cardSuit = "Spades";
                break;
        }
        System.out.println(C + " card: " + cardLabel + " of " + cardSuit);

    }

    public static void main(String[] args)
    {
        int cardLeft, cardMiddle, cardRight;
        int vLeft, vMiddle, vRight;
        int cardIndex;
        int cards[] = new int[52];
        int score, t1, t2, bet;
        boolean anotherTurn;

        System.out.println("Acey-Deucey\n");
        System.out.println("Two cards are dealt face up.");
        System.out.println("You bet whether the next card dealt has a value between the displayed cards.\n");
        //  Start new game loop
        do
        {
            System.out.println("New card deck shuffled");
            cards = NIntegers(52);
            score = 100;
            cardIndex = 0;
            anotherTurn = true;
            //  Start player turn loop
            do
            {
                cardLeft = cards[cardIndex];
                vLeft = cardLeft % 13 + 1;
                cardRight = cards[cardIndex + 1];
                vRight = cardRight % 13 + 1;
                cardMiddle = cards[cardIndex + 2];
                vMiddle = cardMiddle % 13 + 1;
                // swap left and right if left is larger
                if (vLeft > vRight)
                {
                    t1 = cardLeft;
                    t2 = vLeft;
                    cardLeft = cardRight;
                    vLeft = vRight;
                    cardRight = t1;
                    vRight = t2;
                }
                System.out.println("\nYou have " + score + " points.");
                DisplayCard(cardLeft, "First");
                DisplayCard(cardRight, "Second");
                cardIndex += 3;
                do
                {
                    bet = Keyin.inInt("There are " + (52 - cardIndex) + " cards remaining. What is your bet?");
                    if (bet > score)
                    {
                        System.out.println("You can only bet " + score);
                    }
                }
                while (bet > score);
                if (bet == 0)
                {
                    System.out.println("Not too sure of yourself, huh?");
                }
                DisplayCard(cardMiddle, "Next");
                if (vMiddle >= vLeft && vMiddle <= vRight)
                {
                    score += bet;
                    System.out.println("You win!!!");
                    if (bet == 0)
                    {
                        System.out.println("You should have bet something.");
                    }
                }
                else
                {
                    score -= bet;
                    System.out.println("Sorry, you lose.");
                    if (bet == 0) {
                        System.out.println("Good thing you didn't bet!");
                    }
                }
                if (score == 0)
                {
                    System.out.println("Sorry friend, you're out of points.");
                    anotherTurn = false;
                }
                if (cardIndex == 51)
                {
                    System.out.println("You're out of cards!");
                    System.out.println("You ended up with " + score + " points.");
                    anotherTurn = false;
                }

            }
            while (anotherTurn);
        }
        while (Keyin.inString("\nPlay again? (enter a y for yes)").equals("y"));
    }

    private static int[] NIntegers(int n)
    {
        /*
         *  Returns n randomly sorted integers 0 -> n-1
         */
        int nArray[] = new int[n];
        int temp, i, j;
        Random jRandom = new Random();
        for (i = 0; i < n; i++)
        {
            nArray[i] = i;
        }
        for (i = n; i >= 1; i--)
        {
            j = jRandom.nextInt(i);
            temp = nArray[j];
            nArray[j] = nArray[i - 1];
            nArray[i - 1] = temp;
        }
        return(nArray);
    }
}
