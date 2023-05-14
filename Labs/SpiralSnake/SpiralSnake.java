/**
 * Name: Darryl Chan
 * Matric. No : A0177428U
**/

public class SpiralSnake {
    
    // Takes in x and y coordinates and the steps and prints the line
    // X and Y coordinates start from 0
    public static void printCoord(int x, int y, int steps){
        System.out.println("Apple at (" + x + ", " + y + ") eaten at step " + steps);
    }
    
    // Cheks the array from left to right at row number
    // end > start
    // returns the number of steps at the end
    // Increment steps by 1 each iteration
    public static int scanRight(char[][] arr, int row, int start, int end, int steps){
        int newSteps = steps;
        for (int i = start; i <= end; i++){
           char charAt = arr[row][i];
           if (charAt == '.'){
               newSteps += 1;
        }
        else{
            newSteps += 1;
            printCoord(i,row,newSteps);
        }  
      }
      return newSteps;
    }
    
    // Cheks the array from up to down at col number
    public static int scanDown(char[][] arr, int col, int start, int end, int steps){
        int newSteps = steps;
        for (int i = start; i <= end; i++){
             char charAt = arr[i][col];
             if (charAt == '.'){
                 newSteps += 1;
                 }
             else{
                  newSteps += 1;
                  printCoord(col,i,newSteps);
             }
           }
        return newSteps;
    }

    // Cheks the array from right to left at row number
    public static int scanLeft(char[][] arr, int row, int start, int end, int steps){
        int newSteps = steps;
        for (int i = end; start <= i; i--){
            char charAt = arr[row][i];
            if (charAt == '.'){
                 newSteps += 1;
            }
            else{
                newSteps += 1;
                printCoord(i,row,newSteps);
            }
        }

        return newSteps;
    }

    // Cheks the array from down to up at col number
    public static int scanUp(char[][] arr, int col, int start, int end, int steps){
        int newSteps = steps;
        for (int i = end; start <= i; i--){
             char charAt = arr[i][col];
             if (charAt == '.'){
                 newSteps += 1;
                  }
             else{
                  newSteps += 1;
                  printCoord(col,i,newSteps);
             }
        }
        return newSteps;
    }
    
    // Note that at the first step when 2D array is being scanned left to right,
    // The top row is no longer needed hence the row start increases from 0 to 1 for future scans
    // Similar arguments can be said for scanning down, left and up. Hence The 
    // needed arrays become smaller after each iteration.
    // Also note that the scans goes in this order: Right, Down, Left, Up.
    // Using increasing counters and modulo at (1), the function is able to follow the
    // Scans in this order.
    // Finally the function terminates when all the arrays have been scanned
    // This is also equivalent to having the steps = row * col at (2), meaning that
    // all the 2D arrays have been checked
    public static void spiralSnake(char[][] arr, int row, int col){
        int steps = 0;
        int colStart = 0;
        int colEnd = col - 1;
        int rowStart = 0;
        int rowEnd = row - 1;
        int counter = 0;
        while (steps < (row*col)){ // --- (2)
            if (counter % 4 == 0){ // --- (1)
               steps =  scanRight(arr,rowStart,colStart, colEnd, steps);
               rowStart += 1;
            }
            else if (counter % 4 == 1){
                steps = scanDown(arr, colEnd, rowStart, rowEnd, steps);
                colEnd -= 1;

            }
            else if (counter % 4 == 2){
                steps = scanLeft(arr, rowEnd, colStart, colEnd, steps);
                rowEnd -= 1;
            }
            else{
                steps = scanUp(arr,colStart, rowStart, rowEnd, steps);
                colStart += 1;
            }
            counter += 1;
         }
  }

  public static void main(String args[]) {

    Kattio io = new Kattio(System.in);
    int row = io.getInt();
    int col = io.getInt();
    char[][] arr2d = new char[row][col];
    for(int i = 0; i < row; i++){ //Create 2D array
        arr2d[i] =io.getWord().toCharArray();
    }
    spiralSnake(arr2d, row,col);
  }
}
