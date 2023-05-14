/**
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */

import java.util.*;

class Node{
    String name;
    boolean student = false;
    boolean nonStudent = false;
    
    ArrayList<Node> child = new ArrayList<>();
    ArrayList<Node> parent = new ArrayList<>();


    public Node(String n, String studentType){
        name = n;
        if(studentType.equals("s")){
            student = true;
        }
        else if (studentType.equals("n")){
            nonStudent = true;
        }
    }
   

    public void addChild(Node n){
        //System.out.println("addChild");
        child.add(n);
        n.parent.add(this);
    }

    
    public boolean makeChildStudent(){
        if (nonStudent){return true;}
        else if (student){return false;}
        else{
            //System.out.println("makeStudent");
            student = true;
            int size = child.size();
            for(int i = 0; i<size; i++){
                Node selectChild = child.get(i);
                boolean connect = selectChild.makeChildStudent();

                if(connect){return true;}
            }
            return false;
        }
    }

    public boolean makeParentNonStudent(){
        if (student){return true;}
        else if (nonStudent){return false;}
        else{
            //System.out.println("makeNonStudent");
            nonStudent = true;
            int size = parent.size();
            for(int i = 0; i < size; i++){
                Node selectParent = parent.get(i);
                boolean connect = selectParent.makeParentNonStudent();

                if(connect){return true;}
            }
            return false;
        }
    }
}
        
        
public class Students {
    
    //HashSet<String>[] arrHash  = new HashSet<String>[3];    
    
    Map<String, Node> players = new HashMap<String, Node>();

    //HashSet<String> student = new HashSet<>();
    //HashSet<String> nonStudent = new HashSet<>();
    //HashSet<String> unknown = new HashSet<>();

    boolean unknownWin = false;

    public void createStudent(String name, String type){
        Node newStudent = new Node(name, type);
        players.put(name, newStudent);
    }

    public boolean guess(String name1, String name2){
        Node student1 = players.get(name1);
        Node student2 = players.get(name2);

        if(student1.nonStudent || student2.student){return false;}
        else if (student1.student && student2.nonStudent){return true;}
        else{
            //System.out.println("else Called");
            unknownWin = true;
            boolean connect = false;
            student1.addChild(student2);
            if(student1.student){
                connect = student2.makeChildStudent();
                if(connect){return true;}
            }
            if(student2.nonStudent){
                connect = student1.makeParentNonStudent();
                if(connect){return true;}
            }
            return false;
        }
    }



    public void print(){
        if (unknownWin){
            System.out.println("OUTCOME UNCLEAR");
        }
        else{
            System.out.println("EVERYONE LOSES");
        }
    }

    public static void main(String args[]) {
        Students runner = new Students();

        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();
        int m = io.getInt();

        for(int i = 0; i<n; i++){
            String name = io.getWord();
            String state = io.getWord();
            runner.createStudent(name, state);
            }
    
        boolean correctGuess = false;

        for(int i = 0; i<m; i++){
            String name1 = io.getWord();
            String arrow = io.getWord();
            String name2 = io.getWord();

            boolean nameGuess = runner.guess(name1, name2);
            //System.out.println("here");
            if(nameGuess){
                System.out.println("VICTORY");
                correctGuess = true;
                break;
            }
        }
        if(correctGuess == false){
            runner.print();
        }
    }
}
