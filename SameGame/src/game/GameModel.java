package game;
import java.awt.Color;

import mvcModule.Controller;
import mvcModule.SceneFactory;
import system.DrawLibrary;
import system.FontMgr;
import system.MouseFacade;
import mvcModule.Model;

public class GameModel extends Model{
	public GameModel(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}
	
	public void update(){
		MouseFacade mf = Controller.getMouseFacade();
		if(mf.getMouseLeftPressCount() == 1) scfacade.sceneChange(SceneFactory.SCENE_ID.TITLE,0);		
	};
	
	public void draw(){
		DrawLibrary drawlib = DrawLibrary.getInstance();
		drawlib.drawString(200, 200, "ÉQÅ[ÉÄâÊñ ÇæÇÊ", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	};
}