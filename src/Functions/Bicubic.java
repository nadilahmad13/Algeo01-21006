package Functions;
import java.lang.Math;
import java.util.*;
import Functions.*;

public class Bicubic {
    public static Scanner sc;
    public static double[][] a;
    public static double[][] a4x4;

    public static double[][] bicubicInterpolation (double[][] matrix, int i, int j, int x, int y){
        //looping untuk mengisi matrix 4x4
        double[][] matrix4x4 = new double[4][4];
            for (j = 0; j < 4; j++) {
                for (i = 0; i < 4; i++) {
                    matrix4x4[i][j] = matrix[i][j];
                }
            }
            
        //looping untuk mengisi matrix 16x16
        double[][] matrix16x16 = new double[16][16];
        int row = 0;
        for (y = -1; y < 3; y++) {
            for (x = -1; x < 3; x++) {
                int col = 0;
                for (j = 0; j < 4; j++) {
                    for (i = 0; i < 4; i++) {
                        matrix16x16[row][col] = ((float) Math.pow(x, i) * (float) Math.pow(y, j));
                        col++; 
                    }
                }
                row++;
            }
        }    

        /* mengubah matrix 4x4 ke 16x1 */
        double[][] matrix16x1 = new double[16][1];
        for (j = 0; j < 4; j++) {
            for (i = 0; i < 4; i++) {
                matrix16x1[row][0] = matrix4x4[i][j];
                row++;
            }
        }

        double[][] invmatrix16 = Inverse.InverseByCofactor(matrix16x16);
        double[][] inverse = Operations.multiplyMatrix(invmatrix16, matrix16x1);

        float Formula = 0;
        row = 0;

        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan x: ");
        float X = input.nextInt();
        System.out.print("Masukkan y: ");
        float Y = input.nextInt();

        for (i = 0; i < 4; i++){
            for (j = 0; j < 4; j++){
                Formula +=  inverse[row][0] * (float) Math.pow(x,i) * (float) Math.pow(y,j);
                row++;
            }
        }
        System.out.printf("f(%.4f, %.4f) = %.4f\n", X, Y, Formula);
    }
}