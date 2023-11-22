import java.util.Comparator;

//Maximilian Ellnestam mael0424
public class DogTailNameComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog firstDog, Dog secondDog) {
        int tailCompare = Double.compare(firstDog.getTailLength(), secondDog.getTailLength());
        if (tailCompare == 0)
            return firstDog.getName().compareTo(secondDog.getName());
        return tailCompare;
    }
}
