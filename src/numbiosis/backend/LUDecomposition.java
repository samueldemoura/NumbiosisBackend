package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.Matrix;

public class LUDecomposition {
    public static void run(LinearSystem ls, Matrix u) {
        double m;

        for(int k = 0; k < ls.getEquationsSize() - 1; k++) {
            for(int l = k + 1; l < ls.getEquationsSize(); l++) {
                // Calculate U
                m = ls.get(l, k) / ls.get(k, k);
                ls.subtractLineBy(ls.getLine(k).multiplyBy(m), l);

                // Write coefficients for L
                u.setValue(m, l, k);
            }
        }

        // Set entire diagonal of L to 1
        for(int k = 0; k < ls.getEquationsSize(); k++) {
            u.setValue(1, k, k);
        }
    }
}
