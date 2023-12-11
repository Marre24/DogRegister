import java.util.Scanner;

//Maximilian Ellnestam mael0424
public class DogRegister {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean readInput = true;

        while (readInput){
            System.out.println("Command?>");
            String command = myObj.nextLine();  // Read user input
            switch (command.toLowerCase()){
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
