import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    double[] fractionOfOpenSites;
    private int n;
    private int trials;

    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        fractionOfOpenSites=new double[trials];

        for (int i =0; i<trials;i++){
            Percolation percolation=new Percolation(n);
            int openSites=0;
           while (!percolation.percolates()){
               int row=StdRandom.uniformInt(n)+1;
               int col=StdRandom.uniformInt(n)+1;
               if (!percolation.isOpen(row,col)){
                   percolation.open(row,col);
                   openSites++;
               }
           }
           fractionOfOpenSites[i]=(double) openSites/(double) (n*n);
        }



    }

    // sample mean of percolation threshold
    public double mean() {
        return 0;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return 0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return 0;
    }

    // test client (see below)
    public static void main(String[] args){

    }
}
