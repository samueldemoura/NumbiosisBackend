package numbiosis.backend.Tests;

import numbiosis.backend.HeunsMethod;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeunsMethodTest {

    @Test
    void run() throws Exception {

        // y' = y-t^2 + 1
        // 0 <= t <= 2
        // y(0) = 0.5
        // h = 0.2 -> N = 10
        double r[] = HeunsMethod.run("y - t^2 + 1", 0.5, 0.2, 10);

        for (int i = 0; i < 11; i++)
            System.out.println(r[i]);

        assertTrue(abs(r[10] - 5.2330546) < 0.001);
    }
}
