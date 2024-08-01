package dev.npex42.npgfx.events;

public final class FramebufferResizedEvent extends Event {
    public final int Width;
    public final int Height;

    public FramebufferResizedEvent(int width, int height) {
        this.Width = width;
        this.Height = height;
    }

}
