import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

// test client (optional)
public class Percolation {

    private final int[][] grid;
    private final WeightedQuickUnionUF set;
    private int openSiteCount;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N must be bigger than 0");

        set = new WeightedQuickUnionUF(n * n);

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        grid[0][0] = 2;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > grid.length || col <= 0 || col > grid.length)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and " + grid.length);

        if (row == 1) {
            grid[row - 1][col - 1] = 2;
            set.union(flatIndex(row - 1, col - 1), 0);
        } else
            grid[row - 1][col - 1] = 1;

        if (isOpen(row - 1, col - 2))
            set.union(
                    flatIndex(row - 1, col - 2),
                    flatIndex(row - 1, col - 1));
        if (isOpen(row - 2, col - 1))
            set.union(
                    flatIndex(row - 2, col - 1),
                    flatIndex(row - 1, col - 1));
        if (isOpen(row - 1, col))
            set.union(
                    flatIndex(row - 1, col),
                    flatIndex(row - 1, col - 1));
        if (isOpen(row, col - 1))
            set.union(
                    flatIndex(row, col - 1),
                    flatIndex(row - 1, col - 1));

        openSiteCount++;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > grid.length || col <= 0 || col > grid.length)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and " + grid.length);
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > grid.length || col <= 0 || col > grid.length)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and " + grid.length);
        return grid[row - 1][col - 1] == 2;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {
        int n = grid.length;

        outerLoop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; i < n; j++)
                if (set.find(0 * n + i) == set.find(4 * n + j))
                    return true;
            break outerLoop;
        }
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

    private int flatIndex(int row, int col) {
        return (row - 1) * grid.length + (col - 1);
    }


    public static void main(String[] args) {
        int n = 7;

        Percolation percolation = new Percolation(n);
        percolation.printArray();

        Random random = new Random();

        for (int i = 0; i < 40; i++)
            percolation.open(random.nextInt(n) + 1, random.nextInt(n) + 1);

        System.out.println();
        percolation.printArray();

    }
}