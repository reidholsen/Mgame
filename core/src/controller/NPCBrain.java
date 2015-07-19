package controller;

import utils.Direction;
import model.ControlledPlayer;
import model.Map;
import model.NPC;

public class NPCBrain extends Brain {

	private NPC npc;
	private HiveMind hivemind;
	private ControlledPlayer culprit;

	private Map map;

	public NPCBrain(NPC npc) {
		this.npc = npc;
		map = npc.getMap();

		hivemind = HiveMind.getInstance();
		hivemind.addRunnable(this, 250);
	}

	@Override
	public void run() {
			if (culprit == null) {
				culprit = map.checkForControlledPlayer(npc.getX(), npc.getY(),
						npc.getVision());
			} else {
				Direction d = map.getBestDirection(npc, culprit);
				if (d != null) {
					npc.changeDirection(d);
					npc.move(d);
				}
				// if (Math.abs(npc.getX() - culprit.getX()) >
				// Math.abs(npc.getY()
				// - culprit.getY())) {
				// if (npc.getX() - culprit.getX() > 0) {
				// npc.move(Direction.LEFT);
				// } else {
				// npc.move(Direction.RIGHT);
				// }
				// } else {
				// if (npc.getY() - culprit.getY() > 0) {
				// npc.move(Direction.DOWN);
				// } else {
				// npc.move(Direction.UP);
				// }
				// }
			}
	}

}
