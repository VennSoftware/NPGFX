package dev.npex42.npgfx.rendering;

import org.lwjgl.opengl.GL33;

public class CreateVBOCommand implements RenderCommand {
    private final RendererHandle mHandle;

    public CreateVBOCommand(RendererHandle mHandle) {
        this.mHandle = mHandle;
    }


    @Override
    public void Invoke() {
        this.mHandle.SetID(GL33.glGenBuffers());
    }
}
