public class PercolationStats {
    private double[] results;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        results = new double[T];
        for (int t = 0; t < T; t++) {
            Percolation percolation = new Percolation(N);
            int openCount = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(i, j)) {
                    openCount++;
                    percolation.open(i, j);
                }
            }
            results[t] = (double) openCount / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(results.length);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}