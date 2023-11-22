import java.util.ArrayList;

//Maximilian Ellnestam mael0424
public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private static final ArrayList<String> DACHSHUND_TRANSLATIONS = new ArrayList<>(){{
        add("tax");
        add("dachshund");
        add("teckel");
        add("mäyräkoira");
    }};

    private Owner owner;
    private final String name;
    private final String breed;
    private final int weight;
    private int age;

    public Dog(String name, String breed, int age, int weight) {
        this.name = formatName(name);
        this.breed = formatName(breed);
        this.age = age;
        this.weight = weight;
    }

    public void updateAge() {
        if (age < Integer.MAX_VALUE)
            age++;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Age: " + age + " Breed: " + breed + " Weight: " + weight + "kg" + " Tail length: " + getTailLength() + " Owner: " + getOwner();
    }

    public double getTailLength(){
        double tailLength = (age * weight) / 10d;

        if (DACHSHUND_TRANSLATIONS.contains(breed.toLowerCase()))
            tailLength = DACHSHUND_TAIL_LENGTH;

        return tailLength;
    }

    public boolean setOwner(Owner owner) {
        if (owner == null){
            this.owner.removeDog(this);
            this.owner = null;
            return true;
        }

        if (this.owner == null) {
            this.owner = owner;
            this.owner.addDog(this);
            return true;
        }

        return false;
    }

    public Owner getOwner() {
        return owner;
    }

    private static String formatName(String name){
        if (name.indexOf("-") != name.lastIndexOf("-"))
            return name.toUpperCase();

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
}
