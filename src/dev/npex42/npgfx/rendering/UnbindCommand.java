package dev.npex42.npgfx.rendering;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class UnbindCommand implements RenderCommand {
    private final RendererHandle mHandle;

    public UnbindCommand(RendererHandle handle) {
        mHandle = handle;
    }

    @Override
    public void Invoke() {
        switch (mHandle.Type) {
            case VERTEX_ARRAY -> glBindVertexArray(0);
            case VERTEX_BUFFER -> glBindBuffer(GL_ARRAY_BUFFER, 0);
        }
    }
}
