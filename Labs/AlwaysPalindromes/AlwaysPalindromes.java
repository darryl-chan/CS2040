/**
 * Name : Darryl Chan
 * Matric. No : A0177482U
 */
import java.util.*;
public class AlwaysPalindromes {

  public static Boolean CheckPalindrome(String str){
    //boolean odd = str.length() % 2;
    for (int i = 0; i < (str.length() / 2)+1; i++){
      if (str.charAt(i) != str.charAt(str.length()-i-1)){
        return false;
    }  
  }
    return true;
 }
  public static String CreatePalindrome(String str){
    if (CheckPalindrome(str)){
      return str;
    }
    else{
      String reference = str;
        for (int i = 0; i < str.length(); i++){
        reference = reference + str.charAt(str.length()-i-1);
      }
        return reference;
    }
  }
   
  public static void main(String args[]) {
    //System.out.println(CheckPalindrome("t23432t"));
    //System.out.println(CreatePalindrome("avs"));
    Scanner s = new Scanner(System.in);
    String str = s.next();
    System.out.println(CreatePalindrome(str));
 
  }
}
