package Models;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum BeerType {
    //Enum's Objects
    BLONDE("Blonde", 1), HONEY("Honey", 2), RED("Scotch", 3), PORTER("Porter", 4), IPA("Indian Pale Ale", 5);
    //Attributes
    private String name;
    private int id;
    // Static Attributes
    private static List<BeerType> values = Arrays.asList(BeerType.values()); // Convert all the values into a List<BeerType>
    private static int size = BeerType.values.size(); // this variable saves the amount of values in the enum
    //Constructors
    BeerType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName() + " style";
    }

    //Getters
    public String getName() { return name; }
    public int getId() { return id; }
    /**
     * Generates a random beerType
     * @return BeerType Obj
     */
    public static BeerType random(){
        // Generate a random number between zero and the amount of values in the enum
        int random = ThreadLocalRandom.current().nextInt(0,BeerType.size); // The second parameter is the limit + 1 (to be inclusive)
        return BeerType.values.get(random);
    }

}
