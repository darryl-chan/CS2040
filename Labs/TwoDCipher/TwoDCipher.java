/**
 * Name : Darryl Chan
 * Matric. No :A0177482U
 */
import java.util.*;
public class TwoDCipher {
  public static String characterConverter( char ch, int offset){
   // int  ascii A = 97;
    int newascii =( ((int) ch - 97 + offset) % 26) + 97;
    return Character.toString(newascii);
  }
 /* public static String lineConverter(String str, int row, int col){
 * for (int i = 1; i <= row; i++){
 *     for (int j = 1; j<= col; j++){
 *     String offsetString = characterConverter(
 *     }
 }*/
  public static String fn (String str, int row, int col){
    StringBuilder finalStr = new StringBuilder();
    for (int i = 0; i < row*col;i++){
        int position = i;
        int charRow = (position / col)+1;
        int charCol = (position % col)+1;
        int offset = charRow * charCol;
        finalStr.append(characterConverter(str.charAt(i),charRow*charCol));
        if(( position + 1)% col == 0 && i+1 != row*col){finalStr.append("\n");}
    }
    return (finalStr.toString());
  }
  public static void main(String args[]) {
  // System.out.println(fn("aaaaaaaaaaaaaaaaaaaa",4,5));
   Scanner s = new Scanner(System.in);
  // System.out.println(s.next());
  // System.out.println(s.next());
  // System.out.println(s.next());
   int row = s.nextInt();   
   int col = s.nextInt();
   String str = s.next();
   System.out.println(fn(str,row,col));
  }
}
