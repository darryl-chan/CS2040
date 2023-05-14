/**
 * Name :
 * Matric. No :
 */

import java.util.*;
public class Fishing {
    int capacity;
    long currVal = 0;
    long maxVal = 0;
    Queue<Fish> q = new LinkedList<Fish>();
    
    public Fishing(int n){
        capacity = n;
    }

    public void capture(Fish f){
        if (capacity > 0){
            currVal += f.getVal();
            q.add(f);
            capacity--;
        }
        else{
            Fish thrownFish = q.poll();
            currVal += ( -(thrownFish.getVal()) + f.getVal());
            q.add(f);
        }
        if(capacity == 0 && (currVal > maxVal)){maxVal = currVal;}

    }

    public void finish(){
        System.out.println(maxVal);
    }

public static class Fish{
    public long val;
    public Fish(long x){
       val = x;
    }
    public long getVal(){return val;}
}



  public static void main(String args[]) {
    Kattio io = new Kattio(System.in,System.out);
    int n = io.getInt();//size of array
    int k = io.getInt();//size of net
    Fishing net = new Fishing(k);
    
    Fish f;
    for (int i = 0; i < n; i++){
        f = new Fish(io.getInt());
        net.capture(f);
    }
    net.finish();
  }
}
