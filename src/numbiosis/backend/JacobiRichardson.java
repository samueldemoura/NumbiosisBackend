package numbiosis.backend;

import numbiosis.backend.utils.LinearSystem;
import numbiosis.backend.utils.Vector;

public class JacobiRichardson {

    public static Vector run(LinearSystem linearSystem, Vector initial, double epslon, int iterations) {
        linearSystem.isolateMainDiagonal();
        Vector result = initial;

        int k = 1;

        previousVector = result.copy();

        for(int j = 0; j < initial.size(); j++) {
            result.set(linearSystem.applyInLine(result,j,j),j);
        }

        while(k < iterations) {
            for(int j = 0; j < initial.size(); j++) {
                result.set(linearSystem.applyInLine(result,j,j),j);
            }
            if(inifiteNorm(result,epslon)) break;
            k++;
        }

        return result;
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
