package Matrix;

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
}