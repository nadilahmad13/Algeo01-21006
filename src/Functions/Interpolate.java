package Functions;
import java.lang.Math;

public class Interpolate {
    public static double[] InterpolateFunction(double[][] matrix){
        double[][] matrix_temp = new double[matrix.length][matrix.length+1];
        int n,i,j;
        n = matrix.length;
        
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

        matrix_temp = Operations.OBE(matrix_temp);

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
}