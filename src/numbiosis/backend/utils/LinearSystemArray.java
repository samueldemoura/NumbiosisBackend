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
            return this.matrix.dotByLine(vector, line) + vector.get(line);
        }

        return this.matrix.dotByLine(vector, line);
    }
}
