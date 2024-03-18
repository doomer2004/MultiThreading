package Tasks.Algorithms.Fox;

import Tasks.Matrix.Matrix;

public class Fox {

    private Matrix matrixA;
    private Matrix matrixB;
    private int threadsCount;
    private Matrix resultMatrix;

    public Fox(Matrix matrixA, Matrix matrixB, int threadsCount) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.resultMatrix = new Matrix(matrixA.getRowsSize(), matrixB.getColumnsSize());

        if (threadsCount > matrixA.getRowsSize() * matrixB.getColumnsSize() / 4) {
            this.threadsCount = matrixA.getRowsSize() * matrixB.getColumnsSize() / 4;
        } else this.threadsCount = Math.max(threadsCount, 1);
    }

    public Matrix multiplyMatrix() {
        var step = (int) Math.ceil(1.0 * matrixA.getRowsSize() / (int) Math.sqrt(threadsCount));

        FoxThread[] threads = new FoxThread[threadsCount];
        var idx = 0;

        for (int i = 0; i < matrixA.getRowsSize(); i += step) {
            for (int j = 0; j < matrixB.getColumnsSize(); j += step) {
                threads[idx] = new FoxThread(matrixA, matrixB, i, j, step, resultMatrix);
                idx++;
            }
        }

        for (int i = 0; i < idx; i++) {
            threads[i].start();
        }

        for (int i = 0; i < idx; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return resultMatrix;
    }

}
