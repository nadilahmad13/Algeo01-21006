package Functions;
import Functions.*;
import Matrix.*;
import java.lang.Math;
import java.util.*;

public class Bicubic {
    public static Scanner sc;
    public static double[][] a;
    public static double[][] a4x4;

    public static double[][] bicubicInterpolation (double[][] matrix, int x, int y){
        int i,j;
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
        matrix16x1 = reshape(matrix4x4, 16, 1);

        double[][] invmatrix16 = Inverse.InverseByOBE(matrix16x16);
        double[][] inverse = Operations.Matrix_Multiplier(invmatrix16, matrix16x1);

        inverse = reshape(inverse, 4, 4);

        float Formula = 0;
        row = 0;

        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan x: ");
        float X = input.nextFloat();
        System.out.print("Masukkan y: ");
        float Y = input.nextFloat();

        for (j = 0; j < 4; j++) {
            for (i = 0; i < 4; i++) {
                Formula += inverse[i][j] * ((float) Math.pow(X, i) * (float) Math.pow(Y, j));
            }
        }
        
        System.out.printf("f(%.4f, %.4f) = %.4f\n", X, Y, Formula);

        return matrix16x16;
    }

    public static double[][] reshape(double[][] matrix, int row, int col){
        double[][] reshaped = new double[row][col];
        int i, j, k, l;
        k = 0;
        l = 0;
        for (i = 0; i < row; i++){
            for (j = 0; j < col; j++){
                reshaped[i][j] = matrix[k][l];
                l++;
                if (l == matrix[0].length){
                    l = 0;
                    k++;
                }
            }
        }
        return reshaped;
    }
}