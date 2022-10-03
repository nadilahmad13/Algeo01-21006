package Matrix;

import java.util.Scanner;

public class Matrix {
    public static Scanner sc;
    public static void outputMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean IsSquare(double[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    // CreateIdentityMatrix
    public static double[][] CreateIdentityMatrix(double[][] M, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    M[i][j] = 1;
                } else {
                    M[i][j] = 0;
                }
            }
        }
        return M;
    }

    public static double DetByKofaktor(double[][] matrix){
        double [][] MCopy = CopyMatrix(matrix); 
        if (!IsSquare(MCopy)){
            System.out.println("Matriks bukan persegi");
            return 0;
        }
        int n = MCopy.length;
        int det = 1;
        int co = 1;
        int idx;
        double temp1,temp2;
        double tmpRow[] = new double[n];
        for (int i = 0 ; i < n ; i++){
            idx = i;
            while(idx < n && MCopy[idx][i] == 0){
                idx++;
            }
            if (idx == n){
                det = 0;
                break;
            }
            if (idx != i){
                for (int j = 0 ; j < n ; j++){
                    double temp = MCopy[i][j];
                    MCopy[i][j] = MCopy[idx][j];
                    MCopy[idx][j] = temp;
                }
                det *= -1;
            }
            for (int j = 0 ; j < n ; j++){
                tmpRow[j] = MCopy[i][j];
            }
            for (int j = i+1 ; j < n ; j++){
                temp1 = tmpRow[i];
                temp2 = MCopy[j][i];
                for (int k = 0 ; k < n ; k++){
                    MCopy[j][k] = (temp1 * MCopy[j][k]) - (temp2 * tmpRow[k]);
                }
                co *= temp1;
            }
        }
        for (int i = 0 ; i < n ; i++){
            det *= MCopy[i][i];
        }
        return det/co;
    }

    public static double DetByGauss(double[][] matrix){
        // Copy the matrix
        double[][] GaussCopy = CopyMatrix(matrix);

        // Inisiasi Variabel
        int i,j,k,n,x;
        double val1, val2, val3, temp, determinan, sum;
        n = GaussCopy.length;
        determinan = 1;
        sum = 1;

        // Eliminasi Gauss
        for (i = 0 ; i < n ; i++){
            x = i;

            // Mencari Indeks Tidak 0 Pertama
            while (x < n && GaussCopy[x][i] == 0){
                x++;
            }

            if (x == n){ // Jika tidak ada
                return 0;
            }

            if (x != i){ // Jika tidak sama dengan indeks sekarang
                for (j = 0 ; j < n ; j++){ // Tukar baris
                    temp = GaussCopy[i][j];
                    GaussCopy[i][j] = GaussCopy[x][j];
                    GaussCopy[x][j] = temp;
                }
                determinan *= -1; // Kali determinan dengan -1
            }
            
            // Operasi Baris Elementer
            for (j = i + 1 ; j < n ; j++){
                val1 = GaussCopy[i][i];
                val2 = GaussCopy[j][i];

                for (k = 0 ; k < n ; k++){
                    val3 = (val1 * GaussCopy[j][k]) - (val2 * GaussCopy[i][k]);
                    GaussCopy[j][k] = val3;
                }
                
                sum *= val1;
            }
        }

        // Menghitung Determinan
        for (i = 0 ; i < n ; i++){
            determinan *= GaussCopy[i][i];
        }

        return determinan/sum;
    }

    public static double[][] TransposeMatrix(double[][] matrix){
        // Copy the matrix
        double[][] TransposeCopy = CopyMatrix(matrix);
        
        int n = TransposeCopy.length;
        int m = TransposeCopy[0].length;
        double[][] transpose = new double[m][n];
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                transpose[j][i] = TransposeCopy[i][j];
            }
        }
        return transpose;
    }
    
    public static double[][] CopyMatrix(double[][] matrix){
        // copy matrix
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
    }

    public static double[][] getMinor(double[][] matrix, int i, int j) {
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] minor = new double[n-1][m-1];
        int p = 0;
        int q = 0;
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < m; l++) {
                if (k != i && l != j) {
                    minor[p][q++] = matrix[k][l];
                    if (q % (m-1) == 0) {
                        p++;
                        q = 0;
                    }
                }
            }
        }
        return minor;
    }

    public static double getCofactor(double[][] matrix, int i, int j) {
        double[][] minor = getMinor(matrix, i, j);
        double det = DetByKofaktor(minor);
        return det;
    }

    public static int zeroRowCounter(double[][] M){
        int RowCount = 0;
        int ZeroCount = 0;

        int Row = M.length;
        int Col = M[0].length;

        for (int i = 0 ; i < Row ; i ++){
            for (int j = 0 ; j < Col ; j++){
                if (M[i][j] == 0){
                    ZeroCount++;
                }
            }
            if (ZeroCount == Col){
                RowCount++;
            }
        }
        return RowCount;
    }

    public static boolean zeroRowChecker(double[][] M, int i){
        int j;
        boolean zeroFlag;
        j = 0;
        zeroFlag = true;
        while (zeroFlag && j < M[0].length - 1){
            if (M[i][j] != 0){
                zeroFlag = false;
            }
            j++;
        }
        return zeroFlag;
    }

    public static double[][] CreateHilbertMatrix(){
        // scan n
        sc = new Scanner(System.in);
        System.out.print("Masukkan Ordo Matriks Hilbert : ");
        int n = sc.nextInt();
        double[][] HilbertMatrix = new double[n][n+1];
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                HilbertMatrix[i][j] = 1.0 / (i + j + 1);
            }
        }
        for (int i = 0 ; i < n ; i++){
            if (i == 0){
                HilbertMatrix[i][n] = 1;
            } else {
                HilbertMatrix[i][n] = 0;
            }
        }
        
        return HilbertMatrix;
    }
}