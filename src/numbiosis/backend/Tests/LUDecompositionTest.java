package numbiosis.backend.Tests;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.LinearSystemArray;
import numbiosis.backend.utils.Matrix;
import numbiosis.backend.utils.Vector;
import numbiosis.backend.LUDecomposition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LUDecompositionTest {

    @Test
    void run() {
        double[][] matrixArray = {{4, 3}, {6, 3}};
        double[] vectorArray = {2, 3};

        Matrix matrix = new Matrix(matrixArray);
        Vector vector = new Vector(vectorArray);
        LinearSystem ls = new LinearSystemArray(matrix,vector);

        Matrix u = new Matrix(2, 2);
        LUDecomposition.run(ls, u);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(ls.get(i, j) + " ");
            } System.out.print("\n");
        }

        System.out.print("\n");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(u.get(i, j) + " ");
            } System.out.print("\n");
        }

        assertTrue(ls.get(0, 0) == 4.0);
        assertTrue(ls.get(0, 1) == 3.0);
        assertTrue(ls.get(1, 0) == 0.0);
        assertTrue(ls.get(1, 1) == -1.5);
        assertTrue(u.get(0, 0) == 1.0);
        assertTrue(u.get(0, 1) == 0.0);
        assertTrue(u.get(1, 0) == 1.5);
        assertTrue(u.get(1, 1) == 1.0);
    }
}