/**
 * Name:Darryl Chan
 * Matric. No: A0177482U
 */

import java.util.*;
public class RhythmGame {
    public boolean altMode = false;
    public boolean putLeft = false;
    public int value;

    public RhythmGame head;
    public RhythmGame tail;
    public RhythmGame next;

    public int numNodes = 0;
    public String combos;
    public int comboLength = 0;

    public RhythmGame(int x){
        if (x >= 0){value = x;}
    }

    public void setNext(RhythmGame x){this.next = x;} 
    public void setHead(RhythmGame x){this.head = x;}
    public void setTail(RhythmGame x){this.tail = x;}
    public void setMaxCombo(String x){this.combos = x;}
    public void setComboLength(int x){this.comboLength = x;}

    public RhythmGame getHead(){return this.head;}
    public RhythmGame getTail(){return this.tail;}
    public RhythmGame getNext(){return this.next;}

    public int getNumNodes(){return this.numNodes;}
    public int getVal(){return this.value;}
    public boolean getAlt(){return this.altMode;}
    public int getComboLength(){return this.comboLength;}
    public String getMaxCombo(){return this.combos;}

    public boolean getAltMode(){return this.altMode;}
    public boolean getPutLeft(){return this.putLeft;}

    public void toggleAlt(){this.altMode = !this.getAlt();}
    public void toggleDir(){this.putLeft = !this.getPutLeft();}

    public void addRight(int x){
        RhythmGame point = new RhythmGame(x);
        //System.out.print(point.getVal());
        if (this.numNodes == 0){
            this.setHead(point);
            this.setTail(point);
        }
        else{
            this.getTail().setNext(point);
            this.setTail(point);
        }
        this.numNodes++;
    }

    public void addLeft(int x){
        RhythmGame point = new RhythmGame(x);
        if (this.numNodes == 0){
            this.addRight(x);
        }
        else{
            point.setNext(this.getHead());
            this.setHead(point);
            this.numNodes++;
        }
    }

    public void intAnalyze(int x){
        if (x == 0){this.saveCombo();}
        else if (x == 5){this.toggleDir();}
        else if (x == 6){
            if (this.getAltMode()){
                this.toggleAlt();
            }
            else{
                this.toggleAlt();
            }
        }
        else{
            
            if(this.getPutLeft()){
                this.addLeft(x);
            }
            else{
                this.addRight(x);
            }
        }
        if(this.getAltMode()){this.toggleDir();}
   }

    public void endGame(){
        this.saveCombo();
        this.print();
    }

    public void saveCombo(){//get 0 or end
        if (this.getNumNodes() > this.getComboLength()){
            int amt = this.getNumNodes();
            RhythmGame curr = this.getHead();
            int[] arr = new int[amt];
            for(int i = 0; i < amt; i++){
                arr[i] = curr.getVal();
                curr = curr.getNext();
            }
            this.setMaxCombo(Arrays.toString(arr));
            this.setComboLength(amt);
        }
        this.setHead(null);
        this.setTail(null);
        this.numNodes = 0;
    }

    public void print(){
        System.out.println(this.getComboLength());
        System.out.println(this.getMaxCombo());
    }
    //resty

  public static void main(String args[]) {
      Kattio io = new Kattio(System.in, System.out);
      RhythmGame game = new RhythmGame(-1);
      //game.addRight(3);
      //System.out.println(game.getHead().getVal());
      //RhythmGame test = new RhythmGame(2);
      //System.out.println(test.getVal());
      int command = io.getInt();
      for (int i = 0; i < command; i++){
          int x = io.getInt();

          //System.out.println("add "+ x);
          game.intAnalyze(x);
          //game.print();
      }
      game.endGame();
  }
}
