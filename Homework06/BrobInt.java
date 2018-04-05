/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  BrobInt.java
 *  Purpose       :  Imitate the BigInteger class
 *  @author       :  T. Herrmann
 *  Date written  :  2018-04-04
 *  Description   :  Class to handle large int values
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  NumberFormatException
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-22 T. Herrmann  Initial writing and release
 *  @version 2.0.0  2018-04-04 T. Herrmann  All methods revised for functionality
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.*;
 
public class BrobInt {
     
    // a BrobInt classwide constants
    public static final BrobInt ZERO = new BrobInt("0");
    public static final BrobInt ONE = new BrobInt("1");
    public static final BrobInt TEN = new BrobInt("10");
 
    private ArrayList<Integer> digitStorage = new ArrayList<Integer>();
    private int storageSize;
    private boolean isNegative = false;

   /** 
     *   Constructor takes a string and assigns it to the internal storage as a ArrayList of Integers called digitStorage,
     *   checks for a sign character and handles that accordingly;  it then checks to see if it's all valid digits,
     *   sets isNegative and storageSize fields to appropriate values
     *  @param  value  String value to make into a BrobInt
     */
    public BrobInt( String value ) {
       long length = value.length();
        int counter = 0;
        if(value.equals("0") || value.length() == 0) {
            digitStorage.add(0);
        }
        else {
            if(value.length() > 1 && value.substring(0, 1).equals("-")) {
                digitStorage.add(Integer.parseInt(value.substring(0, 2)));
                counter = counter + 2;
                isNegative = true;
            }
            for(int x = counter; x<length; x++) {
                try {
                    digitStorage.add(Integer.parseInt(value.substring(x, x+1)));
                }
                catch (NumberFormatException nfe) {
                    if(value.substring(x, x+1).equals("-") || value.substring(x, x+1).equals("+")) {
                        continue;
                    }
                    else {
                        System.out.print(nfe.toString());
                        System.exit(-1);
                    }
                }
            }
        }
        storageSize = digitStorage.size();
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to call addPositives and assign return values the appropriate sign
     *  @param  value         BrobInt to add to this
     *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt add( BrobInt value ) {
        if(isNegative != value.isNegative && abs(value).equals(abs(new BrobInt(toString())))) {
            return ZERO;
        }
        if(isNegative == value.isNegative) {
            if(isNegative) {
                BrobInt temp = abs(new BrobInt(toString())).addPositives(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            return abs(new BrobInt(toString())).addPositives(abs(new BrobInt(value.toString())));
        }
        else if(abs(new BrobInt(toString())).compareTo(abs(new BrobInt(value.toString()))) == 1) {
            //System.out.println("Test1");
            if(isNegative) {
                BrobInt temp = abs(new BrobInt(toString())).subtractPositives(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            else {
                return abs(new BrobInt(toString())).subtractPositives(abs(new BrobInt(value.toString())));
            }
        }
        else {
            //System.out.println("Test2");
            if(!(isNegative)) {
                BrobInt temp = abs(new BrobInt(toString())).subtractPositives(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            else {
                return abs(new BrobInt(toString())).subtractPositives(abs(new BrobInt(value.toString())));
            }  
        }
       
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to add two positive BrobInts
     *  @param  value         Positive BrobInt to add to this
     *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt addPositives( BrobInt value ) {
        int size = (storageSize > value.digitStorage.size()) ? value.digitStorage.size() : storageSize; 
        int tensPlace = 0;
        int counter = 0;
        String strTemp = "";
        if(size == 0) {
            return ZERO;
        }
        for( int index = size - 1; index >= 0; index-- ) {
            int rowAddition = value.digitStorage.get(value.digitStorage.size() - 1 - counter) + digitStorage.get(digitStorage.size() - 1 - counter) + tensPlace;
            tensPlace = (int)(rowAddition/10);
            rowAddition = (int)rowAddition%10;
            strTemp = rowAddition + strTemp + "";
            counter++;
        }
        ArrayList<Integer> largerBrobIntList = ((storageSize > value.digitStorage.size()) ? digitStorage : value.digitStorage);
        int currentIndex = largerBrobIntList.size() - counter - 1;
        for( int index = currentIndex; index >= 0; index-- ) {
            if(tensPlace != 0) {
                int temp = largerBrobIntList.get(index) + tensPlace;
                tensPlace = (temp == 10 ? 1 : 0);
                temp = (temp == 10 ? 0 : temp);
                strTemp = temp  + strTemp + "";
            }
            else {
                strTemp = largerBrobIntList.get(index)  + strTemp + "";
            }
        }
        if(tensPlace != 0) {
            strTemp = tensPlace + strTemp + "";
        }
        if(strTemp == "" || strTemp == " ") {
            return ZERO;
        }
        return new BrobInt(strTemp);
    }
    
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to call subtractAbs and assign return values the appropriate sign
     *  @param  value         BrobInt to subtract from this
     *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt subtract( BrobInt value ) {
        if(compareTo(value) == 0) {
            return ZERO;
        }
        else if(isNegative == value.isNegative) {
            if(isNegative) {
                return add(abs(new BrobInt(value.toString())));
            }
            return add(new BrobInt("-" + value.toString()));
            }
        else if(abs(new BrobInt(toString())).compareTo(abs(new BrobInt(value.toString()))) == 1) {
            if(isNegative) {
                BrobInt temp = abs(new BrobInt(toString())).addPositives(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            else {
                return abs(new BrobInt(toString())).addPositives(abs(new BrobInt(value.toString())));
            }
        }
        else {
            if(isNegative) {
                    BrobInt temp = abs(new BrobInt(toString())).addPositives(abs(new BrobInt(value.toString())));
                    return new BrobInt("-" + temp.toString());
            }
            else {
                    return abs(new BrobInt(toString())).addPositives(abs(new BrobInt(value.toString())));
            } 
        }    
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to subtract two positive BrobInts
     *  @param  value         Positive BrobInt to subtract from to this
     *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt subtractPositives( BrobInt value ) {
        BrobInt calculation = new BrobInt("0");
        int rowSubtraction;
        calculation.digitStorage.remove(0);
        ArrayList<Integer> largerValueBrobIntList = compareTo(value) == 1 ? digitStorage : value.digitStorage;
        ArrayList<Integer> smallerValueBrobIntList = compareTo(value) == 1 ? value.digitStorage : digitStorage;
        int size = smallerValueBrobIntList.size() < largerValueBrobIntList.size() ? smallerValueBrobIntList.size() : largerValueBrobIntList.size();
        int tensPlace = 0;
        int counter = 0;
        for( int index = size - 1; index >= 0; index-- ) {
            if(compareTo(value) == 1 ? true : false) {
                rowSubtraction = largerValueBrobIntList.get(digitStorage.size() - 1 - counter) - smallerValueBrobIntList.get(value.digitStorage.size() - 1 - counter) - tensPlace;
            }
            else {
                rowSubtraction = largerValueBrobIntList.get(value.digitStorage.size() - 1 - counter) - smallerValueBrobIntList.get(digitStorage.size() - 1 - counter) - tensPlace;
            }
            tensPlace = 0;
            if(rowSubtraction < 0 && !(index == 0 && storageSize == value.digitStorage.size())) {
                rowSubtraction += 10;
                tensPlace = 1;
                
            }
            calculation.digitStorage.add(0, rowSubtraction);
            counter++;
        }
        ArrayList<Integer> largerBrobIntList = ((storageSize > value.digitStorage.size()) ? digitStorage : value.digitStorage);
        int currentIndex = largerBrobIntList.size() - counter - 1;
        for( int index = currentIndex; index >= 0; index-- ) {
            if(tensPlace != 0) {
                int temp = largerBrobIntList.get(index) - tensPlace;
                if(temp < 0) {
                    temp += 10;
                    tensPlace++;
                }
                calculation.digitStorage.add(0, temp);
                tensPlace--;
            }
            else {
                calculation.digitStorage.add(0, largerBrobIntList.get(index));
            }
        }
        if(calculation.digitStorage.get(0) == 0) {
            calculation.digitStorage.remove(0);
        }
        return removeZeroes(abs(calculation));
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to call multiplyPositives and assign return values the appropriate sign
     *  @param  value         BrobInt to multiply by this
     *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt multiply( BrobInt value ) {
        BrobInt calculation = new BrobInt(abs(new BrobInt(toString())).multiplyPositives(new BrobInt(abs(new BrobInt(value.toString())).toString())).toString());
        //System.out.println(new BrobInt(abs(new BrobInt(toString())).toString()));
        //System.out.println(new BrobInt(abs(new BrobInt(value.toString())).toString()));
        if(value.equals(ZERO) || equals(ZERO)) {
            return ZERO;
        }
        if(isNegative == value.isNegative) {
            return calculation;
        }
        else {
            return new BrobInt("-" + calculation.toString());
        }    
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to multiply two positive BrobInts
     *  @param  value         Positive BrobInt to multiply by this
     *  @return BrobInt that is the product of the positive value of this BrobInt and the positive one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt multiplyPositives( BrobInt value ) {
        BrobInt calculation = new BrobInt("0");
        ArrayList<BrobInt> additionValues = new ArrayList<BrobInt>();
        ArrayList<Integer>largerBrobIntList = (storageSize > value.digitStorage.size()) ? digitStorage : value.digitStorage; 
        ArrayList<Integer>smallerBrobIntList = (storageSize > value.digitStorage.size()) ? value.digitStorage : digitStorage;
        int counter = 0; 
        for( int index = smallerBrobIntList.size() - 1; index >= 0; index-- ) {
            int tensPlace = 0;
            additionValues.add(0, new BrobInt("0"));
            additionValues.get(0).digitStorage.remove(0);
            for(int x = 0; x < counter; x++) {
                additionValues.get(0).digitStorage.add(0, 0);
            }
            for( int bigIndex = largerBrobIntList.size() - 1; bigIndex >= 0; bigIndex-- ) {
                int temp = largerBrobIntList.get(bigIndex) * smallerBrobIntList.get(index) + tensPlace;
                
                tensPlace = (int)(temp/10);
                temp = (int)(temp%10);
                additionValues.get(0).digitStorage.add(0, temp);
                
            }
            if(tensPlace != 0) {
                additionValues.get(0).digitStorage.add(0, tensPlace);
            }
            counter++;
        } 
        for(int x = 0; x < additionValues.size(); x++) {
            calculation = new BrobInt(calculation.addPositives(additionValues.get(x)).toString());
        }
        return abs(calculation);
    } 
            
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
     *  @param  value         BrobInt to divide this by
     *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt divide( BrobInt value ) {
        BrobInt counter = new BrobInt("0");
        BrobInt temp = new BrobInt("0");
        while(true) {
            temp = new BrobInt(temp.add(abs(new BrobInt(value.toString()))).toString());
            if(abs(new BrobInt(toString())).compareTo(temp) == -1) {
                break;
            }
            counter = counter.add(ONE);
        }
        if(isNegative != value.isNegative) {
            return new BrobInt("-" + counter.toString()); 
        }
        return new BrobInt(counter.toString()); 
    }
   
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to get the remainder of division of this BrobInt by the one passed as argument
     *  @param  value        BrobInt to divide this one by
     *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt remainder( BrobInt value ) {
        if(abs(new BrobInt(toString())).compareTo(abs(new BrobInt(value.toString()))) == -1)
            return new BrobInt(toString());
        
        BrobInt calculation = removeZeroes(abs(new BrobInt(toString())).subtractPositives(abs(new BrobInt(value.toString())).multiply(abs(new BrobInt(toString())).divide(abs(new BrobInt(value.toString()))))));
        
       if(isNegative != value.isNegative) {
            return new BrobInt("-" + abs(calculation).toString()); 
        }
        return new BrobInt(abs(calculation).toString()); 
    }
    
    
    public BrobInt removeZeroes(BrobInt value) {
        while(value.digitStorage.size() != 0 && value.digitStorage.get(0) == 0) {
            value.digitStorage.remove(0);
        }
        return value;
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to compare a BrobInt passed as argument to this BrobInt
     *  @param  value  BrobInt to add to this
     *  @return int   that is one of -1/0/1 if this BrobInt precedes/equals/follows the argument
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int compareTo( BrobInt value ) {
        if(value.toString().length() < toString().length()) {
             return 1;
         }
        if(value.toString().equals(toString())) {
            return 0; 
        }
        if(value.toString().length() > toString().length()) {
            return -1;
        }
        else {
            for(int i = 0; i<storageSize; i++) {
                if(value.digitStorage.get(i) < digitStorage.get(i)) {
                    return 1;
                }
                if(value.digitStorage.get(i) > digitStorage.get(i)) {
                    return -1;
                }
            }
             return 0;
         }
    }
     
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to return a String representation of this BrobInt
     *  @return String  which is the String representation of this BrobInt
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */ 
    public String toString() {
        String str = "";
        int x = 0;
        if(digitStorage.size() > 0) {
            str = str + digitStorage.get(x);
            x++; 
        }
        for(; x < digitStorage.size(); x++) {
            str = str + Math.abs(digitStorage.get(x));
        }
        return str;
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to return absolute value of inputed BrobInt
     *  @param  value         BrobInt to take the absolute value of
     *  @return BrobInt       absolute value of BrobInt passed in
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt abs( BrobInt value ) {
        BrobInt returnValue = new BrobInt(value.toString());
        if(returnValue.digitStorage.size() == 0) {
            return ZERO;
        }
        int temp = returnValue.digitStorage.remove(0);
        returnValue.digitStorage.add(0, (int)Math.abs(temp));
        returnValue.isNegative = false;
        return returnValue;
    }
        
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to check if a BrobInt passed as argument is equal to this BrobInt
     *  @param  x     Object to compare to this
     *  @return boolean  that is true if they are equal and false otherwise
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public boolean equals( Object x ) {
        if(x.toString().equals(toString())) {
            return true;
        }
        return false;
    }
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Method to return a BrobInt given a long value passed as argument
     *  @param  value         long type number to make into a BrobInt
     *  @return BrobInt  which is the BrobInt representation of the long
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static BrobInt valueOf( long value ) {
        String strValue = "" + value;
        BrobInt br = new BrobInt(strValue);
        return br;
        
    } 
    
   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  the main method contains a debug menu for inputing test numbers manually 
     *  @param  args  String array which contains command line arguments
     *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static void main(String[] args) {
        try {
            while(true) {
                Scanner keyboard = new Scanner(System.in);
                Scanner keyboard2 = new Scanner(System.in);
                System.out.println("First Digit? (+)");
                String one = keyboard.nextLine();
                System.out.println("Second Digit? (+)");
                String two = keyboard2.nextLine();
                BrobInt first = new BrobInt(one);
                BrobInt second = new BrobInt(two);
                System.out.println("\n" + first.add(second).toString());
            
                Scanner keyboard3 = new Scanner(System.in);
                Scanner keyboard4 = new Scanner(System.in);
                System.out.println("First Digit? (-)");
                String three = keyboard3.nextLine();
                System.out.println("Second Digit? (-)");
                String four = keyboard4.nextLine();
                BrobInt third = new BrobInt(three);
                BrobInt fourth = new BrobInt(four);
                System.out.println("\n" + third.subtract(fourth).toString());
            
                Scanner keyboard5 = new Scanner(System.in);
                Scanner keyboard6 = new Scanner(System.in);
                System.out.println("First Digit? (x)");
                String five = keyboard5.nextLine();
                System.out.println("Second Digit? (x)");
                String six = keyboard6.nextLine();
                BrobInt fifth = new BrobInt(five);
                BrobInt sixth = new BrobInt(six);
                System.out.println("\n" + fifth.multiply(sixth).toString());
            
                Scanner keyboard7 = new Scanner(System.in);
                Scanner keyboard8 = new Scanner(System.in);
                System.out.println("First Digit? (/)");
                String seven = keyboard7.nextLine();
                System.out.println("Second Digit? (/)");
                String eight = keyboard8.nextLine();
                BrobInt seventh = new BrobInt(seven);
                BrobInt eighth = new BrobInt(eight);
                System.out.println("\n" + seventh.divide(eighth).toString());
            
                Scanner keyboard9 = new Scanner(System.in);
                Scanner keyboard10 = new Scanner(System.in);
                System.out.println("First Digit? (mod)");
                String nine = keyboard9.nextLine();
                System.out.println("Second Digit? (mod)");
                String ten = keyboard10.nextLine();
                BrobInt ninth = new BrobInt(nine);
                BrobInt tenth = new BrobInt(ten);
                System.out.println("\n" + ninth.remainder(tenth).toString());
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
            System.exit(0);
        }
        
    }
 }
 
 