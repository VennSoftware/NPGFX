package dev.npex42.npgfx;

public interface Logger {
    void Trace(String message, Object... args);
    void Debug(String message, Object... args);
    void Info(String message, Object... args);
    void Warn(String message, Object... args);
    void Error(String message, Object... args);
    void Fatal(String message, Object... args);
}
