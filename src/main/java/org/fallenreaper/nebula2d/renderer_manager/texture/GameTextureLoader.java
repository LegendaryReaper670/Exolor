package org.fallenreaper.nebula2d.renderer_manager.texture;

import com.badlogic.gdx.graphics.Texture;
import org.lwjgl.opengl.GL11;

public class GameTextureLoader {


    private Texture texture;
    private float x;
    private float y;

    public GameTextureLoader (Texture texture) {
        this.texture = texture;
        x = 0;
        y = 0;
    }

    public void update() {
        x += 0.1f;
        y += 0.1f;
    }


    public void render() {
        texture.bind();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + texture.getWidth(), y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + texture.getWidth(), y + texture.getHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + texture.getHeight());
        GL11.glEnd();
    }

    public void loadTexture() {


    }
}
