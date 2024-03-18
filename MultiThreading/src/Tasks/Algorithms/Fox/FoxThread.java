package Tasks.Algorithms.Fox;

import Tasks.Algorithms.Sequential;
import Tasks.Matrix.Matrix;

public class FoxThread extends Thread {

    private Matrix matrixA;
    private Matrix matrixB;
    private int curRowShift;
    private int curColumnShift;
    private int blockSize;
    private Matrix resultMatrix;

    public FoxThread(Matrix matrixA, Matrix matrixB, int curRowShift,
                               int curColumnShift, int blockSize, Matrix resultMatrix) {
        this.resultMatrix = resultMatrix;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.curRowShift = curRowShift;
        this.curColumnShift = curColumnShift;
        this.blockSize = blockSize;
    }

    @Override
    public void run() {
        var m1RowSize = blockSize;
        var m2ColSize = blockSize;

        if (curRowShift + blockSize > matrixA.getRowsSize())
            m1RowSize = matrixA.getRowsSize() - curRowShift;

        if (curColumnShift + blockSize > matrixB.getColumnsSize())
            m2ColSize = matrixB.getColumnsSize() - curColumnShift;

        for (int k = 0; k < matrixA.getRowsSize(); k += blockSize) {
            var m1ColSize = blockSize;
            var m2RowSize = blockSize;

            if (k + blockSize > matrixB.getRowsSize()) {
                m2RowSize = matrixB.getRowsSize() - k;
            }

            if (k + blockSize > matrixA.getColumnsSize()) {
                m1ColSize = matrixA.getColumnsSize() - k;
            }

            var blockFirst = copyBlock(matrixA, curRowShift, curRowShift + m1RowSize,
                    k, k + m1ColSize);
            var blockSecond = copyBlock(matrixB, k, k + m2RowSize,
                    curColumnShift, curColumnShift + m2ColSize);

            var resBlock = new Sequential().multiplyMatrix(blockFirst, blockSecond);
            for (int i = 0; i < resBlock.getRowsSize(); i++) {
                for (int j = 0; j < resBlock.getColumnsSize(); j++) {
                    resultMatrix.set(i + curRowShift, j + curColumnShift, resBlock.get(i, j)
                            + resultMatrix.get(i + curRowShift, j + curColumnShift)); 
                }
            }
        }
    }
    private Matrix copyBlock(Matrix src, int rowStart, int rowFinish,
                                   int colStart, int colFinish) {
        var copyMatrix = new Matrix(rowFinish - rowStart, colFinish - colStart);
        for (int i = 0; i < rowFinish - rowStart; i++) {
            for (int j = 0; j < colFinish - colStart; j++) {
                copyMatrix.set(i, j, src.get(i + rowStart, j + colStart));
            }
        }
        return copyMatrix;
    }
}

