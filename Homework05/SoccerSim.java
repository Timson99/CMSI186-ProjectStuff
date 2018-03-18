/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  @see
 *  @author       :  T. Herrmann
 *  Date written  :  2018-03-13
 *  Description   :  None
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-13  T. Herrmann   Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.text.DecimalFormat;
 
 public class SoccerSim {
    
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
    private double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
    private int numberOfBalls;
    private int ballArgSet = 4;
 
    private static double poleX = 3;
    private static double poleY = 3;
    private static double fieldXLength = 500;
    private static double fieldYLength = 500;
    
    private Timer timerSim = new Timer();
    private Ball[] soccerBalls;
    private Field soccerField;
 
     public SoccerSim() {
         super();
     }
     
     public void handleInitialArgs(String[] args) {
         
         
         
         if(args.length < ballArgSet) {
             System.out.println("\nArguments Invalid: Cannot run with less than four arguments.\n");
             System.exit(1);
         } 
         if(args.length%ballArgSet > 1) {
             System.out.println("\nArguments Invalid: Too many arguments inputed.\n");
             System.exit(1);
         } 
         
         numberOfBalls = (int)(args.length/ballArgSet);
         soccerBalls = new Ball[numberOfBalls];
         soccerField = new Field(poleX, poleY, fieldXLength, fieldYLength, numberOfBalls, soccerBalls);
         for(int x = 0; x<numberOfBalls; x++) {
             int count = ballArgSet * x;
             soccerBalls[x] = new Ball(fieldXLength, fieldYLength, timeSlice);
             soccerBalls[x].validateCoordinates( args[count] , args[count + 1] );
             soccerBalls[x].validateSpeeds( args[count + 2] , args[count + 3] );
         }
   
         if(!(args.length%ballArgSet == 0)) {
            timeSlice = timerSim.validateTimeSliceArg(args[args.length - 1]);
         }   
     }
     
     public void reportingInterface() {
         DecimalFormat d1 = new DecimalFormat("#0.0000");
         /* Initial Report */
         
         
         while(soccerField.isNotEmpty() && soccerField.isMovement()) {
            timerSim.tick();
            System.out.println("PROGRESS REPORT at " + timerSim.toString());         
            for(int x = 0; x < numberOfBalls; x++) {
                soccerBalls[x].positionUpdate();
                System.out.print(x + ":   position <" + d1.format(soccerBalls[x].getXCoor()) + "," + d1.format(soccerBalls[x].getYCoor()));
                
                if(soccerBalls[x].speedUpdate()) {
                    System.out.println(">    velocity <" + d1.format(soccerBalls[x].getXSpeed()) + "," + d1.format(soccerBalls[x].getYSpeed()) + ">\n");
                }
                else {
                    System.out.println(">    AT REST\n");
                }
            }
            soccerField.checkForCollision();
         }
         System.out.println("FINAL SYSTEM REPORT AS OF " + timerSim.toString());
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
         System.out.println("NO COLLISION POSSIBLE");
         System.exit(0);
     }
     
     public static void main(String args[]) {
         SoccerSim ss = new SoccerSim();
         ss.handleInitialArgs(args);
         ss.reportingInterface();
         System.exit(0);   
     }
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 