public class Main {
    public static void main(String[] args) {
        // read a matrix
        int[][] matrix = MatrixInput.InputMatrix();
        // output the matrix
        MatrixOutput.outputMatrix(matrix);
        // send matrix to gauss
        int[][] matrix2 = Gauss.Gauss_Method(matrix);
        // output the result
        MatrixOutput.outputMatrix(matrix2);
    }
}