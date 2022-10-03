package Main;
import Matrix.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        double[][] M = Input_Matrix.MatrixFileInput();
        Matrix.outputMatrix(M);
        double res = Matrix.DetByKofaktor(M);
        System.out.println(res);
        Output_Matrix.DetToFile(res);
        // double[][] FX = Input_Matrix.MatrixFileInput();
        // Matrix.outputMatrix(FX);
        // System.out.println("Regresi Linear Berganda");
        // double[] FXRes = Functions.linearRegression.Regression(FX);
        // System.out.println("Hasil Regresi Linear Berganda");
        // linearRegression.RegressionOutput(FXRes);
        // System.out.println("Hasil Prediksi");
        // double[] x = {1,2,3};
        // System.out.println(linearRegression.RegressionFX(FXRes, x));
    }
}