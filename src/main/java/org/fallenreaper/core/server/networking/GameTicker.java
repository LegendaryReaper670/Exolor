package org.fallenreaper.core.server.networking;

public class GameTicker implements Runnable {

        public Runnable tickingSystem = ()->{};
        private final long tickDuration = 50;

        public void run() {
            while (true) {
                long startTime = System.currentTimeMillis();

                // Update game state
                tickingSystem.run();

                // Render graphics


                // Sleep for the remaining time in the tick
                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = Math.max(1, tickDuration - elapsedTime);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // Handle exception
                }
            }
        }
}
