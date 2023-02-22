import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF uf;
    private int length;
    private int topSite;
    private int bottomSite;
    private int open;
    private int openCount;
    private int dimension;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.uf = new WeightedQuickUnionUF(N * N + 3);
        this.length = N * N + 3;
        this.topSite = length - 3;
        this.bottomSite = length - 2;
        this.open = length - 1; // open connects to topSite -> game over
        this.openCount = 0;
        this.dimension = N;
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col);
        validate(p);
        if (!isOpen(row, col)) {
            openCount += 1;
        }
        uf.union(p, open);
        if (row == 0) { // add top row to top site
            uf.union(p, topSite);
        }
        if (row != 0 && isOpen(row - 1, col)) { // not top row
            uf.union(p, xyTo1D(row - 1, col));
        }
        if (row != (dimension - 1) && isOpen(row + 1, col)) { // not bottom row
            uf.union(p, xyTo1D(row + 1, col));
        }
        if (row == dimension - 1) { // add bottom row to bottom site
            uf.union(p, bottomSite);
        }
        if (col != 0 && isOpen(row, col - 1)) { // not leftmost
            uf.union(p, xyTo1D(row, col - 1));
        }
        if (col != (dimension - 1) && isOpen(row, col + 1)) { // not rightmost
            uf.union(p, xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        int p = xyTo1D(row, col);
        validate(p);
        if (uf.connected(p, open)) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        int p = xyTo1D(row, col);
        validate(p);
        return uf.connected(p, topSite);
        // Prevent backwash (connected to bottom but not top
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }

    public int xyTo1D(int r, int c) {
        return r * dimension + c;
    }

    private void validate(int p) {
        if (p < 0 || p >= length - 3) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

}
