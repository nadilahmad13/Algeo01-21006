package Main;
import Matrix.*;
import Functions.*;
import java.util.*;

public class Main {
    public static Scanner sc;
    public static void main(String[] args) {
        while (true){
            double[][] M;
            clear();
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic");
            System.out.println("6. Regresi Linier Berganda");
            System.out.println("7. Keluar");
            
    
            sc = new Scanner(System.in);
            System.out.print("Masukkan pilihan menu: ");
            int pilihan = sc.nextInt();

            while (true){
                if (pilihan == 1){
                    clear();
                    System.out.println("SISTEM PERSAMAAN LINEAR");
                    int pilih_spl;
                    String[] res;
                    sc = new Scanner(System.in);
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Metode cramer");
                    System.out.println("4. Metode matriks balikan");
                    System.out.print("Masukkan pilihan metode: ");
                    pilih_spl = sc.nextInt();
                    
                    if (pilih_spl == 1){
                        clear();
                        System.out.println("METODE ELIMINASI GAUSS");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        SPL spl = new SPL(M);
                        res = spl.gaussElimination();
                        System.out.println("Solusi SPL: ");
                        spl.outputSolusi(res);
                        printBatas();
                        enterToExit();
                        break;
                    }

                    else if (pilih_spl == 2){
                        clear();
                        System.out.println("METODE ELIMINASI GAUSS-JORDAN");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        SPL spl = new SPL(M);
                        res = spl.gaussJordanElimination();
                        System.out.println("Solusi SPL: ");
                        spl.outputSolusi(res);
                        printBatas();
                        enterToExit();
                        break;
                    }

                    else if (pilih_spl == 3){
                        clear();
                        System.out.println("METODE CRAMER");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        SPL spl = new SPL(M);
                        res = spl.cramer();
                        System.out.println("Solusi SPL: ");
                        spl.outputSolusi(res);
                        printBatas();
                        enterToExit();
                        break;
                    }

                    else if (pilih_spl == 4){
                        clear();
                        System.out.println("METODE MATRIKS BALIKAN");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        SPL spl = new SPL(M);
                        res = spl.SPLInverse();
                        System.out.println("Solusi SPL: ");
                        spl.outputSolusi(res);
                        printBatas();
                        enterToExit();
                        break;
                    }

                    else {
                        System.out.println("Pilihan tidak tersedia");
                        enterToExit();
                        break;
                    }
                }

                else if (pilihan == 2){
                    clear();
                    System.out.println("DETERMINAN");
                    printBatas();
                    System.out.println("1. Metode reduksi baris");
                    System.out.println("2. Metode ekspansi kofaktor");
                    System.out.print("Masukkan pilihan metode: ");
                    int pilih_det = sc.nextInt();
                    
                    if (pilih_det == 1){
                        clear();
                        System.out.println("METODE REDUKSI BARIS");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        double det = Matrix.DetByGauss(M);
                        System.out.println("Determinan Matriks: " + det);
                        printBatas();
                        Output_Matrix.DetToFile(det);
                        printBatas();
                        enterToExit();
                        break;
                    }
                        
                    else if (pilih_det == 2){
                        clear();
                        System.out.println("METODE EKSPANSI KOFAKTOR");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        double det = Matrix.DetByKofaktor(M);
                        System.out.println("Determinan Matriks: " + det);
                        printBatas();
                        Output_Matrix.DetToFile(det);
                        printBatas();
                        enterToExit();
                        break;
                    }
    
                    else {
                        System.out.println("Pilihan tidak tersedia");
                        enterToExit();
                        break;
                    }
                }

                else if (pilihan == 3){
                    clear();
                    System.out.println("MATRIKS BALIKAN");
                    printBatas();
                    System.out.println("1. Metode reduksi baris");
                    System.out.println("2. Metode ekspansi kofaktor");
                    System.out.print("Masukkan pilihan metode: ");
                    int pilih_inv = sc.nextInt();

                    if (pilih_inv == 1){
                        clear();
                        System.out.println("METODE REDUKSI BARIS");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        double[][] inv = Inverse.InverseByOBE(M);
                        System.out.println("Matriks balikan: ");
                        Matrix.outputMatrix(inv);
                        Output_Matrix.InverseToFile(inv);
                        printBatas();
                        enterToExit();
                        break;
                    }

                    else if (pilih_inv == 2){
                        clear();
                        System.out.println("METODE EKSPANSI KOFAKTOR");
                        printBatas();
                        M = getMatrix();
                        printBatas();
                        double[][] inv = Inverse.InverseByCofactor(M);
                        System.out.println("Matriks balikan: ");
                        Matrix.outputMatrix(inv);
                        Output_Matrix.InverseToFile(inv);
                        printBatas();
                        enterToExit();
                        break;
                    }

                    else {
                        System.out.println("Pilihan tidak tersedia");
                        enterToExit();
                        break;
                    }
                }

                else if (pilihan == 4){
                    clear();
                    System.out.println("INTERPOLASI POLINOM");
                    printBatas();
                    M = getMatrix();
                    printBatas();
                    double[] res = Interpolate.InterpolateFunction(M);
                    System.out.println("Polinom interpolasi: ");
                    Interpolate.OutputInterpolation(res);
                    printBatas();
                    // input x
                    System.out.print("Masukkan nilai x: ");
                    double x = sc.nextDouble();
                    double y = Interpolate.InterpolateFX(res, x);
                    System.out.println("F(" + x + ") = " + y);
                    printBatas();
                    Output_Matrix.InterpolateToFile(res, x, y);
                    enterToExit();  
                    break;
                }

                else if (pilihan == 5){
                    break;
                }

                else if (pilihan == 6){
                    clear();
                    System.out.println("REGRESI LINEAR BERGANDA");
                    printBatas();
                    M = getMatrix();
                    printBatas();
                    double[] res = linearRegression.Regression(M);
                    System.out.println("Persamaan regresi: ");
                    String ResString = linearRegression.RegressionOutput(res);
                    printBatas();
                    System.out.print("Masukkan nilai x1: ");
                    double x1 = sc.nextDouble();
                    System.out.print("Masukkan nilai x2: ");
                    double x2 = sc.nextDouble();
                    System.out.print("Masukkan nilai x3: ");
                    double x3 = sc.nextDouble();
                    double[] x = new double[]{x1, x2, x3};
                    double y = linearRegression.RegressionFX(res, x);
                    System.out.println("F(" + x1 + ", " + x2 + ", " + x3 + ") = " + y);
                    printBatas();
                    Output_Matrix.RegressionToFile(ResString, x, y);
                    enterToExit();
                    break;
                }

                else if(pilihan == 7){
                    System.exit(0);
                }
            }
        }
    }

    public static double[][] getMatrix(){
        double[][] M;
        System.out.println("Menu Input Matriks");
        System.out.println("1. Input Matriks dari keyboard");
        System.out.println("2. Input Matriks dari file");

        sc = new Scanner(System.in);
        System.out.print("Masukkan pilihan menu: ");
        int pilihan_matrix = sc.nextInt();

        while (true){
            if (pilihan_matrix == 1){
                M = Input_Matrix.MatrixKeyboardInput();
                break;
            } else if (pilihan_matrix == 2){
                M = Input_Matrix.MatrixFileInput();
                break;
            } else {
                System.out.println("Input tidak valid");
                System.out.print("Masukkan pilihan menu: ");
                pilihan_matrix = sc.nextInt();
            }
        }
        return M;
    }

    public static void clear() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static void enterToExit(){
        System.out.println("Tekan enter untuk kembali ke menu");
        sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static void printBatas(){
        System.out.println("======================================");
    }
}