package game;
import java.awt.Color;

import mvcModule.Controller;
import mvcModule.SceneFactory;
import system.DrawLibrary;
import system.FontMgr;
import system.MouseFacade;
import mvcModule.Model;

public class GameModel extends Model{
	private Map map;
	
	public GameModel(Controller.SceneChangeFacade scfacade){
		super(scfacade);
		
		map = new Map(Map.BIG_BLOCK_SIZE, 4, 40, 40);
//		map = new Map(Map.SMALL_TIP_SIZE, 4);
	}
	
	public void update(){
//		MouseFacade mf = Controller.getMouseFacade();
//		if(mf.getMouseLeftPressCount() == 1) scfacade.sceneChange(SceneFactory.SCENE_ID.TITLE,0);
		
		switch(map.update()){
		case GAMEOVER:
			scfacade.sceneChange(SceneFactory.SCENE_ID.GAMEOVER);
			return;
		case NEXTSTAGE:
			break;
		default:
			break;
		}
	}
	
	public void draw(){
		MouseFacade mf = Controller.getMouseFacade();

		DrawLibrary drawlib = DrawLibrary.getInstance();
		//drawlib.drawString(200, 200, "ÉQÅ[ÉÄâÊñ ÇæÇÊ", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
		drawlib.drawString(10, 10, "x:"+mf.getMouseX()+" y:"+mf.getMouseY() , Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.INFOMATION), true);
		
		map.draw();
	}
}