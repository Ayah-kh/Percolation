import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

// test client (optional)
public class Percolation {

    private final int[][] grid;
    private final WeightedQuickUnionUF set;
    private int openSiteCount;
    private final int n;


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
        this.n = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and " + n);

        if (!isOpen(row, col))
            openSiteCount++;

        grid[row - 1][col - 1] = 1;

        unionTheOpen(row - 1, col - 2,row - 1, col - 1);
        unionTheOpen(row - 1, col ,row - 1, col - 1);
        unionTheOpen(row - 2, col - 2,row - 1, col - 1);
        unionTheOpen(row , col - 2,row - 1, col - 1);

    }

    private void unionTheOpen(int rowA, int colA, int rowB, int colB) {
        if (isOpen(rowB, colB) && rowB >= 0 && rowB < n && colB >= 0 && colB < n)
            set.union(
                    flatIndex(colA, colB),
                    flatIndex(rowB, colB));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and " + n);
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException
                    ("Row and column must be between 1 and " + n);
        return grid[row - 1][col - 1] == 2;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {

        boolean isPercolates = false;


        outerLoop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (set.find(i) == set.find(4 * n + j)) {
                    isPercolates = true;
                    break outerLoop;
                }
        }
        return isPercolates;
    }

    //delete before submission
    public void printArray() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int flatIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
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