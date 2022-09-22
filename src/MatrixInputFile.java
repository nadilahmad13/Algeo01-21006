import java.util.*;
import java.io.*;

public class MatrixInputFile {
    // input matrix from file
    public static int[][] InputMatrix(String filename){
        // create matrix
        int[][] matrix = null;
        try {
            // read file
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            // get m rows and n colls
            int m = sc.nextInt();
            int n = sc.nextInt();
            // create matrix
            matrix = new int[m][n];
            // input 1 row of matrix in 1 line (separated by space)
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
            e.printStackTrace();
        }
        return matrix;
    }
}