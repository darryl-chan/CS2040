/**
 * Name : Darryl Chan
 * Matric. No : A0177482U
 */

import java.util.*;
public class SquaringArray {
  
  
    //Note that the index for every length of unit is a multiple of the
    //first (lenth) index
    //The 2nd length indexes are reversed but they are multiple of 2
    //The 3rd are not reversed but they are multiple of 3 so on...
    //Create a StringBuilder object, store the items and then print
    public static void printArray(int[] refArray, int length){
        StringBuilder s = new StringBuilder();
        Integer x;
             for (int i = 0; i<length; i++){
                     boolean isReverse = (i%2 == 1);
                     if (!isReverse){
                        for (int j = 0; j<length; j++){
                            x = refArray[j]*(i+1);
                            s.append(x.toString());
                            if (i == length-1 && j == length - 1){break;}
                            s.append(" ");
                        }
                    }
                     else{
                          for (int j = 0; j < length; j++){
                             x = refArray[length -1 - j] * (i+1);
                             s.append((x).toString());
                             if (i == length-1 && j == length - 1){break;}
                              s.append(" ");
                          }
                     }
             }
        System.out.println(s.toString());
           }

  public static void main(String args[]) {
    Kattio io = new Kattio(System.in);
    int length = io.getInt();
    int[] finalArr = new int[length];
    for (int i = 0; i< length; i++){
        int x = io.getInt();
        finalArr[i] = x;
    }
    printArray(finalArr, length);

  }
}
