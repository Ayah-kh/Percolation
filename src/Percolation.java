import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// test client (optional)
public class Percolation {

    private final boolean[][] grid;
    private final WeightedQuickUnionUF set;
    private final int n;
    private int openSiteCount;
    private int top = 0;
    private int bottom;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be bigger than 0");
        this.n = n;
        bottom = n * n + 1;
        grid = new boolean[n][n];
        set = new WeightedQuickUnionUF(n * n + 2);
    }

    public static void main(String[] args) {


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("Row and column must be between 1 and " + n);

        if (!isOpen(row, col))
            openSiteCount++;

        grid[row - 1][col - 1] = true;

        if (row == 1)
            set.union(flatIndex(row, col), top);

        if (row == n)
            set.union(flatIndex(row, col), bottom);


        if (row > 1 && isOpen(row - 1, col)) {
            set.union(flatIndex(row, col), flatIndex(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            set.union(flatIndex(row, col), flatIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            set.union(flatIndex(row, col), flatIndex(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            set.union(flatIndex(row, col), flatIndex(row, col + 1));
        }

    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("Row and column must be between 1 and " + n);
        return grid[(row - 1)][(col - 1)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("Row and column must be between 1 and " + n);

        return set.find(flatIndex(row, col)) == set.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates() {

        return set.find(top) == set.find(bottom);
    }

    private int flatIndex(int row, int col) {
        int index = (row - 1) * n + col;
        return index;
    }
}