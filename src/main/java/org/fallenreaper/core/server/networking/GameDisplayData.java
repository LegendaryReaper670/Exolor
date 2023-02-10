package org.fallenreaper.core.server.networking;

import org.fallenreaper.core.util.Colored;

import java.awt.*;

public class GameDisplayData {
    public Dimension dimension;
    private String title;
    private Colored color;
    private LevelDifficulty difficulty;

    public GameDisplayData(String title, Dimension dimensions) {
        this.title = title;
        this.dimension = dimensions;
        this.color = new Colored(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public GameDisplayData() {

    }

    public Colored getColor() {
        return color;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setColor(Colored color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public Dimension getDimension() {
        return dimension;
    }


    public GameDisplayData defaultSettings() {
        this.dimension = new Dimension(500, 500);
        this.title = "Game";

        return this;
    }
}
