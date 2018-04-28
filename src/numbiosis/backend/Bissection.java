package numbiosis.backend;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Bissection {

    public static double run(String expression, double a, double b, double tolerance, int max_iterations) throws NoRootException {
        Expression exp = new ExpressionBuilder(expression).variable("x").build();

        exp.setVariable("x", a);
        double f_a = exp.evaluate();
        exp.setVariable("x", b);
        double f_b = exp.evaluate();

        if (f_a * f_b >= 0) {
            throw new NoRootException("There are no real roots between a and b.");
        }

        int i = 0;
        double x_m, f_xm;

        do {
            x_m = (a + b) / 2;

            exp.setVariable("x", x_m);
            f_xm = exp.evaluate();

            if (f_a * f_xm < 0) {
                b = x_m;
                f_b = f_xm;
            } else if (f_b * f_xm < 0) {
                a = x_m;
                f_a = f_xm;
            } else {
                // Found root
                break;
            }

            i++;
            if (i > max_iterations) break;
        } while (Math.abs(f_xm) > tolerance || Math.abs(a - b) > tolerance);

        return x_m;
    }
}

class NoRootException extends Exception {

    NoRootException(String msg) {
        super(msg);
    }
}