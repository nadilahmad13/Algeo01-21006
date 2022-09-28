package Main;
import Matrix.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(
            "Menu"
        );
        // read a matrix
        double[][] matrix = Input_Matrix.MatrixKeyboardInput();
        double[][] matrixx = Input_Matrix.MatrixKeyboardInput();
        // output the matrix
        Matrix.outputMatrix(matrixx);
        // det by kofaktor
        double det = Matrix.DetByGauss(matrix);
        System.out.println("Determinan = " + det);
        // transpose
        System.out.println("Transpose :");
        double[][] transpose = Matrix.TransposeMatrix(matrix);
        Matrix.outputMatrix(transpose);

        //cramer

    }
}