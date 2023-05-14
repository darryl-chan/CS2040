 /*
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */

import java.util.*;
public class Entrepreneurship {
    Stack<PizzaOrders> stck = new Stack<PizzaOrders>();
    int capacity;

    public int getCap(){return capacity;}
    public void increaseCap(int x){capacity += x;}

    public Entrepreneurship(int cap){ capacity = cap;}

    public void cancel(int x){
        PizzaOrders pOrders; 
        for (int i = 0; i < x; i++){
            pOrders = stck.pop();
            this.increaseCap(pOrders.getAmtPizza());
        }
    }

    public void push(PizzaOrders x){
        stck.push(x);
    }
    
    public void count(){
        double x = 0;
        while (!stck.empty()){
            double y = stck.pop().getValPizza();
            x += y;

        System.out.println(String.format("%.1f", x));

    } 

    public void read(int orders ,String dir, Kattio io){
        double[] arr = new double[orders];
        int numberPizzas;
        double price;
        
        Queue<Pizza> q = new LinkedList<Pizza>();
        Stack<Pizza> s = new Stack<Pizza>();
        
        for (int i = 0; i<orders ; i++){
            numberPizzas = io.getInt();
            price = io.getDouble();
            Pizza piz = new Pizza(numberPizzas, price);
            if (dir.equals("L")){
                q.offer(piz);
            }
            else{
                s.push(piz);
            }
        }
        if (dir.equals("L")){this.readLeft(orders, q); return;}
        else{this.readRight(orders,s); return;}
    }
    
    public void readLeft(int orders, Queue<Pizza> q){
        Pizza p;
        PizzaOrders pOrders = new PizzaOrders();
        for (int i = 0; i < orders; i++){
            p = q.poll();
            if (p.getAmt() <= capacity){
                capacity -= p.getAmt();
                pOrders.insert(p);
            }
            else{
                p = new Pizza(0,0);
                pOrders.insert(p);
            }
        }
        this.push(pOrders);
    }

    public void readRight(int orders, Stack<Pizza> s){
        Pizza p;
        PizzaOrders pOrders = new PizzaOrders();
        for (int i = 0; i < orders; i++){
            p = s.pop();
            if (p.getAmt() <= capacity){
                capacity -= p.getAmt();
                pOrders.insert(p);
            }
            else{
                p = new Pizza(0,0);
                pOrders.insert(p);
            }
        }
        this.push(pOrders);
    }

class Pizza{
    public int amt = 0;
    public double val = 0;
    
    public Pizza(int a, double v){
        amt = a;
        val = v;
    }

    public double getTotalPrice(){return amt*val;}
    public int getAmt(){return amt;}
    public double getVal(){return val;}
}


class PizzaOrders{
    public int amtPizza = 0;
    public double valPizza = 0;
    public Stack<Pizza> stck = new Stack<Pizza>();

    public void insert(Pizza p){
        amtPizza+= p.getAmt();
        valPizza += p.getTotalPrice();
    }

    public int getAmtPizza(){return amtPizza;}
    public double getValPizza(){return valPizza;}

}
  public static void main(String args[]) {
    Kattio io = new Kattio(System.in,System.out);
    int n = io.getInt();
    int m = io.getInt();
    Entrepreneurship orders = new Entrepreneurship(m);

    String dir;
    String command;
    for (int i = 0; i < n; i ++){
         command = io.getWord();
    
         if (command.equals("ADD")){
             int amtAdd = io.getInt();
             dir = io.getWord();
             orders.read(amtAdd, dir, io);
             
         }
         else{
             int amtCancel = io.getInt();
             orders.cancel(amtCancel);
         }
     }
    orders.count();
  }
}
