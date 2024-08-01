package dev.npex42.npgfx;

import java.util.concurrent.atomic.AtomicBoolean;

public class Fence {
    private final Object monitor = new Object();

    public void Wait() throws RuntimeException {
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void NotifyAll() {
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
}
