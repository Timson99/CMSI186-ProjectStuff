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
    private double radiusFeet = radiusInches/INCHES_PER_FOOT;
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
          throw new NumberFormatException("BAD");
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
            double fractionTimeSlice = timeSlice;
            if(timeSlice >  1.0) {
                if(timeSlice % 1.0 != 0) {
                    fractionTimeSlice = timeSlice % 1.0;
                }
                else {
                    fractionTimeSlice = 1.0;
                }
            }
            double xMovement = xSpeed * fractionTimeSlice;
            double yMovement = ySpeed * fractionTimeSlice;
            this.xCoor += xMovement;
            this.yCoor += yMovement;
    }
    
    public void positionUpdateOneSecond() {
            double xMovement = xSpeed;
            double yMovement = ySpeed;
            this.xCoor += xMovement;
            this.yCoor += yMovement;
    }
    /**
      *  Method to Update Ball Speed by one Tick According to the Timer TimeSlice      
      *  @return  boolean      returns true only if the ball is in motion
      */
    public boolean speedUpdate() {
            double fractionTimeSlice = timeSlice;
            if(timeSlice >  1.0) {
                if(timeSlice % 1.0 != 0) {
                    fractionTimeSlice = timeSlice % 1.0;
                }
                else {
                    fractionTimeSlice = 1.0;
                }
            }
            xSpeed = xSpeed - ((xSpeed * (0.01)) * fractionTimeSlice);
            ySpeed = ySpeed - ((ySpeed * 0.01) * fractionTimeSlice);
            double vectorVelocity = Math.pow(xSpeed * xSpeed + ySpeed * ySpeed, 0.5);
            
            if(vectorVelocity * INCHES_PER_FOOT < 1) {
                xSpeed = 0.0;
                ySpeed = 0.0;
                return false;
            }
            return true;
    }
    public void speedUpdateOneSecond() {
            xSpeed = xSpeed - (xSpeed * (0.01));
            ySpeed = ySpeed - ((ySpeed * 0.01));
    }     
    
 
 
 public static void main(String[] args) {
    
    System.out.println("Initializing~~~~~~~~~~~~~~~~~~~");
    Timer timer = new Timer();
    try { System.out.println(("Should Error: " + timer.validateTimeSliceArg("abc") == "abc" ? "Good" : "Bad")); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { System.out.println("Should Error: " + (timer.validateTimeSliceArg("0.0") == 0.0 ? "Good" : "Bad")); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { System.out.println("Should Error: " + (timer.validateTimeSliceArg("2000.0") == 2000.0 ? "Good" : "Bad")); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { System.out.println("Should be Good: " + (timer.validateTimeSliceArg("1.0") == 1.0 ? "Good" : "Bad")); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    
    Ball ball = new Ball(10000, 10000, 1.0);
    System.out.println("\nTests for validateCoordinates()\n");
    try { ball.validateCoordinates("blerg", "abc");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { ball.validateCoordinates("0.0", "abc");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { ball.validateCoordinates("10001", "10000");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { ball.validateCoordinates("10000", "10000");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { ball.validateCoordinates("0.0", "0.0");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    
    System.out.println("\nTests for validateSpeeds()\n");
    try { ball.validateSpeeds("blarg", "abc");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { ball.validateSpeeds("0.0", "abc");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    try { ball.validateSpeeds("0.0", "0.0");
    System.out.println("Good"); }
    catch(NumberFormatException nfe) { System.out.println("Error"); }
    
    System.out.println("\nTests for update methods"); 
        Ball ball2 = new Ball(10000, 10000, 2.0);
        Ball ball3 = new Ball(10000, 10000, 2.6);
        Ball ball4 = new Ball(10000, 10000, 0.6);
        try { ball2.validateCoordinates("0.0", "0.0"); }
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        try { ball3.validateCoordinates("0.0", "0.0"); }
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        try { ball4.validateCoordinates("0.0", "0.0"); }
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        try { ball.validateSpeeds("2.0", "2.0");}
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        try { ball2.validateSpeeds("2.0", "2.0");}
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        try { ball3.validateSpeeds("2.0", "2.0");}
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        try { ball4.validateSpeeds("2.0", "2.0");}
        catch(NumberFormatException nfe) { System.out.println("Error"); }
        
        System.out.println("\n\nBall 1; TimeSlice 1.0; -- (" + ball.getXCoor() + "," + ball.getYCoor() + ") <" + ball.getXSpeed() + "," + ball.getYSpeed() + "> \n");
        ball.positionUpdate();
        ball.speedUpdate();
        System.out.println("Update one slice; -- (" + ball.getXCoor() + "," + ball.getYCoor() + ") <" + ball.getXSpeed() + "," + ball.getYSpeed() + "> ");
        ball.positionUpdate();
        ball.speedUpdate();
        System.out.println("Update one slice; -- (" + ball.getXCoor() + "," + ball.getYCoor() + ") <" + ball.getXSpeed() + "," + ball.getYSpeed() + "> ");
        ball.positionUpdate();
        ball.speedUpdate();
        System.out.println("Update one slice; -- (" + ball.getXCoor() + "," + ball.getYCoor() + ") <" + ball.getXSpeed() + "," + ball.getYSpeed() + "> ");
        ball.positionUpdate();
        ball.speedUpdate();
        System.out.println("Update one slice; -- (" + ball.getXCoor() + "," + ball.getYCoor() + ") <" + ball.getXSpeed() + "," + ball.getYSpeed() + "> ");
        ball.positionUpdate();
        ball.speedUpdate();
        
        System.out.println("\n\nBall 2; TimeSlice 2.0; -- (" + ball2.getXCoor() + "," + ball2.getYCoor() + ") <" + ball2.getXSpeed() + "," + ball2.getYSpeed() + "> \n");
        System.out.println("SHOULD HAVE SAME CHANGES AS BALL1");
        ball2.positionUpdate();
        ball2.speedUpdate();
        System.out.println("Update one slice; -- (" + ball2.getXCoor() + "," + ball2.getYCoor() + ") <" + ball2.getXSpeed() + "," + ball2.getYSpeed() + "> ");
        ball2.positionUpdate();
        ball2.speedUpdate();
        System.out.println("Update one slice; -- (" + ball2.getXCoor() + "," + ball2.getYCoor() + ") <" + ball2.getXSpeed() + "," + ball2.getYSpeed() + "> ");
        ball2.positionUpdate();
        ball2.speedUpdate();
        System.out.println("Update one slice; -- (" + ball2.getXCoor() + "," + ball2.getYCoor() + ") <" + ball2.getXSpeed() + "," + ball2.getYSpeed() + "> ");
        ball2.positionUpdate();
        ball2.speedUpdate();
        System.out.println("Update one slice; -- (" + ball2.getXCoor() + "," + ball2.getYCoor() + ") <" + ball2.getXSpeed() + "," + ball2.getYSpeed() + "> ");
        ball2.positionUpdate();
        ball2.speedUpdate();
        
        System.out.println("\n\nBall 3; TimeSlice 2.6; -- (" + ball3.getXCoor() + "," + ball3.getYCoor() + ") <" + ball3.getXSpeed() + "," + ball3.getYSpeed() + "> \n");
        ball3.positionUpdate();
        ball3.speedUpdate();
        System.out.println("Update one slice; -- (" + ball3.getXCoor() + "," + ball3.getYCoor() + ") <" + ball3.getXSpeed() + "," + ball3.getYSpeed() + "> ");
        ball3.positionUpdate();
        ball3.speedUpdate();
        
        System.out.println("\n\nBall 4; TimeSlice 0.6; -- (" + ball4.getXCoor() + "," + ball4.getYCoor() + ") <" + ball4.getXSpeed() + "," + ball4.getYSpeed() + "> \n");
         System.out.println("SHOULD HAVE SAME CHANGES AS BALL3");
        ball4.positionUpdate();
        ball4.speedUpdate();
        System.out.println("Update one slice; -- (" + ball4.getXCoor() + "," + ball4.getYCoor() + ") <" + ball4.getXSpeed() + "," + ball4.getYSpeed() + "> ");
        ball4.positionUpdate();
        ball4.speedUpdate();
        
        
    }
 }


 