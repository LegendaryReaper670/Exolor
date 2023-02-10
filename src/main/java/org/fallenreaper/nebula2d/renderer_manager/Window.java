package org.fallenreaper.nebula2d.renderer_manager;

import org.fallenreaper.core.util.Time;
import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Objects;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window implements AutoCloseable {
    private long window;
    private static Window instance;
    private int width, height;
    private String title;
    private boolean vsync;
    private boolean fullscreen;

    public Window() {
        this.width = 720;
        this.height = 720;
        this.title = "Exolor";
        this.vsync = true;
        this.fullscreen = false;
    }

    public static Window getInstance() {
        if (Window.instance == null) {
            Window.instance = new Window();
        }

        return Window.instance;
    }

    public void run() {
        System.out.println("LWJGL " + Version.getVersion() + "!");

        init();
        loop();
        clean();

    }

    public void init() {
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        // Create the window
        window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (window == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

    //   glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
    //   glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
    //   glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
    //   glfwSetKeyCallback(window, KeyListener::keyCallback);

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        
    }
    public void updateDisplay() {
        flipFrame(this.window);


    }

    private void flipFrame(long window) {
        GLFW.glfwPollEvents();

        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    public void loop() {
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!glfwWindowShouldClose(window)) {
            // Poll events

         update();
            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt >= 0) {
             runTick();
            }



            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    private void runTick() {

    }

    public void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        if (vsync) {
            glfwSwapInterval(1);
        }

        glfwShowWindow(window);
    }

    public void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(window, title);
    }

    public void setVsync(boolean vsync) {
        this.vsync = vsync;
        glfwSwapInterval(vsync ? 1 : 0);
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        glfwSetWindowMonitor(window, fullscreen ? glfwGetPrimaryMonitor() : 0, 0, 0, width, height, GLFW_DONT_CARE);
    }

    @Override
    public void close() throws Exception {

    }
    public long getWindow() {
        return window;
    }





    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        glfwSetWindowSize(window, width, height);
    }

    public void setPosition(int x, int y) {
        glfwSetWindowPos(window, x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void clean() {

        // Free the memory


        // Terminate GLFW and the free the error callback


        glfwSetErrorCallback(null).free();
        glfwDestroyWindow(window);
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }
}
