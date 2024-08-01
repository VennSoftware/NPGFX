package dev.npex42.npgfx;

public class Time {
    public static void SleepMillis(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException _intex) {}
    }
}
