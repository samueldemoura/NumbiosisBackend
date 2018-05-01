package numbiosis.backend.Tests;

import numbiosis.backend.GaussSeidel;
import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.LinearSystemArray;
import numbiosis.backend.utils.Matrix;
import numbiosis.backend.utils.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GaussSeidelTest {

    @Test
    void run() {

        double[][] matrixArray = {{5, 1, 1},
                {3, 4, 1},
                {3, 3, 6}};
        double[] vectorArray = {5,6,0};

        Matrix matrix = new Matrix(matrixArray);
        Vector vector = new Vector(vectorArray);
        LinearSystem ls = new LinearSystemArray(matrix,vector);

        Vector initial = new Vector(Vector.Type.ZEROS,3);

        List<Vector> vec = GaussSeidel.run(ls, initial,0.0001,3);

        Vector iter0 = new Vector(new double[]{0, 0, 0});
        Vector iter1 = new Vector(new double[]{1, 0.75, -0.875});
        Vector iter2 = new Vector(new double[]{1.025, 0.95, -0.9875});

        assertTrue(vec.get(0).enoughNear(iter0,0.00001));
        assertTrue(vec.get(1).enoughNear(iter1,0.00001));
        assertTrue(vec.get(2).enoughNear(iter2,0.00001));

    }
}