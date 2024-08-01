package dev.npex42.npgfx.rendering;

import static org.lwjgl.opengl.GL46.*;

public class BindCommand implements RenderCommand {
    private final RendererHandle mHandle;

    public BindCommand(RendererHandle handle) {
        mHandle = handle;
    }

    @Override
    public void Invoke() {
        switch (mHandle.Type) {
            case VERTEX_ARRAY -> glBindVertexArray(mHandle.ID());
            case VERTEX_BUFFER -> glBindBuffer(GL_ARRAY_BUFFER, mHandle.ID());
        }
    }
}
