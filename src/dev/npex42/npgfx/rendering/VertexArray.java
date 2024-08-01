package dev.npex42.npgfx.rendering;

import dev.npex42.npgfx.Graphics;
import dev.npex42.npgfx.backend.OpenGL;

public class VertexArray {
    private final RendererHandle mHandle;

    private VertexArray(RendererHandle mHandle) {
        this.mHandle = mHandle;
    }

    public static VertexArray Create() {
        return new VertexArray(Graphics.CreateVAO());
    }

    public void Bind() {
        OpenGL.Submit(new BindCommand(mHandle));
    }

    public void Unbind() {
        OpenGL.Submit(new UnbindCommand(mHandle));
    }
}
