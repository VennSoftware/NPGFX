package dev.npex42.npgfx;

import dev.npex42.npgfx.rendering.RendererHandle;
import dev.npex42.npgfx.rendering.VertexArray;
import dev.npex42.npgfx.rendering.VertexBuffer;

public class Renderer2D {
    private static VertexBuffer mPositions;
    private static VertexArray mVAO;

    public static void Init() {
        mVAO = VertexArray.Create();
        mVAO.Bind();
        mPositions = VertexBuffer.Create();
        mPositions.Bind();

        mVAO.Unbind();
    }
}
