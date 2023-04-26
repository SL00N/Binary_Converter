package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Worker implements Runnable{
    private String inputLine;
    private boolean running;
    private int periodToSleep;
    private final int PERIOD_TO_SLEEP_LONG_LONG = 75;
    private final int PERIOD_TO_SLEEP_LONG = 50;
    private final int PERIOD_TO_SLEEP_SHORT = 25;
    private ArrayList<Thread> threads;

    public Worker(String inputLine) {
        this.inputLine = inputLine;
        this.running = true;
        this.threads = new ArrayList<>();
        this.periodToSleep = PERIOD_TO_SLEEP_LONG;
    }

    @Override
    public void run(){
        while (running) {
            ArrayList<Data> temp = (new Gson()).fromJson(
                    inputLine.substring(11),
                    new TypeToken<ArrayList<Data>>() {}.getType()
            );
            if (temp.size() < 50) periodToSleep = PERIOD_TO_SLEEP_SHORT;
            if (temp.size() < 100) periodToSleep = PERIOD_TO_SLEEP_LONG;
            else periodToSleep = PERIOD_TO_SLEEP_LONG_LONG;
            boolean flagNewLine = false;
            for (int i = 0; i < temp.size(); i++){
                flagNewLine = (i == temp.size() - 1 || temp.size() == 1) ? true: false;
                threads.add(new Thread(new Sorter(temp.get(i), flagNewLine)));
                threads.get(0).start();
                try {
                    Thread.sleep(periodToSleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }
                threads.remove(0);
            }
            running = false;
        }
    }

}
