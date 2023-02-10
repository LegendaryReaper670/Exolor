package org.fallenreaper.main;

import com.badlogic.gdx.Game;
import com.sun.tools.javac.Main;
import org.fallenreaper.core.server.Exolor;

public class GameLauncher {
    public static void main(String[] args) {
        final Exolor exolor;

        try {
            Thread.currentThread().setName("Render thread");
            System.out.println(Thread.currentThread().getName());
            exolor = new Exolor();

        } catch (Throwable silentinitexception) {

            return;
        }

        Thread gameThread;
        if (exolor.start) {
            gameThread = new Thread("Game thread") {
                public void run() {
                    try {

                        exolor.run();
                    } catch (Throwable throwable2) {
                        System.out.print("Exception on game thread");
                    }

                }
            };
            gameThread.start();
        }
    }
}