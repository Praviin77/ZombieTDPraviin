package com.ztd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Loot {
    Texture ltx;
    float x, y, w, h, angle, speed;
    int item_value;
    String type;
    boolean active = true;

    public Loot(String type, float x, float y){
        this.type = type;
        ltx = UI.lootTable.get(type) == null ? Resources.zCoin : UI.lootTable.get(type);
        this.x = x;
        this.y = y;
        w = ltx.getWidth();
        h = ltx.getHeight();
        speed = 5;
        item_value = 5;
    }

    public void draw(SpriteBatch batch){
        batch.draw(ltx, x, y);
    }

    public Rectangle getHitbox(){
        return new Rectangle(x, y, w*2, h*2);
    }

    public void moveToTarget(Loot target){
        getAngle(target);
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }

    public void getAngle(Loot l){
        angle = (float)Math.atan((y - (MainGame.lootList.get(0).y + MainGame.lootList.get(0).h/2)) / (x - (MainGame.lootList.get(0).x + MainGame.lootList.get(0).w/2)));
        if(x >= MainGame.lootList.get(0).x + MainGame.lootList.get(0).w/2) angle += Math.PI;
    }
}
