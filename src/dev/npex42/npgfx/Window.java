package dev.npex42.npgfx;

import org.lwjgl.system.Pointer;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    private int mWidth, mHeight;
    private long mHandle;
    private String mTitle;

    public Window(int width, int height, String title) {
        mWidth = width;
        mHeight = height;
        mTitle = title;
        glfwInit();
        mHandle = glfwCreateWindow(mWidth, mHeight, mTitle, 0, 0);
    }

    public void UpdateEvents(double timeout) {
        if (timeout == 0.0) {
            glfwPollEvents();
        } else {
            glfwWaitEventsTimeout(timeout);
        }
    }

    public void Present() {
        glfwSwapBuffers(mHandle);
    }

    public long Handle() {return mHandle;}

    public boolean IsClosed() {
        return glfwWindowShouldClose(mHandle);
    }

    public int Width() {
        int[] width = {0}, height = {0};
        glfwGetWindowSize(mHandle, width, height);
        mWidth = width[0];
        mHeight = height[0];
        return mWidth;
    }

    public void Show() {
        glfwShowWindow(mHandle);
    }


}
