import java.util.ArrayList;

//Maximilian Ellnestam mael0424
public class Main {
    public static void main(String[] args) {
        var dog = new Dog("jhalkjdh", "qwriu" ,20, 20);
        var owner = new Owner("maxi");

        dog.setOwner(owner);

        System.out.println(owner.toString());
    }
}