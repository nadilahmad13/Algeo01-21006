package Functions;

public class linearRegression {
    public static double[] Regression (double[][] M){
        int n , Row , Col , i , j , k;
        n = M.length;
        Row = M[0].length;
        Col = M[0].length + 1;
        double[][] MRes = new double[Row][Col];

        double[][] X0 = new double[n][1];

        for (i = 0 ; i < n ; i++){
            X0[i][0] = 1;
        }

        M = Operations.MatrixExtender(X0, M);

        for(i = 0 ; i < Row ; i++){
            for(j = 0 ; j < Col ; j++){
                double temp = 0;

                for(k = 0 ; k < n ; k++){
                    if(i == 0 && j == 0){
                        temp += 1;
                    }
                    else if (i == 0){
                        temp += M[k][j];
                    }
                    else if (j == 0){
                        temp += M[k][i];
                    }
                    else{
                        temp += M[k][i] * M[k][j];
                    }
                }
                MRes[i][j] = temp;
            }
        }
        MRes = Operations.OBE_Tereduksi(MRes);

        int a = MRes[0].length - 1;
        double[] X = new double[a];
        for (i = 0 ; i < a ; i ++){
            X[i] = MRes[i][a];
        }

        return X;
    }

    public static double RegressionFX (double[] FX, double[] x){
        double res;
        int i,n;
        n = x.length;

        res = FX[0];

        for (i = 0 ; i < n ; i++){
            res += FX[i+1] * x[i];
        }
        
        return res;
    }

    public static void RegressionOutput(double[] M){
        int i;
        String out = "";
        String plus = " + ";
        String minus = " - ";
        boolean isNegative = false;
        int length = M.length;
        out += M[0];
        
        for(i = 1 ; i < length ; i ++){
            isNegative = false;
            if (M[i] == 0.0){
                M[i] = 0.0;
            }
            if (M[i] < 0){
                out += minus;
                isNegative = true;
                M[i] = M[i] * -1;
            }
            else{
                out += plus;
            }

            out += M[i] + "*x" + i;

            if (isNegative){
                M[i] = M[i] * -1;
            }
        }
        System.out.println(out);
    }
}