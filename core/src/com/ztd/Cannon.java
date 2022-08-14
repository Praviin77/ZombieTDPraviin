

package com.ztd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;

public class Cannon {
    public Texture ctex;
    public Sprite sprite;
    public float xpos, ypos, width, height, angle;
    public int firedelay = 30;
    public int counter = 0;
    public int health;
    public boolean active;
    public String type;

    //Animation variables
    public int cols, rows;
    public Animation animation;
    public TextureRegion[] frames;
    public TextureRegion frame;
    public float frameTime;
    public Sprite[][] spriteFrames;
    public int count = 0, duration = 15, animIndex = 0;

    public Cannon(float x, float y, int health){
        xpos = lock(x);
        ypos = lock(y);
        type = UI.currentType;
        this.cols = UI.columns.get(type) == null ? 1 : UI.columns.get(type);
        ctex = UI.cannonTable.get(type);
        width = ctex.getWidth() / cols;
        height = ctex.getHeight();
        sprite = new Sprite(animate());
        sprite.setPosition(xpos - width/2, ypos - height/2);
        this.health = health;
        active = true;
    }

    public void draw(SpriteBatch batch){
        batch.draw(Resources.redBar, xpos - width/2 + 5, ypos + height/2 + 5, 40, 5);
        batch.draw(Resources.greenBar, xpos - width/2 + 5, ypos + height/2 + 5, health * 8, 5);
        sprite.draw(batch);
    }

    public void update(){
        getAngle();

        if(count++ > duration) {
            sprite = spriteFrames[0][animIndex++];
            sprite.setPosition(xpos - width/2, ypos - height/2);
            if (animIndex >= cols) animIndex = 0;
            count = 0;
        }

        sprite.setRotation(angle);
        if(counter++ >= firedelay) {
            if(!type.equals("double")) MainGame.slist.add(new Bullet(type, xpos, ypos));
            else {
                if((angle > 45 && angle < 135) || (angle > 275 && angle < 315)) {
                    MainGame.slist.add(new Bullet(type, xpos + width / 3, ypos));
                    MainGame.slist.add(new Bullet(type, xpos - width / 3, ypos));
                }
                else {
                    MainGame.slist.add(new Bullet(type, xpos, ypos + width / 3));
                    MainGame.slist.add(new Bullet(type, xpos, ypos - width / 3));
                }
            }

            counter = 0;
        }
        active = health > 0;
    }

    public float lock(float pos){
        return (int)(pos / 50)* 50 + 25;
    }

    public Rectangle getHitbox() {
        return new Rectangle(xpos - width/2, ypos - height/2, width, height);
    }

    public void getAngle(){
        if (MainGame.zlist.isEmpty()) return;

        float[] da = new float[MainGame.zlist.size()];
        for(int i = 0; i < MainGame.zlist.size(); i++){
            da[i] = Math.abs(xpos - (MainGame.zlist.get(i).xpos + MainGame.zlist.get(i).width / 2)) + Math.abs(ypos - (MainGame.zlist.get(i).ypos + MainGame.zlist.get(i).height / 2));
        }

        Arrays.sort(da);
        float zx, zy;
        zx = 0;
        zy = 0;

        for(int i = 0; i < da.length; i++){
            if(da[0] == Math.abs(xpos - (MainGame.zlist.get(i).xpos + MainGame.zlist.get(i).width / 2)) + Math.abs(ypos - (MainGame.zlist.get(i).ypos + MainGame.zlist.get(i).height / 2))){
                zx = MainGame.zlist.get(i).xpos;
                zy = MainGame.zlist.get(i).ypos;
            }
        }

        angle = (float)Math.atan((ypos - zy) / (xpos - zx));
        if(xpos >= zx)
            angle += Math.PI;
        angle = (float)Math.toDegrees(angle);
    }

    public Sprite animate(){
        // this goes into the constructor later
        rows = 1;
        spriteFrames = new Sprite[rows][cols];

        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++){
                spriteFrames[r][c] = new Sprite(ctex, c*50, r*50, 50, 50);
            }

        return spriteFrames[0][0];
    }
}
