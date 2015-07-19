package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import controller.PlayerController;

public class ControlledPlayer extends Player{

	private PlayerController controller;
	
	public ControlledPlayer(Map map) {
		currentMap = map;
		controller = new PlayerController(this);
		Gdx.input.setInputProcessor(controller);

		img = new Texture("entities/car_red.png");
		setPassable(false);
	}

	@Override
	public void interact(NPC npc) {
		System.out.println("npc interacting with Controlled Player.");
	}

	@Override
	public void interact(ControlledPlayer cp) {	}
}
