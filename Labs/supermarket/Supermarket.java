/**
 * Name         : Darryl Chan
 * Matric. No   : A0177482U
*/

import java.util.*;

class QNode implements Comparable<QNode>{
    long size = 0;
    long num;
    Queue<Long> queue = new LinkedList<Long>();

    public QNode(long number){
        num = number;
    }

    @Override
    public int compareTo(QNode q){
        if(queue.size() > q.queue.size()){return 1;}
        else if (queue.size() < q.queue.size()){return -1;}
        else if (num > q.num){return 1;}
        else if (num < q.num){return -1;}
        else{return 0;}
        //else{return -1;}
    }
}

class ANode implements Comparable<ANode>{
    long anger;
    long num;

    public ANode(long angerNum, long number){
        anger = angerNum;
        num = number;
    }

    @Override
    public int compareTo(ANode a){
        if (anger > a.anger){return 1;}
        else if (anger < a.anger){return -1;}
        else if (num > a.num){return 1;}
        else if (num < a.num){return-1;}
        else{ return 0;}

        //else{return -1;}
    }
}


public class Supermarket {
    TreeSet<QNode> qTree = new TreeSet<QNode>();
    
    TreeSet<ANode> aTree = new TreeSet<ANode>();

    Map<Long,QNode> map = new HashMap<Long,QNode>();

    Map<Long,ANode> aMap = new HashMap<Long,ANode>();

    Kattio io;

    public void createKattio(Kattio kattioObject){
        io = kattioObject;
    }

    public void createQueue(int n){
        for( int i = 1; i <= n; i++){
            long x = (long) i;
            QNode qnode = new QNode(x);
            map.put(Long.valueOf(x),qnode);
            qTree.add(qnode);
        }
    }

    public void join(long x){
        QNode qnode = qTree.pollFirst();
        
        if(qnode.queue.size() == 0){
            ANode anode = new ANode(x, qnode.num);
            aTree.add(anode);
            aMap.put(Long.valueOf(qnode.num),anode);
        }

        qnode.size++;
        qnode.queue.offer(Long.valueOf(x));

        qTree.add(qnode);
        io.println(qnode.num);
    }

    public void serve(){
        //System.out.println("serve aTree size: " + aTree.size());
        ANode anode = aTree.pollFirst();
        QNode qnode = map.get(Long.valueOf(anode.num));
        //System.out.print("qnode: " + qnode);

        aMap.remove(Long.valueOf(qnode.num));

        //System.out.println("inside queueu: " + qnode.queue.toString());

        qTree.remove(qnode);
        //System.out.println("l89 ");
        Long serveNum = qnode.queue.poll();
        qnode.size--;

        //System.out.println("l93 ");
        //System.out.println("qnode size: " + qnode.size);
        if(qnode.queue.size() > 0 ){
            Long head = qnode.queue.peek();
            ANode newAnode = new ANode(head.longValue(),qnode.num);
            aTree.add(newAnode);
            aMap.put(Long.valueOf(qnode.num),newAnode);
        }
        //System.out.println("l 100 ");
        io.println(serveNum.longValue());
        qTree.add(qnode);
    }

    public void move(long x){
        QNode qnode = map.get(Long.valueOf(x));
        //System.out.println("hash get qnode: " + qnode);
        
        //System.out.print("qnode exists in qtree: ");
        //System.out.println(qTree.contains(qnode));

        qTree.remove(qnode);
        
        //System.out.print("qnode exists in qtree: ")

        ANode findAnode = aMap.get(Long.valueOf(x));
        //System.out.print(" contains the node: " );
        //System.out.println(aTree.contains(findAnode));

        aTree.remove(findAnode);

        long sizeBefore = qnode.queue.size();

        Long head = qnode.queue.poll();
        qnode.size--;

        //ANode findAnode = new ANode(head.longValue(),qnode.num);
        //System.out.print(" contains the node: " );
        //System.out.println(aTree.contains(findAnode));
        //System.out.println("atree size: " + aTree.size());
        //aTree.remove(findAnode);
        //System.out.println("atree size: " + aTree.size());

        qTree.add(qnode);
        this.join(head.longValue());
        long sizeAfter = qnode.queue.size();
        boolean sameSize = sizeBefore == sizeAfter;
        //System.out.print("size before after same: ");
        //System.out.println(sameSize);

        if ((sizeBefore == sizeAfter) && (sizeAfter == 0 || sizeAfter == 1)){
            return;
        }
        else if (qnode.queue.size() == 0){
            return;
        }
        else{
            head = qnode.queue.peek();
            ANode anode = new ANode(head.longValue(),qnode.num);
            aTree.add(anode);
            aMap.put(Long.valueOf(qnode.num),anode);
        }
    }

    public static void main(String args[]) {
        Supermarket runner = new Supermarket();
        
        Kattio io = new Kattio(System.in, System.out);
        runner.createKattio(io);
        //io.println("xxxx"); 
        int queueNumbers = io.getInt();
        int queries = io.getInt();

        runner.createQueue(queueNumbers);
        //io.println("here");
        for(int i = 0; i < queries; i++){
            String command = io.getWord();
            long x;
            //System.out.println(i + " " + command);
            //System.out.println("size of aTree: " + runner.aTree.toString());
            //System.out.println("Size of aMap: " + runner.aMap.toString());
            if (command.equals("join")){
                x = io.getLong();
                runner.join(x);
            }
            else if (command.equals("serve")){
                runner.serve();
            }
            else{
                x = io.getLong();
                runner.move(x);
            }
        }
        

        io.flush();
        io.close();
    }
}


















