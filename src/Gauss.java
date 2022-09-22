public class Gauss {
    public static void main(String[] args) {
        int[][] matrix = MatrixInput.InputMatrix();
        MatrixOutput.outputMatrix(matrix);
        int[][] matrix2 = Gauss_Method(matrix);
        MatrixOutput.outputMatrix(matrix2);
    }

    public static int[][] Gauss_Method(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] matrix2 = new int[m][n];
        // copy matrix to matrix2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix2[i][j] = matrix[i][j];
            }
        }
        // Gauss Method
        for (int i = 0; i < m; i++) {
            // check if diagonal is 0
            if (matrix2[i][i] == 0) {
                // swap row
                for (int j = i + 1; j < m; j++) {
                    if (matrix2[j][i] != 0) {
                        int[] temp = matrix2[i];
                        matrix2[i] = matrix2[j];
                        matrix2[j] = temp;
                        break;
                    }
                }
            }
            // check if diagonal is 0 again
            if (matrix2[i][i] == 0) {
                System.out.println("Matriks tidak memiliki solusi");
                break;
            }
            // make diagonal 1
            int diagonal = matrix2[i][i];
            for (int j = 0; j < n; j++) {
                matrix2[i][j] = matrix2[i][j] / diagonal;
            }
            // make other element 0
            for (int j = 0; j < m; j++) {
                if (j != i) {
                    int multiplier = matrix2[j][i];
                    for (int k = 0; k < n; k++) {
                        matrix2[j][k] = matrix2[j][k] - multiplier * matrix2[i][k];
                    }
                }
            }
        }
        return matrix2;
    }
}

