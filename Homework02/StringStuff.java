/** @StringStuff.java  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File          :  StringStuff.java
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
 
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {


    public static final String evenLetters = "BDFHJLNPRTVXZbdfhjlnprtvxz";
    public static final String oddLetters = "ACEGIKMOQSUWYacegikmoqsuwy";
  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
        
      for(int x=0; x<(s.length()); x++) {
          String letter = s.substring(x, x+1);
          switch(letter) {
          case "A": 
          case "a":
          case "E":
          case "e":
          case "I":
          case "i":
          case "O":
          case "o":
          case "U":
          case "u": return true;
          }    
      }
      return false;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
       int halfString =(s.length())/2;
       for(int x =0; x < halfString; x++) { /*
           System.out.print(s.substring(x,x+1) + s.substring(s.length()-x-1, s.length()-x));
           if(s.substring(x,x+1) != s.substring(s.length()-x-1, s.length()-x));
                return false; */
        if(s.charAt(x) != s.charAt(s.length()-x-1)) 
            return false;
       }
       
      return true;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      
      String evensOnly = "";
      for(int x = 0; x < s.length(); x++) {
          if(evenLetters.indexOf(s.substring(x, x+1)) != -1) 
              evensOnly = evensOnly + s.substring(x, x+1);     
       }
       return evensOnly;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      String oddsOnly = "";
      for(int x = 0; x < s.length(); x++) {
          if(oddLetters.indexOf(s.substring(x, x+1)) != -1) 
              oddsOnly = oddsOnly + s.substring(x, x+1);     
       }
       return oddsOnly;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      String evensOnly = "";
      String duplicates = "";
      for(int x = 0; x < s.length(); x++) {
          if(evenLetters.indexOf(s.substring(x, x+1)) != -1 && duplicates.indexOf((s.substring(x, x+1)).toUpperCase()) == -1) {
              evensOnly = evensOnly + s.substring(x, x+1); 
              duplicates = duplicates + s.substring(x, x+1);
          }
       }
       return evensOnly;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      String oddsOnly = "";
      String duplicates = "";
      for(int x = 0; x < s.length(); x++) {
          if(oddLetters.indexOf(s.substring(x, x+1)) != -1 && duplicates.indexOf((s.substring(x, x+1)).toUpperCase()) == -1) {
              oddsOnly = oddsOnly + s.substring(x, x+1); 
              duplicates = duplicates + (s.substring(x, x+1)).toUpperCase();
          }
       }
       return oddsOnly;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      String reversedString = "";
      for(int x = 0; x < s.length(); x++) {
          reversedString = s.substring(x, x+1) + reversedString;
      }
      return reversedString; 
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) + " true" );
      System.out.println( containsVowel( woof ) + " false" );
      System.out.println( isPalindrome( pal1 ) + " true" );
      System.out.println( isPalindrome( pal2 ) + " false" );
      System.out.println( isPalindrome( pal3 ) + " true" );
      System.out.println( isPalindrome( pal4 ) + " true");
      System.out.println( isPalindrome( pal5 ) + " true");
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) + " Should: RHRLZ");
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) + " Should: RhrLz" );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) + " Should: RhLz" );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) + " Should: yooes" );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) + " Should: YooES" );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) + " Should: YoES" );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) + " Should: ZSLASRAEHER");
   }
} 
 
    