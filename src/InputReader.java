import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
//Maximilian Ellnestam mael0424
public class InputReader {
    private static HashSet<InputStream> usedStreams = new HashSet<>();
    private Scanner input;
    public InputReader(){
        if (!usedStreams.add(System.in)) {
            throw new IllegalStateException("ERROR");
        }
        input = new Scanner(System.in);
    }

    public InputReader(InputStream stream){
        if (!usedStreams.add(stream)) {
            throw new IllegalStateException("ERROR");
        }
        input = new Scanner(stream);
    }


    public int readInt(String string){
        System.out.print(string + "?>");
        return Integer.parseInt(input.nextLine());
    }

    public float readFloat(String string){
        System.out.print(string + "?>");
        return Float.parseFloat(input.nextLine().replace(',', '.'));
    }

    public String nextLine(String string){
        System.out.print(string + "?>");
        return input.nextLine();
    }

}
