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

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_MINUTE = 0.5;
   private static final double MINUTE_HAND_DEGREES_PER_MINUTE = 6.0;
   private static final double SECONDS_PER_MINUTE = 60.0;
   private static final double MINUTES_PER_HOUR = 60.0; 
   private double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   private double roundingValue; //Used to specify number of decimal places total seconds is rounded to.
   private double minuteHandAngle = 0.0;
   private double hourHandAngle = 0.0;
   private double totalSeconds = 0.0;

  /**
   *  Constructor goes here
   */
   public Clock() {
        calculateTimePrecision();
   }

   
 /**
   *  Method to maintain precision of seconds field by making a rounding value based on the timeSlice.
   */
   public void calculateTimePrecision() {
        
       int preciseDigits = 0;
       double tempTimeSlice = timeSlice;
       while (tempTimeSlice < 1.0) {
           tempTimeSlice *= 10;
           preciseDigits++;
       }
       
       roundingValue = Math.pow(10, preciseDigits);
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
       
       /*if((minuteHandAngle + minuteIncrement) < 360.1  && (minuteHandAngle + minuteIncrement) > 359.9) //Replace with TimeSlice Differencial
            minuteHandAngle = -(minuteIncrement);
   
       if((hourHandAngle + hourIncrement) < 360.1  && (hourHandAngle + hourIncrement) > 359.9)
           hourHandAngle = -(hourIncrement); */
       
       hourHandAngle %= 360.0;
       minuteHandAngle %= 360.0;
       minuteHandAngle += minuteIncrement;
       hourHandAngle += hourIncrement; 
       totalSeconds += timeSlice;
       totalSeconds = Math.round(totalSeconds * roundingValue)/roundingValue; 
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
      calculateTimePrecision();
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
      
      if(handLargerAngle < 180)
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
      
      if(handSmallerAngle > 180)
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
      
      if(getMinute()%60 < 10)
            toStringOutput = toStringOutput + "0";
            
      toStringOutput = toStringOutput + getMinute()%60 + " Minutes ";
      
      if(getTotalSeconds()%60 < 10)
            toStringOutput = toStringOutput + "0";
      
      toStringOutput = toStringOutput + d1.format(getTotalSeconds()%(int)60) + " Seconds";
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
      System.out.println("Round" + clock.roundingValue);
      System.out.println("Time Slice:" + clock.validateTimeSliceArg("1.0"));
      System.out.println("New Round" + clock.roundingValue);
      
      for(int x = 0; x<327; x++)
          clock.tick();
      
      System.out.println(clock.totalSeconds);
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println(clock.getMinuteHandAngle());
      System.out.println(clock.getHourHandAngle());
      System.out.println(clock.getHandLargerAngle());
      System.out.println(clock.getHandSmallerAngle());
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (1.0 == clock.validateTimeSliceArg( "1.0" )) ? " - got 1.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "60.0" )) ? " - got 60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
