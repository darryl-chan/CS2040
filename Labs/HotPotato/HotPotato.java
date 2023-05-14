/**
 * Name : Darryl Chan
 * Matric. No : A0177482U
 */

import java.util.*;
public class HotPotato{
    public int n; // Number of people
    public int k; // Turns before exploding
    public int m; // Amount of people left
    public int lowest;

    public Queue<Integer> q = new LinkedList<Integer>();


    public HotPotato(int a, int b, int c){
        n = a;
        k = b;
        m = c;
    }


    public void queue(Integer x){
        this.q.add(x);
    }

    public void loopQueue(){
        Integer curr = q.poll();
        this.queue(curr);
    }

    public void deQueue(){
        this.q.remove();
    }
    
    public void fill(){
        Integer x;
        for(int i = 1; i <= n; i++){
            x = Integer.valueOf(i);
            this.queue(x);
        }
    }

    public int removeAndPeek(){
        return this.q.poll().intValue();
    }

    public int peek(){
        return this.q.peek().intValue();
    }

    public void findLowest(){
        int lowest = this.n;
        for(int i = 0; i< this.m; i++){
            //System.out.println("current: " + this.peek() + ", lowest: " + lowest);
            if (this.peek() < lowest){
                lowest = this.peek();
            }
        this.loopQueue();
    }
     this.lowest = lowest;
    }

    public void play(){
        int peopleLeft = this.n;
        int counter = 1;
        while(peopleLeft != m){
            if (counter == k){
                this.deQueue();
                counter = 1;
                peopleLeft--;
            }
            else{
                this.loopQueue();
                counter++;
            }
        }
        this.findLowest();
    }
    
    public void print(){
        this.sort();
        int[] arr = new int[m];
        for(int i = 0; i < m; i++){
            arr[i] = this.removeAndPeek();
        }
        System.out.println(Arrays.toString(arr));
    }

    public void sort(){
        while (this.peek() != this.lowest){ 
            //System.out.println("current :" + this.peek() + ", the lowest val: " + this.lowest);
            this.loopQueue();}
    }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int m = sc.nextInt();
    HotPotato game = new HotPotato(n, k, m);
    game.fill();
    game.play();
    game.print();
  }
}
