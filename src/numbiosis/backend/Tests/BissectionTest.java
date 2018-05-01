package numbiosis.backend.Tests;

import numbiosis.backend.Bissection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BissectionTest {

    @Test
    void run() throws Exception {
        ArrayList<Double> results = Bissection.run("x^3 - 7", 0, 5, 0.0001, 100);
        double result = results.get(results.size() - 1);
        assertTrue(result >= 1.911 && result <= 1.913);

        results = Bissection.run("x^2 - 3", -3, 0, 0.0001, 100);
        result = results.get(results.size() - 1);
        assertTrue(result >= -1.74 && result <= -1.72);
    }
}