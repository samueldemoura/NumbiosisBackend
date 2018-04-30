package numbiosis.backend.Tests;

import numbiosis.backend.JacobiRichardson;
import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.LinearSystemArray;
import numbiosis.backend.utils.Matrix;
import numbiosis.backend.utils.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JacobiRichardsonTest {

    @Test
    void run_coverage_test() {

        double[][] matrixArray = {{10, 2, 1},
                                  {1, 5, 1},
                                  {2, 3, 10}};
        double[] vectorArray = {7,-8,6};

        double[] kickArray = {0.7,-1.6, 0.6};

        Matrix matrix = new Matrix(matrixArray);
        Vector vector = new Vector(vectorArray);
        LinearSystem ls = new LinearSystemArray(matrix,vector);

        Vector initial = new Vector(kickArray);

        Vector result = JacobiRichardson.run(ls, initial,0.0001,3);

        System.out.println(result);

        for(Vector v: matrix) System.out.println(v.dot(vector));

        assertTrue(result.enoughNear(new Vector(new double[]{0.9994, -1.9888, 0.9984}),1));

    }
}