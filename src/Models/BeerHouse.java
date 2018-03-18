package Models;

import java.util.Map;

public class BeerHouse {

    private BeerInventory inventory;
    private Boolean close;
    public BeerHouse() {
        inventory = new BeerInventory();
        this.close =  false;
    }

    public synchronized void put(Beer b, int qty) {
        /**
         * Loop that blocks the thread
         */
        while (inventory.getRemainingCapacity() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // add beer
        inventory.addBeerQtyByType(b,qty);
        // notify another thread
        notify();
    }

    public synchronized int get(BeerType bt, int qty) {
        int removed = 0;
        // remove beer
        removed = inventory.removeBeerQtyByType(bt, qty);
        if(removed>0)
            notify();
        // Return the amount consumed by the operation
        return removed;
    }

    public boolean hasBeer() {
        return !inventory.isEmpty();
    }

    public void close() {
        this.close = true;
    }

    public Boolean isClosed(){
        return this.close;
    }

}
