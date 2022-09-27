package Main;
import Matrix.Matrix;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        // read a matrix
        double[][] matrix = Matrix.InputMatrix();
        // output the matrix
        Matrix.outputMatrix(matrix);
        // gauss
        Matrix.Gauss(matrix);
        // output the matrix
        System.out.println("Hasil eliminasi Gauss:");
        Matrix.outputMatrix(matrix);

        // calculate determinant
        double det = Matrix.DetByGauss(matrix);
        System.out.println("Determinan matriks: " + det);
    }
}