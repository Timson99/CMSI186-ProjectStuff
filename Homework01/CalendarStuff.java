/** @CalendarStuff.java  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File          :  CalendarStuff.java
 *  Purpose       :  
 *  Date          :  2018-01-16 
 *  Author        :  Timothy Herrmann
 *  Description   :  N/A  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History:
 *  ------------------
 *    Ver      Date     Modified by:  Description for change/modification
 *   -----  ----------  ------------  -------------------------------------------------------------------
 *   1.0.0  2018-01-16  T. Herrmann   Initial Version
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  
public class CalendarStuff {
    
  /**
   * A listing of the days of the week, assigning numbers; 
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; 
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;
  
   /**
   * Constant stores the amount of days in a typical year
   */
   private static int DAYS_IN_YEAR = 365;
   
  /**
   * An array containing the number of days in each month.
   */
   private static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   
  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor was called" );  //Tells User if CalendarStuff class was constructed
   }

  /**
   * A method to determine if the year argument is a leap year or not
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      return ((year%400 == 0) || (year%4 == 0) && !(year % 100 == 0)); //Are these magic numbers?
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   */
   public static long daysInMonth( long month, long year ) {
        if(isLeapYear(year) && (month - 1 == FEBRUARY))   //Checks if month in question is a February during a Leap Year
            return (days[(int)month - 1] + 1);            //Returns days in a regular February plus 1, to account for a leap day
        
        else
            return (days[(int)month - 1]);
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) { 
        return ((year1 == year2) && (month1 == month2) && (day1 == day2)); //Returns true or false depending on whether or not  the two months, days, and years are both equal
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      boolean yearGreaterOrEqual = year1 >= year2; //Variable to store whether or not the first year is greater than the second year
      boolean monthGreaterOrEqual = month1 >= month2; //Variable to store whether or not the first month is greater than the second month
      
      if(dateEquals(month1, day1, year1, month2, day2, year2))
          return 0;
      else if((year1 > year2) || ((yearGreaterOrEqual) && (month1 > month2)) || ((yearGreaterOrEqual) && (monthGreaterOrEqual) && (day1 > day2)))
          return 1;
      else 
          return -1;
    }
    
  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
        if(isLeapYear(year) == true && (month - 1 == FEBRUARY) && (day == days[FEBRUARY] + 1)) //Variable day is a valid leap day if the date passed in is the 29th day of February during a leap year
            day--; //Subtracts 1 from day if the variable day happens to be a leap day, allowing it to be checked for validity based on the regular days in the month of February
        if((year >= 0) && (month - 1 >= JANUARY && month - 1 <= DECEMBER) && ( (day >= 1) && day <= days[ (int)(month - 1) ] ))
            return true;
        else 
            return false; 
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         case JANUARY:   return "January";
         case FEBRUARY:  return "February";
         case MARCH:     return "March";
         case APRIL:     return "April";
         case MAY:       return "May";
         case JUNE:      return "June";
         case JULY:      return "July";
         case AUGUST:    return "August";
         case SEPTEMBER: return "September";
         case OCTOBER:   return "October";
         case NOVEMBER:  return "November";
         case DECEMBER:  return "December";
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
         case SUNDAY: return "Sunday";
         case MONDAY: return "Monday";
         case TUESDAY: return "Tuesday";
         case WEDNESDAY: return "Wednesday";
         case THURSDAY: return "Thursday";
         case FRIDAY: return "Friday";
         case SATURDAY: return "Saturday";
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return the amount of days in the given year
   * @param    year   long   containing four-digit year
   * @return          long   total amount of days in a year
   */
   public static long daysInYear(long year) {
       if(isLeapYear(year))
           return DAYS_IN_YEAR + 1;
       else 
           return DAYS_IN_YEAR;
       
   }
   
   /**
   * A method to returns the amount of days that have passed in the year up till the given date
   * @param    month long   containing month number, starting with "1" for "January"
   * @param    day1  long   containing day number
   * @param    year  long   containing four-digit year
   * @return         long   The day of the year of the given date
   */
   public static long dayOfTheYear(long month, long day, long year) {
        long dayOfTheYear = 0;
                
        for(int m = 0; m < month - 1; m++) {
            
            dayOfTheYear += daysInMonth(m + 1, year);
        }
        dayOfTheYear += day;
        return dayOfTheYear;
       
   }
   
   /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
       long dayCount = 0;
       long dayOfYearOne = dayOfTheYear(month1, day1, year1);
       long dayOfYearTwo = dayOfTheYear(month2, day2, year2);
       
       if(year1==year2)
           return Math.abs(dayOfYearOne - dayOfYearTwo);
       
       long yearDifference = Math.abs(year2 - year1);
       
       if(year2 > year1) {
           dayCount += daysInYear(year1) - dayOfYearOne;
           for(int y = 1; y < yearDifference; y++) {
                dayCount += daysInYear(y+year1);
           }
           dayCount += dayOfYearTwo;
           return dayCount; 
       }           
       else {
           dayCount += daysInYear(year2) - dayOfYearTwo;
           for(int y = 1; y < yearDifference; y++) {
                dayCount += daysInYear(y+year2);
           }
           dayCount += dayOfYearOne;
           return dayCount;   
       }
    }
}