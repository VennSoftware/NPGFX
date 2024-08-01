package dev.npex42.npgfx.rendering;

import org.lwjgl.opengl.GL11;

public class ViewportResize implements RenderCommand {
    public final int Width, Height;

    public ViewportResize(int width, int height) {
        Width = width;
        Height = height;
    }

    @Override
    public void Invoke() {
        GL11.glViewport(0, 0, Width, Height);
    }
}
