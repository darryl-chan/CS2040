/**
 * Name : Darryl Chan
 * Matric. No : A0177482U
 */

import java.util.*;
public class AmusementPark {
  
  //The head points to the first non empty index and the tail points to the first empty index
  //Then tail - head = number of elements in this ride list
  
  public ArrayList<Integer> list = new ArrayList<Integer>();
  public int head = 0; // points to the first non empty index
  public int tail = 1; // points to the frrst empty index
  public int day = 1; // Which day is this
  public int invCommand = 0; // Number of invalid commands given
  public ArrayList<String> listOfEachDay = new ArrayList<String>(); //Makes a full String list of rides each day
  
  //getters
  public int getHead(){return head;}
  public int getTail(){return tail;}
  public int getDay(){return day;}
  public ArrayList<Integer> getList(){return list;}
  public int getInvCommand(){return invCommand;}
  public ArrayList<String> getListOfEachDay(){return listOfEachDay;}

  //Reset the list 
  public void start(int x){
      this.list.clear();
      Integer nInt = Integer.valueOf(x);
      this.list.add(nInt);
      this.head = 0;
      this.tail = 1;
  }

  // Add a new ride at the end and increment tail by 1 (pointing to the new empty element)
  // O(1) operation
  public void nextRide(int ride){
      Integer rideNumber = Integer.valueOf(ride);
      this.list.add(getTail(), rideNumber);
      this.tail += 1;
  }

  //this.getTail() - this.getHead() indicates the amount of element if the amount removed is more than the element
  //it is invalid
  //Precondition: index <= elements in the arraylist
  //Instead of deleting index ( 0 to n) which is an O(n) operation, we move the head pointer to the index and treat
  //the elements before this index pointer to be empty which is an O(1) operation
  public void removeFront(int index){
      if( this.getTail() - this.getHead() < index){this.invCommand += 1; return;}
      this.head += index;
  }

  //Similar to removeFront
  //Precondition: index <= elements in the arraylist
  public void removeBack(int index){
      if( this.getTail() - this.getHead() < index){this.invCommand += 1; return;}
      this.tail -= index;
  }

  //Calls RemoveFront changes the pointer to the index(removing useless elements at the front) then changing the element
  // O(1)
  //Precondition: index <= elements in the arraylist
  public void changeFront(int index, int x){
      if( this.getTail() - this.getHead() < index){this.invCommand += 1; return;}
      this.removeFront(index - 1);
      Integer nInt = Integer.valueOf(x);
      this.getList().set(this.getHead(), nInt);
  }

  //Similar to changeFront
  //Precondition: index <= elements in the arraylist
  public void changeBack(int index, int x){
      if( this.getTail() - this.getHead() < index){this.invCommand += 1; return;}
      this.removeBack(index - 1);
      Integer nInt = Integer.valueOf(x);
      this.getList().set(this.getTail() - 1, nInt);
  }

  // Stores output in an array list first
  // Increment day by 1
  public void endDay(){
      int[] arr = new int[this.getTail() - this.getHead()];
      for (int i = this.getHead(); i < this.getTail(); i++){
          arr[i - this.getHead()] = this.getList().get(i).intValue();
      }
      this.getListOfEachDay().add("Day " + this.getDay() + ": " + Arrays.toString(arr));
      this.day += 1;
  }

  // Create a stringbuilder to store no of invalid commands
  // then append the ridelist output for each day
  public void endGame(){
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < this.getInvCommand(); i++){
          sb.append("Invalid command\n");
  
      }
      for (int j = 0; j < this.getListOfEachDay().size(); j++){
          sb.append(this.getListOfEachDay().get(j)+"\n");
      }
      System.out.print(sb.toString());
  }

  public static void main(String args[]) {
    Kattio io = new Kattio(System.in, System.out);

    AmusementPark ride = new  AmusementPark();

    while(io.hasMoreTokens()){
        String command = io.getWord();
        if (command.equals("START")){
            String word = io.getWord();// "RIDE:"
            int rideNumber = io.getInt();
            ride.start(rideNumber);
        }
        else if(command.equals("NEXT")){
            String word = io.getWord();
            if (word.equals("RIDE:")){
                int rideNumber = io.getInt();
                ride.nextRide(rideNumber);
            }
            else{
                ride.endDay();
            }
        }
        else if(command.equals("DELETE")){
            String word = io.getWord();
            String trash = io.getWord(); // "RIDE:"
            if (word.equals("FRONT")){
                int x = io.getInt();
                ride.removeFront(x);
            }
            else{
                int x = io.getInt();
                ride.removeBack(x);
            }
        }
        else if(command.equals("CHANGE")){
            String word = io.getWord();
            String trash = io.getWord(); // "RIDE:"
            if (word.equals("FRONT")){
                int x = io.getInt();
                int a = io.getInt();
                ride.changeFront(x,a);
            }
            else{
                int x = io.getInt();
                int a = io.getInt();
                ride.changeBack(x,a);
            }
        }
        else{
            ride.endDay();
            ride.endGame();
        }
     }
  }
}


















