package model;

import com.badlogic.gdx.graphics.Texture;

import controller.NPCBrain;

public class NPC extends Player{

	private NPCBrain brain;
	private int vision = 5;
	
	public NPC(Map map) {
		currentMap = map;

		img = new Texture("entities/cop_car.png");
		setPassable(false);
		
		brain = new NPCBrain(this);
	}
	
	public int getVision(){ return vision; }

	@Override
	public void interact(NPC npc) {

	}

	@Override
	public void interact(ControlledPlayer cp) {
		System.out.println("ControlledPlayer interacting with npc.");
	}
}
