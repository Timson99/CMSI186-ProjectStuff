/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  T. Herrmann
 *  Date written  :  2017-02-17
 *  Description   :  None
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  T. Herrmann  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double TEST_EPSILON = 0.00000001;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_MINUTE = 0.5004; //Emulates Imprecision of demonstration classes
   private static final double MINUTE_HAND_DEGREES_PER_MINUTE = 6.0;
   private static final double SECONDS_PER_MINUTE = 60.0;
   private static final double MINUTES_PER_HOUR = 60.0; 
   private static int secondsPreciseDigits = 10;
   private static double roundingValue = Math.pow(10, secondsPreciseDigits);
   private double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   private double minuteHandAngle = 0.0;
   private double hourHandAngle = 0.0;
   private double totalSeconds = 0.0;

  /**
   *  Constructor goes here
   */
   public Clock() {
       
   }

   
 /**
   *  Method to maintain precision of seconds field.
   */
   public void calculatePrecision() {
       totalSeconds = Math.round(totalSeconds * roundingValue)/roundingValue;  
   }
   
  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
       
       double scaledTimeSlice = timeSlice/SECONDS_PER_MINUTE;
       double minuteIncrement = MINUTE_HAND_DEGREES_PER_MINUTE * scaledTimeSlice;
       double hourIncrement = HOUR_HAND_DEGREES_PER_MINUTE * scaledTimeSlice;
     
       minuteHandAngle += minuteIncrement;
       hourHandAngle += hourIncrement; 
       hourHandAngle %= MAXIMUM_DEGREE_VALUE;
       minuteHandAngle %= MAXIMUM_DEGREE_VALUE;
       totalSeconds += timeSlice;
       calculatePrecision();
       return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double angleArg = Double.parseDouble(argValue);
      return angleArg;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
      double timeSliceArg = Double.parseDouble(argValue);
      timeSlice = timeSliceArg;
      return timeSlice;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return hourHandAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return minuteHandAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandLargerAngle() {
      double higherDegree;
      double smallerDegree;
      double handLargerAngle;
      
      if(minuteHandAngle > hourHandAngle) {
        higherDegree = minuteHandAngle;
        smallerDegree = hourHandAngle;
      }
      else{
        smallerDegree = minuteHandAngle;
        higherDegree = hourHandAngle;
      }
      
      handLargerAngle = higherDegree - smallerDegree;
      
      if(handLargerAngle < (int)(MAXIMUM_DEGREE_VALUE/2))
          return MAXIMUM_DEGREE_VALUE - handLargerAngle;
      
      return handLargerAngle;      
   }
   
   /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandSmallerAngle() {
      double higherDegree;
      double smallerDegree;
      double handSmallerAngle;
      
      if(minuteHandAngle > hourHandAngle) {
        higherDegree = minuteHandAngle;
        smallerDegree = hourHandAngle;
      }
      else{
        smallerDegree = minuteHandAngle;
        higherDegree = hourHandAngle;
      }
      
      handSmallerAngle = higherDegree - smallerDegree;
      
      if(handSmallerAngle > (int)(MAXIMUM_DEGREE_VALUE/2))
          return MAXIMUM_DEGREE_VALUE - handSmallerAngle;
      
      return handSmallerAngle;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }
   
   public int getMinute() {
      return (int)(totalSeconds/(int)SECONDS_PER_MINUTE);
   }
   
   public int getHour() {
      if((int)((getMinute())/(int)MINUTES_PER_HOUR) == 0)
          return 0;
      
      return (int)((getMinute())/(int)MINUTES_PER_HOUR);
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      DecimalFormat d1 = new DecimalFormat("#,#0.0");
      String toStringOutput = "";
      if(getHour() < 10)
            toStringOutput = toStringOutput + "0";
    
      toStringOutput = toStringOutput + getHour() + " Hours ";
      
      if(getMinute()%(int)MINUTES_PER_HOUR < secondsPreciseDigits)
            toStringOutput = toStringOutput + "0";
            
      toStringOutput = toStringOutput + getMinute()%((int)MINUTES_PER_HOUR) + " Minutes ";
      
      if(getTotalSeconds()%(int)SECONDS_PER_MINUTE < secondsPreciseDigits)
            toStringOutput = toStringOutput + "0";
      
      toStringOutput = toStringOutput + d1.format(getTotalSeconds()%(int)SECONDS_PER_MINUTE) + " Seconds";
      return toStringOutput; 
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
  
      System.out.println( "\n    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.print( "      sending '  78.9 degrees', expecting double value   78.9" );
      try { System.out.println( (78.9 == clock.validateAngleArg( "78.9" )) ? " - got 78.9" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      
      System.out.println( "\n    Testing validateTimeSliceArg()....");
      System.out.print( "      sending '  1.0 degrees', expecting double value   1.0" );
      try { System.out.println( (1.0 == clock.validateTimeSliceArg( "1.0" )) ? " - got 1.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("      Current Time Slice is " + clock.timeSlice);
      System.out.print( "      sending '  67.8 degrees', expecting double value   67.8" );
       try { System.out.println( (67.8 == clock.validateTimeSliceArg( "67.8" )) ? " - got 67.8" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("      Current Time Slice is " + clock.timeSlice);
      System.out.print( "      sending '  60.0 degrees', expecting double value   60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "60.0" )) ? " - got 60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println("      Current Time Slice is " + clock.timeSlice);
      
      System.out.println( "\n    Testing calculateTimePrecision()....");
      System.out.print( "\t(Seconds 60.0) expecting 60.0 for roundingValue" );
      clock.totalSeconds = 60.0;
      clock.calculatePrecision();
      System.out.println( (60.0 == clock.totalSeconds ? " - got 1.0" : " - no joy" ));
      
      System.out.println( "\n    tick()....");
      Clock clock2 = new Clock();
      
      System.out.println("**Time Slice: 60.0**");
      System.out.println("\t1 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
      System.out.println("\t2 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
      System.out.println("\t3 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
      for(int i = 0; i < 55; i++)
          clock2.tick();
      
      System.out.println("\t59 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
      System.out.println("\t60 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
      System.out.println("\t61 tick --- Total Seconds: " + clock2.tick() + "; Minute Hand Angle: " + clock2.getMinuteHandAngle() + "; Hour Hand Angle: " + clock2.getHourHandAngle());
      
      Clock clock3 = new Clock();
      clock3.validateTimeSliceArg("1.0");
      
      System.out.println("**Time Slice: 1.0**");
      System.out.println("\t1 tick --- Total Seconds: " + clock3.tick() + "; Minute Hand Angle: " + clock3.getMinuteHandAngle() + "; Hour Hand Angle: " + clock3.getHourHandAngle());
      System.out.println("\t2 tick --- Total Seconds: " + clock3.tick() + "; Minute Hand Angle: " + clock3.getMinuteHandAngle() + "; Hour Hand Angle: " + clock3.getHourHandAngle());
      System.out.println("\t3 tick --- Total Seconds: " + clock3.tick() + "; Minute Hand Angle: " + clock3.getMinuteHandAngle() + "; Hour Hand Angle: " + clock3.getHourHandAngle());
      for(int i = 0; i < 3595; i++)
          clock3.tick();
      
      System.out.println("\t59 tick --- Total Seconds: " + clock3.tick() + "; Minute Hand Angle: " + clock3.getMinuteHandAngle() + "; Hour Hand Angle: " + clock3.getHourHandAngle());
      System.out.println("\t60 tick --- Total Seconds: " + clock3.tick() + "; Minute Hand Angle: " + clock3.getMinuteHandAngle() + "; Hour Hand Angle: " + clock3.getHourHandAngle());
      System.out.println("\t61 tick --- Total Seconds: " + clock3.tick() + "; Minute Hand Angle: " + clock3.getMinuteHandAngle() + "; Hour Hand Angle: " + clock3.getHourHandAngle());
      
      Clock clock4 = new Clock();
      clock4.validateTimeSliceArg("0.1");
      
      System.out.println("**Time Slice: 0.1**");
      System.out.println("\t1 tick --- Total Seconds: " + clock4.tick() + "; Minute Hand Angle: " + clock4.getMinuteHandAngle() + "; Hour Hand Angle: " + clock4.getHourHandAngle());
      System.out.println("\t2 tick --- Total Seconds: " + clock4.tick() + "; Minute Hand Angle: " + clock4.getMinuteHandAngle() + "; Hour Hand Angle: " + clock4.getHourHandAngle());
      System.out.println("\t3 tick --- Total Seconds: " + clock4.tick() + "; Minute Hand Angle: " + clock4.getMinuteHandAngle() + "; Hour Hand Angle: " + clock4.getHourHandAngle());
      for(int i = 0; i < 35995; i++)
          clock4.tick();
      
      System.out.println("\t59 tick --- Total Seconds: " + clock4.tick() + "; Minute Hand Angle: " + clock4.getMinuteHandAngle() + "; Hour Hand Angle: " + clock4.getHourHandAngle());
      System.out.println("\t60 tick --- Total Seconds: " + clock4.tick() + "; Minute Hand Angle: " + clock4.getMinuteHandAngle() + "; Hour Hand Angle: " + clock4.getHourHandAngle());
      System.out.println("\t61 tick --- Total Seconds: " + clock4.tick() + "; Minute Hand Angle: " + clock4.getMinuteHandAngle() + "; Hour Hand Angle: " + clock4.getHourHandAngle());
      
       Clock clock5 = new Clock();
      clock5.validateTimeSliceArg("0.001");
      
      System.out.println("**Time Slice: 0.001**");
      System.out.println("\t1 tick --- Total Seconds: " + clock5.tick() + "; Minute Hand Angle: " + clock5.getMinuteHandAngle() + "; Hour Hand Angle: " + clock5.getHourHandAngle());
      System.out.println("\t2 tick --- Total Seconds: " + clock5.tick() + "; Minute Hand Angle: " + clock5.getMinuteHandAngle() + "; Hour Hand Angle: " + clock5.getHourHandAngle());
      System.out.println("\t3 tick --- Total Seconds: " + clock5.tick() + "; Minute Hand Angle: " + clock5.getMinuteHandAngle() + "; Hour Hand Angle: " + clock5.getHourHandAngle());
      for(int i = 0; i < 3599995; i++)
          clock5.tick();
      
      System.out.println("\t59 tick --- Total Seconds: " + clock5.tick() + "; Minute Hand Angle: " + clock5.getMinuteHandAngle() + "; Hour Hand Angle: " + clock5.getHourHandAngle());
      System.out.println("\t60 tick --- Total Seconds: " + clock5.tick() + "; Minute Hand Angle: " + clock5.getMinuteHandAngle() + "; Hour Hand Angle: " + clock5.getHourHandAngle());
      System.out.println("\t61 tick --- Total Seconds: " + clock5.tick() + "; Minute Hand Angle: " + clock5.getMinuteHandAngle() + "; Hour Hand Angle: " + clock5.getHourHandAngle());

      System.out.println( "\n    Testing toString()....");
      Clock clock6 = new Clock();
      
      clock6.validateTimeSliceArg("1.0");
      System.out.print("\tTesting 5 seconds:   ");
      for(int i = 0; i <5; i++)
          clock6.tick();

      System.out.print(clock6.toString());
      System.out.println(("00 Hours 00 Minutes 05.0 Seconds".equals(clock6.toString()) ? " - got it" : " - WRONG" ));
      System.out.print("\tTesting 1 minute:   ");
      for(int i = 0; i <55; i++)
          clock6.tick();

      System.out.print(clock6.toString());
      System.out.println(("00 Hours 01 Minutes 00.0 Seconds".equals(clock6.toString()) ? " - got it" : " - WRONG" ));
      System.out.print("\tTesting 1 hour:   ");
      for(int i = 0; i < 3540; i++)
          clock6.tick();

      System.out.print(clock6.toString());
      System.out.println(("01 Hours 00 Minutes 00.0 Seconds".equals(clock6.toString()) ? " - got it" : " - WRONG" ));
      System.out.print("\tTesting 6 hours 30 minutes 5 seconds   ");
      for(int i = 0; i < 19805; i++)
          clock6.tick();

      System.out.print(clock6.toString());
      System.out.println(("06 Hours 30 Minutes 05.0 Seconds".equals(clock6.toString()) ? " - got it" : " - WRONG" ));
      System.out.print("\tTesting 11 hours 59 minutes and 59 seconds   ");
      for(int i = 0; i < 19794; i++)
          clock6.tick();

      System.out.print(clock6.toString());
      System.out.println(("11 Hours 59 Minutes 59.0 Seconds".equals(clock6.toString()) ? " - got it" : " - WRONG" ));
      
      
      System.out.println("\n********DEBUG MENU**********\n"); //Debug to test accuracy of angle calculations
      Scanner keyboard = new Scanner(System.in);
      int inTicks;
      double inTimeSlice;
      int inBreak;
      
      inputLoop: while( true ) {
        System.out.println("\n\tEnter 0 to Quit\n\tEnter 1 to Loop");
        inBreak = (int)keyboard.nextDouble();
        
        if(inBreak == 0) {
            break inputLoop;
        }
        
        System.out.println("\tEnter Time Slice");
        inTimeSlice = keyboard.nextDouble();
        System.out.println("\tEnter Number of Ticks");
        inTicks = (int)keyboard.nextDouble();
      
        Clock clockTest = new Clock();
        clockTest.timeSlice = inTimeSlice;
        for(int i = 0; i < inTicks; i++) {
            clockTest.tick();
        }
        
        System.out.println("Time: " + clockTest.toString());
        System.out.println("Minute Hand: " + clockTest.getMinuteHandAngle());
        System.out.println("Hour Hand:   " + clockTest.getHourHandAngle());
        System.out.println("Large Angle: " + clockTest.getHandLargerAngle());
        System.out.println("Small Angle: " + clockTest.getHandSmallerAngle());
        System.out.println("Total Seconds: " + clockTest.totalSeconds);
      
      }
      
   }
}
