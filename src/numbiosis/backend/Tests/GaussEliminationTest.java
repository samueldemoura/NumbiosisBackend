package numbiosis.backend.Tests;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.LinearSystemArray;
import numbiosis.backend.GaussElimination;
import numbiosis.backend.utils.Matrix;
import numbiosis.backend.utils.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaussEliminationTest {

    @Test
    void run() {
        double[][] matrixArray = {
                {2, 1, -1},
                {-3, -1, 2},
                {-2, 1, 2}};
        double[] vectorArray = {8, -11, -3};

        Matrix matrix = new Matrix(matrixArray);
        Vector vector = new Vector(vectorArray);
        LinearSystem ls = new LinearSystemArray(matrix,vector);

        GaussElimination.run(ls);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    assertTrue(ls.get(i, j) == 1.0);
                else
                    assertTrue(ls.get(i, j) == 0.0);

                System.out.print(ls.get(i, j) + " ");
            } System.out.print("\n");
        }

        assertTrue(ls.getVector().get(0) == 2.0);
        assertTrue(ls.getVector().get(1) == 3.0);
        assertTrue(ls.getVector().get(2) == -1.0);
        System.out.println(ls.getVector());


    }
}