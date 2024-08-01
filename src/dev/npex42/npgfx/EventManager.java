package dev.npex42.npgfx;

import dev.npex42.npgfx.events.EventDispatcher;
import dev.npex42.npgfx.events.FramebufferResizedEvent;
import dev.npex42.npgfx.events.handler.EventHandler;

import static org.lwjgl.glfw.GLFW.*;

public class EventManager {



    public static void Init(Window window) {
        glfwSetFramebufferSizeCallback(window.Handle(), EventManager::OnFrameBufferResized);
    }

    public static void AddFrameBufferResizeHandler(EventHandler<FramebufferResizedEvent> handler) {
        FBResize.Register(handler);
    }

    public static void OnFrameBufferResized(long _w, int width, int height) {
        FramebufferResizedEvent e = new FramebufferResizedEvent(width, height);
        FBResize.Dispatch(e);
    }


    // Event Handler Lists
    private static final EventDispatcher<FramebufferResizedEvent> FBResize = new EventDispatcher<>();

}
