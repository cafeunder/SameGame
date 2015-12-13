package title;

import system.MouseFacade;
import mvcModule.Controller;
import mvcModule.Model;
import mvcModule.SceneFactory;

public class TitleModel extends Model {
	public TitleModel(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}
	
	public void update(){
		MouseFacade mf = Controller.getMouseFacade();
		if(mf.getMouseLeftPressCount() == 1) scfacade.sceneChange(SceneFactory.SCENE_ID.GAME, 0);
	};
}
