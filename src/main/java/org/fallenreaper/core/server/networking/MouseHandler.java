package org.fallenreaper.core.server.networking;

import org.fallenreaper.core.client.GameInputs;
import org.fallenreaper.core.client.gui.GameScreen;
import org.fallenreaper.core.server.Exolor;
import org.lwjgl.glfw.GLFWDropCallback;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.fallenreaper.core.client.GameInputs.setupMouseCallbacks;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

public class MouseHandler {
    private static MouseHandler instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private final boolean[] mouseButtonPressed = new boolean[3];
    private boolean isDragging;
    public Exolor exolor;

    public MouseHandler(Exolor game) {
        this.scrollX = 0.0D;
        this.scrollY = 0.0D;
        this.xPos = 0.0D;
        this.yPos = 0.0D;
        this.lastX = 0.0D;
        this.lastY = 0.0D;
        this.exolor = game;

    }

    public static MouseHandler getInstance() {
        if (instance == null) {
            instance = new MouseHandler(Exolor.getInstance().getMouseHandler().exolor);
        }
        return MouseHandler.instance;
    }

    public static void mousePosCallBack(long window, double xPos, double yPos) {
        getInstance().lastY = getInstance().yPos;
        getInstance().lastX = getInstance().xPos;
        getInstance().yPos = yPos;
        getInstance().xPos = xPos;
        getInstance().isDragging = getInstance().mouseButtonPressed[0] || getInstance().mouseButtonPressed[1] || getInstance().mouseButtonPressed[2];

    }

    public static void mouseButtonCallBack(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < getInstance().mouseButtonPressed.length) {
                for (boolean buttson : getInstance().mouseButtonPressed) {
                    if (buttson) {
                        getInstance().isDragging = true;
                        getInstance().mouseButtonPressed[button] = true;
                    }

                }
            }
        } else if (action == GLFW_RELEASE) {
            if (button < getInstance().mouseButtonPressed.length) {
                for (boolean buttson : getInstance().mouseButtonPressed) {
                    if (buttson) {
                        getInstance().isDragging = false;
                        getInstance().mouseButtonPressed[button] = false;
                    }
                }
            }
        }
    }
    //this will handle all the mouse interactions, ex: calling the mouseClick method inside GameScreen
    public void mouseSetup(long pWindowPointer) {
        GameInputs.setupMouseCallbacks(pWindowPointer, (pWindowCode, p_91592_, p_91593_) -> {
            this.exolor.execute(() -> {
                this.onMove(pWindowCode, (int) p_91592_, (int) p_91593_);
            });
        }, (pWindowCode, p_91567_, p_91568_, p_91569_) -> {
            this.exolor.execute(() -> {
                this.onPress(pWindowCode, p_91567_, p_91568_, p_91569_);
            });
        }, (pWindowCode, p_91577_, p_91578_) -> {
            this.exolor.execute(() -> {
                this.onScroll(pWindowCode, p_91577_, p_91578_);
            });
        }, (pWindowCode, p_91537_, p_91538_) -> {
            Path[] apath = new Path[p_91537_];

            for(int i = 0; i < p_91537_; ++i) {
                apath[i] = Paths.get(GLFWDropCallback.getName(p_91538_, i));
            }

            this.exolor.execute(() -> {
                this.onDrop(pWindowCode, Arrays.asList(apath));
            });
        });
    }

    private void onMove(long pWindowPointer, int mouseX, int mouseY) {
    }

    private void onDrop(long pWindowCode, List<Path> asList) {
    }

    private void onScroll(long pWindowCode, double mouseX, double mouseY) {
    }

    private void onPress(long pWindowPointer, int mouseX, int mouseY, int pButton) {
        GameScreen gameScreen = exolor.gameScreen;
        boolean flag = pButton == 1;
        if (pWindowPointer == this.exolor.getWindow().getWindow()) {
            if (gameScreen != null) {
                gameScreen.mouseClick(mouseX, mouseY, pButton);

            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        getInstance().scrollY = yOffset;
        getInstance().scrollX = xOffset;

    }

    public void setUpCallbacks(long glfwWindow) {
        glfwSetCursorPosCallback(glfwWindow, MouseHandler::mousePosCallBack);
        glfwSetMouseButtonCallback(glfwWindow, MouseHandler::mouseButtonCallBack);
        glfwSetScrollCallback(glfwWindow, MouseHandler::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyboardHandler::keyCallBack);

    }



    public static void endFrame() {
        getInstance().scrollX = 0;
        getInstance().scrollY = 0;
        getInstance().lastX = 0;
        getInstance().lastY = 0;
    }

    public static float getX() {
        return (float) getInstance().xPos;
    }

    public static float getY() {
        return (float) getInstance().yPos;
    }

    public static float getDx() {
        return (float) (getInstance().lastX - getInstance().xPos);
    }

    public static float getDy() {
        return (float) (getInstance().lastY - getInstance().yPos);
    }

    public static float getScrollX() {
        return (float) getInstance().scrollX;
    }

    public static float getScrollY() {
        return (float) getInstance().scrollY;
    }

    public static boolean isDragging() {
        return getInstance().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < getInstance().mouseButtonPressed.length) {
            return getInstance().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
}
