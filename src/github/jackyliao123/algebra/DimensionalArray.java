package github.jackyliao123.algebra;

import java.math.BigInteger;

public class DimensionalArray{
    private Rational[] values;
    private int dimensions;
    private int sizePerDimension;
    public DimensionalArray(int dimensions, int sizePerDimension){
        values = new Rational[pow(sizePerDimension, dimensions)];
        this.dimensions = dimensions;
        this.sizePerDimension = sizePerDimension;
    }
    public void setValue(int[] position, Rational value){
        if(position.length != dimensions){
            throw new ArrayIndexOutOfBoundsException("Dimensions mismatch");
        }
        for(int i = 0; i < position.length; i ++){
            if(position[i] >= sizePerDimension){
                throw new ArrayIndexOutOfBoundsException("Index out of bound");
            }
        }
        int pos = 0;
        System.out.println("------------set--------------");
        for(int i = 0; i < position.length; i ++){
            pos += position[i] * pow(sizePerDimension, dimensions);
            System.out.println("i: " + i + "     pos: " + pos);
            System.out.println("Size: " + sizePerDimension + "     Dimension: " + dimensions);
        }
        values[pos] = value;
    }
    public int getDimensions(){
        return dimensions;
    }
    public Rational getValue(int[] position){
        if(position.length != dimensions){
            throw new ArrayIndexOutOfBoundsException("Dimensions mismatch");
        }
        for(int i = 0; i < position.length; i ++){
            if(position[i] >= sizePerDimension){
                throw new ArrayIndexOutOfBoundsException("Index out of bound");
            }
        }
        int pos = 0;
        System.out.println("------------get--------------");
        for(int i = 0; i < position.length; i ++){
            pos += position[i] * pow(sizePerDimension, dimensions);
            System.out.println("i: " + i + "     pos: " + pos);
            System.out.println("Size: " + sizePerDimension + "     Dimension: " + dimensions);
        }
        return values[pos];
    }
    private static int pow(int x, int y){
        int value = 1;
        for(int i = 0; i < y; i ++){
            value *= x;
        }
        System.out.println(x+"^"+y+"="+value);
        return value;
    }
    public static void main(String[] args){
        DimensionalArray array = new DimensionalArray(2, 2);
        array.setValue(new int[]{0, 0}, new Rational(new BigInteger("8"), (new BigInteger("1"))));
        array.setValue(new int[]{1, 0}, new Rational(new BigInteger("5"), (new BigInteger("1"))));
        array.setValue(new int[]{1, 1}, new Rational(new BigInteger("5"), (new BigInteger("4"))));
        array.setValue(new int[]{0, 1}, new Rational(new BigInteger("6"), (new BigInteger("1"))));
        System.out.println(array.getValue(new int[]{0, 0}));
        System.out.println(array.getValue(new int[]{1, 0}));
        System.out.println(array.getValue(new int[]{1, 1}));
        System.out.println(array.getValue(new int[]{0, 1}));
    }
}
