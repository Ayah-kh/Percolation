import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// test client (optional)
public class Percolation {

    private final int[][] grid;
    private int openSiteCount;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0)
            throw new IllegalArgumentException("N must be bigger than 0");

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row<=0||row> grid.length||col<=0||col> grid.length)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and size of the array");
        grid[row][col]=1;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row<=0||row> grid.length||col<=0||col> grid.length)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and size of the array");
        if (grid[row][col]==1)
        return true;
        else
            return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row<=0||row> grid.length||col<=0||col> grid.length)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and size of the array");
        if (grid[row][col]==0)
            return true;
        else
            return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

   //delete before submission
    public void printArray() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }


    }


    public static void main(String[] args) {

    }
}