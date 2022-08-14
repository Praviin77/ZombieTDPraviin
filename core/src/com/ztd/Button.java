package com.ztd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    public float xpos, ypos, width, height;
    public String type;
    public Texture ButtonTexture;
    public boolean locked, selected;
    public int cost;
    public ToolTip t;

    public Button(Texture tx, String type, float x, float y, boolean locked, boolean selected, int cost){
        ButtonTexture = tx;
        this.type = type;
        xpos = x;
        ypos = y;
        width = tx.getWidth();
        height = tx.getHeight();
        this.locked = locked;
        this.selected = selected;
        this.cost = cost;
        if(!type.equals("no")) t = new ToolTip(x, y, width, height, cost, type);
    }

    public void draw(SpriteBatch batch){
        batch.draw(ButtonTexture, xpos, ypos);
        if (selected) batch.draw(Resources.border, xpos - 7, ypos - 7);
        if (locked) batch.draw(Resources.locked, xpos, ypos);

        if(t != null) t.draw(batch);
    }

    private Rectangle buttonbox() {
        return new Rectangle(xpos, ypos, width, height);
    }

    public boolean click(float x, float y){
        return buttonbox().contains(x, y);
    }
}
