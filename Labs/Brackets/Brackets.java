/**
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */


import java.util.*;
public class Brackets {
    boolean mult = false;
    Stack<String> stack;
    long LARGE =(long) (long) (long) (long) (long) (long) (long) (long)  Math.pow(10,9) + 7;

    public Brackets(){
        stack = new Stack<String>();
    }

    public void get(String s){
        //System.out.println(s);
        if (s.equals("(")){
            stack.push(s);
            mult = !mult;
        }
        else if(s.equals(")")){
            //System.out.println("this is ( : " +  s);
            String a = stack.pop();
            //System.out.println("this is a: " + a);
            stack.pop();
            mult = !mult;
            this.get(a);

        }
        else{//number
            if(stack.isEmpty() || stack.peek().equals("(")){stack.push(s);}
            else{
                Long op1 = Long.valueOf(stack.pop());
                Long op2 = Long.valueOf(s);
                long op3;

                if(mult == false){op3 = (op1.longValue() + op2.longValue())% LARGE;}
                else{op3 = (op1.longValue() * op2.longValue())%LARGE;}//mult = true so multiply
                
                s = String.valueOf(op3);
                this.get(s);
                //stack.push(s);

            }

        }
        //System.out.println(stack.toString());
    }

    public void print(){System.out.println(stack.pop());}

  public static void main(String args[]) {
      Kattio io = new Kattio(System.in, System.out);
      int n = io.getInt();
      Brackets operation = new Brackets();

      for(int i = 0; i < n; i++){
          String command = io.getWord();
          //System.out.println(command);
          operation.get(command);
      }
      operation.print();


      io.close();

  }
}
