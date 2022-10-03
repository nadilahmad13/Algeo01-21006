package Functions;
import Matrix.*;
import java.lang.Math;

public class Inverse {
    public static double[][] InverseByOBE(double[][] M)
    {
        double[][] Extend, MRes, Identity;
        int n,i,j;
        boolean Check = true;
        n = M.length;
        Extend = new double[n][2*n];
        MRes = new double[n][n];
        Identity = new double[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i == j) {
                    Identity[i][j] = 1;
                } else {
                    Identity[i][j] = 0;
                }
            }
        }

        // Extend Matrix menggunakan matriks identitasnya
        Extend = Operations.MatrixExtender(M, Identity);

        // Operasi Gauss Jordan untuk matriks yang telah di-extend
        Extend = Operations.OBE_Tereduksi(Extend);

        for (i = 0 ; i < n ; i++){
            if (Extend[i][i] == 0){
                Check = false;
                System.out.println("Matriks tidak memiliki invers");
                break;
            }
        }
        if (Check){
            for (i = 0; i < n ; i++){
                for (j = 0 ; j < n ; j++){
                    MRes[i][j] = Extend[i][j+n];
                }
            }
        }

        return MRes;
    }

    public static double[][] InverseByCofactor(double[][] M){
        double[][] mTemp = Matrix.CopyMatrix(M);
        double [][] MAdj;
        int n = mTemp.length;
        MAdj = new double[n][n];
        double det = Matrix.DetByKofaktor(mTemp);
        if (det == 0){
            System.out.println("Matriks tidak memiliki invers");
            return mTemp;
        }

        // Matriks Adjoin dengan looping
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MAdj[i][j] = Matrix.getCofactor(M, i, j);
                MAdj[i][j] = MAdj[i][j] * Math.pow(-1, i + j);
            }
        }

        // Transpose Matriks Adjoin
        MAdj = Matrix.TransposeMatrix(MAdj);
        Operations.Constant_Multiply(MAdj, 1/det);

        return MAdj;
    }
}