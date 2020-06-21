import java.util.Scanner;
class Game{
    // Note private functions can only be assessed within its class
    private Scanner scan = new Scanner(System.in); // my Scanner object

    // This Function generates the random index call which the computer makes
    private int randomIndex(){
        double index = Math.random() * 3; 
        return (int) index; // We return a random index from 0 to 2
    }
    
    // This Function Generates the computers move
    private int computerMove(){
        int possibleMoves[] = {1,2,3}; // list contains possible moves 1 for Rock, 2 for Paper and 3 for scissor
        int idx = randomIndex(); // We are generating a random index
        int move; 
        move = possibleMoves[idx]; // this is the computers random move 
        return move;
    }
    // This Function validates players results it takes in both the player and computers score + the human name 
    private void result(int compScore,int humnScore,String playerName){
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if(compScore > humnScore){ // if the computers score is greater than the humans score, the computer wins
            System.out.println("You Lose!");
        }
        else if(humnScore > compScore){ // if the humans score is greater than the computers score, the human wins
            System.out.println("You Won!");
        }
        else if(compScore == humnScore){ // but if they both have same score line the game ends in a draw
            System.out.println("Draw!");
        }
        // The below messages print out the score lines to the user
        System.out.println("\t\tFINAL SCORE:");
        System.out.println("COMPUTER: " + compScore);
        System.out.println(playerName.toUpperCase() + ": " + humnScore);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // The logicScoreCounter compares players results and awards points based on their correspoinding inputs
    private int[] logicScoreCounter(int computersMove,int humansMove){
        int computerScore = 0; // initializing the scores of the computer to 0
        int humanScore = 0; // initializing the scores of the computer to 0
        /*
        1 -- ROCK
        2 -- PAPER
        3 -- SCISSOR
        */

        if((humansMove == 1) && (computersMove==3)){ // if you play rock and computer playes scissor
            System.out.println("Round won, computer went for Scissor!");
            humanScore += 1; // 1 point is added to your total score
        }
        else if ((humansMove == 1) && (computersMove == 2)){ // if you play rock and computer playes paper
            System.out.println("Round lost, computer went for Paper!");
            computerScore +=1; // 1 point is added to the computers total score
        }
        else if ((humansMove == 2) && (computersMove == 1)){ // if you play paper and computer plays rock
            System.out.println("Round won, computer went for Rock!");
            humanScore +=1; // 1 point is added to your total score
        }
        else if ((humansMove == 2) && (computersMove == 3)){ // if you play paper and computer plays scissor
            System.out.println("Round lost, computer went for Scissor!");
            computerScore +=1; // 1 point is added to the computers total score
        }
        else if ((humansMove == 3) && (computersMove == 2)){ // if you play scissor and computer plays paper
            System.out.println("Round won, computer went for Paper!");
            humanScore +=1; // 1 point is added to your total score
        }
        else if ((humansMove == 3) && (computersMove == 1)){ // if you play scissor and computer plays rock
            System.out.println("Round lost, computer went for Rock!");
            computerScore +=1; // 1 point is added to the computers total score
        }
        else if(humansMove == computersMove){ // if computer and human moves are the same, no point is awarded to either 
            System.out.println("Round ended in a draw.");
        }
        else{ // Warning, any invalid move you make will result in the computer being awarded points for that round
            System.out.println("Invalid Move!");
            computerScore +=1; // 1 point is added to the computers total score
        }
        int points[] = {computerScore,humanScore}; // point for that round is contained in a list
        return points; // We return the round points
    }

    // The gameSimulator simulates the game for a particular number of rounds and gives result of match when all rounds are completed
    private void gameSimulator(int numberOfRounds, String usersName){
        // initializing the scores and moves of both computer and human
        int compsTotalScore = 0;
        int humansTotalScore = 0;
        int compsMove,myMove;

        // printing out instructions of the game to the user
        System.out.println("\n Rules:\n~~~ Input 1 for ROCK,\n~~~ Input 2 for PAPER\n~~~ Input 3 for Scissor");
        System.out.println("Any other input apart from the above will result in you losing the round to the computer.\n GOOD LUCK!!!\n");

        // We loop through each round and perform the below actions
        for(int round = 0;round < numberOfRounds;round++){

        compsMove = computerMove(); // computer making moves at each round
        // Printing out some info to the screen
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\tRound " + (round + 1));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Make a Move:"); // Prompting user to make a move
        myMove = scan.nextInt(); // usersmove is stored in myMove variable
        
        int pointList[] = logicScoreCounter(compsMove, myMove); // We now pass both moves in the logicScoreCounter and award point

        compsTotalScore += pointList[0]; // computers points are accumulated into its total score variable
        humansTotalScore += pointList[1]; // humans points are accumulated into its total score variable
    }
    // After all the rounds are done we pass in the total scores and the name of the user into result function to generate a result 
   result(compsTotalScore, humansTotalScore, usersName); 

   }
   public void startGame(){
       int numRounds; // How many rounds will the user like to play
       System.out.println("Welcome to Rock, Paper Scissor Console Game!!!\n");
       // We First ask for the Users name
       System.out.println("Please Enter your name: ");
       String name = scan.nextLine();
       
       // We now ask for the number of rounds they will like to play
       System.out.println("How many rounds will you like to play: ");
       numRounds = scan.nextInt();

       gameSimulator(numRounds,name); // We now simulate the game via the gameSimulator function

       scan.close(); // Always remember to close your scanner

   }

}