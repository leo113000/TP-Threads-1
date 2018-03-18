import Models.*;

public class App {

    public static void main(String[] args) {
        BeerHouse bar = new BeerHouse();
        Thread producer = new Thread(new Producer(bar,1,"Cheverry"));
        Thread consumerUno = new Thread(new Consumer(bar, BeerType.BLONDE, 1));
        Thread consumerDos = new Thread(new Consumer(bar, BeerType.HONEY, 2));
        Thread consumerTres = new Thread(new Consumer(bar, BeerType.IPA, 3));
        Thread consumerCuatro = new Thread(new Consumer(bar, BeerType.PORTER, 4));
        Thread consumerCinco = new Thread(new Consumer(bar, BeerType.RED, 5));
        producer.start();
        consumerUno.start();
        consumerDos.start();
        consumerTres.start();
        consumerCuatro.start();
        consumerCinco.start();
    }

}
