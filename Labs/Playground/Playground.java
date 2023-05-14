/**
 * Name : Darryl Chan
 * Matric. No : A0177482U
 */

import java.util.*;
class Graph{
    int start;
    int numPlayground;
    ArrayList<Integer> dist = new ArrayList<>();
    Node[] nodes;
    PriorityQueue<DistanceWithNode> pq = new PriorityQueue<>();

    public Graph(int s, int n, Node[] nodelist){
        start = s;
        numPlayground = n;
        nodes = nodelist;
        for(int i = 0; i<=n; i++){
            if(i == s){
                dist.add(0);
            }
            else{
                dist.add(Integer.MAX_VALUE);
                }
        }
    }

    public void decreaseKey(Neighbour n){
        int totalCost = dist.get(n.start).intValue() + n.cost;
        int endCost = dist.get(n.end).intValue();
        if( totalCost < endCost){
            dist.set(n.end, Integer.valueOf(totalCost));
            DistanceWithNode newSource = new DistanceWithNode(totalCost,n.end);
            pq.offer(newSource);
        }
    }

    public void findSSP(){

        DistanceWithNode source = new DistanceWithNode(0,start);
        pq.offer(source);

        int count = numPlayground;

        while(count > 0 && !pq.isEmpty()){
            DistanceWithNode dwn = pq.poll();
            if(dwn.dist != dist.get(dwn.node).intValue()){continue;}
            else{
                count--;
                Node process = nodes[dwn.node];
                int lengthNeighbour = process.neigh.size();

                for (int j = 0; j <lengthNeighbour; j++){
                    Neighbour neigh = process.neigh.get(j);

                    this.decreaseKey(neigh);


                }
            }

            
        }
    }


}

class DistanceWithNode implements Comparable<DistanceWithNode>{
    int dist;
    int node;

    public DistanceWithNode(int d, int n){
        dist = d;
        node = n;
    }
    @Override
    public int compareTo(DistanceWithNode d){
        if(dist > d.dist){return 1;}
        else if (dist < d.dist){return -1;}
        else{return 0;}
    }
}

class Node{
    
    int vertex;
    ArrayList<Neighbour> neigh = new ArrayList<>();

    public Node(int v){
        vertex = v;
    }

    public void addNeighbour(Neighbour n){
        neigh.add(n);
    }


}

class Neighbour{
    int start;
    int end;
    int cost;

    public Neighbour(int s, int e, int c){
        start = s;
        end = e;
        cost = c;
    }
}

public class Playground {
    Graph t1;
    Graph t2;
    Graph timmy;
    int start;
    int[] fList;
    int[] minDist;
    int magicDist;
    //int min = 2147483647;



    public Playground(Graph tunnel1, Graph tunnel2, Graph timmyStart, int s, int[] friendList, int md){
        t1 = tunnel1;
        t2 = tunnel2;
        timmy = timmyStart;
        start = s;
        fList = friendList;
        minDist = new int[friendList.length];
        magicDist = md;
    }

    public void findMin(){
        StringBuilder sb = new StringBuilder();
        int t1End = t2.start;
        int t2End = t1.start;

        int count1;
        int count2;
        int friendAt;
        int count3;

        for(int i = 0; i < fList.length; i++){
            friendAt = fList[i];
            count1 = t1.dist.get(start).intValue() + t2.dist.get(friendAt).intValue() + magicDist;
            count2 = t2.dist.get(start).intValue() + t1.dist.get(friendAt).intValue() + magicDist;
            count3 = Math.min(count1, count2);
            

            if(count3 == timmy.dist.get(friendAt).intValue()){
                minDist[i] = 1;
            }
        }
        for(int i = 0; i < fList.length; i++){
            if ( minDist[i] == 1){
                sb.append(fList[i] + " ");
            }
        }
        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb.toString());

    }

    public static void main(String args[]) {
        Kattio io = new Kattio(System.in, System.out);

        int numPlayground = io.getInt();
        int commands = io.getInt();
        int friend = io.getInt();

        int start = io.getInt();
        int tunnelStart = io.getInt();
        int tunnelEnd = io.getInt();
        
        Node[] nodes = new Node[numPlayground+1];
        for(int i = 1; i <= numPlayground; i++){
            nodes[i] = new Node(i);
        }

        int magicTunnelCost = 0;

        for(int i = 0; i < commands; i++){
            //System.out.println(i);
            int vStart = io.getInt();
            int vEnd = io.getInt();
            int cost = io.getInt();
            if ((vStart == tunnelStart && vEnd == tunnelEnd) || vEnd == tunnelStart && vStart == tunnelEnd){
                //System.out.println("hahaha");
                magicTunnelCost = cost;
            }
            Neighbour connect = new Neighbour(vStart, vEnd, cost);
            Neighbour connect2 = new Neighbour(vEnd, vStart, cost);
            nodes[vStart].addNeighbour(connect);
            nodes[vEnd].addNeighbour(connect2);
        }

        int[] friendList = new int[friend];
        for(int i = 0; i < friend; i++){
            friendList[i] = io.getInt();
        }
        Arrays.sort(friendList);

        Graph t1 = new Graph(tunnelStart, numPlayground, nodes);
        t1.findSSP();
        Graph t2 = new Graph(tunnelEnd, numPlayground, nodes);
        t2.findSSP();
        Graph timmyStart = new Graph(start, numPlayground,nodes);
        timmyStart.findSSP();
        
        Playground runner = new Playground(t1, t2, timmyStart, start, friendList, magicTunnelCost);
        runner.findMin();


  }
}















