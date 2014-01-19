package github.jackyliao123.algebra;

public class DimensionalArray {

    private Rational[] values;
    private final int dimensions;
    private final int sizePerDimension;

    public DimensionalArray(int dimensions, int sizePerDimension) {
        values = new Rational[pow(sizePerDimension, dimensions)];
        this.dimensions = dimensions;
        this.sizePerDimension = sizePerDimension;
    }

    public void setValue(int[] position, Rational value) {
        if (position.length != dimensions) {
            throw new ArrayIndexOutOfBoundsException("Dimensions mismatch");
        }
        for (int i = 0; i < position.length; i++) {
            if (position[i] >= sizePerDimension) {
                throw new ArrayIndexOutOfBoundsException("Index out of bound");
            }
        }
        int pos = 0;
        for (int i = 0; i < position.length; i++) {
            pos += position[i] * pow(sizePerDimension, dimensions - 1 - i);
        }
        values[pos] = value;
    }

    public void setValue(int postition, Rational value) {
        values[postition] = value;
    }

    public int getDimensions() {
        return dimensions;
    }

    public int getSizePerDimension() {
        return sizePerDimension;
    }

    public Rational getValue(int[] position) {
        if (position.length != dimensions) {
            throw new ArrayIndexOutOfBoundsException("Dimensions mismatch");
        }
        for (int i = 0; i < position.length; i++) {
            if (position[i] >= sizePerDimension) {
                throw new ArrayIndexOutOfBoundsException("Index out of bound");
            }
        }
        int pos = 0;
        for (int i = 0; i < position.length; i++) {
            pos += position[i] * pow(sizePerDimension, dimensions - 1 - i);
        }
        return values[pos];
    }

    public Rational getValue(int postition) {
        return values[postition];
    }

    private static int pow(int x, int y) {
        int value = 1;
        for (int i = 0; i < y; i++) {
            value *= x;
        }
        return value;
    }

}
