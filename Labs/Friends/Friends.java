/**
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */

import java.util.*;
public class Friends {
    ArrayList<String> cafeNames = new ArrayList<String>();
    long max = 0;

    public void addCafe(GameCafe  cafe){
        if (cafe.maxFriends < max){return;}
        else if (cafe.maxFriends == max){cafeNames.add(cafe.name);}
        else{//cafe.maxFriends > max
            max = cafe.maxFriends;
            cafeNames.clear();
            cafeNames.add(cafe.name);
        }
    }

    public void print(){
        Collections.sort(cafeNames);
        System.out.println(max);
        for (int i = 0; i < cafeNames.size(); i++){
            System.out.println(cafeNames.get(i));
        }
    }


public static class GameCafe{
    public String name;
    public long[] start;
    public long[] end;
    public long hours;
    public long maxFriends = 0;

    public GameCafe(String n, long[] s, long[] e, long h){
        name = n;
        start = s;
        end = e;
        hours = h;
    }

    public void initialize(){
        Arrays.sort(start);
        Arrays.sort(end);
        LinkedList<Long> startQ = new LinkedList<Long>();
        LinkedList<Long> endQ = new LinkedList<Long>();
        for (int i = 0; i < end.length; i ++ ){
            startQ.addLast(Long.valueOf(start[i]));
            endQ.addLast(Long.valueOf(end[i]));
        }
        this.findMax2(startQ, endQ);
    }


    public void findMax2(LinkedList<Long> startQ, LinkedList<Long> endQ){
        long currFriend = 0;
        long endPoint;
        long startPoint;

        while(startQ.peek() != null){
            startPoint = startQ.poll().longValue();
            currFriend++;
            endPoint = startPoint - hours;

            while(endPoint > endQ.peek().longValue()){
                currFriend--;
                endQ.poll();
            }
            if(currFriend > maxFriends){maxFriends = currFriend;}
        }
    }
}

  public static void main(String args[]) {
    Kattio io = new Kattio(System.in, System.out);
    int numGameCafes = io.getInt();
    long time = io.getLong();
    Friends gameCafe = new Friends();
    for (int i = 0; i < numGameCafes; i++){
        String cafeName = io.getWord();
        int customers = io.getInt();
        long[] start = new long[customers];
        long[] end = new long[customers];
        
        for (int j = 0; j < customers; j++){
            start[j] = io.getLong();
            end[j] = io.getLong();
        }
        GameCafe caf = new GameCafe(cafeName, start, end, time);
        //System.out.println("create game cafe");
        caf.initialize();
        gameCafe.addCafe(caf);
        //System.out.println(caf.name + " max : " + caf.maxFriends);
    }
    gameCafe.print();
  }
}
