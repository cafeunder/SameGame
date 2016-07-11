package game;

import mvcModule.SceneFactory;

public class GamePlay extends GameProcess{
	private Map map;

	public GamePlay(Map map, GameModel model){
		super(model);
		this.map = map;
	}

	public GameProcess update(){
		switch(this.map.update()){
		case GAMEOVER:
			this.model.sceneChange(SceneFactory.SCENE_ID.GAMEOVER);
			return null;
		case NEXTSTAGE:
			this.map.setEnable(false);
			return new StageClear(this.map, this.model);
		default:
			break;
		}

		return null;
	}

	public void draw(){
		this.map.draw();
	}
}