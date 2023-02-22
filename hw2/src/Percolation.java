import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF uf;
    private int length;
    private int topSite;
    private int bottomSite;
    private boolean[] open;
    private boolean[] full; // H
    private int openCount;
    private int dimension;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.length = N * N + 2;
        this.topSite = length - 2;
        for (int a = 0; a < dimension; a++) { // H
            uf.union(topSite, xyTo1D(0, a));
        }
        this.bottomSite = length - 1;
        for (int b = 0; b < dimension; b++) { // H
            uf.union(bottomSite, xyTo1D(dimension - 1, b));
        }
        this.open = new boolean[length - 2];
        this.full = new boolean[length - 2]; // H
        for (int i = 0; i < length - 2; i++) {
            this.open[i] = false;
            this.full[i] = false;
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
        }
        if (row != 0 && isOpen(row - 1, col)) { // not top row
            uf.union(p, xyTo1D(row - 1, col));
        }
        if (row != (dimension - 1) && isOpen(row + 1, col)) { // not bottom row
            uf.union(p, xyTo1D(row + 1, col));
        }
        if (col != 0 && isOpen(row, col - 1)) { // not leftmost
            uf.union(p, xyTo1D(row, col - 1));
        }
        if (col != (dimension - 1) && isOpen(row, col + 1)) { // not rightmost
            uf.union(p, xyTo1D(row, col + 1));
        }
        if (row == dimension - 1) { // add bottom row to bottom site
            uf.union(p, bottomSite);
            full[uf.find(p)] = uf.connected(p, topSite); // H
            if (full[uf.find(p)]) {
                for (int j = 0; j < dimension; j++) {
                    int top = xyTo1D(0, j);
                    int bot = xyTo1D(dimension - 1, j);
                    if (uf.connected(top, bot)) {
                        full[uf.find(top)] = true;
                        full[uf.find(bot)] = true;
                    }
                }
            }
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
        return open[p] && full[uf.find(p)]; // H
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
