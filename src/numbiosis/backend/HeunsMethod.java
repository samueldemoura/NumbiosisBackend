package numbiosis.backend;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class HeunsMethod {

    public static double[] run(String f, double w0, double h, int iterations) {
        double w[] = new double[iterations + 1];
        w[0] = w0;

        Expression exp = new ExpressionBuilder(f).variables("t", "y").build();

        for (int i = 1; i < iterations + 1; i++) {
            // Predict
            exp.setVariable("t", (i-1) * h);
            exp.setVariable("y", w[i-1]);
            double f_ti_wi = exp.evaluate();
            double predictor = w[i-1] + h * f_ti_wi;

            // Correct
            exp.setVariable("t", i * h);
            exp.setVariable("y", predictor);

            w[i] = w[i-1] + ((h/2) * (f_ti_wi + exp.evaluate()) );
        }

        return w;
    }
}
