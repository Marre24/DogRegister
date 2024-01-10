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

    public static void main(String[] args) {
        OwnerCollection owners = new OwnerCollection();
        DogCollection dogs = new DogCollection();
        boolean readInput = true;
        InputReader input = new InputReader();

        while (readInput) {
            String command = input.nextLine("Command").toLowerCase();
            switch (command) {
                case COMMAND_REGISTER_OWNER:
                    registerNewOwner(owners, input);
                    break;
                case COMMAND_REMOVE_OWNER:
                    removeOwner(owners, input, dogs);
                    break;
                case COMMAND_REGISTER_DOG:
                    registerDog(input, dogs);
                    break;
                case COMMAND_REMOVE_DOG:
                    removeDog(dogs, input);
                    break;
                case COMMAND_LIST_DOGS:
                    listDogs(dogs, input);
                    break;
                case COMMAND_LIST_OWNERS:
                    listOwners(owners);
                    break;
                case COMMAND_INCREASE_AGE:
                    increaseDogAge(dogs, input);
                    break;
                case COMMAND_GIVE_DOG_TO_OWNER:
                    giveDogToOwner(input, owners, dogs);
                    break;
                case COMMAND_REMOVE_DOG_FROM_OWNER:
                    removeDogFromOwner(input, owners, dogs);
                    break;
                case COMMAND_EXIT:
                    readInput = false;
                    break;
                default:
                    System.out.println("Error: Invalid command");
                    break;
            }
        }
    }

    private static void registerNewOwner(OwnerCollection owners, InputReader input) {
        String ownerName = input.nextLine("Enter owner name");
        while (ownerName.isEmpty() || ownerName.matches("[\\s\\t]*")) {
            System.out.println("Error: A blank string is not allowed, try again");
            ownerName = input.nextLine("Enter owner name");
        }
        owners.addOwner(new Owner(ownerName));
        System.out.println("The owner " + ownerName + " has been added to the register");
    }

    private static void removeOwner(OwnerCollection owners, InputReader input, DogCollection dogs) {
        if (owners.getOwners().isEmpty()){
            System.out.println("Error: No owners in register");
            return;
        }
        String ownerName = input.nextLine("Enter owner name");
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

    private static void registerDog(InputReader input, DogCollection dogs) {
        String name = input.nextLine("Enter owner name");
        while (name.isEmpty() || name.matches("[\\s\\t]*")) {
            System.out.println("Error: A blank string is not allowed, try again");
            name = input.nextLine("Enter owner name");
        }
        if (dogs.containsDog(name)){
            System.out.println("ERROR");
            return;
        }
        String breed = input.nextLine("Enter dog breed");
        while (breed.isEmpty() || breed.matches("[\\s\\t]*")) {
            System.out.println("Error: A blank string is not allowed, try again");
            breed = input.nextLine("Enter owner name");
        }
        int age = input.readInt("Enter dog age");
        int weight = input.readInt("Enter dog weight");
        dogs.addDog(new Dog(name, breed, age, weight));
        System.out.println("The dog " + name + " has been added to the register");
    }

    private static void removeDog(DogCollection dogs, InputReader input) {
        if (dogs.getDogs().isEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }
        String dogName = input.nextLine("Enter dog name");
        if (dogs.getDog(dogName) != null) {
            if (dogs.removeDog(dogName))
                System.out.println("The dog " + dogName + " has been removed from the register");
        } else {
            System.out.println("Error: Dog not found");
        }
    }

    private static void listDogs(DogCollection dogs, InputReader input) {
        List<Dog> dogList = dogs.getDogs();
        if (dogList.isEmpty()) {
            System.out.println("ERROR: No dogs in the register.");
        } else {
            float length = input.readFloat("Enter minimum tail length");
            for (Dog dog : dogs.getDogsWithMinimumTailLength(length))
                System.out.println(dog);
        }
    }

    private static void listOwners(OwnerCollection owners) {
        List<Owner> ownerList = owners.getOwners();
        if (ownerList.isEmpty()) {
            System.out.println("ERROR: No owners in the register.");
        } else {
            for (Owner owner : ownerList) {
                System.out.println(owner.toString());
            }
        }
    }

    private static void increaseDogAge(DogCollection dogs, InputReader input) {
        if (dogs.getDogs().isEmpty()){
            System.out.println("Error");
            return;
        }
        String dogName = input.nextLine("Enter dog name");
        Dog dog = dogs.getDog(dogName);
        if (dog != null) {
            dog.updateAge();
            System.out.println("Age increased for dog " + dogName);
        } else {
            System.out.println("Error: Dog not found");
        }
    }

    private static void giveDogToOwner(InputReader input, OwnerCollection owners, DogCollection dogs) {
        if (owners.getOwners().isEmpty() || dogs.getDogs().isEmpty()){
            System.out.println("Error");
            return;
        }
        String dogName = input.nextLine("Enter dog name");
        if (dogs.getDog(dogName) == null || dogs.getDog(dogName).getOwner() != null){
            System.out.println("Error");
            return;
        }

        String ownerName = input.nextLine("Enter owner name");

        Dog dog = dogs.getDog(dogName);
        Owner owner = owners.getOwner(ownerName);

        if (dog != null && owner != null) {
            dog.setOwner(owner);
            System.out.println("Dog " + dogName + " given to owner " + ownerName);
        } else {
            System.out.println("Error: Dog or owner not found");
        }
    }

    private static void removeDogFromOwner(InputReader input, OwnerCollection owners, DogCollection dogs) {
        if (owners.getOwners().isEmpty() || dogs.getDogs().isEmpty()){
            System.out.println("Error");
            return;
        }
        String dogName = input.nextLine("Enter dog name");
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
