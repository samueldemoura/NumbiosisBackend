package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.LinearSystemArray;
import numbiosis.backend.utils.Matrix;
import numbiosis.backend.utils.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaussEliminationTest {

    @Test
    void run() {
        double[][] matrixArray = {{3, 2, 4},
                {1, 1, 2},
                {4, 3, -2}};
        double[] vectorArray = {1, 2, 3};

        Matrix matrix = new Matrix(matrixArray);
        Vector vector = new Vector(vectorArray);
        LinearSystem ls = new LinearSystemArray(matrix,vector);

        GaussElimination.run(ls);

    }
}