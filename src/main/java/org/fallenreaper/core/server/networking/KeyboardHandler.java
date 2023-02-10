package org.fallenreaper.core.server.networking;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyboardHandler {
    private static KeyboardHandler instance;
    private final boolean[] keyPressed = new boolean[348];

    public KeyboardHandler() {

    }

    public static void keyCallBack(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            getInstance().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            getInstance().keyPressed[key] = false;
        }

      //switch (action) {
      //
      //    case GLFW_PRESS -> {
      //        getInstance().keyPressed[key] = true;
      //    }
      //    case GLFW_RELEASE -> {
      //        getInstance().keyPressed[key] = false;
      //    }
     //   }
    }

    public static KeyboardHandler getInstance() {
        if (KeyboardHandler.instance == null) {
            KeyboardHandler.instance = new KeyboardHandler();
        }
        return KeyboardHandler.instance;
    }

    public static boolean isKeyPress(int key) {

        return getInstance().keyPressed[key];
    }

}
