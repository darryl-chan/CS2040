/**
 * Name         : Darryl Chan
 * Matric. No   : A0177482U
*/

import java.util.*;

public class Bracketflips {
    //We keep 2 linkedlist of bracket sequences
    //p1 is the first priority sequence while p2 is 2nd priority
    //and p2 is the reveresed of p1
    //so when we receive the rotate command we just swap pointers
    //So we incur only O(1) cost
    LinkedList<String> p1 = new LinkedList<String>();
    LinkedList<String> p2 = new LinkedList<String>();

    //The hashmap of the reversed bracket
    Map<String,String> hash = new HashMap<String,String>();

    //Create the hashmap of reversed bracket sequence
    private void run() {
        hash.put("(",")");
        hash.put("[","]");
        hash.put("{","}");

        hash.put(")","(");
        hash.put("]","[");
        hash.put("}","{");
    }

    //Precon: A bracket string
    //Adds s to the front of p1
    //Adds the opposite of s to the back of p2
    public void addFront(String s){
        p1.addFirst(s);
        p2.addLast(hash.get(s));
    }

    //Precon: Same as above
    //Does the same as above but in reverse
    public void addBack(String s){
        p1.addLast(s);
        p2.addFirst(hash.get(s));
    }

    //PreCon receives rotate command and p1 and p2
    //equal length
    //Switches p1 and p2 position
    //Now p2 will have priority 1 and 1 to 2
    public void rotate(){
        LinkedList<String> temp = p1;
        p1 = p2;
        p2 = temp;
    }

    //p1 is filled with brackets
    //Prints the bracket sequence in order
    public void print(){
        StringBuilder sb = new StringBuilder();
        while (!p1.isEmpty()){sb.append(p1.poll());}
        System.out.println(sb.toString());
        //System.out.println("size: " + p1.size());
        //System.out.println(p1.toString());
    }

    public static void main(String args[]) {
        Bracketflips runner = new Bracketflips();
        runner.run();

        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();

        for(int i = 0; i < n; i++){
            String command = io.getWord();

            if (command.equals("FRONT")){
                String bracket = io.getWord();
                runner.addFront(bracket);
            }
            else if (command.equals("BACK")){
                String bracket = io.getWord();
                runner.addBack(bracket);
            }
            else{//rotate
                runner.rotate();
            }
        }

        runner.print();
    }
}
