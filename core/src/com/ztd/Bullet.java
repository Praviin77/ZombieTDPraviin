package com.ztd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;

public class Bullet {
    public Texture bulletTexture;
    public float x, y, w, h, speed, angle;
    public boolean active;
    public int damage = 1;
    public int lifespan = 50;

    public Bullet(String type, float x, float y){
        Resources.sfxBullet.play(0.2f);
        bulletTexture = UI.bulletTable.get(type) == null ? Resources.bulletTexture : UI.bulletTable.get(type);
        speed = UI.bulletSpeed.get(type) == null ? 5 : UI.bulletSpeed.get(type);
        damage = UI.bulletDamage.get(type) == null ? 1 : UI.bulletDamage.get(type);
        this.x = x;
        this.y = y;
        w = bulletTexture.getWidth();
        h = bulletTexture.getHeight();
        active = true;
        getAngle();
    }

    public void draw(SpriteBatch batch){
        batch.draw(bulletTexture, x - w/2, y - h/2);
    }

    public void update(){
        if(lifespan-- <= 0) active = false;
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }

    public void getAngle(){
        if (MainGame.zlist.isEmpty()) return;

        float[] da = new float[MainGame.zlist.size()];
        for(int i = 0; i < MainGame.zlist.size(); i++){
            da[i] = Math.abs(x - (MainGame.zlist.get(i).xpos + MainGame.zlist.get(i).width / 2)) + Math.abs(y - (MainGame.zlist.get(i).ypos + MainGame.zlist.get(i).height/2));
        }

        Arrays.sort(da);
        float zx, zy;
        zx = 0;
        zy = 0;

        for(int i = 0; i < da.length; i++){
            if(da[0] == Math.abs(x - (MainGame.zlist.get(i).xpos + MainGame.zlist.get(i).width / 2)) + Math.abs(y - (MainGame.zlist.get(i).ypos + MainGame.zlist.get(i).height/2))){
                zx = MainGame.zlist.get(i).xpos;
                zy = MainGame.zlist.get(i).ypos;
            }
        }

        angle = (float)Math.atan((y - zy) / (x - zx));
        if(x >= zx)
            angle += Math.PI;
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, w, h);
    }
}
