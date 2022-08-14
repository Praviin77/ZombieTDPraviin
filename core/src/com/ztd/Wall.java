package com.ztd;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Wall {
    ArrayList<Cannon> wallCannons = new ArrayList<Cannon>();
    public float xpos, ypos, width, height;
    int durability = 5;
    boolean active = true;

    public Wall(float x, float y){
        xpos = x;
        ypos = y;
        width = Resources.wallTexture.getWidth();
        height = Resources.wallTexture.getHeight();
    }

    public void update(){
        active = durability > 0;
        for(Cannon c : wallCannons) c.update();
    }
    public Rectangle getHitbox(){
        return new Rectangle(xpos, ypos, width, height);
    }

    public boolean collides(Cannon c){
        return (this.xpos <= c.xpos + c.width &&
                this.xpos + this.width >= c.xpos &&
                this.ypos <= c.ypos + c.height &&
                this.ypos + this.height >= c.ypos
        );
    }

    public void draw(SpriteBatch batch){
        batch.draw(Resources.wallTexture, xpos, ypos);
        for(Cannon c : wallCannons) c.draw(batch);
    }

}
