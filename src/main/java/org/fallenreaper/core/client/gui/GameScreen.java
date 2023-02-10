package org.fallenreaper.core.client.gui;

import com.google.common.collect.Lists;
import org.fallenreaper.core.client.ScreenElement;
import org.fallenreaper.core.server.Exolor;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements ScreenElement {
    private List<ScreenElement> renderables = Lists.newArrayList();
    protected Exolor game;


    public GameScreen() {
        System.out.println("game screen constructor");
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(int pMouseX, int pMouseY) {
       renderScreenElements(pMouseX, pMouseY);
    }

    public void renderScreenElements(int pMouseX, int pMouseY) {
        renderables.forEach((button)-> button.render(pMouseX, pMouseY));
    }

    public void addButton(ScreenElement screenElement) {
        this.renderables.add(screenElement);

    }

    public void mouseClick(int mouseX, int mouseY, int pButton) {
        System.out.println(mouseX);
    }
}
