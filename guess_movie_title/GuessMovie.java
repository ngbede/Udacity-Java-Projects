import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
class GuessMovie{
    private String[] loadFile(){ // This Function loads the movie titles from a text file into an array
        String  movieNames[]; // an array to load the movie names into
        movieNames = new String[25]; // Syntax var_name = new type[length_of_array]
        int i = 0;
        File movieFolder = new File("movie names.txt"); // movieFolder object
        try{
        Scanner fileScanner = new Scanner(movieFolder);
        while (fileScanner.hasNextLine()){ // We load each movie title into the movieNames array
            String movie = fileScanner.nextLine();
            movieNames[i] = movie;
            i++; 
        }
        fileScanner.close();
        }catch(FileNotFoundException exception){ // If file not found please check the directory you are in
            System.out.println("The file was not found! Please check the directory.");
        }
        return movieNames; // We return the movie names as an array
    }

    private String pickRandomMovie(){ // Picks a random movie title from the movieList array
        double movieIndex;
        movieIndex = Math.random() * 25; // We multiply by 25 since we have 25 movie titles in the movie text file
        String movieList[] = loadFile(); // loading the movie titles into an array now
        String movie = movieList[(int) movieIndex]; // picking a random movie
        return movie;
    }
    private boolean stringPresent(String bigGuy, String smallGuy){ // This function checks if a given string is contained in another
        boolean smallGuyPresent = bigGuy.contains(smallGuy.toLowerCase()); 
        return smallGuyPresent; // returns true or false      
    }
    // The function gets the corresponding index location of a string contained in another string
    private int indexLocation(String string1,String string2){
        int indexLoc = string1.indexOf(string2);
        return indexLoc;
    }
    // this functon replaces a string in an index location with another string
    private StringBuffer replaceString(StringBuffer bufferString,String rep,int idx){
        bufferString = bufferString.replace(idx, idx+1, rep);
        return bufferString;
    }
    // The function simulates the guessing game
    public void guessGame(){
        String movieToGuess = pickRandomMovie(); // getting the random movie selected
        StringBuffer movieToGuessBuffer = new StringBuffer(movieToGuess);
        StringBuffer missedWords = new StringBuffer(" "); // the missed word counter
        boolean continueLooping = true,retval,equal; // declaring some boolean values to be used later on
        char[] movieStatus = movieToGuess.toCharArray();  // We are making a character array of the movie to guess. Move to for loop
        int movieLength = movieToGuess.length(); // length of the movie String
        int wrongLetters = 0; // counter starts at 0
        Scanner scan = new Scanner(System.in);
        // So as to set each corresponding index location value to "-" 
        for(int i = 0;i<movieLength;i++){
            movieStatus[i] = '-';
        }
        String movieSyntax = new String(movieStatus); // converting the character array into a string
        StringBuffer bufferSyntax = new StringBuffer(movieSyntax);
        while(continueLooping){ // this loop will run as long as continueLooping set to true
            System.out.println("\nYou are guessing: " + bufferSyntax);
            System.out.println("You have guessed (" + wrongLetters + ") wrong letters:" + missedWords);
            System.out.println("Guess a letter: ");
            try{ // Using this try block to handle errors
            Character userInput = scan.nextLine().charAt(0); // collecting user input
            String userString = userInput.toString().toLowerCase();// converting user Input to a string
            retval = stringPresent(movieToGuess, userString); // checks if the user string input is present in the movie title
            if(retval){ // if present
                int indx = indexLocation(movieToGuess,userString); // grabs the index location of that string in the movie title
                bufferSyntax = replaceString(bufferSyntax, userString, indx); // replaces "-" with the corresponding string value
                movieToGuess = movieToGuess.replaceFirst(userString, "~"); // changing the values of movie to guess
                equal = movieToGuessBuffer.toString().equalsIgnoreCase(bufferSyntax.toString()); // if the users guess is equal to movie title
                if(equal){ // Enter this block
                    System.out.println("\nYOU WIN!"); // print a victory message
                    System.out.println("You guessed \'" + bufferSyntax.toString().toUpperCase() + "\' CORRECTLY");
                    continueLooping = false; // and terminate the loop
                }
            }
            else{ // else if user string not present
                missedWords.append(userString+","); // append the wrongly guessed letters to the missedWords variable
                wrongLetters++;
                if(wrongLetters == 10){ // if you guess 10 words incorrectly
                    System.out.println("\nSorry you have used up all your guesses.\nGood luck next time!"); 
                    continueLooping = false; // Game Over!
                }
            }
        }catch(Exception exception){ // catch all errors
            System.out.println("Invalid Input!\nPlease input an appropriate word."); // and print user friendly message
        }
        }
        scan.close(); // close scanner object 
    }
}