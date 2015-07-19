package model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
	private Terrain terrain;
	private Texture img;

	private int tileWidth = 32;
	private int tileHeight = 32;

	public ArrayList<Entity> entities;
	
	final public int x;
	final public int y;

	private boolean isPassable;
	private boolean isWalkable;
	
	//These are used in TileComparator.java
	private int gNumber;
	private int hNumber;
	private Tile parent = null;

	public Tile(Terrain terrain, int x, int y) {
		this.x = x;
		this.y = y;
		
		this.terrain = terrain;
		entities = new ArrayList<Entity>();

		switch (terrain) {
		case GRASS:
			img = new Texture("terrains/grass.png");
			isPassable = true;
			isWalkable = true;
			break;
		case WATER:
			img = new Texture("terrains/water.png");
			isPassable = false;
			isWalkable = false;
			break;
		}
	}
	
	public void setEntity(Entity e) {
		entities.add(e);
		isPassable = e.isPassable();
		
		if(e instanceof Obstacle)
			isWalkable =  ((Obstacle)e).isWalkable();
			
	}

	public void removeEntity() {
		entities.remove(entities.size() - 1);

		if (entities.isEmpty()) {
			setDefaultPassable();
		} else {
			isPassable = entities.get(entities.size() - 1).isPassable();
		}
	}

	private void setDefaultPassable() {
		switch (terrain) {
		case GRASS:
			isPassable = true;
			break;
		case WATER:
			isPassable = false;
			break;
		default:
			break;
		}
	}

	public boolean isOccupied() {
		if (!isPassable) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isWalkable(){
		return isWalkable;
	}
	
	public ControlledPlayer checkForPlayer(){
		for(int i = 0; i < entities.size(); ++i){
			if(entities.get(i) instanceof ControlledPlayer){
				return (ControlledPlayer)entities.get(i);
			}
		}
		return null;
	}
	
	public void interactWithTile(Player p){
		for(int i = 0; i < entities.size(); ++i){
			if(p instanceof ControlledPlayer){
				entities.get(i).interact((ControlledPlayer)p);;
			} else if (p instanceof NPC){
				entities.get(i).interact((NPC)p);;
			}
		}
	}
	
	public void setHNumber(Tile destin){
		this.hNumber = (Math.abs(destin.x - this.x)) + (Math.abs(destin.y - this.y));
	}
	public void setGNumber(Tile init){
		this.gNumber = (Math.abs(init.x - this.x)) + (Math.abs(init.y - this.y));
	}
	public void setGNumber(int g) { this.gNumber = g; }
	public int getHNumber() { return hNumber; }
	public int getGNumber() { return gNumber; }
	public int getFNumber() { return hNumber + gNumber; }
	
	public void setParent(Tile parent){ this.parent = parent; }
	public Tile getParent(){ return parent; }
	
	public void draw(SpriteBatch batch, int x, int y) {
		batch.draw(img, x * tileWidth, y * tileHeight, tileWidth, tileHeight);
		for (int i = 0; i < entities.size(); ++i) {
			entities.get(i).draw(batch, x, y, tileWidth, tileHeight);
		}
	}
}
