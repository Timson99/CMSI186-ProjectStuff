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
     *   Constructor that calls the constructor of superclass: java.lang.Object
     */ 
    public DynamicChangeMaker() {
        super();
    }
    
   /** 
     *   Method that checks for bad data.
     *   If all data is acceptable, this method runs evaluateTupleTable() with the same parameters
     *   @see evaluateTupleTable()
     *   @param  coins  int arrayof coin denominations
     *   @param  target int representing target value
     *   @return Tuple  result of evaluate Tuple Table. If parameters contain bad data, returns an Impossible Tuple
     */
    public static Tuple makeChangeWithDynamicProgramming( int[] coins, int target ) {
        boolean willRun = true;
        if( coins.length <= 1 ) {
            System.out.println("BAD DATA: Too few denominations");
        }
        outer: for( int x = 0; x < coins.length; x++ ) {
                    if( target < 1 || coins[x] < 1 ) {
                        System.out.println("BAD DATA: Can't have zero/negative target/demoninations");
                        willRun = false;
                        break;
                    }
                    for( int y = 0; y < coins.length; y++ ) {
                        if( x != y && coins[x] == coins[y] ) {
                            System.out.println("BAD DATA: No Doubles Allowed");
                            willRun = false;
                            break outer;
                        }
                    }
                }
        if( willRun ) {
            return evaluateTupleTable( coins, target );
        }
        return Tuple.IMPOSSIBLE; 
    }
    
   /** 
     *   Method that runs the optimal change algorithm on valid coins and target parameters
     *   @param  coins  int array of coin denominations
     *   @param  target int representing target value
     *   @return  Tuple  optimal number of each coin denomination, stored as a Tuple
     */
    public static Tuple evaluateTupleTable( int[] coins, int target ) {
        Tuple[][] tupleTable = new Tuple[coins.length][target + 1];
        for( int i=0; i < tupleTable.length; i++ ) { 
            for( int j = 0; j < target + 1; j++ ) {
                if( j == 0 ) {
                    tupleTable[i][j] = new Tuple( coins.length );
                    continue;
                }
                if( j/coins[i] < 1 || j < coins[i] || tupleTable[i][j-coins[i]].isImpossible() ) { 
                    if( i > 0  && !tupleTable[i-1][j].isImpossible() ){
                       tupleTable[i][j] = tupleTable[i-1][j];
                    }
                    else {
                       tupleTable[i][j] = Tuple.IMPOSSIBLE;
                    }
                }
                else { 
                    tupleTable[i][j] = new Tuple( coins.length );
                    tupleTable[i][j].setElement( i , 1 );
                    Tuple temp = tupleTable[i][j].add( tupleTable[i][j-coins[i]] );
                    if( i != 0 && !tupleTable[i-1][j].isImpossible() && temp.total() > tupleTable[i-1][j].total() ) {
                        tupleTable[i][j] = tupleTable[i-1][j];
                    }
                    else {
                        tupleTable[i][j] = temp;
                    }
                }
            }
        } 
        return tupleTable[ coins.length - 1 ][target];
    }
    
   /**
     *  main method to handle command line arguments.
     * Takes denominations in the '#,#,#,#,...' format as arg[0]
     * Takes target as arg[1]
     * Program checks to make sure there is only two arguments and that the arguments are convertible to int values
     * After checking for argument validity, the denominations are turned into an int array and the target becomes an int
     * These values are passed to method makeChangeWithDynamicProgramming() to be further validated and optimized
     * @see makeChangeWithDynamicProgramming()
     */
    public static void main( String args[] ) {'
        String[] denominations;
        int[] intArr = new int[0];
        int target = 0;
        try {
            if( args.length != 2 ) {
                throw new NumberFormatException();
            }
            denominations = args[0].split(",");
            intArr = new int[denominations.length];
            for(int x = 0; x < denominations.length; x++){
                intArr[x] = Integer.parseInt( denominations[x] );
            }
            target = Integer.parseInt( args[1] );
            System.out.print("Created demominations ");
            for( int z : intArr ) {
                System.out.print(" " + z + " ");
            }
            System.out.println(" with target " + target);
        }
        catch( NumberFormatException nfe ) {
            System.out.println("BAD DATA: Invalid Digits");
            System.out.println( nfe.toString() );
            System.exit(0);
        }
        System.out.print( makeChangeWithDynamicProgramming( intArr , target ) );
    }
 }