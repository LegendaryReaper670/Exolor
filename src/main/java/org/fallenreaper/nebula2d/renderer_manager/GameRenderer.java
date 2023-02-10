package org.fallenreaper.nebula2d.renderer_manager;

import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;

public class GameRenderer {
    private int textureId;

    // Utility method to load a PNG file into a texture
    public void loadTexture(String filePath) {
        ByteBuffer image = STBImage.stbi_load(filePath, new int[] {0}, new int[] {0}, new int[] {0}, 4);
        textureId = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 800, 800, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        STBImage.stbi_image_free(image);
    }


    // Method to render the loaded texture
    public void renderTexture(float x, float y) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(x + 1, y);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(x + 1, y + 1);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(x, y + 1);
        GL11.glEnd();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
}
