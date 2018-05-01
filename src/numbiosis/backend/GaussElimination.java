package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;

public class GaussElimination {

    public static void run(LinearSystem ls) {
        int size = ls.getEquationsSize();

        double m;
        for(int k = 0; k < size - 1; k++) {
            for(int l = k + 1; l < size; l++) {
                m = ls.get(l, k) / ls.get(k, k);
                ls.subtractLineBy(ls.getLine(k).multiplyBy(m), l);
            }
        }

        // System is now in echelon form. Now we perform
        // regressive substitution so we're left with the
        // identity matrix.

        // Regressive substitution
        for (int col = size - 1; col >= 0; col--) {
            for (int row = 0; row < col; row++) {
                m = ls.get(row, col) / ls.get(col, col);
                ls.subtractLineBy(ls.getLine(col).multiplyBy(m), row);
            }
        }

        // Normalize
        for (int i = 0; i < size; i++)
            ls.multiplyLineBy(1 / ls.get(i, i), i);
    }
}
