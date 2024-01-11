import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
//Maximilian Ellnestam mael0424
public class InputReader {
    private static final HashSet<InputStream> USED_STREAMS = new HashSet<>();
    private final Scanner input;
    public InputReader(){
        if (!USED_STREAMS.add(System.in))
            throw new IllegalStateException("ERROR");
        input = new Scanner(System.in);
    }

    public InputReader(InputStream stream){
        if (!USED_STREAMS.add(stream))
            throw new IllegalStateException("ERROR");
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

    public String readString(String string){
        System.out.print(string + "?>");
        return input.nextLine();
    }

}
