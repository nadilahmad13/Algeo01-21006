package Matrix;
import java.io.*;
import java.util.*;

public class Input_Matrix {
    public static double[][] MatrixKeyboardInput(){
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

    public static double[][] MatrixFileInput(){
        // scan a filename
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nama file: ");
        String filename = sc.nextLine();
        sc.close();

        // create a path
        String path = "..\\test\\" + filename;

        // print the path
        System.out.println(path);

        // error handling if file doesnt exist
        try{
            // read the file
            File file = new File(path);
            // create a scanner
            Scanner fileScanner = new Scanner(file);
            // get the number of rows
            int m = 0;
            // get the number of columns
            int n = 0;
            // read the file
            while(fileScanner.hasNextLine()){
                m = (fileScanner.nextLine()).split(" ").length;
                n++;
            }
            // close the scanner
            fileScanner.close();

            // create a matrix
            double[][] matrix = new double[n][m];
            // create a scanner
            fileScanner = new Scanner(file);
            // read the file
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    matrix[i][j] = fileScanner.nextDouble();
                }
            }
            // close the scanner
            fileScanner.close();
            return matrix;
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.out.println("Returning a 1x1 matrix with value 0");
            double[][] matrix = new double[1][1];
            return matrix;
        }
    }
}