package numbiosis.backend.Tests;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import numbiosis.backend.Bissection;
import numbiosis.backend.NewtonInterpolation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewtonInterpolationTest {

    @Test
    void run() throws Exception {
        ArrayList<Double> X = new ArrayList<>();
        ArrayList<Double> Y = new ArrayList<>();
        X.add(-1.5);
        X.add(-0.75);
        X.add(0.0);
        X.add(0.75);
        X.add(1.5);
        Y.add(-14.1014);
        Y.add(-0.931596);
        Y.add(0.0);
        Y.add(0.931596);
        Y.add(14.1014);

        String p = NewtonInterpolation.run(X, Y);
        System.out.println(p);

        Expression exp = new ExpressionBuilder(p).variable("x").build();

        exp.setVariable("x", 1.5);
        assertTrue(abs(exp.evaluate() - 14.1014) < 0.001);
        exp.setVariable("x", 0.75);
        assertTrue(abs(exp.evaluate() - 0.931596) < 0.001);
        exp.setVariable("x", 0);
        assertTrue(abs(exp.evaluate() - 0) < 0.001);
    }
}
