package Matrix;
import java.util.*;

public class Matrix {
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

    public static double DetByKofaktor(double[][] matrix){
        if (!IsSquare(matrix)){
            System.out.println("Matriks bukan persegi");
            return 0;
        }
        int n = matrix.length;
        int det = 1;
        int co = 1;
        int idx;
        double temp1,temp2;
        double tmpRow[] = new double[n];
        for (int i = 0 ; i < n ; i++){
            idx = i;
            while(idx < n && matrix[idx][i] == 0){
                idx++;
            }
            if (idx == n){
                det = 0;
                break;
            }
            if (idx != i){
                for (int j = 0 ; j < n ; j++){
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[idx][j];
                    matrix[idx][j] = temp;
                }
                det *= -1;
            }
            for (int j = 0 ; j < n ; j++){
                tmpRow[j] = matrix[i][j];
            }
            for (int j = i+1 ; j < n ; j++){
                temp1 = tmpRow[i];
                temp2 = matrix[j][i];
                for (int k = 0 ; k < n ; k++){
                    matrix[j][k] = (temp1 * matrix[j][k]) - (temp2 * tmpRow[k]);
                }
                co *= temp1;
            }
        }
        for (int i = 0 ; i < n ; i++){
            det *= matrix[i][i];
        }
        return det/co;
    }

    public static double DetByGauss(double[][] matrix){
        // call copy function
        double[][] copy = CopyMatrix(matrix);
        // call gauss function
        Gauss(copy);
        // calculate determinant
        double det = 1;
        for (int i = 0; i < copy.length; i++){
            det *= copy[i][i];
        }
        return det;
    }

    public static void Gauss(double[][] matrix){
        // eliminasi gauss
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++){
            // cari pivot
            int pivot = i;
            for (int j = i + 1; j < m; j++){
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivot][i])){
                    pivot = j;
                }
            }
            // swap baris
            if (pivot != i){
                double[] temp = matrix[i];
                matrix[i] = matrix[pivot];
                matrix[pivot] = temp;
            }
            // eliminasi
            for (int j = i + 1; j < m; j++){
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < n; k++){
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }
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

    public static void tukarKolom (double matrix[][], int baris, int i, int j) {
        int k;

        for (k = 0; k < baris; k++) {
            double temp = matrix[k][i];
            matrix[k][i] = matrix[k][j];
            matrix[k][j] = temp;
        }
    }	

    public static double[][] hapusKolomAkhir (double matrix[][]) {
        int i, j;
        int baris = matrix.length;
        int kolom = matrix[0].length;
        double [][] matrixBaru = new double[baris][kolom-1];
    
        for (i = 0; i < baris; i++) {
            for (j = 0; j < (kolom - 1); j++) {
                matrixBaru[i][j] = matrix[i][j];
            }
        }
        return matrixBaru;
    }

    public static void cramer (double matrix[][], double hasilCramer[]) {
        int i, j, k, l;
        int n = matrix.length;
        double[][] mainMatrix = new double[n][n];

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                mainMatrix[i][j] = matrix[i][j];
            }
        }

        double mainDet = DetByKofaktor(mainMatrix);
        if (mainDet != 0) {
            // mencari d1 d2 d3
            for (i = 0; i < n; i++) {
                double temp[][] = new double[n][matrix[0].length];
                for(k = 0; k < n; k++) {
                    for(l = 0; l < matrix[0].length; l++) {
                        temp[k][l] = matrix[k][l];
                    }
                }
                tukarKolom(temp, n, i, temp[0].length - 1);
                double[][] subMatrix = hapusKolomAkhir(temp);
                double detSubMatrix = DetByKofaktor(subMatrix);
                hasilCramer[i] = detSubMatrix / mainDet;
            }
        } else { // determinan 0
            System.out.println("Persamaan tidak bisa diselesaikan");
        }
    }
}