public class Percolation {
    private final int N;
    private final int VIRTUAL_TOP_INDEX;
    private final int VIRTUAL_BOTTOM_INDEX;

    private boolean[] sites;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        VIRTUAL_TOP_INDEX = 0;
        VIRTUAL_BOTTOM_INDEX = N * N + 1;
        sites = new boolean[N * N + 2];
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {
        int index = getIndex(i, j);
        if (!isOpen(i, j)) {
            // Open it!
            sites[index] = true;

            // Connect to virtual top case
            if (index <= N) {
                uf.union(VIRTUAL_TOP_INDEX, index);
            }
            // Connect to virtual bottom case
            if (index >= N * N - N + 1) {
                uf.union(VIRTUAL_BOTTOM_INDEX, index);
            }
            // Connect top, bottom, left, and right sides
            // with brute force, yea baby!
            int[][] sides = new int[][] {
                    {i - 1, j},    // Top side index
                    {i + 1, j},    // Bottom side index
                    {i, j - 1},    // Left side index
                    {i, j + 1},    // Right side index
            };
            for (int[] side : sides) {
                try {
                    int sideIndex = getIndex(side[0], side[1]);
                    if (sites[sideIndex]) {
                        uf.union(sideIndex, index);
                    }
                } catch (IndexOutOfBoundsException ignored) {}
            }
        }
    }

    public boolean isOpen(int i, int j) {
        int index = getIndex(i, j);
        return sites[index];
    }

    public boolean isFull(int i, int j) {
        int index = getIndex(i, j);
        return sites[index] && uf.connected(VIRTUAL_TOP_INDEX, index);
    }

    public boolean percolates() {
        return uf.connected(VIRTUAL_TOP_INDEX, VIRTUAL_BOTTOM_INDEX);
    }

    private int getIndex(int i, int j) {
        if (i <= 0 || j <= 0 || j > N || j > N) {
            throw new IndexOutOfBoundsException();
        }
        return N * (i - 1) + j;
    }
}