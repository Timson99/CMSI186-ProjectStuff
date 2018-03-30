/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  BrobInt.java
 *  Purpose       :  
 *  @author       :  T. Herrmann
 *  Date written  :  2018-03-22
 *  Description   :  None
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :   
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-22 T. Herrmann  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.util.*;
 
 public class BrobInt {
     
    // a BrobInt classwide constant for zero
    public static final BrobInt ZERO = new BrobInt("0");
    
    // a BrobInt classwide constant for one
    public static final BrobInt ONE = new BrobInt("1");

    // a BrobInt classwide constant for ten
    public static final BrobInt TEN = new BrobInt("10");
    
    // . add .get(Index), .remove(Index), .add(index, Object) (Shift to right), .size(), .set(Index, Object)
    //Doesn't change for each instance
    private ArrayList<Integer> digitStorage = new ArrayList<Integer>();
    private int storageSize;
    private boolean isNegative = false;

    
    // mimics one of the several java.math.BigInteger constructors
    //Optional Minus Sign?????????????? Character to Digit????????????????? ASK
    //Must also learn to accept negatives for all methods
    public BrobInt( String value ) {
        long length = value.length();
        int counter = 0;
        if(value.substring(0, 1).equals("-")) {
            digitStorage.add(Integer.parseInt(value.substring(0, 2)));
            counter = counter + 2;
            isNegative = true;
        }
        for(int x = counter; x<length; x++) {
            
            digitStorage.add(Integer.parseInt(value.substring(x, x+1)));
        }
        storageSize = digitStorage.size();
    }
    
    
    public BrobInt abs( BrobInt value) {
        int temp = value.digitStorage.remove(0);
        value.digitStorage.add(0, (int)Math.abs(temp));
        return value;
    }
    public void abs() {
        int temp = digitStorage.remove(0);
        digitStorage.add(0, (int)Math.abs(temp));
    }
    
    public BrobInt add( BrobInt value ) {
        if(isNegative == value.isNegative) {
            if(isNegative) {
                abs();
                BrobInt temp = addSameSigns(abs(value));
                return new BrobInt("-" + temp.toString());
            }
            abs();
            BrobInt temp = addSameSigns(abs(value));
            return temp;
        }
        else if(compareTo(value) == 1) {
            if(isNegative) {
                abs();
                BrobInt temp = addSameSigns(abs(value));
                return new BrobInt("-" + temp.toString());
            }
            else {
                abs();
                BrobInt temp = addSameSigns(abs(value));
                return temp;
            }
        }
        else {
            if(!(isNegative)) {
                abs();
                BrobInt temp = addSameSigns(abs(value));
                return new BrobInt("-" + temp.toString());
            }
            else {
                abs();
                BrobInt temp = addSameSigns(abs(value));
                return temp;
            }  
        }
        /*else {
        }
        */
    }
    public BrobInt subtract( BrobInt value ) {
            if(compareTo(value) == 0)
                return ZERO;
            
            else if(compareTo(value) == 1) {
               if(isNegative) {
                   abs();
                    BrobInt temp = subSameSigns(abs(value));
                    return new BrobInt("-" + temp.toString());
                }
                else {
                    abs();
                    BrobInt temp = subSameSigns(abs(value));
                    return temp;
                }
            }
            else {
                if(!(isNegative)) {
                    abs();
                    BrobInt temp = subSameSigns(abs(value));
                    return new BrobInt("-" + temp.toString());
                }
                else {
                    abs();
                    BrobInt temp = subSameSigns(abs(value));
                    return temp;
                } 
            }    
    }
        
    // returns a BrobInt whose value is the sum of this plus the argument
    public BrobInt addSameSigns( BrobInt value ) {
        int size = (storageSize > value.digitStorage.size()) ? value.digitStorage.size() : storageSize; 
        int tensPlace = 0;
        int counter = 0;
        String strTemp = "";
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
        return new BrobInt(parseNegatives(strTemp));
    }
    
    public String parseNegatives(String value) {
        String temp = "";
        int x = 0;
        if(value.length() > 0) {
            temp = temp + value.substring(x, ++x);
        }
        for(; x < value.length(); x++ ) {
            if(value.substring(x, x+1).equals("-")) {
                continue;
            }
            
            temp = temp + value.substring(x, x+1); 
        }
        return temp;
    }
    
    // returns a BrobInt whose value is the difference of this minus the argument
    //SubtractLarger from smalller and worry about signs later
    public BrobInt subSameSigns( BrobInt value ) {
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
        return abs(calculation);
    }
    
    // returns a BrobInt whose value is the product of this times the argument
    //WORKS
    public BrobInt multiply( BrobInt value ) {
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
            calculation = new BrobInt(calculation.addSameSigns(additionValues.get(x)).toString());
        }
        return calculation;
    }

    // returns a BrobInt whose value is the quotient of this divided by the argument
    public BrobInt divide( BrobInt value ) {
        BrobInt counter = new BrobInt("0");
        BrobInt temp = new BrobInt("0");
        while(true) {
            temp = temp.add(value);
            if(compareTo(temp) == -1) {
                break;
            }
            counter = counter.add(ONE);
        }
        return new BrobInt(counter.toString()); 
    }
   
    // returns a BrobInt whose value is the remainder of this divided by the argument
    public BrobInt remainder( BrobInt value ) {
        
        return subSameSigns(value.multiply(divide(value)));
        
    }
    
    // returns the decimal string represention of this BrobInt
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
    
    // returns -1/0/1 as this BrobInt is numerically less than/equal to/greater than the value passed as the argument
    /*
    public int compareTo( BrobInt value ) {
        if(value.toString().equals(toString())) {
            return 0; //Depends on whether 0s are allowed at the beginning
        }
        else if(toString().compareTo(value.toString()) < 0) {
            return -1;
        }
        else {
            return 1;
        }
    }
    */
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
    
    // returns true if x is a BrobInt whose value is numerically equal to this BrobInt
    public boolean equals( Object x ) {
        if(x.toString().equals(toString())) {
            return true;
        }
        return false;
    }
    
    // a BrobInt "static factory" for constructing BrobInt out of longs
    public static BrobInt valueOf( long value ) {
        String strValue = "" + value;
        BrobInt br = new BrobInt(strValue);
        return br;
        
    } 

    public static void main(String[] args) {
        while(true) {
        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard2 = new Scanner(System.in);
        System.out.println("First Digit?");
        String one = keyboard.nextLine();
        System.out.println("Second Digit?");
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
        System.out.println("First Digit? (-)");
        String five = keyboard5.nextLine();
        System.out.println("Second Digit? (-)");
        String six = keyboard6.nextLine();
        BrobInt fifth = new BrobInt(five);
        BrobInt sixth = new BrobInt(six);
        System.out.println("\n" + fifth.multiply(sixth).toString());
        }
    }
 }
 
 