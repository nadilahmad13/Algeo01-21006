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

    public static void CreatePath(){
        String directory = System.getProperty("user.dir");
        directory = directory.substring(directory.lastIndexOf("\\")+1);
        if (directory.equals("bin")){
            dir = "..\\test\\result\\";
        }
        else{
            dir = "test\\result\\";
        }
    }

    public static void CreateFile(){
        try{
            CreatePath();
            Date date = new Date();
            File file = new File(dir + dateFormat.format(date) + ".txt");
            if(!file.exists()){
                file.createNewFile();
            }
            path = file.getAbsolutePath();
        }
        catch (IOException e){
            System.out.println("Cannot create file");
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
            WR.write("Matriks Invers: ");
            WR.write(MatrixString);
            WR.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("Inverse File error occured.");
        }
    }
}