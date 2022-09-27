package Main;
import Matrix.*;

public class Main {
    public static void main(String[] args) {
        // read a matrix
        double[][] matrix = Input_Matrix.MatrixFileInput();
        // output the matrix
        Matrix.outputMatrix(matrix);
        // det by kofaktor
        double det = Matrix.DetByGauss(matrix);
        System.out.println("Determinan = " + det);
    }
}