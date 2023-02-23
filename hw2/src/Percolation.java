import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF uf;
    WeightedQuickUnionUF ufBackwash;
    private int length;
    private int topSite;
    private int bottomSite;
    private boolean[] open;
    private int openCount;
    private int dimension;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.ufBackwash = new WeightedQuickUnionUF(N * N + 1); // H
        this.length = N * N + 2;
        this.topSite = length - 2;
        this.bottomSite = length - 1;
        this.open = new boolean[length - 2];
        for (int i = 0; i < length - 2; i++) {
            this.open[i] = false;
        }
        for (int i = 0; i < dimension; i++) { // H
            //uf.union(topSite, xyTo1D(0, i));
            //uf.union(bottomSite, xyTo1D(dimension - 1, i));
            ufBackwash.union(topSite, xyTo1D(0, i));
        }
        this.openCount = 0;
        this.dimension = N;
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col);
        validate(p);
        if (!isOpen(row, col)) {
            openCount += 1;
        }
        open[p] = true;
        if (row == 0) { // add top row to top site
            uf.union(p, topSite);
            ufBackwash.union(p, topSite);
        }
        if (row != 0 && isOpen(row - 1, col)) { // not top row
            uf.union(p, xyTo1D(row - 1, col));
            ufBackwash.union(p, xyTo1D(row - 1, col)); // H
        }
        if (row != (dimension - 1) && isOpen(row + 1, col)) { // not bottom row
            uf.union(p, xyTo1D(row + 1, col));
            ufBackwash.union(p, xyTo1D(row + 1, col)); // H
        }
        if (row == dimension - 1) { // add bottom row to bottom site
            uf.union(p, bottomSite);
        }
        if (col != 0 && isOpen(row, col - 1)) { // not leftmost
            uf.union(p, xyTo1D(row, col - 1));
            ufBackwash.union(p, xyTo1D(row, col - 1));
        }
        if (col != (dimension - 1) && isOpen(row, col + 1)) { // not rightmost
            uf.union(p, xyTo1D(row, col + 1));
            ufBackwash.union(p, xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        int p = xyTo1D(row, col);
        validate(p);
        return open[p];
    }

    public boolean isFull(int row, int col) {
        int p = xyTo1D(row, col);
        validate(p);
        //return uf.connected(p, topSite);
        return open[p] && ufBackwash.connected(topSite, p);
        // Prevent backwash (connected to bottom but not top)
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }

    private int xyTo1D(int r, int c) {
        return r * dimension + c;
    }

    private void validate(int p) {
        if (p < 0 || p >= length - 2) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

}
