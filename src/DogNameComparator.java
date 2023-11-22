import java.util.Comparator;

//Maximilian Ellnestam mael0424
public class DogNameComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog firstDog, Dog secondDog) {
        return firstDog.getName().compareTo(secondDog.getName());
    }
}
