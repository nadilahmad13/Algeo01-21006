package Functions;

public class Operations {
    public static double[][] MatrixExtender(double[][] Mat1, double[][] Mat2){
        double [][] Extended;
        int RowMat1, ColMat1, ColMat2, i, j;
        RowMat1 = Mat1.length;
        ColMat1 = Mat1[0].length;
        ColMat2 = Mat2[0].length;

        Extended = new double[RowMat1][ColMat1+ColMat2];
        for (i = 0; i < RowMat1; i++){
            for (j = 0; j < (ColMat1 + ColMat2) ; j++){
                if (j < ColMat1){
                    Extended[i][j] = Mat1[i][j];
                } else {
                    Extended[i][j] = Mat2[i][j-ColMat1];
                }
            }
        }
        return Extended;
    }

    public static double[][] OBE (double[][] M){
        int row = M.length;
        int col = M[0].length;
        int i = 0;
        int j = 0;

        while (j < col){
            boolean FindOne = false;

            if (M[i][j] == 0){
                boolean FindZero = false;
                int ColChecker = i + 1;

                while (ColChecker < row && !(FindZero)){
                    if (M[ColChecker][j] != 0){
                        FindZero = true;

                        for (int z = 0 ; z < col ; z++){
                            double temp = M[ColChecker][z];
                            M[ColChecker][z] = M[i][z];
                            M[i][z] = temp;
                        }
                    }
                    ++ColChecker;
                }
            }

            if (M[i][j] != 0){
                double per = M[i][j];
                for (int y = 0 ; y < col ; y++){
                    M[i][y] /= per;
                }
                FindOne = true;

                double Factor;
                int otherRow = i + 1;

                while (otherRow < row){
                    Factor = M[otherRow][j];
                    double value;
                    for (int k = 0 ; k < col ; k++){
                        value = M[i][k] * Factor;
                        M[otherRow][k] -= value;
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
        return M;
    }

    public static double[][] OBE_Tereduksi(double[][] M){
        int i,j;
        int row = M.length;
        int col = M[0].length;
        M = OBE(M);

        for (i = row-1 ; i >= 0 ; i--){
            for (j = col-1 ; j >= 0 ; j--){
                if (M[i][j] == 1){
                    double Factor;
                    int otherRow = i - 1;

                    while (otherRow >= 0){
                        Factor = M[otherRow][j];
                        double value;
                        for (int k = 0 ; k < col ; k++){
                            value = M[i][k] * Factor;
                            M[otherRow][k] -= value;
                        }
                        otherRow--;
                    }
                }
            }
        }

        return M;
    }

    public static void Constant_Multiply(double[][] Matriks, double k){
        int RowMatriks, ColMatriks, i, j;
        RowMatriks = Matriks.length;
        ColMatriks = Matriks[0].length;
        for (i = 0 ; i < RowMatriks ; i++){
            for (j = 0 ; j < ColMatriks ; j++){
                Matriks[i][j] *= k;
            }
        }
    }

    public static double[][] Matrix_Right_Cutter(double[][] Matriks){
        double[][] ret = new double[Matriks.length][1];
        int Col = Matriks[0].length;
        for (int i = 0 ; i < Matriks.length ; i++){
            ret[i][0] = Matriks[i][Col-1];
        }
        return ret;
    }

    public static double[][] Matrix_Multiplier (double[][] M1, double[][] M2){
        int Row = M1.length;
        int Col = M2[0].length;
        int i, j, k;
        double temp;
        double[][] ret = new double[Row][Col];
        for (i = 0 ; i < Row ; i++){
            for (j = 0 ; j < Col ; j++){
                temp = 0;
                for (k = 0 ; k < M1[0].length ; k++){
                    temp += M1[i][k] * M2[k][j];
                }
                ret[i][j] = temp;
            }
        }
        return ret;
    }
}