package Mgame;

import model.Map;
import utils.State;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Map map;
	State state = State.RUNNING;
	int level = 1;
	
	@Override
	public void create () {
		Gdx.graphics.setDisplayMode(640, 672, false);
		batch = new SpriteBatch();		
		map = new Map(20,20, level, this);
	}
	
	public void nextMap(){
		level++;
		map = new Map(20, 20, level, this);
	}

	@Override
	public void render () {
 
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			map.draw(batch);
			batch.end();
	}
}
