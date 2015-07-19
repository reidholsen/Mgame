package model;

public abstract class Obstacle extends Entity {
	private boolean isWalkable;
	
	public void setWalkable(boolean isWalkable){
		this.isWalkable = isWalkable;
	}
	public boolean isWalkable(){ return isWalkable; }
}
