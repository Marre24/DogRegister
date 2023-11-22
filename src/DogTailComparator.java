import java.util.Comparator;

//Maximilian Ellnestam mael0424
public class DogTailComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog firstDog, Dog secondDog) {
        return Double.compare(firstDog.getTailLength(), secondDog.getTailLength());
    }
}
