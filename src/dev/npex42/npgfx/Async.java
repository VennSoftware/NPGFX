package dev.npex42.npgfx;

import java.util.Random;

public class Async {
    public static Thread Run(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.setName("Task-"+ Math.random() * 10000);
        t.start();
        return t;
    }
}
