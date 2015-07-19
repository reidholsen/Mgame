package controller;

import java.util.Timer;
import java.util.TimerTask;

public class HiveMind {
	
	private static HiveMind me;

	private Timer timer;

	public HiveMind() {
		timer = new Timer();
	}

	public static HiveMind getInstance() {
		if (me == null) {
			me = new HiveMind();
		}
		return me;
	}

	public void addRunnable(TimerTask t, int timeInMilliseconds) {
		//timer.schedule(t, timeInMilliseconds);
		timer.scheduleAtFixedRate(t, 0, timeInMilliseconds);
	}
	
	public void stopTimer(){
		timer.cancel();
	}

}
