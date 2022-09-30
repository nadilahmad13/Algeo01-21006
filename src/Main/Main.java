package Main;
import Matrix.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        double[][] M = Input_Matrix.MatrixFileInput();
        double[][] M2 = Matrix.CopyMatrix(M);
        M = Inverse.InverseByCofactor(M);
        M2 = Inverse.InverseByOBE(M);
        Matrix.outputMatrix(M);
        System.out.println("");
        Matrix.outputMatrix(M2);
    }
}