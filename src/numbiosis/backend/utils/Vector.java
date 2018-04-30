package numbiosis.backend.utils;

import java.util.Arrays;
import java.util.Iterator;

public class Vector implements Iterable<Double> {

    public enum Type {
        ZEROS, ONES
    }
    private double[] vector = null;
    private int size = 0;
    public Vector(double[] vector) {
        this.vector = vector;
        this.size = vector.length;
    }

    public Vector(Type T, int size) {
        if(T.equals(Type.ZEROS)) vector = new double[size];
        else if(T.equals(Type.ONES)) {
            vector = new double[size];
            for(int i = 0; i < size; i++) vector[i] = 1;
        }
        if(vector == null) throw new IllegalArgumentException("There's not this Type");
        this.size = size;
    }

    public int size() {
        return this.size;
    }

    public double get(int index) {
        return this.vector[index];
    }

    public double[] getValues() {
        return this.vector;
    }
    public void set(double value, int index) {
        this.vector[index] = value;
    }
    public double dot(Vector v) {
        if(v.size() != this.size()) throw new IllegalArgumentException();
        double r = 0;
        for(int k = 0;k < this.size; k++) r += this.get(k) * v.get(k);
        return r;
    }

    public void multiplyBy(Vector v) {
        for(int k = 0; k < this.size(); k++) {
            this.vector[k] *= v.get(k);
        }
    }

    public void divideBy(Vector v) {
        for(int k = 0; k < this.size(); k++) {
            this.vector[k] /= v.get(k);
        }
    }

    public Vector subtract(Vector v) {
        double[] result = new double[v.size()];
        for (int i = 0; i < v.size(); i++) {
            result[i] = this.get(i) - v.get(i);
        }
        return new Vector(result);
    }

    public boolean enoughNear(Vector v, double precision) {
        for(int i = 0; i < v.size(); i++) {
            if (Math.abs(Math.abs(v.get(i)) - Math.abs(this.get(i))) > precision)
                return false;
        }
        return true;
    }

    public Vector copy(){
        return new Vector(this.vector.clone());
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                if(vector.length > i) return true;
                return false;
            }

            @Override
            public Double next() {
                return vector[i++];
            }
        };
    }

    @Override
    public String toString() {
        return "Vector{" +
                "vector=" + Arrays.toString(vector) +
                ", size=" + size +
                '}';
    }
}
