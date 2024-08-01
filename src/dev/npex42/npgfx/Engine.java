package dev.npex42.npgfx;

import dev.npex42.npgfx.backend.OpenGL;
import dev.npex42.npgfx.events.handler.EventHandler;
import dev.npex42.npgfx.rendering.ClearCommand;
import dev.npex42.npgfx.rendering.CreateVAOCommand;
import dev.npex42.npgfx.rendering.PresentCommand;
import dev.npex42.npgfx.rendering.RendererHandle;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class Engine {
    Window mWindow;
    EngineSpec mEngineSpec;

    LinkedList<Layer> mLayers = new LinkedList<>();

    public Engine(EngineSpec spec) {
        mWindow = new Window(spec.width(), spec.height(), spec.title());
        mEngineSpec = spec;


        GLFW.glfwSwapInterval(mEngineSpec.config().GetIntOr("VSync", 0));
        EventManager.Init(mWindow);

        EventManager.AddFrameBufferResizeHandler(OpenGL::FrameResized);

        OpenGL.StartRenderThread(mWindow);

        Renderer2D.Init();
    }

    public void Start() {
        mWindow.Show();


        while (!mWindow.IsClosed()) {
            mWindow.UpdateEvents(mEngineSpec.pollingTimeout());

            for (Layer layer : mLayers) {
                layer.Update();
            }


            for (Layer layer : mLayers) {
                layer.Render();
            }

            Graphics.FrameSync();
        }
    }

    public void AddLayer(Layer l) {
        mLayers.addFirst(l);
        l.OnAttach();

        EventManager.AddFrameBufferResizeHandler(l::OnResize);
    }
}
