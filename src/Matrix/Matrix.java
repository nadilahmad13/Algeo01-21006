package Matrix;
import java.util.*;

public class Matrix {
    public static double[][] InputMatrix(){
        // get m rows and n colls
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris (m): ");
        int m = sc.nextInt();
        System.out.print("Masukkan jumlah kolom (n): ");
        int n = sc.nextInt();
        // create matrix
        double[][] matrix = new double[m][n];
        // input 1 row of matrix in 1 line (separated by space)
        System.out.println("Masukkan elemen-elemen matriks");
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = sc.nextDouble();
            }
        }
        
        sc.close();
        return matrix;
    }

    public static void outputMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
