/** @CountTheDays.java  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File          :  CountTheDays.java
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
 
public class CountTheDays {
     
    public static void main(String args[]) {
       
        if(args.length < 6) {
            System.out.println( "\n  Run with six command line arguments. Please.\n" );
            System.exit( 0 );
        }
        long month1 = Long.parseLong(args[0]);
        long day1   = Long.parseLong(args[1]);
        long year1  = Long.parseLong(args[2]);
        long month2 = Long.parseLong(args[3]);
        long day2   = Long.parseLong(args[4]);
        long year2  = Long.parseLong(args[5]);
        
        if(!(CalendarStuff.isValidDate(month1, day1, year1)) || !(CalendarStuff.isValidDate(month2, day2, year2))) { //Checks to see if both dates are valid according to the isValidDate method
            System.out.println("An invalid date cannot be input"); 
            System.exit( 0 );
        } 
        long daysBetween = CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2);
        switch((int)daysBetween){
            case 0: 
                System.out.println("\n  There are no days between those two dates.\n"); 
                break;
            case 1: 
                System.out.println("\n  There is one day between those two dates.\n"); 
                break;
            default: 
                System.out.println("\n  There are " + daysBetween + " days between those two dates.\n"); 
                break;
        }
    }
}