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

    public void menuSPL (int pilihan){
        String[] ans = {""};
        if (pilihan == 1){
            ans = gaussElimination();
        }
        // else if (pilihan == 2){
        //     ans = gaussJordan();
        // }
        // else if (pilihan == 3){
        //     ans = cramer();
        // }
        // else if (pilihan == 4){
        //     ans = inverseSPL();
        // }
        else{
            System.out.println("Pilihan tidak ada");
        }
        this.solusi = ans;
    }

    public String solusiOutput(){
        String out = "";
        if (satuSolusi){
            for (int i = 0 ; i < this.solusi.length ; i++){
                if (this.solusi.length-1 == i){
                    out += "X" + (i+1) + " = " + this.solusi[i];
                }
                else{
                    out += "X" + (i+1) + " = " + this.solusi[i] + "\n";
                }
            }
        }

        else if (banyakSolusi){
            for (int i = 0 ; i < this.solusi.length ; i++){
                if (this.solusi.length - 1 == i){
                    out += "X" + (i+1) + " = " + this.solusi[i];
                }
                else{
                    out += "X" + (i+1) + " = " + this.solusi[i] + "\n";
                }
            }
        }

        else if (tidakAdaSolusi){
            out = this.solusi[0];
        }

        return out;
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
}