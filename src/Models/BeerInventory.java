package Models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeerInventory {
    //Constants
    private final int TOPLIMIT = 100; // The default limit of beer
    // Attributes
    private Map inventory; // Map to handle the inventory. Key: Beer - Value: Quantity
    private int limit; // The max quantity of beer
    // Constructors
    /**
     * Empty Constructor
     */
    public BeerInventory() {
        this.inventory = new HashMap<Beer, Integer>();
        this.limit = this.TOPLIMIT;
    }
    /**
     * @param [int]limit: the max quantity of beer
     */
    public BeerInventory(int limit) {
        this.inventory = new HashMap<Beer, Integer>();
        this.limit = limit;
    }
    //Methods
    /**
     * @return [int] remainingCapacity: the remaining capacity according the limit
     */
    public synchronized int getRemainingCapacity() {
        // Accumulative auxiliar variable
        int actualUsed = 0;
        // Get all the values in the Map
        Collection values = this.inventory.values();
        // For each one
        for(Object o : values){
           // Cast and added to the total
            actualUsed += (Integer) o;
        }
        // The limit minus the used
        int remainingCapacity = limit - actualUsed;
        // return remaining capacity or zero
        return remainingCapacity > 0 ? remainingCapacity : 0;
    }
    /**
     * @param [Beer] toSearch:The name of the beer
     * @param [int]  qty:The quantity to be added to the inventory
     * @return [Beer] target: The searched beer obj, return null if the beer didn't exists
     */
    public Beer addBeerQtyByType(Beer toSearch, int qty) throws UnsupportedOperationException, NullPointerException {

        // Throw Exception if qty<0
        if(qty <= 0)
            throw new UnsupportedOperationException("Add negative quantities is not allowed");
        // Throw Exception if toSearch is null
        if(toSearch == null)
            throw new NullPointerException("The beer to search is null");
        // Search the beer in the inventory
        Beer target = this.searchBeerByType(toSearch.getBeerType());
        Integer newQty = 0;
        if(target != null){
            // Calculate the new quantity
            int remainingCapacity = this.getRemainingCapacity();
            newQty = remainingCapacity > qty ? qty : remainingCapacity;
            newQty = (Integer) inventory.get(target) + newQty;
        }else{
            // if the beer didn't exist, use the one that came by parameter
            target = toSearch;
            newQty = qty;
        }
        // Save it the value in the Map
        inventory.put(target, newQty);
        // Return the beer
        return target;
    }
    /**
     * @param [BeerType] toSearch:The type of the beer
     * @param [int]  qty:The quantity to be removed to the inventory
     * @return [int] consumed: the amount removed from the inventory
     */
    public int removeBeerQtyByType(BeerType toSearch, int qty) throws UnsupportedOperationException, NullPointerException {
        // Throw Exception if qty<0
        if(qty <= 0)
            throw new UnsupportedOperationException("Remove negative quantities is not allowed");
        // Throw Exception if toSearch is null
        if(toSearch == null)
            throw new NullPointerException("The beer to search is null");
        // Total consumed
        int consumed = 0;
        // Search the beer in the inventory
        Beer target = this.searchBeerByType(toSearch);
        // if the beer exists
        if (target != null) {
            // Get the actual qty of the beer
            int actualQty = (Integer) inventory.get(target);
            // Calculate the new quantity
            Integer newQty = actualQty - qty < 0 ? 0 : actualQty - qty;
            // Save the amount consumed
            consumed = actualQty - newQty;
            // Check positivity
            newQty = newQty > 0 ? newQty : 0;
            // Save it the value in the Map
            inventory.put(target, newQty);
        }
        // Return the beer if exists
        return consumed;
    }
    /**
     * @param Beertype to search into the Map
     * @return Beer Obj or null if the beer didn't exists
     */
    private Beer searchBeerByType(BeerType bt) {
        // The searched beer, null if not exists
        Beer target = null;
        // Get all the keys of the Map (the method returns a Set)
        Set keys = inventory.keySet();
        // For each around all keys
        for (Object b : keys) {
            Beer beer = (Beer) b;
            // Comparing the name of the beer Type in the set with the name of the received
            if (beer.getBeerType().getName().equals(bt.getName())) {
                // if it's found, assign it to the variable to be returned
                target = beer;
                // Sorry Sonvico, I prefer For Each over iterator, and in this case i need this break
                break;
            }
        }
        return target;
    }

    public Boolean isEmpty() {
        return this.getRemainingCapacity() == this.limit;
    }
    /**
     * @param beerName: Beer to search into the Map
     * @return Beer Obj or null if the beer didn't exists
     */
    /*private Beer searchBeerByName(String beerName) {
        // The searched beer, null if not exists
        Beer target = null;
        // Get all the keys of the Map (the method returns a Set)
        Set keys = inventory.keySet();
        // For each around all keys
        for (Object b : keys) {
            Beer beer = (Beer) b;
            // Comparing the name of the beer in the set with the received string
            if (beer.getName().equals(beerName)) {
                // if it's found, assign it to the variable to be returned
                target = beer;
                // Sorry Sonvico, I prefer For Each over iterator, and in this case i need this break
                break;
            }
        }
        return target;
    }*/
}
