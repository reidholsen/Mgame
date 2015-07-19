package model;

import utils.Direction;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Player extends Entity {

	private Direction direction = Direction.LEFT;

	public Player(){
		currentMap = null;
		setPassable(false);
	}

	public void draw(SpriteBatch batch, int x, int y, int tileWidth,
			int tileHeight) {
		switch (direction) {
		case RIGHT:
			batch.draw(img, x * tileWidth, y * tileHeight, tileWidth,
					img.getHeight() / (img.getWidth() / tileWidth));
			break;
		case UP:
			batch.draw(img, (x * tileWidth) + tileWidth, y * tileHeight, 0, 0,
					tileWidth, img.getHeight() / (img.getWidth() / tileWidth),
					1, 1, 90, 0, 0, img.getWidth(), img.getHeight(), false,
					false);
			break;
		case DOWN:
			batch.draw(img, x * tileWidth, (y * tileHeight) + tileHeight, 0, 0,
					tileWidth, img.getHeight() / (img.getWidth() / tileWidth),
					1, 1, 270, 0, 0, img.getWidth(), img.getHeight(), false,
					false);
			break;
		case LEFT:
			batch.draw(img, x * tileWidth, y * tileHeight, tileWidth,
					img.getHeight() / (img.getWidth() / tileWidth), 0, 0,
					img.getWidth(), img.getHeight(), true, false);
			break;
		default:
			break;

		}
	}

	public void changeDirection(Direction d) {
		direction = d;
	}
	
	public void move(Direction d){
		currentMap.movePlayer(this, d);
	}
}
