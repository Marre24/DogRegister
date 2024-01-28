import java.util.ArrayList;
import java.util.Optional;

//Maximilian Ellnestam mael0424
public class DogCollection {

    private final ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog dog){
        if (containsDog(dog.getName()))
            return false;
        return dogs.add(dog);
    }

    public boolean removeDog(Dog dog){
        if (dog.getOwner() != null)
            return false;
        return dogs.remove(dog);
    }

    public boolean removeDog(String name){
        Optional<Dog> dog = dogs.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if (!containsDog(name) || dog.isEmpty() || dog.get().getOwner() != null)
            return false;
        return dogs.remove(dog.get());
    }

    public boolean containsDog(String name){
        return dogs.stream().anyMatch(dog -> dog.getName().equalsIgnoreCase(name));
    }


    public boolean containsDog(Dog dog){
        return dogs.contains(dog);
    }

    public ArrayList<Dog> getDogs() {
        DogSorter.sortDogs(new DogNameComparator(), dogs);
        return (ArrayList<Dog>) dogs.clone();
    }

    public Dog getDog(String name){
        Optional<Dog> dog = dogs.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if (!containsDog(name) || dog.isEmpty())
            return null;
        return dog.get();
    }

    public ArrayList<Dog> getDogsWithMinimumTailLength(double d){
        if (d == 0)
            DogSorter.sortDogs(new DogTailNameComparator(), dogs);
        else
            DogSorter.sortDogs(new DogNameComparator(), dogs);
        return new ArrayList<>(dogs.stream().filter(dog -> dog.getTailLength() >= d).toList());
    }
}