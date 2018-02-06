/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Timothy Herrmann
 *  Date          :  2017-02-01
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-01  T. Herrmann    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      this.count = count;
      this.sides = sides;
      if(count < 1 || sides < MINIMUM_SIDES )
          throw new IllegalArgumentException(); 
      
      this.ds = new Die[ count ];
      
      for(int x = 0; x<count; x++) {
          this.ds[x] = new Die(sides);
      }
      
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int sum = 0;
      
      for(int x = 0; x<count; x++){
          sum += this.ds[x].getValue();
      }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   */
   public void roll() {
       for(int x = 0; x<count; x++){
          this.ds[x].roll();
      } 
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
        return ds[dieIndex].roll();

   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to get the value of
   * @throws IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String result = "";
      for(int x = 0; x < count; x++) {
          result = result + ds[x].toString() + "\n";
          
      }
      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) { //Change to be like instance method
      String result = "";
      for(int x = 0; x<ds.count; x++) {
          result = result + "[Sides: (" + ds.sides + ") Value: ("+ds.getIndividual(x)+")]\n"; 
      }
      return result;
   }

  /**
   * @return  true if this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
      return toString().equals(ds.toString());
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
        DiceSet x = new DiceSet(6,6);
        System.out.println(x.toString());
        System.out.println(x.sum());
        x.roll();
        System.out.println(x.toString());
        System.out.println(x.sum());
        DiceSet y = new DiceSet(6,6);
        System.out.println(DiceSet.toString(y));
        System.out.println(y.rollIndividual(3));
        System.out.println(y.toString());
        System.out.println(y.getIndividual(3));
        DiceSet w = new DiceSet(5,5);
        DiceSet z = new DiceSet(5,5);
        System.out.println(z.toString());
        System.out.println(w.toString());
        System.out.println(y.isIdentical(x));
        System.out.println(w.isIdentical(z));
   
   }

}