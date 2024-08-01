package dev.npex42.npgfx;

import dev.npex42.npgfx.backend.OpenGL;
import dev.npex42.npgfx.rendering.*;

import static dev.npex42.npgfx.rendering.RendererHandle.*;

import java.awt.*;

public class Graphics {
    private Graphics() {};

    public static void Clear(Color c) {
        OpenGL.Submit(new ClearCommand(c));
    }

    public static RendererHandle CreateVAO() {
        RendererHandle handle = CreateVAOAsync();
        handle.WaitForInit();
        return handle;
    }
    public static RendererHandle CreateVAOAsync() {
        RendererHandle handle = new RendererHandle(HandleType.VERTEX_ARRAY);
        OpenGL.Submit(new CreateVAOCommand(handle));
        return handle;
    }

    public static RendererHandle CreateVBO() {
        RendererHandle handle = CreateVBOAsync();
        handle.WaitForInit();
        return handle;
    }
    public static RendererHandle CreateVBOAsync() {
        RendererHandle handle = new RendererHandle(HandleType.VERTEX_BUFFER);
        OpenGL.Submit(new CreateVBOCommand(handle));
        return handle;
    }


    public static void FrameSync() {
        OpenGL.Submit(new PresentCommand());
        OpenGL.WaitForFrameEnd();
    }
}
