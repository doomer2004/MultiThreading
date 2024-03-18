package Tasks.Matrix;

public class MatrixGenerator {

    Matrix matrix;

    public Matrix generateMatrix(int rows, int columns) {
        matrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix.set(i, j, (int) (Math.random() * 10));
            }
        }
        return matrix;
    }
}
