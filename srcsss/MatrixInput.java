import java.util.*;

public class MatrixInput{
    // input matrix
    public static int[][] InputMatrix(){
        // get m rows and n colls
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris (m): ");
        int m = sc.nextInt();
        System.out.print("Masukkan jumlah kolom (n): ");
        int n = sc.nextInt();
        // create matrix
        int[][] matrix = new int[m][n];
        // input 1 row of matrix in 1 line (separated by space)
        System.out.println("Masukkan elemen-elemen matriks");
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        
        sc.close();
        return matrix;
    }
}   