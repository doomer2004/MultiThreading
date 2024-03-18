package Tasks;

import Tasks.Algorithms.Fox.Fox;
import Tasks.Algorithms.Parallel;
import Tasks.Algorithms.Sequential;
import Tasks.Matrix.MatrixGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args)
    {
        var rows = 3;
        var columns = 3;

        MatrixGenerator generator = new MatrixGenerator();

        var matrixA = generator.generateMatrix(rows, columns);
        matrixA.print2D();

        var matrixB = generator.generateMatrix(rows, columns);
        matrixB.print2D();

        System.out.println("Sequential:");
        var sequential = new Sequential();
        var matrixC = sequential.multiplyMatrix(matrixA, matrixB);
        matrixC.print2D();

        System.out.println("Parallel:");
        var parallel = new Parallel();
        var matrixD = parallel.MatrixMultiplication(matrixA, matrixB, 2);
        matrixD.print2D();

        System.out.println("Fox:");
        var fox = new Fox(matrixA, matrixB, 3);
        var matrixE = fox.multiplyMatrix();
        matrixE.print2D();
    }
}