/**
 * Name : Darryl Chan  
 * Matric. No : A0177482U
 */

import java.util.*;
public class CardGame {
    public int revNum = 0;
    public int incNum = 0;
    public int[] arr;
    public Kattio io;

    public CardGame(int[] newArr, Kattio newX){
        this.arr = newArr;
        this.io = newX;
    }

    public void increase( int x){ incNum  += x;}

    public void reverse(){ revNum += 1;}

    public int getIncrease(){return incNum;}

    public int getReverse(){return revNum;}

    public int[] getArr(){return arr;}

    public Kattio getIo(){return io;}

    public void actualReverse(){
        if (this.getReverse() % 2 == 0){return;}
        int temp;
        int[] arr = this.getArr();
        int length = arr.length;
        for (int i = 0; i < length /2; i++){
            temp = arr[i];
            arr[i] = arr[length -1 -i];
            arr[length -1 -i] = temp;
        }
    }
     
    public void actualIncrement(){
        int[] arr = this.getArr();
        int length = arr.length;
        for (int i = 0; i < length; i++){
            arr[i] += this.getIncrease();
        }
    }
    
    public void compile(){
       this.actualReverse();
       this.actualIncrement();
       this.revNum = 0;
       this.incNum = 0;

    }

    public void print(StringBuilder sb){
        int[] arr = this.getArr();
        int length = arr.length;
        for (int i = 0; i <length; i++){
            sb.append(arr[i]);
            sb.append(" ");
        }
    }

    public static void staticIncrement(int amt, int index, ArrayList<CardGame> list){
        if (index == -1){
            int length = list.size();
            for (int i = 0; i< length; i++){
                list.get(i).increase(amt);
            }
            
        }
        else{
            list.get(index-1).increase(amt);
        }
    }

    public static void staticReverse(int x, ArrayList<CardGame> list){
        if( x == - 1){
            int length = list.size();
            for (int i = 0; i < length/2 ; i++){
                staticSwap(i+1,length-i, list);
                staticReverse(i+1, list);
                staticReverse(length-i, list);
            }
            if (length % 2 == 1){
                staticReverse(length/2 + 1,list);
            }
            // Reverse All
        }

        else{list.get(x-1).reverse();}
    }

    public static void staticSwap( int x, int y, ArrayList<CardGame> list){
        CardGame temp;
        temp = list.get(x-1);
        list.set(x-1, list.get(y-1));
        list.set(y-1, temp);
    }

    public static void populate( int[] deck, Kattio io){
        int length = deck.length;
        for (int i = 0; i < length; i++){
            deck[i] = io.getInt();
        }
    }

    public static void printStringBuilder( ArrayList<CardGame> list){
        StringBuilder sb = new StringBuilder();
        int length = list.size();
        for (int i = 0; i < length; i++){
            list.get(i).compile();
            list.get(i).print(sb);    
        }
        sb.deleteCharAt( sb.length() - 1);
        System.out.println(sb.toString());
    }

  public static void main(String args[]) {
    // game.addDeck(...);
    Kattio io = new Kattio(System.in, System.out);
    int numInt = io.getInt();
    int lendeck1 = io.getInt();
    int lendeck2 = io.getInt();
    int lendeck3 = numInt - lendeck1 - lendeck2;

    ArrayList<CardGame> game = new ArrayList<CardGame>();

    int[] deck1 = new int[lendeck1];
    int[] deck2 = new int[lendeck2];
    int[] deck3 = new int[lendeck3];
    
    populate(deck1,io);
    populate(deck2,io);
    populate(deck3,io);

    CardGame left = new CardGame(deck1,io);
    CardGame mid = new CardGame(deck2,io);
    CardGame right = new CardGame(deck3,io);

    game.add(left);
    game.add(mid);
    game.add(right);

   // int[] abc = {1,2,3,4};
   // CardGame cgabc = new CardGame(abc, io);
   // cgabc.increase(5);
   //cgabc.reverse();
   // cgabc.compile();

    //game.add(cgabc);
    //printStringBuilder(game);
    //CardGame cgdeck1 = new CardGame(deck1,io);
    //System.out.println(Arrays.toString(deck1));
    //CardGame cgdeck1 = new CardGame(deck2,io);
    //CardGame cgdeck1 = new CardGame(deck3,io);

    //game.add(cgdeck1);
    //game.add(cgdeck2);
    //game.add(cgdeck3);

    int orders = io.getInt();
    //System.out.println("orders: " + orders); 
    String command;
    String innerCommand;
    int x,y;
    for (int i = 0; i < orders; i++){
        command = io.getWord();

        if (command.equals("SWAP")){
            //System.out.print("True");
            x = io.getInt();
            y = io.getInt();
            //System.out.println("Swap " + x + " " + y);
            staticSwap(x,y,game);
        }
        
        else if (command.equals("INCREMENT")){
            innerCommand = io.getWord();
            if ( innerCommand.equals("ALL")){
                x = io.getInt();
                //System.out.println("Increment all " + x);
                staticIncrement(x, -1, game);
            }
            else{
                x = Integer.parseInt(innerCommand);
                y = io.getInt();
                //System.out.println("Increment " + x + " by " + y);
                staticIncrement(y, x, game);
            }
        }

        else{
            innerCommand = io.getWord();
            if (innerCommand.equals("ALL")){
                //System.out.println("Reverse all");
                staticReverse(-1, game);
            }
            else{
                x = Integer.parseInt(innerCommand);
                //System.out.println("Reverse " + x);
                staticReverse(x, game);
            }
        }
        //printStringBuilder(game);
    }
    //System.out.println(game.get(0).getIncrease());
    //System.out.println(Arrays.toString(game.get(0).getArr()));
    //System.out.println("size " + game.size());
    printStringBuilder(game);

    }
  }


















