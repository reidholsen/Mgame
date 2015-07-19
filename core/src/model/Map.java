package model;

import utils.Direction;
import utils.TileAlgorithms;
import Mgame.MyGdxGame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.HiveMind;

public class Map {
	public Tile[][] tiles;

	private int level;
	private int width;
	private int height;
	
	private MyGdxGame MGG;

	public Map(int width, int height, int level, MyGdxGame myGdxGame) {
		MGG = myGdxGame;
		tiles = new Tile[width][height];
		// initializeMap();
		this.width = width;
		this.height = height;
		
		this.level = level;

		nextLevel();
	}

	// private void initializeMap() {
	// for (int i = 0; i < tiles.length; ++i) {
	// for (int j = 0; j < tiles[i].length; ++j) {
	// if (j == 3)
	// tiles[i][j] = new Tile(Terrain.WATER,i ,j);
	// else
	// tiles[i][j] = new Tile(Terrain.GRASS, i, j);
	// }
	// }
	// ControlledPlayer p = new ControlledPlayer(this);
	// this.placeOnMap(p, 0, 0);
	//
	// Bridge b = new Bridge(this);
	// this.placeOnMap(b, 3, 3);
	//
	// NPC npc = new NPC(this);
	// this.placeOnMap(npc, 15, 15);
	//
	// NPC npc2 = new NPC(this);
	// this.placeOnMap(npc2, 1, 15);
	//
	// NPC npc3 = new NPC(this);
	// this.placeOnMap(npc3, 8, 6);
	//
	// Key key = new Key(this);
	// this.placeOnMap(key, 1,0);
	// }

	public void draw(SpriteBatch batch) {
		for (int i = 0; i < tiles.length; ++i) {
			for (int j = 0; j < tiles[i].length; ++j) {
				tiles[i][j].draw(batch, i, j);
			}
		}
	}

	public void placeOnMap(Entity e, int x, int y) {
		try {
			tiles[x][y].setEntity(e);
			e.updateXY(x, y);
		} catch (IndexOutOfBoundsException ex) {
		}
	}

	public void movePlayer(Player p, Direction d) {
		switch (d) {
		case UP:
			try {
				if (!tiles[p.getX()][p.getY() + 1].isOccupied()) {
					tiles[p.getX()][p.getY() + 1].interactWithTile(p);
					tiles[p.getX()][p.getY() + 1].setEntity(p);
					tiles[p.getX()][p.getY()].removeEntity();
					p.updateXY(p.getX(), p.getY() + 1);
				} else {
					tiles[p.getX()][p.getY() + 1].interactWithTile(p);
				}
			} catch (IndexOutOfBoundsException e) {
			}
			break;

		case DOWN:
			try {
				if (!tiles[p.getX()][p.getY() - 1].isOccupied()) {
					tiles[p.getX()][p.getY() - 1].interactWithTile(p);
					tiles[p.getX()][p.getY() - 1].setEntity(p);
					tiles[p.getX()][p.getY()].removeEntity();
					p.updateXY(p.getX(), p.getY() - 1);
				} else {
					tiles[p.getX()][p.getY() - 1].interactWithTile(p);
				}
			} catch (IndexOutOfBoundsException e) {
			}
			break;
		case LEFT:
			try {
				if (!tiles[p.getX() - 1][p.getY()].isOccupied()) {
					tiles[p.getX() - 1][p.getY()].interactWithTile(p);
					tiles[p.getX() - 1][p.getY()].setEntity(p);
					tiles[p.getX()][p.getY()].removeEntity();
					p.updateXY(p.getX() - 1, p.getY());
				} else {
					tiles[p.getX() - 1][p.getY()].interactWithTile(p);
				}
			} catch (IndexOutOfBoundsException e) {
			}
			break;
		case RIGHT:
			try {
				if (!tiles[p.getX() + 1][p.getY()].isOccupied()) {
					tiles[p.getX() + 1][p.getY()].interactWithTile(p);
					tiles[p.getX() + 1][p.getY()].setEntity(p);
					tiles[p.getX()][p.getY()].removeEntity();
					p.updateXY(p.getX() + 1, p.getY());
				} else {
					tiles[p.getX() + 1][p.getY()].interactWithTile(p);
				}
			} catch (IndexOutOfBoundsException e) {
			}
			break;
		default:
			break;
		}
	}

	public ControlledPlayer checkForControlledPlayer(int x, int y, int vision) {
		ControlledPlayer temp = null;

		for (int i = x - vision; i < x + vision; i++) {
			for (int j = y - vision; j < y + vision; j++) {
				try {
					temp = tiles[i][j].checkForPlayer();
				} catch (IndexOutOfBoundsException e) {
				}
				if (temp != null)
					return temp;
			}
		}

		return null;
	}

	public Direction getBestDirection(NPC npc, ControlledPlayer culprit) {
		return TileAlgorithms.fastestDirection(tiles[npc.getX()][npc.getY()],
				tiles[culprit.getX()][culprit.getY()], tiles);
	}

	public void nextLevel() {
		tiles = newTiles();
	}

	public void newMap(){
		MGG.nextMap();
		
		HiveMind.getInstance().stopTimer();
	}
	
	public Tile[][] newTiles() {
		tiles = new Tile[width][height];
		ControlledPlayer p;

		switch (level) {
		case 1:
			for (int i = 0; i < tiles.length; ++i) {
				for (int j = 0; j < tiles[i].length; ++j) {
					if (j == 3)
						tiles[i][j] = new Tile(Terrain.WATER, i, j);
					else
						tiles[i][j] = new Tile(Terrain.GRASS, i, j);
				}
			}
			p = new ControlledPlayer(this);
			placeOnMap(p, 0, 0);

			Bridge b = new Bridge(this);
			placeOnMap(b, 3, 3);

			NPC npc = new NPC(this);
			placeOnMap(npc, 15, 15);

			NPC npc2 = new NPC(this);
			placeOnMap(npc2, 1, 15);

			NPC npc3 = new NPC(this);
			placeOnMap(npc3, 8, 6);

			Key key = new Key(this);
			placeOnMap(key, 10, 10);

			break;
		case 2:
			for (int i = 0; i < tiles.length; ++i) {
				for (int j = 0; j < tiles[i].length; ++j) {
					tiles[i][j] = new Tile(Terrain.GRASS, i, j);
				}
			}
			p = new ControlledPlayer(this);
			placeOnMap(p, 0, 0);

		}
		return tiles;
	}

	public void removeKeyFromMap(Key key) {
		tiles[key.getX()][key.getY()].removeEntity();
		
		Teleport tp = new Teleport(this);
		placeOnMap(tp, 15, 16);
	}
}
