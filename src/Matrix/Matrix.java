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
        double[][] copy = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
    }
}