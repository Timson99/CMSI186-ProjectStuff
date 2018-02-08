/** @StringStuff.java  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File          :  HighRoll.java
 *  Purpose       :  
 *  Date          :  2018-02-01 
 *  Author        :  Timothy Herrmann
 *  Description   :  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  ------------------
 *    Ver      Date     Modified by:  Description for change/modification
 *   -----  ----------  ------------  -------------------------------------------------------------------
 *   1.0.0  2018-02-01  T. Herrmann   Initial Version
 *   1.0.1  2017-02-08  T. Herrmann  Version for Submission
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner; 

public class HighRoll{
    
    public static void main(String args[]) {
        
        Scanner keyboard = new Scanner(System.in);
        BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
        int highScore = 0;
        int currentScore = 0;
        
        System.out.println("\nHow many dice?");
        int inputCount = keyboard.nextInt(); 
        System.out.println("How many sides?");
        int inputSides = keyboard.nextInt();
        DiceSet ds = new DiceSet(inputCount, inputSides);
       

        while(true) {
            System.out.println("\nPLEASE ENTER THE NUMBER/CHARACTER OF YOUR PREFERED ACTION");
            System.out.println("***********************************************************\n");
            System.out.println("\t1): ROLL ALL THE DICE");
            System.out.println("\t2): ROLL A SINGLE DIE");
            System.out.println("\t3): CALCULATE THE SCORE FOR THIS SET");
            System.out.println("\t4): SAVE THIS SCORE AS HIGH SCORE");
            System.out.println("\t5): DISPLAY THE HIGH SCORE");
            System.out.println("\tQ): QUIT THE PROGRAM\n");
            
            String inputLine = null;
            try { 
                inputLine = input.readLine();
                if(inputLine.trim().equals("Q") || inputLine.trim().equals("q")) 
                    break;
                else if(inputLine.trim().equals("1")) {
                    ds.roll();
                    System.out.println(ds.toString());
                }
                else if(inputLine.trim().equals("2")) {
                    
                    System.out.println("Roll which dice?"); //Test boundaries
                    int diceNumber = keyboard.nextInt();
                    if(diceNumber < 1 || diceNumber > inputCount) 
                        System.out.println("Invalid number of dice");
                    else {
                    ds.rollIndividual((diceNumber - 1)); 
                    System.out.println(ds.toString());
                    }
                }
                else if(inputLine.trim().equals("3")) {
                    currentScore = ds.sum();
                    System.out.println(currentScore);
                }
                else if(inputLine.trim().equals("4")) {
                    highScore = currentScore; 
                    System.out.println("High Score: " + highScore);
                }
                else if(inputLine.trim().equals("5"))
                    System.out.println("High Score: " + highScore);
            
                else 
                    System.out.println("Input is not valid. Please try again.");
                }
                
            catch( IOException ioe ) {
                System.out.println( "Caught IOException" );
                }
                
        }
    }
}