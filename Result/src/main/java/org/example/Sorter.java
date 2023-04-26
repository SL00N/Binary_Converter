package org.example;

public class Sorter implements Runnable{
    private static long sumOfItemsWithMyId = 0;
    private static long quantityOfItemsWithMyId = 0;
    private static long counterOfThreads = 0;
    private final int MY_ID = 72;
    private Data data;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private boolean flagNewLine;
    Sorter(Data data, boolean flagNewLine){
        this.data = data;
        this.flagNewLine = flagNewLine;
    }
    @Override
    public void run(){
        if (data.getId() == MY_ID){
            sumOfItemsWithMyId += data.getNumber();
            quantityOfItemsWithMyId++;
            if (quantityOfItemsWithMyId % 1000 == 0)
                System.out.println(ANSI_PURPLE + "Quantity of all data with myId - " + quantityOfItemsWithMyId  +ANSI_RESET);
        }
        if (flagNewLine) {
            counterOfThreads += 1;
        }
    }

    public static int getMean(){
        if (quantityOfItemsWithMyId != 0) {
            return (int) (sumOfItemsWithMyId / quantityOfItemsWithMyId);}
        else {return 0;}
    }

    public static long getCounterOfThreads(){return counterOfThreads;}

}
