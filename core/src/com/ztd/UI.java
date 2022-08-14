package com.ztd;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Hashtable;

public class UI {
    public static Hashtable<String, Texture> cannonTable = new Hashtable<String, Texture>();
    public static Hashtable<String, Integer> columns = new Hashtable<String, Integer>();
    public static Hashtable<String, Texture> bulletTable = new Hashtable<String, Texture>();
    public static Hashtable<String, Integer> bulletSpeed = new Hashtable<String, Integer>();
    public static Hashtable<String, Integer> bulletDamage = new Hashtable<String, Integer>();
    public static Hashtable<String, Texture> lootTable = new Hashtable<String, Texture>();
    public static Hashtable<String, Texture> zombieTable = new Hashtable<String, Texture>();
    public static Hashtable<String, Integer> zombieColumns = new Hashtable<String, Integer>();
    public static Hashtable<String, Integer> zombieHealth = new Hashtable<String, Integer>();
    public static Hashtable<String, Integer> zombieSpeed = new Hashtable<String, Integer>();
    public static Hashtable<String, String> toolTipInfo = new Hashtable<String, String>();


    public static String currentType;
    public static int money;
    public static int life;
    public static int wave;
    public static int cost = 10;
    public static BitmapFont font = new BitmapFont();

    public static void draw(SpriteBatch batch){
        if(life > 0) {
            font.setColor(Color.YELLOW);
            font.draw(batch, "Money: " + money, 45, 525);
            font.setColor(Color.GREEN);
            font.draw(batch, "Life: " + life, 15, 550);
            font.setColor(Color.CYAN);
            font.draw(batch, "Wave: " + wave, 15, 575);

            font.getData().setScale(2f);
            font.setColor(Color.MAROON);
            font.draw(batch, currentType, 125, 555);
            font.getData().setScale(1f);
        }
        else {
            batch.draw(Resources.blackout, 0, 0);
            font.getData().setScale(5);
            font.setColor(Color.SCARLET);
            font.draw(batch, "GAME OVER", 300, 350);
            font.getData().setScale(1);
        }
    }
}
