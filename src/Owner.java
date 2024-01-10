//Maximilian Ellnestam mael0424

import java.util.ArrayList;

public class Owner implements Comparable<Owner> {
    private String name = "";

    private final ArrayList<Dog> dogs = new ArrayList<>();

    public Owner(String name){
        this.name = formatName(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Dog dog : dogs)
            string.append(dog.getName()).append(",");

        for (int i = 0; i < dogs.size(); i++){
            string.append(dogs.get(i).getName());
            if (i < dogs.size() - 1)
                string.append(", ");
        }

        return "Name: " + name + " Owned Dogs: " + string;
    }

    public boolean addDog(Dog dog){
        if (dog.getOwner() == null || dog.getOwner() == this)
            return dogs.add(dog);
        return false;
    }

    public boolean removeDog(Dog dog){
        return dogs.remove(dog);
    }

    public ArrayList<Dog> getDogs() {
        return (ArrayList<Dog>) dogs.clone();
    }

    private static String formatName(String name){
        String[] names = name.toLowerCase().split(" ");
        StringBuilder formattedName = new StringBuilder();

        for (int i = 0; i < names.length; i++){
            if (names[i].contains("-")){
                int secondNameStart = names[i].indexOf("-") + 1;
                String firstName = names[i].substring(0, secondNameStart);
                String firstCharSecondName = names[i].substring(secondNameStart, secondNameStart + 1).toUpperCase();
                String secondName =  names[i].substring(secondNameStart + 1);

                names[i] = firstName + firstCharSecondName + secondName;
            }

            names[i] = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
        }

        for (String splitName : names){
            formattedName.append(splitName);
            if (!splitName.equals(names[names.length - 1]))
                formattedName.append(" ");
        }

        return formattedName.toString();
    }


    @Override
    public int compareTo(Owner owner) {
        return name.compareTo(owner.getName());
    }
}
