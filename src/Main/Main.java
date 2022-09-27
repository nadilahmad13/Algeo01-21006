package Main;
import Matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        // read a matrix
        double[][] matrix = Matrix.InputMatrix();
        // output the matrix
        Matrix.outputMatrix(matrix);
    }
}