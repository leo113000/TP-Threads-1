# TP-Threads-1

1. Debido a la tasa de producción y consumo, solo con tener un hilo por cada tipo de cerveza, el programa terminara (según pruebas incipientes en menos de 15 segundos)
2. Los bloques Synchronized son aquellos en los cuales los hilos accederán de manera síncrona y no concurrente. Esta palabra reservada deberá agregarse a los sectores críticos de la aplicación donde se lea o escriba sobre algún recurso compartido.
3. El recurso compartido en este caso, es el inventario de cervezas, donde el consumidor quitará cerveza siempre que haya y el productor agregará cerveza siempre que la capacidad no esté al máximo o, como en este caso, si está vació, se deba terminar la ejecución de la aplicación. El productor produce aleatoriamente cinco tipos de cerveza, mientras que el consumidor solo puede consumir un solo tipo.
4. Las tres formas de instanciar un Thread son: 
- Heredando de Thread y sobreescribir el método run().
- Implementando Runnable (interfaz que Thread también implementa) e implementar el método run)
- Declarando una clase anónima que implemente o extienda de Runnable o Thread respectivamente y, como en los casos anteriores, implementar el método run().
