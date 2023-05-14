/**
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */
import java.util.*;
public class Macarons {

    int d; // Remainder
    int n; // Length of array
    int[] arrMacarons; // Array of macarons
    
    public Macarons(int rem, int[] arr, int length){
        arrMacarons = arr;
        d = rem;
        n = length;

    }

    // PreCon: Array of positive integers
    // Turns the arrMacarons into a prefix sum and then turning it into a remainder of d
    // so [2 1 2 1 2] with d = 4 will turn it into [2 3 1 2 0] in one iteration so O(n)
    public void countRemainder(){
        arrMacarons[0] %= d;
        for (int i = 1; i < n; i++){
            arrMacarons[i] = (arrMacarons[i]%d + arrMacarons[i-1]%d)%d;
        }
    }

    // PreCon: Array of positive integers x that is 0 <= x < d
    // Note that for a contiguous array of the same remainder at index i and j, where i < j
    // The contiguous array from 0 to j minus from 0 to i will give the remainder of 0
    // This tells us that i + 1 to j, the macarons can be summed up to form a perfect remainder of 0
    // Then we form an array of length d to keep track of the number of remainders
    public void countArray(){
        int[] arrCountTotalRemainder = new int[d];
        for (int i = 0; i<n; i++){
            int remainder = arrMacarons[i];
            arrCountTotalRemainder[remainder]++;
        }
        
        arrCountTotalRemainder[0]++;//Special case, because remainder of 0 can be counted as contiguous with the start of the array so we add 1

        int sum = 0;
        for (int j = 0; j < d; j++){//For each remainders, find the sum of how many contiguous array can be formed
            sum += sumToN(arrCountTotalRemainder[j]);
        }
        System.out.println(sum);
    }


    // PreCon: n >= 0
    // Finds the sum from 1 to n-1
    // Note that if there is k different indexes where it is the same remainder.
    // Then we can form 1 + 2 + 3 + ... + (k-1) different contiguous arrays. So it is simply the sum
    // From 1 to k-1 and the formula for this sum is (k-1)/ 2 * (1 + k-1). Note that, there will be 
    // inaccuracies if n is an even number because (n-1) is odd and dividing by 2 will cause inaccuracy
    // So the sum is split into when n is even or odd cases to overcome this problem.
    // Also note that if there is 1 or 0 of the same remainder, a contigous array cannot be formed
    // This is convenient because value of 0 or 1 will give 0 in the formula
    // Returns ((n-1)/2) * (n)
    public static int sumToN(int n){
        if ( n % 2 == 1){
            return (n/2)*(n);
        }
        else{
            return (n/2)*(n-1);
        }
    }

  public static void main(String args[]) {
    Kattio io = new Kattio(System.in, System.out);
    
    int n = io.getInt();
    int d = io.getInt();
    int[] arrMacarons = new int[n];
    for (int i = 0;i<n;i++){
        arrMacarons[i] = io.getInt();
    }
    
    Macarons tomMacarons = new Macarons(d, arrMacarons, n);
    tomMacarons.countRemainder();
    tomMacarons.countArray();


  }
}
