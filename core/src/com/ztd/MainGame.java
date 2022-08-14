package com.ztd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends ApplicationAdapter {

	public static ArrayList<Zombie> zlist = new ArrayList<Zombie>();
	public static ArrayList<Cannon> clist = new ArrayList<Cannon>();
	public static ArrayList<Wall> wlist = new ArrayList<Wall>();
	public static ArrayList<Button> blist = new ArrayList<Button>();
	public static ArrayList<Bullet> slist = new ArrayList<Bullet>();
	public static ArrayList<Loot> lootList = new ArrayList<Loot>();
	public static ArrayList<Effect> eList = new ArrayList<Effect>();
	SpriteBatch batch;
	boolean paused = false;
	OrthographicCamera camera;
	boolean started;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		started = false;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		blist.add(new Button(Resources.start, "start", 1024 / 2 - 100, 600 / 2 - blist.size() * 125, false, false, 0));
		blist.add(new Button(Resources.exit, "exit", 1024 / 2 - 100, 600 / 2 - blist.size() * 125, false, false, 0));
	}

	public void createGame(){
		// clear lists
		zlist.clear();
		slist.clear();
		clist.clear();
		wlist.clear();
		blist.clear();
		eList.clear();
		lootList.clear();

		// game variables
		UI.money = 10000;
		UI.wave = 0;
		UI.life = 5;
		UI.currentType = "cannon";
		paused = false;

		// buttons
		blist.add(new Button(Resources.cannonIcon, "cannon", 250 + blist.size() * 75, 525, false, true, 0));
		//tlist.add(new ToolTip(250 + blist.size() * 75, 525, 50, 50));
		blist.add(new Button(Resources.fireIcon, "fire", 250 + blist.size() * 75, 525, true, false, 100));
		blist.add(new Button(Resources.superIcon, "super", 250 + blist.size() * 75, 525, true, false, 200));
		blist.add(new Button(Resources.laserIcon, "laser", 250 + blist.size() * 75, 525, true, false, 500));
		blist.add(new Button(Resources.doubleIcon, "double", 250 + blist.size() * 75, 525, true, false, 200));
		blist.add(new Button(Resources.wallIcon, "wall", 250 + blist.size() * 75, 525, false, false, 0));
		blist.add(new Button(Resources.mountedIcon, "mounted", 250 + blist.size() * 75, 525, true, false, 1000));
		blist.add(new Button(Resources.playButton, "play", 950, 525, false, false, 0));
		blist.add(new Button(Resources.restartButton, "restart", 875, 525, false, false, 0));

		// cannons
		UI.cannonTable.put("cannon", Resources.cannonTexture);
		UI.cannonTable.put("fire", Resources.fireCannonTexture);
		UI.cannonTable.put("super", Resources.superCannonTexture);
		UI.cannonTable.put("laser", Resources.laserCannonTexture);
		UI.cannonTable.put("double", Resources.doubleCannonTexture);
		UI.cannonTable.put("mounted", Resources.mountedCannonTexture);
		UI.columns.put("laser", 16);

		// tool tips info
		UI.toolTipInfo.put("fire", "Fires low damage ammo at a high rate of fire");
		UI.toolTipInfo.put("super", "Fires low damage ammo at a high rate of fire");
		UI.toolTipInfo.put("laser", "Fires extreme damage ammo at a very low rate of fire");
		UI.toolTipInfo.put("double", "Fires two low damage ammo at a low rate of fire");
		UI.toolTipInfo.put("mounted", "Spawns a wall with mounted basic cannons");

		// bullets
		UI.bulletTable.put("fire", Resources.fireBulletTexture);
		UI.bulletTable.put("super", Resources.superBulletTexture);
		UI.bulletTable.put("laser", Resources.laserBulletTexture);
		UI.bulletDamage.put("fire", 3);
		UI.bulletDamage.put("super", 2);
		UI.bulletDamage.put("laser", 100);
		UI.bulletSpeed.put("fire", 20);

		// Zomb, table/column/health/speed
		UI.zombieTable.put("zombie", Resources.zombieTexture);
		UI.zombieTable.put("skeleton", Resources.difZombieTexture);
		UI.zombieTable.put("fast", Resources.fastZombieTexture);
		UI.zombieTable.put("speedy", Resources.speedyZombieTexture);
		UI.zombieTable.put("riot", Resources.riotZombieTexture);
		UI.zombieTable.put("bigRiot", Resources.bigRiotZombieTexture);
		UI.zombieColumns.put("speedy", 6);
		UI.zombieHealth.put("speedy", 3);
		UI.zombieHealth.put("riot", 10);
		UI.zombieHealth.put("bigRiot", 25);
		UI.zombieSpeed.put("speedy", 5);
		UI.zombieSpeed.put("fast", 3);
		UI.zombieSpeed.put("riot", 1);
		UI.zombieSpeed.put("bigRiot", 1);

		// Loots
		UI.lootTable.put("pouch", Resources.pouch);
		lootList.add(new Loot("pouch", 15, 510));
	}

	@Override
	public void render () {
		// clear screen
		ScreenUtils.clear(0, 0, 0, 1);

		// call logic
		update();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		//Draw
		batch.begin();
		if(started) {
			batch.draw(Resources.bgTexture, 0, 0);
			UI.draw(batch);
			for (Wall w : wlist) w.draw(batch);
			for (Zombie z : zlist) z.draw(batch);
			for (Cannon c : clist) c.draw(batch);
			for (Button b : blist) b.draw(batch);
			for (Bullet s : slist) s.draw(batch);
			for (Loot l : lootList) l.draw(batch);
			for (Effect e : eList) e.draw(batch);
		}
		else {
			batch.draw(Resources.title, 0,0);
			for (Button b : blist) b.draw(batch);
			for (Effect e : eList) e.draw(batch);
		}
		batch.end();
	}

	public void update(){
		// Call logic
		controls();
		if (paused) return;
		spawnZombies();
		bzCollision();
		czCollision();
		cpouchCollision();
		wzCollision();
		housekeeping();


		//Update lists
		for(Zombie z : zlist) z.update();
		for(Cannon c : clist) c.update();
		for(Bullet s : slist) s.update();
		for(Wall w : wlist) w.update();
		for(Effect e : eList) e.update();
		//for(ToolTip t : tlist) t.update();
		for(Loot l : lootList) if(l.type.equals("coin")) l.moveToTarget((lootList.get(0)));
	}

	public void housekeeping(){
		for(int i = 0; i < zlist.size(); i++)
			if(!zlist.get(i).active) {
				eList.add(new Effect(Resources.BOOM, (int)zlist.get(i).xpos, (int)zlist.get(i).ypos, 7,1));
				if(zlist.get(i).drop) {
					lootList.add(new Loot("coin", zlist.get(i).xpos, zlist.get(i).ypos));
				}
				zlist.remove(i);
			}
		for(int i = 0; i < wlist.size(); i++) if(!wlist.get(i).active) {
			wlist.get(i).wallCannons.clear();
			wlist.remove(i);
		}
		for(int i = 0; i < slist.size(); i++) if(!slist.get(i).active) slist.remove(i);
		for(int i = 0; i < clist.size(); i++) if(!clist.get(i).active) clist.remove(i);
		for(int i = 0; i < eList.size(); i++) if(!eList.get(i).active) eList.remove(i);
		for(int i = 0; i < lootList.size(); i++) if(!lootList.get(i).active) lootList.remove(i);
	}

	public void controls(){
		if(Gdx.input.justTouched()) {
			float x, y;
			Vector3 touchpos = new Vector3();
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchpos);
			x = touchpos.x;
			y = touchpos.y;

			eList.add(new Effect(Resources.clickEffect, (int)x, (int)y, 4, 1));

			for(Button b : blist)
				if(b.t != null && !b.t.hidden && b.t.hitBox().contains(x,y)) {
					if(b.t.btn.click(x,y)) b.t.hidden = true;
					return;
				}
			for(Button b : blist)
				if (b.click(x, y)) {
					if(b.t != null && b.t.hidden && b.locked){
						cleartt();
						b.t.hidden = false;
						return;
					}
					switch(b.type){
						case "cannon":
						case "fire":
						case "super":
						case "laser":
						case "double":
							if(!b.locked) {
								deselction(b.type);
								UI.currentType = b.type;
							}
							else if (b.t != null  && UI.money >= b.cost) {
								UI.money -= b.cost;
								b.locked = false;
								b.t.hidden = true;
							}
							break;
						case "mounted":
							if(b.locked && UI.money >= b.cost) {
								UI.money -= b.cost;
								b.locked = false;
							}
						case "wall":
							if(wlist.size() < 3) wlist.add(new Wall(wlist.size() * Resources.wallTexture.getWidth(), 0));
							if(wlist.size() <= 3 && b.type.equals("mounted")) for(int i = 0; i < 10; i++) {
								String tempType = UI.currentType;
								UI.currentType = b.type;
								wlist.get(wlist.size() - 1).wallCannons.add(new Cannon(wlist.size() * Resources.wallTexture.getWidth() - Resources.wallTexture.getWidth(),
										i*Resources.mountedCannonTexture.getHeight(),
										5)
								);
								UI.currentType = tempType;
							}
							break;
						case "play":
						case "pause":
							// Can do same thing with seperate case for pause and play;
							b.ButtonTexture = b.type.equals("play") ? Resources.pauseButton : Resources.playButton;
							b.type = b.type.equals("play") ? "pause" : "play";
							paused = b.type.equals("pause");
							break;
						case "start":
						case "restart":
							started = true;
							createGame();
							break;
						case "exit":
							System.exit(0);
						default:
							System.out.println("No type available");
							break;
					}
					return;
				}

			for(Cannon c : clist) if (c.getHitbox().contains(x,y)) return;
			System.out.println(x + ", " + y);
			if (UI.money >= UI.cost && (y < 200 || (y > 300 && y < 500))) {
				clist.add(new Cannon(x, y, 5));
				UI.money -= UI.cost;
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void deselction(String type){
		for(Button b : blist) b.selected = b.type.equals(type);
		cleartt();
	}

	public void cleartt() {
		for(Button b : blist) if(b.t != null) b.t.hidden = true;
	}

	public void spawnZombies(){
		if(!zlist.isEmpty()) return;
		String[] ztypes = {"", "", "", "", "", "skeleton", "skeleton", "fast", "fast", "speedy", "riot"};

		Random r = new Random();
		int zpw = 5; // zombies per wave
		UI.wave++;
		for(int i = 0; i < UI.wave * zpw; i++){
			int zx = 1024 + i * r.nextInt(75) + 35, zy = 25 + r.nextInt(375);
			int typeIndex = r.nextInt(ztypes.length);
			zlist.add(new Zombie(ztypes[typeIndex].equals("speedy") && UI.wave <= 5 ? "" : ztypes[typeIndex], zx, zy));
		}
	}

	public void bzCollision(){
		if(slist.isEmpty() || zlist.isEmpty()) return;

		for(Zombie z : zlist)
			for(Bullet b : slist){
				if(z.getHitbox().contains(b.x, b.y)){
					z.hp -= b.damage;
					b.active = false;
				}
			}
	}

	public void czCollision(){
		if(clist.isEmpty() || zlist.isEmpty()) return;

		for(Zombie z : zlist)
			for(Cannon c : clist){
				if(c.getHitbox().contains(z.xpos, z.ypos)){
					z.active = false;
					c.health--;
				}
			}
	}

	public void cpouchCollision(){
		for(int i = 1; i < lootList.size(); i++)
			if(lootList.get(0).getHitbox().contains(lootList.get(i).x, lootList.get(i).y)){
				UI.money += lootList.get(i).item_value;
				lootList.get(i).active = false;
			}
	}

	public void wzCollision(){
		if(wlist.isEmpty() || wlist.isEmpty()) return;

		for(Zombie z : zlist)
			if(wlist.get(wlist.size() - 1).getHitbox().contains(z.xpos, z.ypos)) {
				wlist.get(wlist.size() - 1).durability--;
				z.active = false;
			}
	}

	public void mcwCollision(Wall w){
		for(Cannon c : clist)
			if(c.type.equals("mounted")) {
				if(w.getHitbox().contains(c.xpos - c.width/2, c.ypos - c.height/2));
					c.active = false;
			}
	}
}
