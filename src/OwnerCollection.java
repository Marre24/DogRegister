import java.util.*;

//Maximilian Ellnestam mael0424
public class OwnerCollection {

    private Owner[] owners = new Owner[0];

    public boolean addOwner(Owner owner){
        if (containsOwner(owner))
            return false;
        incrementSize();
        owners[owners.length - 1] = owner;
        return owners[owners.length - 1] != null;
    }

    public boolean removeOwner(Owner owner){
        int index = findIndex(owner);
        if (index < 0)
            return false;
        for (int j = index; j < owners.length - 1; j++)
            owners[j] = owners[j + 1];
        decrementSize();
        return true;
    }
    public boolean removeOwner(String name){
        int index = findIndex(name);
        if (index < 0)
            return false;
        for (int j = index; j < owners.length - 1; j++)
            owners[j] = owners[j + 1];
        decrementSize();
        return true;
    }

    public boolean containsOwner(String name){
        return findIndex(name) >= 0;
    }

    public boolean containsOwner(Owner owner){
        return findIndex(owner) >= 0;
    }

    public ArrayList<Owner> getOwners() {
        return new ArrayList<>(Arrays.stream(owners).toList());
    }

    public Owner getOwner(String name){
        if (findIndex(name) < 0)
            return  null;
        return owners[findIndex(name)];
    }

    private int findIndex(Owner owner){
        for (int i = 0; i < owners.length; i++)
            if (owners[i] == owner)
                return i;

        return -1;
    }
    private int findIndex(String name){
        for (int i = 0; i < owners.length; i++)
            if (owners[i].getName().equals(name))
                return i;

        return -1;
    }

    private void incrementSize(){
        owners = Arrays.copyOf(owners, owners.length + 1);
    }

    private void decrementSize(){
        owners = Arrays.copyOf(owners, owners.length - 1);
    }
}
