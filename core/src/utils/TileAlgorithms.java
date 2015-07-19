package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import model.Tile;

public class TileAlgorithms {
	public static Direction fastestDirection(Tile init, Tile destin,
			Tile[][] tiles) {
		HashMap<Integer, Tile> closedSet = new HashMap<Integer, Tile>();
		// HashMap<Tile, Tile> cameFrom = new HashMap<Tile, Tile>();
		PriorityQueue<Tile> openSet = new PriorityQueue<Tile>(11,
				new TileComparator());

		Tile current;
		Tile neighbor = null;

		init.setGNumber(0);
		init.setHNumber(destin);

		openSet.add(init);

		int tempG;
		
		for (int i = 0; i < tiles.length; ++i) {
			for (int j = 0; j < tiles[i].length; ++j) {
				tiles[i][j].setParent(null);
			}
		}

		while (!openSet.isEmpty()) {
			current = openSet.remove();

			if (current == destin) {
				return reconstructPath(destin);
			}

			closedSet.put(cantorKey(current), current);
			for (Direction d : Direction.values()) {
				switch (d) {
				case UP:
					try {
						if (tiles[current.x][current.y + 1].isWalkable()) {
							neighbor = tiles[current.x][current.y + 1];
						} else {
							neighbor = null;
						}
					} catch (IndexOutOfBoundsException e) {
						neighbor = null;
					}
					break;
				case RIGHT:
					try {
						if (tiles[current.x + 1][current.y].isWalkable()) {
							neighbor = tiles[current.x + 1][current.y];
						} else {
							neighbor = null;
						}
					} catch (IndexOutOfBoundsException e) {
						neighbor = null;
					}
					break;
				case DOWN:
					try {
						if (tiles[current.x][current.y - 1].isWalkable()) {
							neighbor = tiles[current.x][current.y - 1];
						} else {
							neighbor = null;
						}
					} catch (IndexOutOfBoundsException e) {
						neighbor = null;
					}
					break;
				case LEFT:
					try {
						if (tiles[current.x - 1][current.y].isWalkable()) {
							neighbor = tiles[current.x - 1][current.y];
						} else {
							neighbor = null;
						}
					} catch (IndexOutOfBoundsException e) {
						neighbor = null;
					}
					break;
				default:
					neighbor = null;
					break;
				}
				if (neighbor == null) {
					continue;
				} else if (closedSet.containsKey(cantorKey(neighbor))) {
					// neighbor.setHeuristics(init, destin);
					continue;
				} else {
					tempG = current.getGNumber() + 1;
					neighbor.setGNumber(init);
					neighbor.setHNumber(destin);

					if (!openSet.contains(neighbor)
							|| tempG < neighbor.getGNumber()) {
						// if (cameFrom.containsKey(neighbor)) {
						// cameFrom.remove(neighbor);
						// cameFrom.put(neighbor, current);
						// } else {
						// cameFrom.put(neighbor, current);
						// }
						neighbor.setParent(current);
						neighbor.setGNumber(tempG);
						neighbor.setHNumber(destin);

						if (!openSet.contains(neighbor)) {
							openSet.add(neighbor);
						}
					}
				}
			}
		}
		
		return null;
	}

	private static Direction reconstructPath(Tile current) {
		Tile second = null;
		Direction d = null;

		int x = 1;
		int y = 1;

		//System.out.println("~~~~~ path.size(): " + path.size() + " ~~~~~");

		while (current.getParent() != null) {

			second = current;
			current = current.getParent();
		}

		try {
			x = second.x - current.x;
			y = second.y - current.y;
		} catch (NullPointerException e) {
			System.out.println("NullPointer, TileAlgorithms.java line 107");
		}
		
		
		if (x == -1 && y == 0) {
			d = Direction.LEFT;
		} else if (x == 1 && y == 0) {
			d = Direction.RIGHT;
		} else if (x == 0 && y == -1) {
			d = Direction.DOWN;
		} else if (x == 0 && y == 1) {
			d = Direction.UP;
		} else {
			System.out.println("Something is wrong.");
		}
		
		return d;
	}

	/**
	 * Used to get a unique key for the hashmap.
	 * 
	 * @param a
	 *            Tile's x
	 * @param b
	 *            Tile's y
	 * @return Unique key for hashmap
	 */
	private static int cantorKey(Tile t) {
		return ((t.x + t.y) * (t.x + t.y + 1) / 2) + t.y;
	}
}
