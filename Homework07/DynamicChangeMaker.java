/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ChangeMaker.java
 *  Purpose       :  Make Change
 *  @author       :  T. Herrmann
 *  Date written  :  2018-04-12
 *  Description   :  Class to make change
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-04-12 T. Herrmann  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.util.*;
public class DynamicChangeMaker {
    
   /** 
     *   Constructor takes a string and assigns it to the internal storage as a ArrayList of Integers called digitStorage,
     *   checks for a sign character and handles that accordingly;  it then checks to see if it's all valid digits,
     *   sets isNegative and storageSize fields to appropriate values
     *  @param  value  String value to make into a BrobInt
     */
     
    public DynamicChangeMaker() {
        super();
    }
    
    public static Tuple makeChangeWithDynamicProgramming( int[] coins, int target ) {
        boolean willRun = true;
        outer: for(int x = 0; x < coins.length; x++) {
                    if(coins[x] == 0 || coins[x] < 0) {
                    System.out.println("BAD DATA");
                    willRun = false;
                    break outer;
                    }
                    for(int y = 0; y < coins.length; y++) {
                        if(x != y && coins[x] == coins[y]) {
                            System.out.println("BAD DATA");
                            willRun = false;
                            break outer;
                        }
                    }
                }
        if(willRun)
            return evaluateTupleTable(coins, target);
        
        return Tuple.IMPOSSIBLE; 
    }
    
    
    
    
    public static Tuple evaluateTupleTable( int[] coins, int target ) {
        Tuple[][] tupleTable = new Tuple[coins.length][target + 1];
        for(int i=0; i < tupleTable.length; i++) { 
            inside: for(int j = 0; j < target + 1; j++) {
                if(j == 0) {
                    tupleTable[i][j] = new Tuple(coins.length);
                    continue inside;
                }
                if(j/coins[i] < 1 || j < coins[i] || tupleTable[i][j-coins[i]].isImpossible()) { 
                    if(i > 0  && !tupleTable[i-1][j].isImpossible()){
                       tupleTable[i][j] = tupleTable[i-1][j];
                    }
                    else {
                       tupleTable[i][j] = Tuple.IMPOSSIBLE;
                    }
                }
                else {
                    tupleTable[i][j] = new Tuple(coins.length);
                    tupleTable[i][j].setElement(i,1);
                    Tuple temp = tupleTable[i][j].add(tupleTable[i][j-coins[i]]);
                    if(i != 0 && !tupleTable[i-1][j].isImpossible() && temp.total() > tupleTable[i-1][j].total()) {
                        tupleTable[i][j] = tupleTable[i-1][j];
                    }
                    else {
                        tupleTable[i][j] = temp;
                    }
                }
            }
        } 
        /*for(int i=0; i < tupleTable.length; i++) { 
            System.out.println("");
            for(int j = 0; j < target + 1; j++) {
                    System.out.print(tupleTable[i][j]);
                }
            } */
            
        
        return tupleTable[coins.length - 1][target];
    }
    
    
    public static void main(String args[]) {
        int[] intArr = new int[0];
        int target = 0;
        try {
            intArr = new int[args.length - 1];
            for(int x = 0; x<args.length - 1 ; x++){
                intArr[x] = Integer.parseInt(args[x]);
            }
            target = Integer.parseInt(args[args.length-1]);
            System.out.print("Created demominations ");
            for(int z : intArr) {
                System.out.print(" " + z + " ");
            }
            System.out.println(" with target " + target);
        }
        catch(NumberFormatException nfe) {
            System.out.println("BAD ARGUMENTS");
            System.out.println(nfe.toString());
            System.exit(0);
        }
        System.out.print(makeChangeWithDynamicProgramming(intArr,target));
    }
 }