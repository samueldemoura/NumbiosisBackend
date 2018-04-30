package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;

class GaussElimination {
    static void run(LinearSystem ls) {
        double m;
        for(int k = 0; k < ls.getEquationsSize() - 1; k++) {
            for(int l = k + 1; l < ls.getEquationsSize(); l++) {
                m = ls.get(l, k) / ls.get(k, k);
                ls.subtractLineBy(ls.getLine(k).multiplyBy(m), l);
            }
        }
    }
}
