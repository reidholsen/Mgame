package utils;

import java.util.Comparator;

import model.Tile;

public class TileComparator implements Comparator<Tile> {

	@Override
	public int compare(Tile o1, Tile o2) {
		return o1.getFNumber() - o2.getFNumber();
	}

}
