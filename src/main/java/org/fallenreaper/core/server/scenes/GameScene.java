package org.fallenreaper.core.server.scenes;

import org.fallenreaper.core.client.ScreenElement;

public abstract class GameScene implements ScreenElement {

    public GameScene() {
    }

    public abstract void update(float dt);

    public abstract void render();

    public abstract void tick();

    public void init(){}
}
