package Main;
import Matrix.*;
import Functions.*;

public class Main {
    public static void main(String[] args) {
        double[][] M = Input_Matrix.MatrixFileInput();
        Matrix.outputMatrix(M);
        System.out.println("SPL Gauss");
        SPL spl = new SPL(M);
        String[] ans = spl.gaussElimination();
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}