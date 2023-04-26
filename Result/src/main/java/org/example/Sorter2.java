package org.example;

import java.util.ArrayDeque;
import java.util.Timer;
import java.util.TimerTask;

public class Sorter2 {

    private static Sorter2 instance;

    public static Sorter2 getInstance(){
        if (instance == null){
            instance = new Sorter2();
        }
        return instance;
    }

    private final ArrayDeque<Data> queue;
    private boolean running;
    private Timer myTimer;
    private final int myId = 72;
    private long sumOfItemsWithMyId = 0;
    private long quantityOfItemsWithMyId = 0;
    private workBySchedule work;
    private final int PERIOD_LONG = 50;
    private final int PERIOD_SHORT = 10;
    private int period = PERIOD_LONG;
    private int period_old = PERIOD_LONG;
    private int emptyCounterOfQueueSize = 0;




    public Sorter2(){
        this.running = true;
        this.myTimer = new Timer();
        this.work = new workBySchedule();
        this.queue = new ArrayDeque<>();
    }

    private class workBySchedule extends TimerTask{
        @Override
        public void run(){
            if (running){
                sortDataInQueue();
            }
            updateQueueInfo(queue.size());
            if (period == PERIOD_LONG){
                emptyCounterOfQueueSize++;
                if (emptyCounterOfQueueSize > 40) stopWork();
            }
        }
    }

    private void sortDataInQueue(){
        if (queue.size() > 0){
            Data toSort = queue.poll();
            if (toSort.getId() == myId){
                sumOfItemsWithMyId += toSort.getNumber();
                quantityOfItemsWithMyId++;

            }
        }
    }

    private void updateQueueInfo(int queueSize){
        period = (queueSize == 0) ? PERIOD_LONG: PERIOD_SHORT;
        if (period != period_old) restartWork();
        period_old = period;
    }

    private void restartWork(){
        myTimer.cancel();
        myTimer = new Timer();
        work = new workBySchedule();
        myTimer.scheduleAtFixedRate(work, 0, period);
        emptyCounterOfQueueSize = 0;
    }

    private void startWork(){
        myTimer.scheduleAtFixedRate(work, 0, period);
        running = true;
    }

    private void stopWork(){
        myTimer.cancel();
        running = false;

    }

    private double getMean(){
        if (quantityOfItemsWithMyId > 0) return sumOfItemsWithMyId / quantityOfItemsWithMyId;
        else return 0;
    }

    public void startWorkWithProcess(){
        startWork();
    }

    public void stopWorkWithProcess(){
        stopWork();
    }

    public void addDataToWork(Data data){
        addData(data);
    }

    private void addData(Data data){
        synchronized (queue){
            queue.add(data);
        }
    }
}