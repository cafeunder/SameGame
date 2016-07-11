package game;

public abstract class GameProcess {
	protected GameModel model;
	public GameProcess(GameModel model){
		this.model = model;
	}

	public abstract GameProcess update();
	public abstract void draw();
}