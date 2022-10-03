package Matrix;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Output_Matrix {
    public static Scanner sc;
    public static FileWriter WR;
    public static String dir = "test\\result\\";
    public static String path = "";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static String MatrixToString(double[][] M){
        String result = "";
        for (int i = 0; i < M.length; i++){
            for (int j = 0; j < M[0].length; j++){
                result += M[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
    
    public static void CreateFile(){
        String directory = System.getProperty("user.dir");
        directory = directory.substring(directory.lastIndexOf("\\")+1);
        if (directory.equals("bin")){
            dir = "..\\test\\result\\";
        }
        else{
            dir = "test\\result\\";
        }

        Date date = new Date();
        File file = new File(dir + dateFormat.format(date) + ".txt");
        try {
            file.createNewFile();
            path = file.getAbsolutePath();
        } catch (IOException e) {
            System.out.println("Cannot create file");
        }
    }

    public static void DetToFile(double res){
        try{
            String result = Double.toString(res);
            CreateFile();
            Date date = new Date();
            WR = new FileWriter(path);
            WR.write(dateFormat.format(date) + "\n");
            WR.write("Determinan Matriks : \n");
            WR.write(result);
            WR.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("Determinant File error occured.");   
        }
    }

    public static void InverseToFile(double[][] M){
        try{
            String MatrixString = MatrixToString(M);
            CreateFile();
            Date date = new Date();
            path = dir + dateFormat.format(date) + ".txt";
            WR = new FileWriter(path);
            WR.write(dateFormat.format(date) + "\n");
            WR.write("Matriks Invers: \n");
            WR.write(MatrixString);
            WR.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("Inverse File error occured.");
        }
    }

    public static void RegressionToFile(String res, double[] X, double regres){
        try{
            CreateFile();
            Date date = new Date();
            path = dir + dateFormat.format(date) + ".txt";
            WR = new FileWriter(path);
            WR.write(dateFormat.format(date) + "\n");
            WR.write("Hasil Interpolasi: \n");
            WR.write(res);
            WR.write("\nNilai X: \n");
            for (int i = 0; i < X.length; i++){
                WR.write("X" + (i+1) + " = " + X[i] + " \n");
            }
            WR.write("Hasil Regresi : " + regres);
            WR.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("Interpolate File error occured.");
        }
    }

    public static void InterpolateToFile(double[] res, double x, double y){
        try{
            CreateFile();
            Date date = new Date();
            path = dir + dateFormat.format(date) + ".txt";
            WR = new FileWriter(path);
            WR.write(dateFormat.format(date) + "\n");
            WR.write("Hasil Interpolasi: \n");
            for (int i = 0; i < res.length; i++){
                WR.write("a" + i + " = " + res[i] + " \n");
            }
            WR.write("Hasil Interpolasi F(" + x + ") = " + y);
            WR.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("Interpolate File error occured.");
        }
    }
}