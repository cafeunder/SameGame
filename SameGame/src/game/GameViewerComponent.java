package game;

import mvcModule.Controller;
import mvcModule.ViewerComponent;

public class GameViewerComponent extends ViewerComponent {
	private GameModel gModel;
	public GameViewerComponent(Controller.SceneChangeFacade scfacade, GameModel gModel){
		super(scfacade);
		this.gModel = gModel;
	}
	
	public void draw(){
		this.gModel.draw();
	}
}
