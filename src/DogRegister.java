import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

//Maximilian Ellnestam mael0424
public class DogRegister {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Maxi\\Documents\\programering\\REPOSOTORIES\\SisyphusPusher\\SisyphusPusher\\SisyphusPusher\\SaveState.txt");
        InputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e){

            return;
        }

        InputReader input = new InputReader(stream);
        InputReader input1 = new InputReader(stream);
        boolean readInput = true;

        while (readInput){
            switch (input.nextLine("Command?>").toLowerCase()){
                case "exit":
                    readInput = false;
                    break;
                default:
                    System.out.println("Error");
                    break;
            }

        }
    }
}
