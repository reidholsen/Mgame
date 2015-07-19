package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Teleport extends Item {
	
	public Teleport(Map map){
		currentMap = map;
		img = new Texture("entities/items/tele.png");
		setPassable(true);
	}
	

	@Override
	public void draw(SpriteBatch batch, int x, int y, int tileWidth,
			int tileHeight) {
		batch.draw(img, x * tileWidth, y * tileHeight, tileWidth, tileHeight);

	}

	@Override
	public void interact(NPC npc) { }

	@Override
	public void interact(ControlledPlayer cp) {
		currentMap.newMap();
	}

}
