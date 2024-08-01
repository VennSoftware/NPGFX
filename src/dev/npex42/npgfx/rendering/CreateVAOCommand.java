package dev.npex42.npgfx.rendering;

import org.lwjgl.opengl.GL33;

public class CreateVAOCommand implements RenderCommand {
    public RendererHandle Handle;

    public CreateVAOCommand(RendererHandle handle) {
        Handle = handle;
    }

    @Override
    public void Invoke() {
        Handle.SetID(GL33.glGenVertexArrays());
    }
}
