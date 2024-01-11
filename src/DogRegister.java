import java.util.List;

//Maximilian Ellnestam mael0424
public class DogRegister {
    private static final String COMMAND_REGISTER_OWNER = "register new owner";
    private static final String COMMAND_REMOVE_OWNER = "remove owner";
    private static final String COMMAND_REGISTER_DOG = "register new dog";
    private static final String COMMAND_REMOVE_DOG = "remove dog";
    private static final String COMMAND_LIST_DOGS = "list dogs";
    private static final String COMMAND_LIST_OWNERS = "list owners";
    private static final String COMMAND_INCREASE_AGE = "increase age";
    private static final String COMMAND_GIVE_DOG_TO_OWNER = "give dog to owner";
    private static final String COMMAND_REMOVE_DOG_FROM_OWNER = "remove dog from owner";
    private static final String COMMAND_EXIT = "exit";

    private OwnerCollection owners;
    private DogCollection dogs;
    private InputReader input;

    public static void main(String[] args) {

        boolean readInput = true;
        DogRegister register = new DogRegister();

        register.init();
        while (readInput)
            if (!register.runCommandLoop())
                readInput = false;
    }

    private void init(){
        owners = new OwnerCollection();
        dogs = new DogCollection();
        input = new InputReader();
    }

    private boolean runCommandLoop() {
        //Add menu
        String command = input.readString("Command").toLowerCase();
        switch (command) {
            case COMMAND_REGISTER_OWNER:
                registerNewOwner();
                break;
            case COMMAND_REMOVE_OWNER:
                removeOwner();
                break;
            case COMMAND_REGISTER_DOG:
                registerDog();
                break;
            case COMMAND_REMOVE_DOG:
                removeDog();
                break;
            case COMMAND_LIST_DOGS:
                listDogs();
                break;
            case COMMAND_LIST_OWNERS:
                listOwners();
                break;
            case COMMAND_INCREASE_AGE:
                increaseDogAge();
                break;
            case COMMAND_GIVE_DOG_TO_OWNER:
                giveDogToOwner();
                break;
            case COMMAND_REMOVE_DOG_FROM_OWNER:
                removeDogFromOwner();
                break;
            case COMMAND_EXIT:
                return false;
            default:
                System.out.println("Error: Invalid command");
                break;
        }
        return true;
    }

    private void registerNewOwner() {
        String ownerName = input.readString("Enter owner name");
        while (ownerName.isEmpty() || ownerName.matches("[\\s\\t]*")) {
            System.out.println("Error: A blank string is not allowed, try again");
            ownerName = input.readString("Enter owner name");
        }
        owners.addOwner(new Owner(ownerName));
        System.out.println("The owner " + ownerName + " has been added to the register");
    }

    private void removeOwner() {
        if (owners.getOwners().isEmpty()){
            System.out.println("Error: No owners in register");
            return;
        }
        String ownerName = input.readString("Enter owner name");
        Owner owner = owners.getOwner(ownerName);
        if (owner != null) {
            if (!owner.getDogs().isEmpty())
                for (Dog dog : owner.getDogs()){
                    dog.setOwner(null);
                    dogs.removeDog(dog);
                }

            if (owners.removeOwner(ownerName))
                System.out.println("The owner " + ownerName + " has been removed from the register");
        } else {
            System.out.println("Error: Owner not found");
        }
    }

    private void registerDog() {
        String name = input.readString("Enter owner name");
        while (name.isEmpty() || name.matches("[\\s\\t]*")) {
            System.out.println("Error: A blank string is not allowed, try again");
            name = input.readString("Enter owner name");
        }
        if (dogs.containsDog(name)){
            System.out.println("ERROR");
            return;
        }
        String breed = input.readString("Enter dog breed");
        while (breed.isEmpty() || breed.matches("[\\s\\t]*")) {
            System.out.println("Error: A blank string is not allowed, try again");
            breed = input.readString("Enter owner name");
        }
        int age = input.readInt("Enter dog age");
        int weight = input.readInt("Enter dog weight");
        dogs.addDog(new Dog(name, breed, age, weight));
        System.out.println("The dog " + name + " has been added to the register");
    }

    private void removeDog() {
        if (dogs.getDogs().isEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }
        String dogName = input.readString("Enter dog name");
        if (dogs.getDog(dogName) != null) {
            if (dogs.removeDog(dogName))
                System.out.println("The dog " + dogName + " has been removed from the register");
        } else {
            System.out.println("Error: Dog not found");
        }
    }

    private void listDogs() {
        List<Dog> dogList = dogs.getDogs();
        if (dogList.isEmpty()) {
            System.out.println("ERROR: No dogs in the register.");
        } else {
            float length = input.readFloat("Enter minimum tail length");
            for (Dog dog : dogs.getDogsWithMinimumTailLength(length))
                System.out.println(dog);
        }
    }

    private void listOwners() {
        List<Owner> ownerList = owners.getOwners();
        if (ownerList.isEmpty()) {
            System.out.println("ERROR: No owners in the register.");
        } else {
            for (Owner owner : ownerList) {
                System.out.println(owner.toString());
            }
        }
    }

    private void increaseDogAge() {
        if (dogs.getDogs().isEmpty()){
            System.out.println("Error");
            return;
        }
        String dogName = input.readString("Enter dog name");
        Dog dog = dogs.getDog(dogName);
        if (dog != null) {
            dog.updateAge();
            System.out.println("Age increased for dog " + dogName);
        } else {
            System.out.println("Error: Dog not found");
        }
    }

    private void giveDogToOwner() {
        if (owners.getOwners().isEmpty() || dogs.getDogs().isEmpty()){
            System.out.println("Error");
            return;
        }
        String dogName = input.readString("Enter dog name");
        if (dogs.getDog(dogName) == null || dogs.getDog(dogName).getOwner() != null){
            System.out.println("Error");
            return;
        }

        String ownerName = input.readString("Enter owner name");

        Dog dog = dogs.getDog(dogName);
        Owner owner = owners.getOwner(ownerName);

        if (dog != null && owner != null) {
            dog.setOwner(owner);
            System.out.println("Dog " + dogName + " given to owner " + ownerName);
        } else {
            System.out.println("Error: Dog or owner not found");
        }
    }

    private void removeDogFromOwner() {
        if (owners.getOwners().isEmpty() || dogs.getDogs().isEmpty()){
            System.out.println("Error");
            return;
        }
        String dogName = input.readString("Enter dog name");
        if (!dogs.containsDog(dogName) || dogs.getDog(dogName).getOwner() == null){
            System.out.println("Error");
            return;
        }

        Dog dog = dogs.getDog(dogName);

        if (dog != null) {
            dog.setOwner(null);
            System.out.println("The dog Rex now have no owner");
        } else {
            System.out.println("Error: Dog or owner not found");
        }
    }
}
