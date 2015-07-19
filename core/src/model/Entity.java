package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	Map currentMap;
	Texture img;
	
	private int x;
	private int y;
	
	private boolean isPassable;
	
	public Entity(){
		currentMap = null;
		this.isPassable = false;
	}
	public Entity(Map map){
		currentMap = map;
		this.isPassable = false;
	}
	
	public void updateXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void placeOnMap(int x, int y){
		currentMap.placeOnMap(this, x, y);
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	public boolean isPassable(){ return isPassable; }
	public void setPassable(boolean isPassable) { this.isPassable = isPassable; }
	public Map getMap(){ return currentMap; }
	
	public abstract void draw(SpriteBatch batch, int x, int y, int tileWidth, int tileHeight);
	
	public abstract void interact(NPC npc);
	public abstract void interact(ControlledPlayer cp);
}
