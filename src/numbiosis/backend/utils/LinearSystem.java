package numbiosis.backend.utils;

public interface LinearSystem {
    void isolateMainDiagonal();

    double applyInLine(Vector vector, int line, int relativeTo);
}
