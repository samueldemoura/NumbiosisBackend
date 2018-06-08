package numbiosis.backend;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MidpointMethod {

    public static double[] run(String f, double w0, double h, int iterations) {
        double w[] = new double[iterations + 1];
        w[0] = w0;

        Expression exp = new ExpressionBuilder(f).variables("t", "y").build();

        for (int i = 1; i < iterations + 1; i++) {
            exp.setVariable("t", (i-1) * h);
            exp.setVariable("y", w[i-1]);
            double f_ti_wi = exp.evaluate();

            exp.setVariable("t", ((i-1)*h) + (h/2));
            exp.setVariable("y", w[i-1] + (h/2) * f_ti_wi);

            w[i] = w[i-1] + h * exp.evaluate();
        }

        return w;
    }
}
