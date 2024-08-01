package dev.npex42.npgfx.backend;

import dev.npex42.npgfx.Fence;
import dev.npex42.npgfx.SimpleLogger;
import dev.npex42.npgfx.Time;
import dev.npex42.npgfx.Window;
import dev.npex42.npgfx.events.FramebufferResizedEvent;
import dev.npex42.npgfx.rendering.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static org.lwjgl.opengl.GL33.*;

public class OpenGL {

    private static final ConcurrentLinkedDeque<RenderCommand> COMMANDS = new ConcurrentLinkedDeque<>();
    private static Thread RenderThread;
    public static void SetViewport(int width, int height) {
        glViewport(0, 0, width, height);
    }
    public static AtomicBoolean hitPresentFlag = new AtomicBoolean(false);

    static final ReentrantLock commandLock = new ReentrantLock();


    static final Fence submitFence = new Fence();

    private static Condition hasCommands = commandLock.newCondition();

    public static boolean FrameResized(FramebufferResizedEvent ev) {
        Submit(new ViewportResize(ev.Width, ev.Height));
        return false;
    }

    private static SimpleLogger sLogger;

    static {
        try {
            sLogger = new SimpleLogger(new PrintStream("logs/gl.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void StartRenderThread(Window win) {
        RenderThread = new Thread(() -> OpenGL.RenderLoop(win));
        RenderThread.setDaemon(true);
        RenderThread.setName("Render");
        RenderThread.start();
        sLogger.Info("Started Render Thread");


    }

    private static void RenderLoop(Window w) {
        // OpenGL Init



        GLFW.glfwMakeContextCurrent(w.Handle());
        GL.createCapabilities();

        double lastStarvedTime = GLFW.glfwGetTime();

        while (true) {
//            if (COMMANDS.size() == 0) {
//                try {
//                    if (GLFW.glfwGetTime() - lastStarvedTime >= (1 / 30.0)) {
//                        sLogger.Warn("Starved");
//                        lastStarvedTime = GLFW.glfwGetTime();
//                    }
//                    Thread.sleep(1);
//                } catch (InterruptedException _intex) {}
//                continue;
//            }

            if (COMMANDS.isEmpty()) {
                submitFence.Wait();
            }



            RenderCommand command = COMMANDS.pollFirst();

            assert command != null;
            sLogger.Trace(command.getClass().getSimpleName());

            if (command instanceof PresentCommand prc) {
                prc.Invoke();
                w.Present();
                hitPresentFlag.compareAndExchange(false, true);
            } else {
                command.Invoke();
            }



        }

    }

    public static synchronized void Submit(RenderCommand... cmds) {
        for (RenderCommand rc : cmds) {
            COMMANDS.addLast(rc);
        }
        if (cmds.length > 0)
            submitFence.NotifyAll();
    }

    public static synchronized void Submit(List<RenderCommand> cmds) {
        for (RenderCommand rc : cmds) {
            COMMANDS.addLast(rc);
        }

        if (!cmds.isEmpty())
            submitFence.NotifyAll();
    }

    public static void WaitForFrameEnd() {
        while (!hitPresentFlag.getAcquire()) {
            Time.SleepMillis(1);
        }
        hitPresentFlag.compareAndExchange(true, false);
    }
}
