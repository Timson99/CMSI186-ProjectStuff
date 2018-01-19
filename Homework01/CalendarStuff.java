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
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
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
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor was called" );  //Informs User if CalendarStuff class was constructed
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      return ((year%400 == 0) || (year%4 == 0) && !(year % 100 == 0));
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
       
        if(isLeapYear(year) && (month == 2)) {
            return (days[(int)month - 1] + 1);
        }
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
        
        return ((year1 == year2) && (month1 == month2) && (day1 == day2));
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
      
      boolean yearGreaterOrEqual = year1 >= year2;
      boolean monthGreaterOrEqual = month1 >= month2;
      
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
        
        if(isLeapYear(year) == true && (day != 1))
            day--;
        
        if((year >= 0) && (month >= 1 && month <= 12) && ( (day >= 1) && day <= days[ (int)(month - 1) ] ))
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
         case 0: return "January";
         case 1: return "February";
         case 2: return "March";
         case 3: return "April";
         case 4: return "May";
         case 5: return "June";
         case 6: return "July";
         case 7: return "August";
         case 8: return "September";
         case 9: return "October";
         case 10:return "November";
         case 11:return "December";
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
         case 0: return "Sunday";
         case 1: return "Monday";
         case 2: return "Tuesday";
         case 3: return "Wednesday";
         case 4: return "Thursday";
         case 5: return "Friday";
         case 6: return "Saturday";
         default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
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
        long[] greaterDate;
        long[] lesserDate;
        long[] markedDate; 
     
        switch(compareDate(month1, day1, year1, month2, day2, year2)) {
            case 0: 
                return 0;
            case 1: 
                greaterDate = new long[] {month1, day1, year1};
                lesserDate = new long[] {month2, day2, year2};
                break;
            case -1:
                lesserDate = new long[] {month1, day1, year1};
                greaterDate = new long[] {month2, day2, year2};
                break;
            default: 
                greaterDate = new long[] {0,0,0};
                lesserDate = new long[] {0,0,0};
                System.out.println("Error in compareDate method");
                break;
        }
        
        if(greaterDate[1] != lesserDate[1] && greaterDate[0] == lesserDate[0]) {
           
            dayCount += greaterDate[1] - lesserDate[1];
            markedDate = new long[] {greaterDate[0], greaterDate[1]};
        }
        else {
            if(greaterDate[0] > lesserDate[0]) {
                
                long monthCounter = lesserDate[0];
                long monthDifference = greaterDate[0] - lesserDate[0];
                dayCount += daysInMonth(lesserDate[0], lesserDate[2]) - lesserDate[1];
                monthCounter++;
                
                for(int m = 0; m < monthDifference - 1; m++) {
                    
                    dayCount += daysInMonth(monthCounter, lesserDate[2]);
                    monthCounter++;
                }
                markedDate = new long[] {greaterDate[0], greaterDate[1]};
                dayCount += greaterDate[1];
            }
        
            if(greaterDate[0] < lesserDate[0]) {
                
                long monthCounter = greaterDate[0];
                long monthDifference = lesserDate[0] - greaterDate[0];
                dayCount -= daysInMonth(greaterDate[0], greaterDate[2]) - greaterDate[1];
                monthCounter++;
                
                for(int m = 0; m < monthDifference - 1; m++) {
                    
                    dayCount -= daysInMonth(monthCounter, greaterDate[2]);
                    monthCounter++;
                }
                markedDate = new long[] {lesserDate[0], lesserDate[1]};
                dayCount -= lesserDate[1];
            }
            else
                markedDate = new long[] {greaterDate[0], greaterDate[1]};
           
        }
        if(greaterDate[2] != lesserDate[2]) {
            
            int yearDifference = (int)(greaterDate[2] - lesserDate[2]);
            if(compareDate(markedDate[0], markedDate[1], greaterDate[2], FEBRUARY, days[FEBRUARY], greaterDate[2]) == -1) {
                for(int y = 0; y < yearDifference; y++) {
                
                    if (isLeapYear(lesserDate[2] + y))
                        dayCount += 366;
                    else
                        dayCount += 365;
                }
            }
            else {
                for(int y = 0; y < yearDifference; y++) {
                
                    if (isLeapYear(lesserDate[2] + 1 + y))
                        dayCount += 366;
                    else
                        dayCount += 365;
                }
            }        
        }
        return dayCount;
   }
}
