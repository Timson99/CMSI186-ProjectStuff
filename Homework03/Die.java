/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Timothy Herrmann
 *  Date          :  2017-02-01
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-01  Timothy Herrmann  Initial writing
 *  @version 1.0.1  2017-02-08  Timothy Herrmann  Version for Submission
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int value;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
       if(nSides < MINIMUM_SIDES)
           throw new IllegalArgumentException();
       
       sides = nSides;
       value = 1; //All dice are initially rolled to the value 1
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
      value = (1 + (int)(Math.random() * this.sides)); //Provides a random number between 1 and sides
      this.value = value;
      return value;
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return value;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public int setSides( int sides ) {
       if(sides < MINIMUM_SIDES)
           throw new IllegalArgumentException();
       
       this.sides = sides;
       return sides;
   }
   
   /** 
   * @return int The sides that are currently logged in the sides instance variable       
   */
   public int getSides() {
       return sides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      return "[Sides: ("+ this.sides + ") Value: ("+getValue()+")]"; //Value and sides
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
        return "[Sides: ("+d.getSides()+ ") Value: ("+d.getValue()+")]"; //Value and sides
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      Die d = null;
      try { d = new Die(1);}
      catch ( IllegalArgumentException iae ) { System.out.println("Too few sides"); }
      try { d = new Die(2);}
      catch ( IllegalArgumentException iae ) { System.out.println("Too few sides"); }
      try { d = new Die(3);}
      catch ( IllegalArgumentException iae ) { System.out.println("Too few sides"); }
      try { d = new Die(4);}
      catch ( IllegalArgumentException iae ) { System.out.println("Too few sides"); }
      System.out.println("Roll Test for 4 sided die");
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println(d.toString());
      System.out.println(Die.toString(d));
      System.out.println("Sides: " + d.getSides());
      System.out.println("Value: " + d.getValue());
      d.setSides(5);
      System.out.println("Roll Test for 5 sided die");
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println(d.toString());
      System.out.println(Die.toString(d));
      System.out.println("Sides: " + d.getSides());
      System.out.println("Value: " + d.getValue());
      d.setSides(6);
      System.out.println("Roll Test for 6 sided die");
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println(d.toString());
      System.out.println(Die.toString(d));
      System.out.println("Sides: " + d.getSides());
      System.out.println("Value: " + d.getValue());
      d.setSides(10);
      System.out.println("Roll Test for 10 sided die");
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println(d.toString());
      System.out.println(Die.toString(d));
      System.out.println("Sides: " + d.getSides());
      System.out.println("Value: " + d.getValue());
      d.setSides(20);
      System.out.println("Roll Test for 20 sided die");
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println("You rolled a " + d.roll());
      System.out.println(d.toString());
      System.out.println(Die.toString(d));
      System.out.println("Sides: " + d.getSides());
      System.out.println("Value: " + d.getValue());

   }

}