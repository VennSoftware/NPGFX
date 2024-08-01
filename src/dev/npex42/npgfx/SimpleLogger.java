package dev.npex42.npgfx;

import org.lwjgl.glfw.GLFW;

import java.io.PrintStream;

public class SimpleLogger implements Logger {

    private PrintStream out = System.out;

    public SimpleLogger() {}
    public SimpleLogger(PrintStream stream) {
        out = stream;
    }


    @Override
    public void Trace(String message, Object... args) {
        out.printf("[TRACE|%04.4f]: %s\n", GLFW.glfwGetTime(), String.format(message, args));
    }

    @Override
    public void Debug(String message, Object... args) {
        out.printf("[DEBUG|%04.4f]: %s\n", GLFW.glfwGetTime(), String.format(message, args));
    }

    @Override
    public void Info(String message, Object... args) {
        out.printf("[INFO |%04.4f]: %s\n", GLFW.glfwGetTime(), String.format(message, args));
    }

    @Override
    public void Warn(String message, Object... args) {
        out.printf("[WARN |%04.4f]: %s\n", GLFW.glfwGetTime(), String.format(message, args));
    }

    @Override
    public void Error(String message, Object... args) {
        out.printf("[ERROR|%04.4f]: %s\n", GLFW.glfwGetTime(), String.format(message, args));
    }

    @Override
    public void Fatal(String message, Object... args) {
        out.printf("[FATAL|%04.4f]: %s\n", GLFW.glfwGetTime(), String.format(message, args));
    }
}
