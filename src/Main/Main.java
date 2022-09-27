package Main;
import Matrix.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(
            "Menu"
        );
        // read a matrix
        double[][] matrix = Input_Matrix.MatrixFileInput();
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