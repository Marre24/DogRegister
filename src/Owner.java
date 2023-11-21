//Maximilian Ellnestam mael0424
public class Owner {
    private final String name;

    public Owner(String name){
        this.name = formatName(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
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
}
