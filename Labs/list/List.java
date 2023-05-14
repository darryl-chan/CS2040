/**
 * Name         :Darryl Chan
 * Matric. No   :A0177482U
*/

import java.util.*;

class Node{
    //long idx;
    long val;
    boolean alt;

   public Node(long value, boolean alternate){
        
        val = value;
        alt = alternate;
    }
}

public class List {
    long start = 0;//let this point to empty slot
    long end = 0;//let this point to the first back item
    Map<Long,Node> hash = new HashMap<Long,Node>();

    TreeMap<Long,Node> map = new TreeMap<Long,Node>();
    long alt1Sum = 0;
    long alt2Sum = 0;

    public void pFront(long x){
        if(start == end){
            Node node = new Node(x,true);
            alt1Sum += x;
            hash.put(Long.valueOf(start),node);
            start--;
        }
        else{
            Node front = hash.get(Long.valueOf(start+1));

            boolean alt = !front.alt;

            Node node = new Node(x, alt);
            hash.put(Long.valueOf(start),node);

            start--;
            
            if(alt){alt1Sum += x;}
            else{alt2Sum += x;}
        }
    }
    
    public void pBack(long x){
        if(start==end){//is empty
            Node node = new Node(x,true);
            alt1Sum += x;
            hash.put(Long.valueOf(start),node);
            start--;
        }
        else{
            Node back = hash.get(Long.valueOf(end));
            //System.out.println(back);

            boolean alt = !back.alt;

            Node node = new Node(x, alt);
            hash.put(Long.valueOf(end+1),node);

            end++;

            if(alt){alt1Sum += x;}
            else{alt2Sum += x;}
        }
    }

    public void calculate(){
        //System.out.println(map.values().toString());
        Node front = hash.get(Long.valueOf(start+1));

        boolean alt = front.alt;

        if(alt){System.out.println(alt1Sum - alt2Sum);}
        else{System.out.println(alt2Sum - alt1Sum);}
    }

    public void dFront(){

        Node front = hash.get(Long.valueOf(start+1));
        
        long value = front.val;
        boolean alt = front.alt;

        if(alt){alt1Sum -= value;}
        else{alt2Sum -= value;}

        System.out.println(value);
        hash.remove(Long.valueOf(start+1));

        start++;

    }
    
    public void dBack(){

        Node back = hash.get(Long.valueOf(end));

        long value = back.val;
        boolean alt = back.alt;

        if(alt){alt1Sum -= value;}
        else{alt2Sum -= value;}

        System.out.println(value);
        map.remove(Long.valueOf(end));

        end--;
    }
    
    public void get(long idx){
        
        //Node front = hash.get(Long.valueOf(start+1));

        Node node = hash.get(Long.valueOf(start + idx));

        
        //System.out.println("size: " + map.size());
        //System.out.println("get: " + idx);

        //System.out.println("key: " + frontKey.longValue());
        //System.out.println("index find: " + (frontIdx + idx - 1));

        //Node nodeAtIdx = map.get(Long.valueOf(frontIdx + idx - 1));
        
        //System.out.println(nodeAtIdx);

        System.out.println(node.val);
    }
      



    private void run() {
        // implement your "main" method here
    }

    public static void main(String args[]) {
        List runner = new List();
        runner.run();

        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();

        for (int i = 0; i < n; i++){
            String command = io.getWord();
            long x;
            //System.out.println(i);
            if (command.equals("pfront")){
                x = io.getLong();
                runner.pFront(x);
            }
            else if (command.equals("pback")){
                x = io.getLong();
                runner.pBack(x);
            }
            else if (command.equals("cal")){
                runner.calculate();
            }
            else if (command.equals("dfront")){
                runner.dFront();
            }
            else if (command.equals("dback")){
                runner.dBack();
            }
            else{//get
                long idx = io.getLong();
                runner.get(idx);
            }

        }














    }
}
