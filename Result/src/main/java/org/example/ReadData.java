package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadData {
    private ArrayList<Thread> threads;
    private ArrayList<Worker> workers;
    private int sizeOfArrayOfThreads;
    private ProgressBar progressBar;

    public ReadData(File file) {
        this.threads = new ArrayList<>();
        this.workers = new ArrayList<>();
        String readLine;
        try(
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                )
        {
            while((readLine = bufferedReader.readLine()) != null)
                threads.add(new Thread(new Worker(readLine)));
            sizeOfArrayOfThreads = threads.size();
        } catch (IOException e) {
            System.err.println("No file!");
        } catch (Exception e){
            System.err.println("Error!");
        }
        this.progressBar = new ProgressBar(sizeOfArrayOfThreads);
    }

    public void start(){
        if (threads != null){
            for (int i = 0; i < sizeOfArrayOfThreads; i++) {
                threads.get(0).start();
                try{
                    Thread.sleep(110);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                threads.remove(0);
                progressBar.showProgressBar(i + 1);
                System.out.println(i + 1 + " " + sizeOfArrayOfThreads);
            }
        }else{
            System.err.println("No threads!");
        }
    }

    public long getLenOfArrayOfThreads(){return sizeOfArrayOfThreads;}
}
