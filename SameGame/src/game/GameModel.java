package game;
import java.awt.Color;

import mvcModule.Controller;
import mvcModule.SceneFactory;
import system.DrawLibrary;
import system.FontMgr;
import system.MouseFacade;
import mvcModule.Model;

public class GameModel extends Model{
	private GameProcess process;
	
	public GameModel(Controller.SceneChangeFacade scfacade){
		super(scfacade);

		Map map = new Map(0);
		this.process = new StageStart(map, this);
	}
	
	public void update(){
		GameProcess result = this.process.update();
		if(result != null){
			this.process = result;
		}
	}
	
	public void draw(){
		MouseFacade mf = Controller.getMouseFacade();

		DrawLibrary drawlib = DrawLibrary.getInstance();
		drawlib.drawString(10, 10, "x:"+mf.getMouseX()+" y:"+mf.getMouseY() , Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.INFOMATION), true);

		this.process.draw();
	}
	
	public void sceneChange(SceneFactory.SCENE_ID id){
		scfacade.sceneChange(id);
	}
}