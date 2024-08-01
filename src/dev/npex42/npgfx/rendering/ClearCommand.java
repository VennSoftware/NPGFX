package dev.npex42.npgfx.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL33;

import java.awt.*;

public class ClearCommand implements RenderCommand {
    public final Color color;

    public ClearCommand(Color color) {
        this.color = color;
    }

    public float R() { return color.getRed()   / 255.0f; }
    public float G() { return color.getGreen() / 255.0f; }
    public float B() { return color.getBlue()  / 255.0f; }
    public float A() { return color.getAlpha() / 255.0f; }


    @Override
    public void Invoke() {
        GL33.glClearColor(R(), G(), B(), A());
        GL33.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
}
