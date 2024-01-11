import java.util.ArrayList;
import java.util.Comparator;

//Maximilian Ellnestam mael0424
public class DogSorter {

    public static int sortDogs(Comparator<Dog> dogComparator, ArrayList<Dog> dogs){
        int swapCount = 0;

        for (int j = 0; j < dogs.size(); j++){
            int nextIndex = nextDog(dogComparator, dogs, j);
            if (nextIndex == j)
                continue;
            swapDogs(dogs, j, nextIndex);
            swapCount++;
        }

        return swapCount;
    }

    private static void swapDogs(ArrayList<Dog> dogs, int i, int j){
        if (dogs.size() - 1 < i || dogs.size() - 1 < j || i == j)
            return;
        Dog firstDog = dogs.get(i);
        Dog secondDog = dogs.get(j);

        dogs.set(j, firstDog);
        dogs.set(i, secondDog);
    }

    private static int nextDog(Comparator<Dog> cmp, ArrayList<Dog> dogs, int index){
        int nextIndex = 0;
        Dog nextDog = null;

        for (int i = index; i < dogs.size(); i++){
            if (nextDog == null || cmp.compare(nextDog, dogs.get(i)) > 0){
                nextDog = dogs.get(i);
                nextIndex = i;
            }
        }

        return nextIndex;
    }
}
