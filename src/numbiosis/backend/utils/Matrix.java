package numbiosis.backend.utils;

import java.util.Iterator;

public class Matrix implements Iterable<Vector> {

    private double [][] matrix;
    private final int i,j,diagonalSize;
    public Matrix() {
        this(3,3);
    }

    public Matrix(int i, int j) {
        this(new double[i][j]);
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.i = matrix.length;
        this.j = matrix[0].length;
        if(this.i < this.j) this.diagonalSize = this.i;
        else this.diagonalSize = this.j;
    }

    public double[] line(int i) {
        return this.matrix[i];
    }

    public double[] column(int c) {
        double [] col = new double[this.i];
        for(int k = 0; k < col.length; k++) {
            col[k] = matrix[k][c - 1];
        }
        return col;
    }

    public void swapLine(int from, int to) {
        double[] line = this.matrix[to].clone();
        this.matrix[to] = this.matrix[from];
        this.matrix[from] = line;
    }

    public void swapColumn(int from, int to) {
        for(int k = 0; k < this.i; k++) {
            swapElements(this.matrix[k],from,to);
        }
    }

    private void swapElements(double[] line,int from, int to) {
        double element = line[to];
        line[to] = line[from];
        line[from] = element;
    }

    public Vector mainDiagonal() {
        double [] vec = new double[this.diagonalSize];
        for (int k = 0; k < j; k++) {
            vec[k] = this.matrix[k][k];
        }
        return new Vector(vec);
    }

    public void multiplyBy(double value) {
        for(int k = 0;k < this.i;k++) {
            multiplyLineBy(value,k);
        }
    }

    public void multiplyLineBy(double value, int line) {
        for(int col = 0; col < this.j; col++) {
            matrix[line][col] *= value;
        }
    }

    public void divideLineBy(double value, int line) {
        for(int col = 0; col < this.j; col++) {
            matrix[line][col] /= value;
        }
    }

    public void setValue(double value, int i, int j) {
        matrix[i][j] = value;
    }

    public void setLine(Vector vector, int line) {
        if(vector.size() != this.j) throw new IllegalArgumentException("The size of vector do not fit");
        matrix[line] = vector.getValues();
    }

    public void setMainDiagonal(Vector vector) {
        if(vector.size() != this.diagonalSize) throw new IllegalArgumentException("The size of vector do not fit");
        for(int k = 0; k < this.diagonalSize; k++) {
            this.setValue(vector.get(k),k,k);
        }
    }

    public double dotByLine(Vector v, int line) {
        if(v.size() != j) throw new IllegalArgumentException("the size of vector do not fit");

        double ret = 0;

        for (int k = 0; k < v.size(); k++) {
            ret += v.getValues()[k] * matrix[line][k];
        }
        return ret;
    }


    @Override
    public Iterator<Vector> iterator() {
        return new Iterator<Vector>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                if(i < matrix.length) return true;
                return false;
            }

            @Override
            public Vector next() {
                return new Vector(matrix[i++]);
            }
        };
    }
}
