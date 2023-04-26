package org.example;

import java.io.File;

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args){
        File file = new File("inputData", "Bsjadqwpurbvjfqs_data.txt");
        System.out.println(ANSI_BLUE + "Reading and sorting the inputData..." + ANSI_RESET);
        System.out.println(ANSI_RED + "Don't worry it may take a few minutes!" + ANSI_RESET);
        ReadData readData = new ReadData(file);
        readData.start();
        while (Sorter.getCounterOfThreads() < readData.getLenOfArrayOfThreads()){
            try{
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(ANSI_GREEN + "\nThe mean of inputData with myId: " + Sorter.getMean() + ANSI_RESET);


    }
}