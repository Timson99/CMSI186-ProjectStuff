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
                    if(value.substring(x, x+1).equals("-")) {
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
    
    public BrobInt add( BrobInt value ) {
        if(isNegative != value.isNegative && abs(value).equals(abs(new BrobInt(toString())))) {
            return ZERO;
        }
        if(isNegative == value.isNegative) {
            if(isNegative) {
                BrobInt temp = abs(new BrobInt(toString())).addSameSigns(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            return abs(new BrobInt(toString())).addSameSigns(abs(new BrobInt(value.toString())));
        }
        else if(abs(new BrobInt(toString())).compareTo(abs(new BrobInt(value.toString()))) == 1) {
            //System.out.println("Test1");
            if(isNegative) {
                BrobInt temp = abs(new BrobInt(toString())).subDifference(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            else {
                return abs(new BrobInt(toString())).subDifference(abs(new BrobInt(value.toString())));
            }
        }
        else {
            //System.out.println("Test2");
            if(!(isNegative)) {
                BrobInt temp = abs(new BrobInt(toString())).subDifference(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            else {
                return abs(new BrobInt(toString())).subDifference(abs(new BrobInt(value.toString())));
            }  
        }
       
    }
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
                BrobInt temp = abs(new BrobInt(toString())).addSameSigns(abs(new BrobInt(value.toString())));
                return new BrobInt("-" + temp.toString());
            }
            else {
                return abs(new BrobInt(toString())).addSameSigns(abs(new BrobInt(value.toString())));
            }
        }
        else {
            if(isNegative) {
                    BrobInt temp = abs(new BrobInt(toString())).addSameSigns(abs(new BrobInt(value.toString())));
                    return new BrobInt("-" + temp.toString());
            }
            else {
                    return abs(new BrobInt(toString())).addSameSigns(abs(new BrobInt(value.toString())));
            } 
        }    
    }
        
    // returns a BrobInt whose value is the sum of this plus the argument
    public BrobInt addSameSigns( BrobInt value ) {
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
    
    /* public String parseNegatives(String value) {
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
    } */
    
    // returns a BrobInt whose value is the difference of this minus the argument
    //SubtractLarger from smalller and worry about signs later
    public BrobInt subDifference( BrobInt value ) {
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
    
    public BrobInt multiply( BrobInt value ) {
        BrobInt calculation = new BrobInt(abs(new BrobInt(toString())).multiplyAbs(new BrobInt(abs(new BrobInt(value.toString())).toString())).toString());
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
    // returns a BrobInt whose value is the product of this times the argument
    //WORKS, only acccurate to 18 integers places
    public BrobInt multiplyAbs( BrobInt value ) {
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
        return abs(calculation);
    } 
    
    /*public BrobInt multiply( BrobInt value ) {

        BrobInt calculation = new BrobInt("0");
        BrobInt counter = new BrobInt("0");
        BrobInt tempValue = new BrobInt((value).toString());
        BrobInt tempThis = new BrobInt(toString());

        while(!counter.equals(abs(tempValue))) { 
            calculation = new BrobInt(abs(tempThis).add(calculation).toString());
            counter = new BrobInt(counter.add(ONE).toString());
        }
        if(isNegative == value.isNegative) {
            return calculation;
        }
        else {
            return new BrobInt("-" + calculation.toString());
        }
    } */
            
    

    // returns a BrobInt whose value is the quotient of this divided by the argument
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
   
    // returns a BrobInt whose value is the remainder of this divided by the argument
    public BrobInt remainder( BrobInt value ) {
        if(abs(new BrobInt(toString())).compareTo(abs(new BrobInt(value.toString()))) == -1)
            return new BrobInt(toString());
        
        BrobInt calculation = removeZeroes(abs(new BrobInt(toString())).subDifference(abs(new BrobInt(value.toString())).multiply(abs(new BrobInt(toString())).divide(abs(new BrobInt(value.toString()))))));
        
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
        }
    }
 }
 
 