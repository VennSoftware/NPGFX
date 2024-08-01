package dev.npex42.npgfx.rendering;

import dev.npex42.npgfx.backend.OpenGL;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
    private List<RenderCommand> mCommands = new ArrayList<>();

    public void AddCommand(RenderCommand command) {
        mCommands.add(command);
    }

    public void AddCommands(RenderCommand... commands) {
        mCommands.addAll(List.of(commands));
    }

    public void Submit() {
        OpenGL.Submit(mCommands);
    }
}
