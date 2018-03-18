package Models;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{
    private final int SLEEP_TIME = 2000;
    private BeerHouse bar;
    private int producerId;
    private String name;

    public Producer(BeerHouse bar, int producerId, String name) {
        this.producerId = producerId;
        this.bar = bar;
        this.name = name;
    }

    @Override
    public void run() {
        BeerType rand;
        // Do while to ensure that the Producer generates beer at least one time
        do{
            // generates a random type of beer
            rand = BeerType.random();
            // The beer with the name of the producer and a random style
            Beer b = new Beer(this.name,rand);
            // The qty with a random value between 1-20
            int qty = ThreadLocalRandom.current().nextInt(1,49);
            // Put the beer and the qty into the monitor
            bar.put(b,qty);
            // Print action
            System.out.println("Producer N° " + this.producerId + " generates " + qty + " liters of " + rand + ".");
            /**
             *  The waiting time of the producer between productions
             *  surrounded by a try catch
             */
            try {
                Thread.sleep(this.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        // The loop iterates until there is zero beer in the bar
        }while (this.bar.hasBeer());
        // Final message
        System.out.println("Producer " + this.producerId + ":There is no more beer :(");
        // Close the bar
        bar.close();
    }
}
