package dev.npex42.npgfx.rendering;

import dev.npex42.npgfx.Time;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class RendererHandle {
    private AtomicInteger mID = new AtomicInteger(0);
    private AtomicBoolean mIsValid = new AtomicBoolean(false);

    public final HandleType Type;

    public RendererHandle(HandleType type) {
        Type = type;
    }


    public int ID() {return mID.getAcquire();}
    public boolean IsValid() {
        return mIsValid.getAcquire();
    }

    public void SetID(int id) {

        mID.setRelease(id);
        mIsValid.setRelease(id != 0);
    }

    public synchronized void WaitForInit() {
        while (!mIsValid.getAcquire()) {
            Time.SleepMillis(1);
        }
    }

    public enum HandleType {
        VERTEX_ARRAY, VERTEX_BUFFER
    }
}
