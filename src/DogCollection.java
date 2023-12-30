import java.util.ArrayList;
import java.util.Optional;

//Maximilian Ellnestam mael0424
public class DogCollection {

    private final ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog dog){
        if (dog.getName().isEmpty()){
            System.out.println("ERROR");
            return false;
        }
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
        Optional<Dog> dog = dogs.stream().filter(x -> x.getName().equals(name)).findFirst();
        if (!containsDog(name) || dog.isEmpty())
            return false;
        if (dog.get().getOwner() != null)
            dog.get().getOwner().removeDog(dog.get());
        return dogs.remove(dog.get());
    }

    public boolean containsDog(String name){
        return dogs.stream().anyMatch(x -> x.getName().equals(name));
    }

    public boolean containsDog(Dog dog){
        return dogs.contains(dog);
    }

    public ArrayList<Dog> getDogs() {
        DogSorter.sortDogs(new DogTailNameComparator(), dogs);
        return (ArrayList<Dog>) dogs.clone();
    }

    public Dog getDog(String name){
        Optional<Dog> dog = dogs.stream().filter(x -> x.getName().equals(name)).findFirst();
        if (!containsDog(name) || dog.isEmpty())
            return null;
        return dog.get();
    }

    public ArrayList<Dog> getDogsWithMinimumTailLength(double d){
        ArrayList<Dog> dogArrayList = new ArrayList<>(dogs.stream().filter(x -> x.getTailLength() >= d).toList());
        DogSorter.sortDogs(new DogTailNameComparator(), dogArrayList);
        return dogArrayList;
    }
}