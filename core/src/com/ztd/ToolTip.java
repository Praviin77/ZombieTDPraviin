package com.ztd;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ToolTip {
    Texture ttex;
    Button btn;
    float x, y, w, h;
    float px, py, pw, ph;
    int variant;
    GlyphLayout layout = new GlyphLayout();
    boolean hidden = true;
    int cost;
    String type;
    BitmapFont font = new BitmapFont();

    ToolTip(float x, float y, float w, float h, int cost, String type){
        ttex = Resources.tooltipBg;
        this.cost = cost;

        px = x;
        py = y;
        pw = w;
        ph = h;

        this.type = type;
        this.w = 200;
        this.h = 100;
        this.x = x + w/2 - this.w/2;
        this.y = y - w/2 - this.h;
        //btn = new Button(Resources.no, "no", this.x + this.w - 55, this.y + 5, false, false, 0);
        btn = new Button(Resources.x, "no", this.x + this.w - 26, this.y + this.h - 26, false, false, 0);
        variant = 1;
    }

    public void draw(SpriteBatch batch){
        if(!hidden) {
            batch.draw(ttex, x, y, w, h);
            if(UI.toolTipInfo.get(type) != null) {
                String[] words = UI.toolTipInfo.get(type).split(" ");
                int rtx = 0;
                int rty = 0;
                for(String s : words) {
                    if(rtx + layout.width > w - btn.width - 30) {
                        rtx = 0;
                        rty += layout.height + 5;
                    }
                    font.setColor(Color.BLACK);
                    font.draw(batch, s, rtx + x + 3, y + h - rty - 1);
                    font.setColor(Color.RED);
                    font.draw(batch, s, rtx + x + 2, y + h - rty - 2);
                    layout.setText(font,  " "  + s);
                    rtx += layout.width;
                }
            }
            font.setColor(Color.BLACK);
            font.draw(batch, "Unlock: $" + cost, x + w/4 + 1, y + 36);
            font.setColor(Color.YELLOW);
            font.draw(batch, "Unlock: $" + cost, x + w/4, y + 35);
            font.setColor(Color.BLACK);
            font.draw(batch, "(Tap again to unlock)", x + w / 6 + 1, y + 14);
            font.setColor(Color.BLACK);
            font.draw(batch, "(Tap again to unlock)", x + w / 6, y + 15);
            btn.draw(batch);
        }
    }

    Rectangle hitBox() {
        return new Rectangle(x,y,w,h);
    }
}

/*
 */
