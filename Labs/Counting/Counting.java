/**
 * Name         : Darryl Chan
 * Matric. No   : A0177482U
*/

import java.util.*;

public class Counting {
    long[] arr;
    long max;
    long totalSubArrays = 0;
    Kattio io = new Kattio(System.in, System.out);

    private void run() {
        // implement your "main" method here
    }

    public void createArr(long[] array){
        arr = array;
    }

    public void createMax(long maximum){
        max = maximum;
    }

    public void count(){
        
        long sum = 0;
        int start = 0;
        int size = arr.length;

        if (max == 0){
            long n = (long) size;
            totalSubArrays = ((n+1) * n) / 2;
            System.out.println(totalSubArrays);
            return;
        }

        for(int end = 0; end <size; end++){
            sum += arr[end];

            if(sum >= max){
                while(sum >= max){
                    //System.out.println("start: " + start + " end: " +end);
                    //io.println("haha:");
                    totalSubArrays += (long) (size - end);
                    sum -= arr[start];
                    start++;
                }
            }
        }
        System.out.println(totalSubArrays);
    }


    public static void main(String args[]) {
        Counting runner = new Counting();
        runner.run();

        Kattio io = new Kattio(System.in, System.out);
        int size = io.getInt();
        long max = io.getLong();

        long[] arr = new long[size];
       
        for(int i = 0; i<size; i++){
            arr[i] = io.getLong();
        }

        runner.createArr(arr);
        runner.createMax(max);


        runner.count();


    }
}
