package numbiosis.backend;

import static java.lang.Math.nextAfter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class NewtonRaphson {

    public static double run(String expression, double x, double tolerance, int max_iterations) {

        Expression exp = new ExpressionBuilder(expression).variable("x").build();

        int i = 0;
        double f_x, previous_x = x;

        do {
            // f(x)
            f_x = exp.setVariable("x", x).evaluate();

            // f'(x)
            double x_plus_delta = nextAfter(x, Double.POSITIVE_INFINITY);
            double f_x_plus_delta = exp.setVariable("x", x_plus_delta).evaluate();
            double df_x = (f_x_plus_delta - f_x) / (x_plus_delta - x);

            // x_new = x - f(x) / f'(x)
            x = x - (f_x / df_x);

            i++;
            if (i > max_iterations) break;
        } while (Math.abs(f_x) > tolerance || Math.abs(x - previous_x) > tolerance);

        return x;
    }
}
