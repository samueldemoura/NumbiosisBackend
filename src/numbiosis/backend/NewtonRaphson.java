package numbiosis.backend;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import static java.lang.Math.nextAfter;
import java.util.ArrayList;

public class NewtonRaphson {

    public static ArrayList<Double> run(String expression, double x, double tolerance, int max_iterations) {

        Expression exp = new ExpressionBuilder(expression).variable("x").build();

        int i = 0;
        double f_x, previous_x = x;
        ArrayList<Double> vectors = new ArrayList<>();

        do {
            // f(x)
            f_x = exp.setVariable("x", x).evaluate();

            // f'(x)
            double x_plus_delta = nextAfter(x, Double.POSITIVE_INFINITY);
            double f_x_plus_delta = exp.setVariable("x", x_plus_delta).evaluate();
            double df_x = (f_x_plus_delta - f_x) / (x_plus_delta - x);

            // x_new = x - f(x) / f'(x)
            x = x - (f_x / df_x);

            i++; vectors.add(x);
            if (i > max_iterations || f_x == 0) break;
        } while (Math.abs(f_x) > tolerance || Math.abs(x - previous_x) > tolerance);

        return vectors;
    }
}
