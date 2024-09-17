/**
 * This program ....
 * @author Nikki Johnson 
 * @version 1.5
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
public class MazeSolver{

      public static void main(String[] args) throws FileNotFoundException{

            // create a scanner object to read the file name 
            Scanner input = new Scanner(System.in);

            // prompt the user for a maze filename
            System.out.println("Please enter the maze filename: ");
            String filename = input.nextLine();
            System.out.println("You entered " + filename + "\n");

            // create the maze object from the file 
            Maze maze = new Maze(filename);

            // get the number of rows and columns from the file
            int numOfRows = maze.getRows();
            int numOfCols = maze.getColumns();

            // prompt the user for a starting row
            System.out.println("Please enter the starting row (1.."+numOfRows+"): ");
            int startingRow = input.nextInt();
            System.out.println("You entered " + startingRow);
            // prompt the user for a starting column
            System.out.println("Please enter the starting column (1.."+numOfCols+"): ");
            int startingCol = input.nextInt();
            System.out.println("You entered: " + startingCol + "\n");

            // display the maze - create a toString method for the file (call it at the top of the recursive method)
            System.out.println(maze.toString());

            // display start position
            System.out.println("\nStarting from " + startingRow + ", " + startingCol);

            startingRow--;
            startingCol--;

            // see if the start position is valid
            boolean validStartPosition = maze.isValid(startingRow, startingCol);
            if (validStartPosition == true) {
                  // if the position is valid, solve the maze
                  maze.solve(startingRow, startingCol);
                  // display the results
                  maze.displayResults();
                  // display solved maze 
                  System.out.println(maze.toString());
            } else {
                  System.out.println("Unabe to start at invalid location " + (startingRow + 1) + ", " + (startingCol +1));
            }

            // close scanner object
            input.close();
      }
}