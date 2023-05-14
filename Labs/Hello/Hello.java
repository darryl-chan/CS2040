/**
 * Name : Darryl Chan
 * Matric. No : A0177482U
 */
import java.util.*;
public class Hello {
  public static int counter(String str){
      int count = 0;
      for (int i = 0; i < str.length()-4; i++){
          if( str.substring(i,i+5).equals("Hello")){count = count + 1;}
          else if (str.substring(i,i+5).equals("World")) { count = count - 1;}
  }
    if(count > 0){ return count;}
    else { return count;}
  }
  public static void main(String args[]) {
    // Here's some I/O boilerplate to get you started.
    Kattio io = new Kattio(System.in);

    io.flush(); /* writes to screen immediately but slows program down */

    String str = "";
    while(io.hasMoreTokens()){
        str = str + io.getWord();
    }
    System.out.println(counter(str));

    io.close();
   
  }
}
