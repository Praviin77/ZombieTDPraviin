package com.ztd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Effect {
    int x,y,w,h;
    Texture etex;
    int counter = 0, delay = 20;
    boolean active = true;

    //Animation variables
    public int cols, rows;
    public Animation animation;
    public TextureRegion[] frames;
    public TextureRegion frame;
    public float frameTime;

    public Effect(Texture tex, int x, int y, int c, int r){
        etex = tex;
        if(tex == Resources.BOOM) delay = 200;
        cols = c;
        rows = r;
        this.x = x;
        this.y = y;
        w = etex.getWidth() / cols;
        h = etex.getHeight() / rows;
        animate();
    }

    public void update() {
        active = counter++ < delay;
    }

    public void draw(SpriteBatch batch){
        frameTime += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)animation.getKeyFrame(frameTime, true);;
        batch.draw(frame, x - w/2, y - h/2);
    }

    public void animate(){
        TextureRegion[][] tempFrames = TextureRegion.split(etex,
                etex.getWidth() / cols,
                etex.getHeight() / rows);

        frames = new TextureRegion[rows * cols];

        int index = 0;
        for(int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                frames[index++] = tempFrames[r][c];

        animation = new Animation(0.2f, frames);

    }

}
