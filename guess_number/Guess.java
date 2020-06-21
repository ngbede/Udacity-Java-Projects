import java.util.Scanner;
class Guess{
    private Scanner scan = new Scanner(System.in); // scanner object used to collect user input
    private int randomNumber(){ // This is the function that generates random numbers between 1 and 100
        double number = Math.random() * 101;
        number += 1;
        return (int) number;
    }
    public void guessTheNumber(){ // This function performs the guess number simulation
        int numTrials = 10,usersGuess,trial,round; // declaring some useful variables
        int numToGuess = randomNumber(); // the number the user is meant to guess randomly being generated
        round = numTrials;

        // Prinitng some info to the screen
        System.out.println("I have randomly chosen a number between [1 , 100]");
        System.out.println("You have 10 Trials to guess the Number. Good Luck!");
        // Each player has 10 trials until they can get the appropriate answer
        for(trial = 0;trial<numTrials;trial++){
            System.out.println("\nYou have (" + round + ") guesses left!"); // Number of guesses left for the user
            usersGuess = scan.nextInt(); // prompts user for input
            if(usersGuess < numToGuess){ // if your guess is less than the actual number 
                System.out.println("It's bigger than " + usersGuess); // message gives hint
            }
            else if(usersGuess > numToGuess){ // if your guess is bigger than the actual number
                System.out.println("It's smaller than " + usersGuess); // Hint
            }
            else if(usersGuess == numToGuess){ // Once you get the actual value 
                System.out.println("CORRECT... You Win!"); // Victory message
                break; // Quit loop
            }
            round -=1; // next round
        }
        if((round) == 0){ // If you use all your trials this message is initiated
            System.out.println("\nSorry, Your Trials are finished. Try again next time.");
        }
        scan.close(); // close the scanner object
    }
}