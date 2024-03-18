package Tasks.Tests;

import Tasks.Algorithms.Fox.Fox;
import Tasks.Algorithms.Parallel;
import Tasks.Algorithms.Sequential;
import Tasks.Matrix.Matrix;
import Tasks.Matrix.MatrixGenerator;

import java.util.random.RandomGenerator;

public class Test1
{
    public static void main(String[] args) {

        var MATRIX_SIZE = 1500;
        var THREADS_COUNT = 4;

        var startTime = System.currentTimeMillis();
        var endTime = System.currentTimeMillis();

        MatrixGenerator generator = new MatrixGenerator();

        var matrixA = generator.generateMatrix(MATRIX_SIZE, MATRIX_SIZE);
        var matrixB = generator.generateMatrix(MATRIX_SIZE, MATRIX_SIZE);

        var sequentialCalculator = new  Sequential();
        var parallelCalculator = new Parallel();
        var foxCalculator = new Fox(matrixA, matrixB, THREADS_COUNT);


        // Sequential test
        startTime = System.currentTimeMillis();
        var seqRes = new Matrix(sequentialCalculator.multiplyMatrix(matrixA, matrixB).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size" );

        // Parallel test
        startTime = System.currentTimeMillis();
        var parRes = new Matrix(parallelCalculator.MatrixMultiplication(matrixA, matrixB, THREADS_COUNT).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size" );

        // Fox test
        startTime = System.currentTimeMillis();
        var foxRes = new Matrix(foxCalculator.multiplyMatrix().getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Fox: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size" );

        // Check results
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (seqRes.get(i, j) != parRes.get(i, j) || seqRes.get(i, j) != foxRes.get(i, j)) {
                    System.out.println("Error");
                    return;
                }
            }
        }


    }
}

