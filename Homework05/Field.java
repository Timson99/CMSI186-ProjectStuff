/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Field.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  T. Herrmann
 *  Date written  :  2018-03-13
 *  Description   :  None
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-13 T. Herrmann  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 
 public class Field {
     
    private double poleX;
    private double poleY;
    private double fieldYLength;
    private double fieldXLength;
    private double numberOfBalls;
    private Ball[] soccerBalls;
    private double ballRadiusFeet = 4.5/12;
    private double epsilon = 0.0000001;
     
    public Field(double poleX, double poleY, double fieldXLength, double fieldYLength, double numberOfBalls, Ball[] soccerBalls) {
         this.poleX = poleX;
         this.poleY = poleY;
         this.fieldXLength = fieldXLength;
         this.fieldYLength = fieldYLength; 
         this.numberOfBalls = numberOfBalls;
         this.soccerBalls = soccerBalls;
     }
     
    public boolean isNotEmpty() {
        int counter = 0;
       for(int x = 0; x<numberOfBalls; x++) {
           double xCoor = soccerBalls[x].getXCoor();
           double yCoor = soccerBalls[x].getYCoor();
           if(Math.abs(xCoor) <= (fieldXLength/2) && Math.abs(yCoor) <= (fieldYLength/2)) {
               counter++;
           }
           else {
               soccerBalls[x].setYSpeed(0);
               soccerBalls[x].setXSpeed(0);
           }
        }
        if(counter > 0) {
           return true;
        }
        return false;    
    }
     
    public boolean isMovement() {
       for(int x = 0; x<numberOfBalls; x++) {
           if(soccerBalls[x].getYSpeed() != 0.0 && soccerBalls[x].getXSpeed() != 0.0) {
               return true;
           }
       }
       return false;
    }
    
    /*
      * Ends program if there is a collision between any of the balls and each other or any of the balls and the pole
      */
    public void checkForCollision() {
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
 }