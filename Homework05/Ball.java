/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Track location and velocity of a Ball
 *  @author       :  T. Herrmann
 *  Date written  :  2018-03-13
 *  Description   :  None
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-13 T. Herrmann  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 
 public class Ball {
    
    private final double INCHES_PER_FOOT = 12;
    private double xCoor = 0;
    private double yCoor = 0;
    private double xSpeed = 0;
    private double ySpeed = 0;
    private double fieldXLength;
    private double fieldYLength;
    private double timeSlice;
    private double secondCounter = 0;
    private double frictionDecimal = 0.01; 
    private double radiusInches = 4.45;
    private double restVelocityInchesPerSecond = 1;
    
    
     
    public Ball(double fieldXLength, double fieldYLength, double timeSlice) {
        super(); 
        this.fieldXLength = fieldXLength;
        this.fieldYLength = fieldYLength; 
        this.timeSlice = timeSlice;
     }
    
    /**
      *  Method to validate the Coordinate Arguments
      *  @param   arg1 String representing a X Coordinate of a Ball
      *  @param   arg2 String representing a Y Coordinate of a Ball        
      *  @return  void
      *  @throws  NumberFormatException
      */
    public void validateCoordinates( String arg1, String arg2 ) throws NumberFormatException {
        double xCoor = Double.parseDouble(arg1);
        double yCoor = Double.parseDouble(arg2);
        if(Math.abs(xCoor) > (fieldXLength/2) || Math.abs(yCoor) > (fieldYLength/2)) {
          System.out.print("\nArgument Invalid: Invalid off-field Coordinates can not be inputed");
          System.exit(1);
        }   
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        return;
     }
     
    /**
      *  Method to validate the Speed Arguments
      *  @param   arg1 String representing a X speed of a Ball
      *  @param   arg2 String representing a Y speed of a Ball         
      *  @return  void
      *  @throws  NumberFormatException
      */
    public void validateSpeeds( String arg1, String arg2 ) throws NumberFormatException {
        double xSpeed = Double.parseDouble(arg1);
        double ySpeed = Double.parseDouble(arg2);
        //Does Speed have any invalid inputs??????????????????????????????????????????????????????????????????????????   
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        return;
    }
    
    /**
      *  Getter Method      
      *  @return xCoor field
      */
    public double getXCoor() {
        return xCoor;
    }
    
    /**
      *  Getter Method      
      *  @return yCoor field
      */
    public double getYCoor() {
        return yCoor;
    }
    /**
      *  Getter Method      
      *  @return xSpeed field
      */
    public double getXSpeed() {
        return xSpeed;
    }
    
    /**
      *  Getter Method      
      *  @return ySpeed field
      */
    public double getYSpeed() {
        return ySpeed;
    }
    
    /**
      *  Setter Method      
      */
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }
    
    /**
      *  Setter Method      
      */
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }
       
    /**
      *  Method to Update Ball Movement by one Tick According to the Timer TimeSlice
      */
    public void positionUpdate() {
        double xMovement = xSpeed * timeSlice;
        double yMovement = ySpeed * timeSlice;
        this.xCoor += xMovement;
        this.yCoor += yMovement;
    }
    /**
      *  Method to Update Ball Speed by one Tick According to the Timer TimeSlice      
      *  @return  boolean      returns true only if the ball is in motion
      */
    public boolean speedUpdate() {
            xSpeed = xSpeed - ((xSpeed * 0.01) * timeSlice);
            ySpeed = ySpeed - ((ySpeed * 0.01) * timeSlice);
            double vectorVelocity = Math.pow(xSpeed * xSpeed + ySpeed * ySpeed, 0.5);
            
            if(vectorVelocity * INCHES_PER_FOOT < 1) {
                xSpeed = 0.0;
                ySpeed = 0.0;
                return false;
            }
            return true;
    } 
    
 }
 