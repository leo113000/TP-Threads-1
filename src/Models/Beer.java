package Models;

public class Beer {
    // Attributes
    private String name;
    private BeerType beerType;

    // Constructors
    public Beer(String name, BeerType beerType) {
        this.name = name;
        this.beerType = beerType;
    }

    // Getters
    public String getName() {
        return name;
    }

    public BeerType getBeerType() {
        return beerType;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBeerType(BeerType beerType) {
        this.beerType = beerType;
    }
}
