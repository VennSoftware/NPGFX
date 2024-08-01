import dev.npex42.npgfx.*;
import dev.npex42.npgfx.Graphics;

public class Sandbox {
    public static void main(String[] args) {
        SimpleLogger l = new SimpleLogger();
        Engine e = new Engine(EngineSpec.LoadFile("sandbox.conf"));

        e.AddLayer(new SandboxLayer());

        e.Start();


    }
}
