package numbiosis.backend.Tests;

import numbiosis.backend.JacobiRichardson;
import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.LinearSystemArray;
import numbiosis.backend.utils.Matrix;
import numbiosis.backend.utils.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        List<Vector> result = JacobiRichardson.run(ls, initial,0.0001,3);

        Vector iter0 = new Vector(kickArray),
                iter1 = new Vector(new double[]{0.96, -1.86, 0.94}),
        iter2 = new Vector(new double[]{0.978, -1.98, 0.966});

        assertTrue(result.get(0).enoughNear(iter0,0.0001));
        assertTrue(result.get(1).enoughNear(iter1,0.0001));
        assertTrue(result.get(2).enoughNear(iter2,0.0001));

    }
}