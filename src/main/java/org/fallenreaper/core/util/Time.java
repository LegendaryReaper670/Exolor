package org.fallenreaper.core.util;

import java.util.function.Supplier;

public class Time {
    public static float timeStarted = System.nanoTime();
    long startTime;

    private static final int ticksPerSecond = 20;
    private static int targetTime = 1000 / ticksPerSecond;

    public static float getTime() {
        return (float) ((System.nanoTime() - timeStarted) * 1E-9);
    }


        public void startTimer(boolean gameRunning, Supplier<Runnable> action) {
            while (true) {
                long startTime = System.currentTimeMillis();

                // update game state, handle input, render graphics, etc.
               if(action != null)
                   action.get().run();

                long elapsedTime = System.currentTimeMillis() - startTime;
                long waitTime = targetTime - elapsedTime;
                if (waitTime > 0) {
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public void update() {
        long currentTime = System.nanoTime();
        if (currentTime - startTime >= 1000000) {
            startTime = currentTime;
         //   Window.getInstance().tick();
        }
    }
}
