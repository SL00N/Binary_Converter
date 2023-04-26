package org.example;

import java.io.IOException;

public class ProgressBar {
    private static String anim = "██████████";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private int all;
    ProgressBar(int all){
        this.all = all;
    }

    public void showProgressBar(long now) {
        String toShow = "\r" + anim.substring(0, (int)(now / all * 10) + 1);

        for (int j = 0; j < 10 - (int)(now / all * 10); j++) toShow += " ";

        toShow += " |" + (int)(now / all * 100) + "%";

        try{
            System.out.write(toShow.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if ((int)(now / all * 100) == 100) System.out.print(ANSI_GREEN + "done" + ANSI_RESET);
    }
}
