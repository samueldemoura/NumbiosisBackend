package numbiosis.backend;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.List;

public class NewtonInterpolation {

    public static String run(List<Double> X, List<Double> Y) {
        int n = X.size();

        // Build DD table
        Double[][] dd = new Double[n+1][n];
        for (int i = 0; i < n; i++) {
            dd[0][i] = X.get(i);
            dd[1][i] = Y.get(i);
        }

        for (int i = 2; i < n+1; i++) {
            for (int j = 0; j < (n-i+1); j++) {
                dd[i][j] = (dd[i-1][j+1]-dd[i-1][j]) / (dd[0][j+(i-1)]-dd[0][j]);
            }
        }

        // Create the polinomial
        StringBuilder p = new StringBuilder("" + dd[1][0]); // f[x0]

        for (int i = 2; i < n; i++) {
            p.append(" + (" + dd[i][0] + ")"); // +f[x0,x1,...]

            for (int j = 0; j < i-1; j++) {
                p.append("*(x-(" + X.get(j) + "))"); // *(x-x0)*(x-x1)*...
            }
        }

        return p.toString();
    }
}
