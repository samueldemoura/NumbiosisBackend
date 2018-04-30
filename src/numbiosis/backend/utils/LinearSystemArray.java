package numbiosis.backend.utils;

public class LinearSystemArray implements LinearSystem {

    private Matrix matrix;
    private Vector vector;
    private boolean mainIsolated = false;

    public LinearSystemArray(Matrix matrix, Vector vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    public void isolateMainDiagonal() {
        Vector mainDiagonal = this.matrix.mainDiagonal();
        this.matrix.multiplyBy(-1);

        for(int i = 0; i < mainDiagonal.size(); i++) {
            this.matrix.divideLineBy(mainDiagonal.get(i), i);
        }

        this.matrix.setMainDiagonal(new Vector(Vector.Type.ZEROS, mainDiagonal.size()));
        this.vector.divideBy(mainDiagonal);
        this.mainIsolated = true;
    }

    public double applyInLine(Vector vector, int line, int relativeTo) {

        if(this.mainIsolated) {
            return this.matrix.dotByLine(vector,line) + this.vector.get(line);
        }

        return this.matrix.dotByLine(vector, line);
    }

    @Override
    public void subtractLineBy(Vector vector, int line) {
            this.matrix.subtractLineBy(
                    vector.split(0,this.matrix.getJ()),
                    line);
            this.vector.set(
                    this.vector.get(line) - vector.get(vector.size() - 1),
                    line);
    }

    @Override
    public void multiplyLineBy(double scalar, int line) {
        this.matrix.multiplyLineBy(scalar, line);
        this.vector.set(
                scalar *  this.vector.get(vector.size() - 1),
                line);
    }

    @Override
    public int getEquationsSize() {
        return this.matrix.getI();
    }

    @Override
    public double get(int i, int j) {
        return this.matrix.get(i, j);
    }

    @Override
    public Vector getLine(int line) {
        double [] vector = new double[this.matrix.getJ() + 1];
        for(int k = 0; k < vector.length-1; k++) {
            vector[k] = matrix.get(line,k);
        }
        vector[vector.length-1] = this.vector.get(line);
        return new Vector(vector);
    }

    @Override
    public void subtractLineFromLine(int i, int i1) {
        this.subtractLineBy(this.getLine(i),i1);
    }

    @Override
    public String toString() {
        return "LinearSystemArray{" +
                "matrix=" + matrix +
                ", vector=" + vector +
                ", mainIsolated=" + mainIsolated +
                '}';
    }
}
