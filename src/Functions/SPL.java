package Functions;
import Matrix.*;

public class SPL {
    private boolean satuSolusi;
    private boolean banyakSolusi;
    private boolean tidakAdaSolusi;
    private double[][] m;
    private String[] solusi;

    public SPL(double[][] M) {
        this.m = M;
        this.satuSolusi = false;
        this.banyakSolusi = false;
        this.tidakAdaSolusi = false;
    }

    public boolean banyakSolusi(){
        return this.banyakSolusi;
    }

    public void SPLbanyakSolusi(){
        this.banyakSolusi = true;
        this.satuSolusi = false;
        this.tidakAdaSolusi = false;
    }

    public boolean tidakAdaSolusi(){
        return this.tidakAdaSolusi;
    }

    public void SPLtidakAdaSolusi(){
        this.tidakAdaSolusi = true;
        this.banyakSolusi = false;
        this.satuSolusi = false;
    }

    public boolean satuSolusi(){
        return this.satuSolusi;
    }

    public void SPLsatuSolusi(){
        this.satuSolusi = true;
        this.banyakSolusi = false;
        this.tidakAdaSolusi = false;
    }

    public void outputSolusi(String[] ans){
        if (ans[0].equals("Tidak ada solusi")){
            System.out.println(ans[0]);
        }
        else{
            for (int i = 0 ; i < ans.length ; i++){
                System.out.println("X" + (i+1) + " = " + ans[i]);
            }
        }
    }

    public String[] doubleToStringConverter(double[] arr){
        String[] ans;
        int n = arr.length;
        ans = new String[n];

        for (int i = 0 ; i < n ; i++){
            ans[i] = Double.toString(arr[i]);
        }

        return ans;
    }

    public String[] parameter (double[][] M){
        String[] param = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int row = M.length;
        int col = M[0].length-1;
        int count = (col - row) + Matrix.zeroRowCounter(M);
        double temp;

        this.m = Operations.OBE_Tereduksi(M);

        int[][] indexTemp = new int[row][2];
        double[] solusiTemp = new double[col];

        // SET MARK
        for (int i = 0 ; i < col ; i++){
            solusiTemp[i] = -999; 
        }

        for (int i = 0 ; i < row ; i++){
            for (int j = 0 ; j < col ; j++){
                if (this.m[i][j] == 1){
                    indexTemp[i][0] = i;
                    indexTemp[i][1] = j;
                    break;
                }
            }
        }

        for (int i = row - 1 ; i >= 0 ; i--){
            temp = this.m[i][col];
            for (int j = indexTemp[i][1] ; j < col ; j++){
                if (solusiTemp[j] == -999){
                    temp -= (this.m[i][j]*0);
                }
                else{
                    temp -= (this.m[i][j]*solusiTemp[j]);
                }
            }
            solusiTemp[indexTemp[i][1]] = temp;
        }

        int zeroCount = 0;
        for (int i = 0 ; i < col ; i++){
            if (solusiTemp[i] == 0){
                zeroCount++;
            }
        }

        boolean semuaNol = (zeroCount == col);
        String[] ans = new String[col];

        if (!semuaNol){
            for (int i = col - 1 ; i >= 0 ; i--){
                if (solusiTemp[i] == -999 && count > 0){
                    ans[i] = param[i];
                    count--;
                }
                else if (solusiTemp[i] == -999 && count <= 0){
                    ans[i] = "";
                }
                else{
                    ans[i] = Double.toString(solusiTemp[i]) + " ";
                }
            }
        }
        else{
            for (int i = col - 1 ; i >= 0 ; i--){
                if (count > 0){
                    ans[i] = param[i];
                    count--;
                }
                else{
                    ans[i] = "";
                }
            }
        }

        String tempString;
        for (int i = 0 ; i < row ; i++){
            tempString = ans[indexTemp[i][1]];

            for (int j = indexTemp[i][1] ; j < col ; j++){
                if (solusiTemp[j] == -999 && m[i][j] != 0 && indexTemp[i][1] != j){
                    if (m[i][j] == 1){
                        tempString += " -" + param[j] + " ";
                    }
                    else if (m[i][j] == -1){
                        tempString += " +" + param[j] + " ";
                    }
                    else if (m[i][j] > 0){
                        tempString += " -" + Double.toString(m[i][j]) + "*" + ans[j] + " ";
                    }
                    else{
                        tempString += " +" + Double.toString(-1*m[i][j]) + "*" + ans[j] + " ";
                    }
                }
            }
            ans[indexTemp[i][1]] = tempString;
        }

        for (int i = 0 ; i < ans.length ; i++){
            if (ans[i] == ""){
                ans[i] = "0";
            }
        }

        return ans;
    }

    public String[] gaussElimination(){
        // Memberikan solusi SPL dengan metode Gauss Elimination dalam bentuk array
        int n, i, j;

        n = this.m[0].length - 1;
        double[] x = new double[n];

        // Gauss Elimination
        this.m = Operations.OBE(this.m);

        // Cek Solusi
        int Row = this.m.length - 1;
        int Col = this.m[0].length - 1;
        if (this.m[Row][Col-1] != 0 && this.m[Row][Col] != 0 && (Row == Col -1)){
            SPLsatuSolusi();
        }
        else {
            SPLbanyakSolusi();
        }

        // Cek Jika Tidak ada Solusi
        for (i = 0 ; i <= Row ; i++){
            if (Matrix.zeroRowChecker(this.m, i) && this.m[i][Col] != 0){
                SPLtidakAdaSolusi();
            }
        }

        // Subtitusi balik
        if (this.satuSolusi){
            x[n-1] = this.m[n-1][n]/this.m[n-1][n-1];
            if (x[n-1] == 0.0){
                x[n-1] = 0.0;
            }
            for (i = n - 2 ; i >= 0 ; i--){
                x[i] = this.m[i][n];

                for (j = i + 1 ; j < n ; j++){
                    x[i] = x[i] - this.m[i][j]*x[j];
                }

                x[i] = x[i]/this.m[i][i];
                if (x[i] == 0.0){
                    x[i] = 0.0;
                }
            }
            return doubleToStringConverter(x);
        }
        else if (this.banyakSolusi){
            return parameter(this.m);
        }
        else{
            String[] ans = {"Tidak ada solusi"};
            return ans;
        }
    }

    public String[] gaussJordanElimination(){
        int i,n;

        n = this.m[0].length - 1;
        double[] x = new double[n];

        // Gauss Jordan Elimination
        this.m = Operations.OBE_Tereduksi(this.m);

        // Cek Solusi
        int Row = this.m.length - 1;
        int Col = this.m[0].length - 1;
        if (this.m[Row][Col-1] != 0 && this.m[Row][Col] != 0 && (Row == Col -1)){
            SPLsatuSolusi();
        }
        else {
            SPLbanyakSolusi();
        }

        // Cek Jika Tidak ada Solusi
        for (i = 0 ; i <= Row ; i++){
            if (Matrix.zeroRowChecker(this.m, i) && this.m[i][Col] != 0){
                SPLtidakAdaSolusi();
            }
        }

        if (this.satuSolusi){
            for (i = 0 ; i < n ; i++){
                x[i] = this.m[i][n];
                if(x[i] == 0.0){
                    x[i] = 0.0;
                }
            }
            return doubleToStringConverter(x);
        }
        else if (this.banyakSolusi){
            return parameter(this.m);
        }
        else{
            String[] ans = {"Tidak ada solusi"};
            return ans;
        }
    }

    public String[] cramer(){
        int n, i, j;
        n = this.m.length;
        double[][] temp = new double[n][n];
        double[] x = new double[n];
        double[] detX = new double[n+1];

        // Cek jika persamaan bisa diselesaikan dengan metode Cramer
        if (this.m[0].length - 1 < n){
            String[] ans = {"SPL tidak dapat diselesaikan menggunakan metode cramer"};
            SPLtidakAdaSolusi();
            return ans;
        }

        // Inisiasi matriks temp dengan n x n SPL
        for (i = 0 ; i < n ; i++){
            for (j = 0 ; j < n ; j++){
                temp[i][j] = this.m[i][j];
            }
        }
        detX[0] = Matrix.DetByKofaktor(this.m);

        // Cek solusi
        int Row = this.m.length - 1;
        int Col = this.m[0].length - 1;

        if (detX[0] == 0 || (Row != Col -1)){
            SPLtidakAdaSolusi();
        }
        else{
            SPLsatuSolusi();
        }

        if (this.satuSolusi){
            // Mengganti setiap kolom dan memasukkan determinan matriks baru ke detX
            for (i = 0 ; i < n ; i++){
                for (j = 0 ; j < n ; j++){
                    temp[j][i] = this.m[j][n];
                    if (i > 0){
                        temp[j][i-1] = this.m[j][i-1];
                    }
                }
                detX[i+1] = Matrix.DetByKofaktor(temp);
            }

            // Mencari nilai x dengan determinan yang telah didapat
            for (i = 0 ; i < n ; i++){
                x[i] = detX[i+1]/detX[0];
                if (x[i] == 0.0){
                    x[i] = 0.0;
                }
            }
            return doubleToStringConverter(x);
        }
        else{
            String[] ans = {"Tidak ada solusi / Tidak dapat menggunakan metode cramer"};
            return ans;
        }
    }

    public String[] SPLInverse(){
        // Inisiasi variabel
        int Row = this.m.length;
        int Col = this.m[0].length;
        double[] Res = new double[Row];
        double[][] MatTemp = new double[Row][Col-1];

        // Membuat Matrix Baru
        double[][] MatBaru = Operations.Matrix_Right_Cutter(this.m);

        // Membuat Matrix Temp
        for (int i = 0 ; i < Row ; i++){
            for (int j = 0 ; j < Col-1 ; j++){
                MatTemp[i][j] = this.m[i][j];
            }
        }
        // Cek Solusi
        if (!Matrix.IsSquare(MatTemp) || Matrix.DetByKofaktor(MatTemp) == 0 || MatTemp.length != MatTemp[0].length){
            SPLtidakAdaSolusi();
        }
        else{
            SPLsatuSolusi();
        }
        Matrix.outputMatrix(MatTemp);
        // Menghitung Solusi
        if (this.satuSolusi){
            // Matrix.outputMatrix(MatTemp);
            double[][] MatRes = Operations.Matrix_Multiplier(Inverse.InverseByOBE(MatTemp), MatBaru);
            Inverse.InverseByOBE(MatTemp);
            for (int i = 0 ; i < Row ; i++){
                Res[i] = MatRes[i][0];
                if (Res[i] == 0.0){
                    Res[i] = 0.0;
                }
            }
            return doubleToStringConverter(Res);
        }
        else{
            String[] ans = {"Tidak ada solusi"};
            return ans;
        }
    }
}