/**
 * Name: Darryl Chan
 * Matric. No: A0177482U
 */

import java.util.*;
public class Constellations {

    // Constants to be used
    public static long LARGE = (long) (Math.pow(10,9) + 7);
    public static int[] primeArr = {2,3,5,7,11,13};
    
    //PreCon: n>=0
    //returns n!
    public static long factorial(int n){
        if (n == 0){return 1;}
        else{return n*factorial(n-1);}
    }

    
    //PreCon: n >= k
    //returns N choose K
    public static long choose(int n, int k){
        if (k>n){return 0;}
        else{ // using (n!)/(n-k)!(k)! property
            k = Math.min(k, n-k);
            long product = 1;
            for (int i = 0; i < k; i++){
                product *= (n-i);
                product /= (i+1);
            }
            return product%LARGE;
        }
    }
    
    //PreCon: A multiple of prime numbers of {2,3,5,7,11,13};
    //Each multiple tells us how many repetitions of sizes of arrays created
    //Returns the divisor: ie for 6 arrays and 3 have the same size, it returns 3! in (6!/3!)
    public static long parsePrime(long num){
        long mult = 1;
        for (int i = 0; i <6; i++){
            int counter = 0;
            while(num % primeArr[i] == 0){
                counter++;
                num/= primeArr[i];
            }
            mult *= factorial(counter);
        }
        return mult;
    }

    //PreCon: lowLim <= uppLim <= numStars and lowLim >= 1
    //returns: no of ways to permute the arrays
    public static long start(int numStars, int lowLim, int uppLim){
        if( numStars < lowLim){return 0;}
        else{
            return permute(numStars, lowLim, uppLim, 0, 1, 1);
        }
    }
    
    //PreCon: lowLim <= uppLim <= numStars, lowLim >= 1, add, prime >= 1 and depth >= 0
    //If UppLim = 5, LowLim = 3: It will permute like this: 5555, 5554, 5553 , 5544 ... So that a previous permutation is not repeated
    //Using the fact that uppLim - lowLim = 5, there will be at most 6 different array sizes
    //To find the repetitions of each array sizes, I used the property that numbers can be split into its respective primes.
    //Suppose we have uppLim = 6, lowLim = 1. Arrays of size 1 corresponds to 2: primeArr[1-lowLim], size 2 corresponds to the next prime
    //Which is 3. We store this information as primes in the subsequent recursive calls to store the repetition information.
    //We also store the depth information in each recursive call.
    //Returns the number of permutation of array sizes and positions.
    public static long permute(int numStars, int lowLim, int uppLim, int depth, long add, long prime){
        if(numStars < lowLim){
            long mult = (factorial(depth) / parsePrime(prime))%LARGE;//How many ways to swap around the positions
            long sum = (add%LARGE)*(mult%LARGE) ;
            return sum%LARGE;
        }
        else{
            uppLim = Math.min(numStars,uppLim);
            long sum = 0;
            for(int i = uppLim; i>= lowLim; i--){
                long combination = choose(numStars,i) % LARGE;
                sum += (permute(numStars-i, lowLim, i, depth+1, (add * combination)%LARGE , prime*primeArr[i-lowLim] ))%LARGE;
            }// sum adds each recusive call
            return sum%LARGE;
        }
    }
    
    
  public static void main(String args[]) {
    Kattio io = new Kattio(System.in, System.out);
    int stars = io.getInt();
    int lowLim = io.getInt();
    int uppLim = io.getInt();

    System.out.println(start(stars,lowLim,uppLim));
  }
}
