/**
 * Decription: This class creates a new instance of a maze object. This object creates a maze from a file and solves it. It will have instance 
 * variables that keep track of how many exits there are and what the exits are. 
 * @author Nikki Johnson
 * @version 2.5
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

 public class Maze{

      // instance variables
      private int rows;                        // number of rows in the maze
      private int columns;                     // number of columns in the maze
      private char[][] maze;                   // the maze itself, true means that there is a wall, false otherwise 
      private int numExits;                    // number of exits in the maze
      private String solvedExits;              // the exit locations of the maze

      /**
       * This method is the construtor method for the maze structure
       * @param filename the file containing the maze structure
       * @throws FileNotFoundException
       */
      public Maze(String filename) throws FileNotFoundException {
            // open the maze file 
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // read the number of rows and columns from the file and ititalize variables row and columns
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            rows = Integer.parseInt(parts[0]);
            columns = Integer.parseInt(parts[1]);

            // initialize walls variable by the number of rows and columns
            maze = new char[rows][columns];

            // read each line and add walls
            for (int row = 0; row < rows; row++){
                  line = scanner.nextLine();
                  for (int col = 0; col < line.length(); col++){
                        maze[row][col] = line.charAt(col);
                  }
            }
            // close scanner object 
            scanner.close();

            // initialize numExits and solvedExits
            numExits = 0;
            solvedExits = "";
      }

      /**
       * This method returns the number of rows in the maze
       * @return the number of rows in the maze
       */
      public int getRows(){return rows;}

      /**
       * This method returns the number of columns in the maze
       * @return the number of columns in the maze
       */
      public int getColumns(){return columns;}

      /**
       * This method checks to see if the initial position is valid (whether or not it is a wall)
       * @param r the starting row number
       * @param c the starting column number
       * @return true if the position is valid
       */
      public boolean isValid(int r, int c){
            return r >= 0 && r < rows && c >= 0 && c < columns && maze[r][c] == ' ';
      }

      /**
       * This method checks to see if the position is an exit
       * @param r the row number
       * @param c the column number
       * @return true if the position is an exit, false otherwise
       */
      public boolean isExit(int r, int c) {
            return (r == 0 || c == 0 || r == rows-1 || c == columns-1) && maze[r][c] == ' ';
      }

      /**
       * This method solves the maze recursively from the given start position 
       * @param r the staring row 
       * @param c the starting column
       */
      public void solve(int r, int c){
            // if the location is invallid or if the cell has been visited (marked by an 'x', then do nothing)
            if (!isValid(r, c) || maze[r][c] == 'x') {
                  return; 
            }

            // if maze[r][c] is an exit, increment numExits and add the position to solvedExits
            if (isExit(r, c)){
                  numExits++;
                  String exit = String.valueOf(r + 1) + ", " + String.valueOf(c + 1) + ".";
                  solvedExits = solvedExits.concat(exit);
            } else {
                  // mark the cell as visited
                  maze[r][c] = 'x';

                  // else recursively check the cells to the left, right, above and below of the current cell
                  solve(r + 1, c);
                  solve(r-1, c);
                  solve(r, c + 1);
                  solve(r, c-1);
            }

      }

      /**
       * This method displays the results of solving the given maze from the given starting position. 
       * If the maze was not solved, display unsolvable, else display the number od exits and the location of those exits 
       */
      public void displayResults(){
            if (numExits == 0){
                  System.out.println("Unsolvable!");
            } else {
                  System.out.println("Found " + numExits + " exits at the following positions: ");
                  String[] parts = solvedExits.split("\\.");
                  for (String s: parts) {
                        System.out.println(s);
                  }
            }
      }

      /**
       * This method returns the string representation of the maze
       * @return the string representation of the maze
       */
      public String toString(){
            StringBuilder sb = new StringBuilder();
            
            for (int row = 0; row < rows; row++){
                  for (int col = 0; col < columns; col++){
                        sb.append(maze[row][col]);
                  }
                  sb.append("\n");
            }

            return sb.toString();
      }
 }