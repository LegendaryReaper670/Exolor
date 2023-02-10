package org.fallenreaper.nebula2d.renderer_manager.texture;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SpriteTexture {
    public int width, height, x, y, maxWidth, maxHeight, textureID;
    public String fileName;


    public SpriteTexture(String fileName, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
       this.fileName = fileName;


    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTextureID() {
        return textureID;
    }

    public String getFileName() {
        return fileName;
    }

    public void readTextureFile(String file, int x, int y, int width, int height) throws IOException {
        BufferedImage image = ImageIO.read(new File("C:/Users/salva/OneDrive/Documentos/GitHub/Exolor/src/main/resources/assets/exolor/textures/gui/" + file + ".png"));
        BufferedImage texture = image.getSubimage(x, y, width, height);
        int textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        int[] pixels = ((DataBufferInt) texture.getRaster().getDataBuffer()).getData();
        ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * 4);
        for (int pixel : pixels) {
            buffer.putInt(pixel);
        }
        buffer.flip();
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
    }


    public void render() throws IOException {

        readTextureFile(fileName, x, y, width, height);

    }
}
