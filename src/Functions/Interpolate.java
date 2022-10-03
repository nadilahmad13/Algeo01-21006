package Functions;
import Matrix.*;
import java.lang.Math;

public class Interpolate {
    public static double[] InterpolateFunction(double[][] matrix){
        int n,i,j;
        n = matrix.length;
        double[][] matrix_temp = new double[n][n+1];
        
        for (i = 0 ; i < n ; i++){
            for (j = 0 ; j < n + 1 ; j++){
                if (j == n){
                    matrix_temp[i][j] = matrix[i][1];
                }
                else{
                    matrix_temp[i][j] = Math.pow(matrix[i][0], j);
                }
            }
        }

        matrix_temp = Operations.OBE_Tereduksi(matrix_temp);
        Matrix.outputMatrix(matrix_temp);
            
        double[] result = new double[n];

        for (i = 0 ; i < n ; i++){
            result[i] = matrix_temp[i][n];
        }

        return result;
    }

    public static double InterpolateFX(double[] matrix, double x){
        int n,i;
        double result;
        n = matrix.length;
        result = 0;

        for (i = 0 ; i < n ; i++){
            result += matrix[i] * Math.pow(x, i);
        }

        return result;
    }

    public static void OutputInterpolation(double[] matrix){
        int n,i;
        n = matrix.length;

        System.out.print("P(x) = ");
        for (i = 0 ; i < n ; i++){
            if (i == 0){
                System.out.print(matrix[i]);
            }
            else if (i == 1){
                System.out.print(" + " + matrix[i] + "x");
            }
            else{
                System.out.print(" + " + matrix[i] + "x^" + i);
            }
        }
        System.out.println();
    }
}