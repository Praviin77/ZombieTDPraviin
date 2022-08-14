package com.ztd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Zombie {
    public Texture ztex;
    public float xpos, ypos, width, height;
    public float speed;
    public boolean active, drop = false;
    public int hp, hp_unit;
    public String type;

    //Animation variables
    public int cols, rows;
    public Animation animation;
    public TextureRegion[] frames;
    public TextureRegion frame;
    public float frameTime;

    public Zombie(String type, float x, float y){
        ztex = UI.zombieTable.get(type) == null ? Resources.zombieTexture : UI.zombieTable.get(type);
        cols = UI.zombieColumns.get(type) == null ? 4 : UI.zombieColumns.get(type);
        rows = 1;
        xpos = x;
        ypos = y;
        width = ztex.getWidth() / cols;
        height = ztex.getHeight() / rows;
        speed = UI.zombieSpeed.get(type) == null ? 2 : UI.zombieSpeed.get(type);
        hp = UI.zombieHealth.get(type) == null ? 5 : UI.zombieHealth.get(type);
        active = true;
        hp_unit = (int)(width / hp);
        this.type = type;
        animate();
    }

    public void draw(SpriteBatch batch){
        frameTime += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)animation.getKeyFrame(frameTime, true);
        batch.draw(Resources.redBar, xpos - width/2, ypos + height/2 + 2, width, 5);
        batch.draw(Resources.greenBar, xpos - width/2 , ypos + height/2 + 2, hp_unit * hp, 5);
        batch.draw(frame, xpos - width/2, ypos - height/2);
    }

    public void update(){
        active = hp > 0;
        drop = !active;
        xpos -= speed;
        if(xpos < -15) {
            active = false;
            drop = false;
            UI.life--;
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(xpos, ypos, width, height);
    }

    public void animate(){
        TextureRegion[][] tempFrames = TextureRegion.split(ztex,
                ztex.getWidth() / cols,
                ztex.getHeight() / rows);

        frames = new TextureRegion[rows * cols];

        int index = 0;
        for(int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                frames[index++] = tempFrames[r][c];

        animation = new Animation(0.2f, frames);

    }
}
