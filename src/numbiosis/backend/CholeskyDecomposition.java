package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.Matrix;

public class CholeskyDecomposition {

    static Matrix run(LinearSystem ls) {
        double[][] G = new double[ls.getEquationsSize()][ls.getEquationsSize()];

        double sum;
        for(int i = 0; i < ls.getEquationsSize(); i++) {
            for(int j = 0; j < i + 1; j++) {
                if (i > j) {
                    sum = 0;
                    for (int k = 0; k < j; k++) {
                        sum += G[i][k] * G[j][k];
                    }

                    G[i][j] = (1/G[j][j])*(ls.get(i, j) - sum);
                } else {
                    sum = 0;
                    for (int k = 0; k < j; k++) {
                        sum += Math.pow(G[j][k], 2);
                    }

                    G[j][j] = Math.sqrt(ls.get(j, j) - sum);
                }

            }
        }

        return new Matrix(G);
    }
}