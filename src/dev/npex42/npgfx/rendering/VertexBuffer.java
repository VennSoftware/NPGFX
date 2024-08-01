package dev.npex42.npgfx.rendering;

import dev.npex42.npgfx.Graphics;
import dev.npex42.npgfx.backend.OpenGL;

public class VertexBuffer {
    private final RendererHandle mHandle;

    private VertexBuffer(RendererHandle mHandle) {
        this.mHandle = mHandle;
    }

    public static VertexBuffer Create() {
        return new VertexBuffer(Graphics.CreateVBO());
    }

    public void Bind() {
        OpenGL.Submit(new BindCommand(mHandle));
    }

    public void Unbind() {
        OpenGL.Submit(new UnbindCommand(mHandle));
    }
}
