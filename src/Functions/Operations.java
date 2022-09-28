package Functions;

public class Operations {
    public static double[][] OBE (double[][] Matrix){
        int row = Matrix.length;
        int col = Matrix[0].length;
        int i = 0;
        int j = 0;

        while (j < col){
            boolean FindOne = false;

            if (Matrix[i][j] == 1){
                boolean FindZero = false;
                int ColChecker = i + 1;

                while (ColChecker < row && !(FindZero)){
                    if (Matrix[ColChecker][j] != 0){
                        FindZero = true;

                        for (int z = 0 ; z < col ; z++){
                            double temp = Matrix[ColChecker][z];
                            Matrix[ColChecker][z] = Matrix[i][z];
                            Matrix[i][z] = temp;
                        }
                    }
                    ++ColChecker;
                }
            }

            if (Matrix[i][j] != 0){
                double per = Matrix[i][j];
                for (int y = 0 ; y < col ; y++){
                    Matrix[i][y] /= per;
                }
                FindOne = true;

                double Factor;
                int otherRow = i + 1;

                while (otherRow < row){
                    Factor = Matrix[otherRow][j];
                    double value;
                    for (int k = 0 ; k < col ; k++){
                        value = Matrix[i][k] * Factor;
                        Matrix[otherRow][k] -= value;
                    }
                    otherRow++;
                }
            }
            if (FindOne){
                i++;
            }

            if (row <= i){
                break;
            }

            j++;
        }
        return Matrix;
    }

    public static double[][] OBE_Tereduksi(double[][] Matrix){
        int i,j;
        int row = Matrix.length;
        int col = Matrix[0].length;
        Matrix = OBE(Matrix);

        for (i = row-1 ; i >= 0 ; i--){
            for (j = col-1 ; j >= 0 ; j--){
                if (Matrix[i][j] == 1){
                    double Factor;
                    int otherRow = i - 1;

                    while (otherRow >= 0){
                        Factor = Matrix[otherRow][j];
                        double value;
                        for (int k = 0 ; k < col ; k++){
                            value = Matrix[i][k] * Factor;
                            Matrix[otherRow][k] -= value;
                        }
                        otherRow--;
                    }
                }
            }
        }

        return Matrix;
    }
}
