package Tasks.Algorithms;

import Tasks.Matrix.Matrix;

public class Sequential {
    
    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getColumnsSize() != matrixB.getRowsSize()) {
            throw new IllegalArgumentException("matrices cannot be multiplied because the " +
                    "number of columns of matrix A is not equal to the number of rows of matrix B.");
        }

        var resultMatrix = new Matrix(matrixA.getRowsSize(), matrixB.getColumnsSize());
        for (int i = 0; i < matrixA.getRowsSize(); i++) {
            for (int j = 0; j < matrixB.getColumnsSize(); j++) {
                for (int k = 0; k < matrixA.getColumnsSize(); k++) {
                    resultMatrix.set(i, j, resultMatrix.get(i, j) + matrixA.get(i, k) * matrixB.get(k, j));
                }
            }
        }

        return resultMatrix;
    }
}
