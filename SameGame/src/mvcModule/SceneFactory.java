package mvcModule;

import game.GameModel;
import game.GameViewerComponent;
import title.TitleModel;
import title.TitleViewerComponent;

public class SceneFactory {
	public enum SCENE_ID{GAME, TITLE, GAMEOVER, GAMECLEAR};
	
	public static class SceneData{
		public Model model;
		public ViewerComponent view;
		
		public SceneData(Model model, ViewerComponent view){
			this.model = model;
			this.view = view;
		}
	}
	
	public static SceneData getSceneData(Controller.SceneChangeFacade scfacade, SCENE_ID id, int stage_num){
		switch(id){
		case GAME:
			GameModel gmodel = new GameModel(scfacade);
			GameViewerComponent gview = new GameViewerComponent(scfacade, gmodel);
			return new SceneData(gmodel, gview);
		case TITLE:
			TitleModel tmodel = new TitleModel(scfacade);
			TitleViewerComponent tview = new TitleViewerComponent(scfacade);
			return new SceneData(tmodel,tview);
		}
		return null;
	}
	
}
