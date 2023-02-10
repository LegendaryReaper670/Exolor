package org.fallenreaper.core.server;

import org.fallenreaper.core.client.*;
import org.fallenreaper.nebula2d.renderer_manager.GameRenderer;
import org.fallenreaper.nebula2d.renderer_manager.Window;
import org.fallenreaper.core.client.gui.GameMenuScreen;
import org.fallenreaper.core.client.gui.GameScreen;
import org.fallenreaper.core.server.networking.GameTicker;
import org.fallenreaper.core.server.networking.Level;
import org.fallenreaper.core.server.networking.MouseHandler;
import org.fallenreaper.core.server.scenes.GameMenuScene;
import org.fallenreaper.core.util.Time;
import org.joml.Vector3f;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

public class Exolor implements  Executor {
     private static Exolor instance;
     public GameRenderer gameRenderer;
     private final Window window;
     public Set<Runnable> tickable;
     public Player player;
     public Camera camera;
     public Set<ScreenElement> screenElements;
     public GameMenuScene scene;
     public GameScreen gameScreen;
     public Level world;
     public boolean start;
     public MouseHandler mouseHandler;
     private  boolean running;
     private Time timer;
     private Thread gameThread;
     public GameTicker ticker;

    public Exolor() {
        this.start = true;
        this.gameThread = Thread.currentThread();
        this.screenElements = new HashSet<>();
        this.window = Window.getInstance();
        this.running = true;
        ticker = new GameTicker();
     //   window.addTickListener(() -> screenElements.forEach(ScreenElement::tick));

        instance = this;
        player = new Player();
        tickable = new HashSet<>();
        timer  = new Time();
        camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
        scene = new GameMenuScene();

        this.gameScreen = new GameMenuScreen();

        Window.getInstance().run();
        init();

        this.mouseHandler = new MouseHandler(this);
      this.mouseHandler.mouseSetup(this.window.getWindow());

    }

    public void init() {

        addScreenElement(player);
        addScreenElement(gameScreen);

    }

    public void runTick() {
        if(this.gameScreen != null) {
            this.gameScreen.tick();
        }
        this.window.update();

       // screenElements.forEach(ScreenElement::tick);
     //   System.out.println("Running Game tick");
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public static Exolor getInstance() {
        return new Exolor();
    }

    public Window getWindow() {
        return this.window;
    }

    public void addScreenElement(ScreenElement screenElement) {
        this.screenElements.add(screenElement);
    }

    public void addTickListener(Runnable runnable) {
        this.tickable.add(runnable);
    }

    public void setGameScreen(GameScreen screen) {
         this.gameScreen = screen;

    }


    public void run() {

        this.gameThread = Thread.currentThread();
        if (Runtime.getRuntime().availableProcessors() > 4) {
            this.gameThread.setPriority(10);
        }

        new Thread(ticker).start();


            boolean flag = false;

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / 20;

        ticker.tickingSystem = this::runTick;
        while (this.running) {
         //  long now = System.nanoTime();
         //  double delta = (now - lastTime) / nsPerTick;
         //  if (delta >= 1) {
          //      lastTime = now;
                runTick();
            // }
        }
    }
    public void stop() {
        if (this.isRunning());
        this.running = false;
    }

    private boolean isRunning() {
        return running;
    }


    public void execute(Runnable command) {
        command.run();
    }
}
