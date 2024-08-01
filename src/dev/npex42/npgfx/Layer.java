package dev.npex42.npgfx;

import dev.npex42.npgfx.events.FramebufferResizedEvent;

public abstract class Layer {
    public abstract void Update();
    public abstract void Render();

    public void OnAttach() {}
    public void OnDetach() {}

    public boolean OnResize(FramebufferResizedEvent e) {return false;}
}
