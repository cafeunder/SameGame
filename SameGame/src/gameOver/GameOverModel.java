package gameOver;

import mvcModule.Controller;
import mvcModule.Model;
import mvcModule.SceneFactory;
import system.MouseFacade;

public class GameOverModel extends Model {
	public GameOverModel(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}

	public void update(){
		MouseFacade mf = Controller.getMouseFacade();
		if(mf.getMouseLeftPressCount() == 1) scfacade.sceneChange(SceneFactory.SCENE_ID.GAME, 0);
	};
}