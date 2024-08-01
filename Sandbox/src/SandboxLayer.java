import dev.npex42.npgfx.Graphics;
import dev.npex42.npgfx.Layer;
import dev.npex42.npgfx.SimpleLogger;
import dev.npex42.npgfx.rendering.CommandList;
import dev.npex42.npgfx.rendering.CreateVAOCommand;
import dev.npex42.npgfx.rendering.RendererHandle;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class SandboxLayer extends Layer {

    SimpleLogger logger;

    public SandboxLayer() {
        try {
            logger = new SimpleLogger(new PrintStream("logs/SandboxLayer.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    CommandList quad_init = new CommandList();

    RendererHandle mQuadVAO, mQuadVBO;

    @Override
    public void OnAttach() {
        logger.Info("Attached");
        mQuadVAO = Graphics.CreateVAOAsync();
        mQuadVBO = Graphics.CreateVBOAsync();

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render() {
        Graphics.Clear(Color.BLUE);
    }
}
