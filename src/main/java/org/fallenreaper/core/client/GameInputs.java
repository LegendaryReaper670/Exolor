package org.fallenreaper.core.client;

import org.lwjgl.glfw.*;

public class GameInputs {
        //apparently i need
    public static void setupMouseCallbacks(long pWindow, GLFWCursorPosCallbackI pCursorPositionCallback, GLFWMouseButtonCallbackI pMouseButtonCallback, GLFWScrollCallbackI pScrollCallback, GLFWDropCallbackI pDragAndDropCallback) {
        GLFW.glfwSetCursorPosCallback(pWindow, pCursorPositionCallback);
        GLFW.glfwSetMouseButtonCallback(pWindow, pMouseButtonCallback);
        GLFW.glfwSetScrollCallback(pWindow, pScrollCallback);
        GLFW.glfwSetDropCallback(pWindow, pDragAndDropCallback);
    }


    public static boolean isKeyDown(long pWindow, int pKey) {
        return GLFW.glfwGetKey(pWindow, pKey) == 1;
    }
}
