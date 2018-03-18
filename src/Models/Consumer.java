package Models;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable{

    private final int SLEEP_TIME = 3000;
    private BeerHouse bar;
    private BeerType likes;
    private int consumerId;

    public Consumer(BeerHouse bar, BeerType likes, int consumerId) {
        this.bar = bar;
        this.likes = likes;
        this.consumerId = consumerId;
    }

    public void run(){
        // Consume until there is no beer
        while(!bar.isClosed()){
            // Random quantity between 0-99
            int qty = ThreadLocalRandom.current().nextInt(0,100);
            // Consume specific Beertype from the monitor
            int consumed = bar.get(this.likes,qty);
            if(consumed > 0)
            System.out.println("Consumer N° " + this.consumerId + " drinks " + consumed + " of " + this.likes + ".");
            try {
                Thread.sleep(this.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumer " + this.consumerId + ":There is no more beer :(");
    }
}
