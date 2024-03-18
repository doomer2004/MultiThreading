package Tasks.Matrix;

import java.util.Arrays;

public class Matrix {

    private int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int rowsSize, int columnsSize) {
        matrix = new int[rowsSize][columnsSize];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getRowsSize() {
        return matrix.length;
    }

    public int getColumnsSize() {
        return matrix[0].length;
    }

    public int get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, int value) {
        matrix[i][j] = value;
    }

    public void print2D() {
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}
