package com.ztd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    //Sounds
    static Sound sfxBullet = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
    static Sound sfxFireBullet = Gdx.audio.newSound(Gdx.files.internal("FireBullet.mp3"));

    //effects
    public static Texture clickEffect = new Texture(Gdx.files.internal("click_effect.png"));
    public static Texture Explosion = new Texture(Gdx.files.internal("Explosion.png"));
    public static Texture ZombieDeath = new Texture(Gdx.files.internal("zombie_death.png"));
    public static Texture BOOM = new Texture(Gdx.files.internal("zombie_boom.png"));

    //UI
    public static Texture bgTexture = new Texture(Gdx.files.internal("DungeonBackground.png"));
    public static Texture border = new Texture(Gdx.files.internal("border.png"));
    public static Texture locked = new Texture(Gdx.files.internal("locked.png"));
    public static Texture title = new Texture(Gdx.files.internal("ztdtitle.png"));
    public static Texture start = new Texture(Gdx.files.internal("startButton.png"));
    public static Texture exit = new Texture(Gdx.files.internal("exitButton.png"));

    //Zombies
    public static Texture zombieTexture = new Texture(Gdx.files.internal("Zombies.png"));
    public static Texture riotZombieTexture = new Texture(Gdx.files.internal("riotzombie.png"));
    public static Texture bigRiotZombieTexture = new Texture(Gdx.files.internal("riotzombieBIG.png"));
    public static Texture speedyZombieTexture = new Texture(Gdx.files.internal("speedy_zombie.png"));
    public static Texture difZombieTexture = new Texture(Gdx.files.internal("DifZombies.png"));
    public static Texture fastZombieTexture = new Texture(Gdx.files.internal("Fastzombies.png"));

    //Cannons
    public static Texture cannonTexture = new Texture(Gdx.files.internal("Cannon.png"));
    public static Texture fireCannonTexture = new Texture(Gdx.files.internal("Firecannon.png"));
    public static Texture superCannonTexture = new Texture(Gdx.files.internal("SuperCannon.png"));
    public static Texture doubleCannonTexture = new Texture(Gdx.files.internal("doubleCannon.png"));
    public static Texture laserCannonTexture = new Texture(Gdx.files.internal("laserCannon.png"));
    public static Texture mountedCannonTexture = new Texture(Gdx.files.internal("mountedCannon.png"));

    //Bullet
    public static Texture bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
    public static Texture fireBulletTexture = new Texture(Gdx.files.internal("firebullet.png"));
    public static Texture superBulletTexture = new Texture(Gdx.files.internal("superbullet.png"));
    public static Texture laserBulletTexture = new Texture(Gdx.files.internal("laserbullet.png"));


    //Wall
    public static Texture wallTexture = new Texture(Gdx.files.internal("Wall.png"));

    //Button
    public static Texture cannonIcon = new Texture(Gdx.files.internal("CannonIcon.png"));
    public static Texture fireIcon = new Texture(Gdx.files.internal("FireCannonIcon.png"));
    public static Texture superIcon = new Texture(Gdx.files.internal("SuperCannonIcon.png"));
    public static Texture laserIcon = new Texture(Gdx.files.internal("laserCannonIcon.png"));
    public static Texture doubleIcon = new Texture(Gdx.files.internal("doubleCannonIcon.png"));
    public static Texture wallIcon = new Texture(Gdx.files.internal("WallIcon.png"));
    public static Texture mountedIcon = new Texture(Gdx.files.internal("mountedCannonIcon.png"));
    public static Texture playButton = new Texture(Gdx.files.internal("play.png"));
    public static Texture pauseButton = new Texture(Gdx.files.internal("pause.png"));
    public static Texture restartButton = new Texture(Gdx.files.internal("restart.png"));

    //Health
    public static Texture redBar = new Texture(Gdx.files.internal("red_bar.png"));
    public static Texture greenBar = new Texture(Gdx.files.internal("green_bar.png"));

    public static Texture tooltipBg = new Texture(Gdx.files.internal("ttbg.png"));
    public static Texture yes = new Texture(Gdx.files.internal("yes.png"));
    public static Texture no = new Texture(Gdx.files.internal("no.png"));
    //public static Texture check = new Texture(Gdx.files.internal("check.png"));
    public static Texture x = new Texture(Gdx.files.internal("x.png"));
    public static Texture blackout = new Texture(Gdx.files.internal("blackout.png"));



    public static Texture zCoin = new Texture(Gdx.files.internal("z_coin.png"));
    public static Texture pouch = new Texture(Gdx.files.internal("coin_pouch.png"));

}
