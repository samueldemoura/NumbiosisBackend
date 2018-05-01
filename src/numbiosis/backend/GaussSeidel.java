package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.Vector;

import java.util.LinkedList;
import java.util.List;

public class GaussSeidel {

    public static List<Vector> run(LinearSystem linearSystem, Vector initial, double epslon, int iterations) {
        linearSystem.isolateMainDiagonal();
        Vector result = initial.copy();

        List<Vector> backTrace = new LinkedList<>();

        int k = 1;
        previousVector = initial.copy();
        backTrace.add(initial);

        while(k < iterations) {

            for(int j = 0; j < result.size(); j++) {
                result.set(linearSystem.applyInLine(result,j,j),j);
            }

            backTrace.add(result.copy());
            if(inifiteNorm(result,epslon)) break;
            previousVector = result.copy();
            k++;

        }

        return backTrace;
    }

    private static Vector previousVector = null;

    private static boolean inifiteNorm(Vector vector, double epslon) {
        double max = 0;
        for(double scalar: previousVector.subtract(vector)) {
            if (Math.abs(scalar) > max) max = Math.abs(scalar);
        }
        previousVector = vector.copy();
        return max - epslon < 0;
    }

}
