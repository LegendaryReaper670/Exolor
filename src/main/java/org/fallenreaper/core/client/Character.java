package org.fallenreaper.core.client;

import org.fallenreaper.core.util.Vec2;

import java.util.UUID;

public abstract class Character implements ScreenElement {
   private Vec2 position;
   private float health;
   private UUID uuid;

    public void updatePosition(Vec2 target) {

        if (target == null) return;

        double angle = Math.atan2(target.y - position.y, target.x - position.x);
        position.add(Math.cos(angle) * target.y, Math.sin(angle) * target.x);

    }

    public Vec2 getPosition() {
        return position;
    }

    public float getHealth() {
        return health;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public void setHealth(float health) {
        this.health = health;
    }




    @Override
    public void tick() {
        System.out.println("wjhshs");
    }
}
