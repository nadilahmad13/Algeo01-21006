package Main;
import Matrix.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Keluar");
        
        Scanner scA = new Scanner(System.in);
        System.out.print("Pilihan: ");
        int a = scA.nextInt();
        switch(a) {
            case 1:
                System.out.println("1.Metode eliminasi Gauss");
                System.out.println("2.Metode eliminasi Gauss-Jordan");
                System.out.println("3.Metode matriks balikan");
                System.out.println("4.Metode Cramer");
            case 2:
                System.out.println("1. Determinan dengan metode eliminasi Gauss");
                System.out.println("2. Determinan dengan metode ekspansi kofaktor");
                Scanner scB = new Scanner(System.in);
                System.out.print("Pilihan: ");
                int b = scB.nextInt();

                // read a matrix
                double[][] matrix = Input_Matrix.MatrixFileInput();
                // output the matrix
                Matrix.outputMatrix(matrix);
                
                switch(b) {
                    case 1:
                        //det by gauss
                        System.out.println("Determinan dengan metode eliminasi Gauss");
                        double det = Matrix.DetByGauss(matrix);
                        System.out.println("Determinan = " + det);
                    case 2:
                        System.out.println("Determinan dengan metode kofaktor");
                        // det by cofactor
                        double detkof = Matrix.DetByKofaktor(matrix);
                        System.out.println("Determinan = " + detkof);
                }
            case 3:
            case 4:
                
            case 5:
                
            case 6:
                
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak ada");
                break;
        }
        scA.close(); 
    }
}