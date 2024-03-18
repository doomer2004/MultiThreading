package Tasks.Algorithms;

import Tasks.Matrix.Matrix;

import java.util.ArrayList;

public class Parallel {

    public Matrix MatrixMultiplication(Matrix matrixA, Matrix matrixB, int threadsCount) {
        if (matrixA.getColumnsSize() != matrixB.getRowsSize()) {
            throw new IllegalArgumentException("matrices cannot be multiplied");

        }

        var height = matrixA.getRowsSize();
        var width = matrixB.getColumnsSize();
        var resultMatrix = new Matrix(height, width);

        var rowsPerThread = height / threadsCount;
        var threads = new ArrayList<Thread>();

        for (int i = 0; i < threadsCount; i++)
        {
            var from = i * rowsPerThread;
            int to;
            if (i == threadsCount - 1) {
                to = height;
            } else {
                to = (i + 1) * rowsPerThread;
            }
            threads.add(new Thread(() -> {
                for (int row = from; row < to; row++) {
                    for (int col = 0; col < width; col++) {
                        for (int k = 0; k < matrixA.getRowsSize(); k++) {
                            resultMatrix.set(row, col, resultMatrix.get(row, col)
                                    + matrixA.get(row, k) * matrixB.get(k, col));
                        }
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultMatrix;
    }
}
