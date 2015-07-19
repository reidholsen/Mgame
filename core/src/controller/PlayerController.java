package controller;

import utils.Direction;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import model.Player;

public class PlayerController implements InputProcessor {
	private Player player;
	
	public PlayerController(Player p) {
		player = p;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			player.changeDirection(Direction.LEFT);
			player.move(Direction.LEFT);
		}
		if (keycode == Input.Keys.RIGHT) {
			player.changeDirection(Direction.RIGHT);
			player.move(Direction.RIGHT);
		}
		if (keycode == Input.Keys.UP) {
			player.changeDirection(Direction.UP);
			player.move(Direction.UP);
		}
		if (keycode == Input.Keys.DOWN) {
			player.changeDirection(Direction.DOWN);
			player.move(Direction.DOWN);
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT) {
		}
		if (keycode == Input.Keys.RIGHT) {
		}
		if (keycode == Input.Keys.UP) {
		}
		if (keycode == Input.Keys.DOWN) {
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
