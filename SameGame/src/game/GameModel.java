package game;
import java.awt.Color;

import mvcModule.Controller;
import mvcModule.SceneFactory;
import system.DrawLibrary;
import system.FontMgr;
import system.MouseFacade;
import mvcModule.Model;

public class GameModel extends Model{
	private class LevelData{
		private final int blockTypeNum;
		private final int goalCompRate;
		public LevelData(int blockTypeNum, int goalCompRate){
			this.blockTypeNum = blockTypeNum;
			this.goalCompRate = goalCompRate;
		}
	}
	public LevelData[] levelDataTable = {
			new LevelData(3, 80),
			new LevelData(4, 90),
			new LevelData(5, 80),
			new LevelData(5, 90),
			new LevelData(6, 80),
			new LevelData(6, 90),
			new LevelData(6, 100),
	};
	
	private Map map;
	private int level;
	private LevelData levelData;

	
	public GameModel(Controller.SceneChangeFacade scfacade){
		super(scfacade);

		this.level = 0;
		this.levelData = levelDataTable[this.level];
		map = new Map(Map.BIG_BLOCK_SIZE, this.levelData.blockTypeNum, this.levelData.goalCompRate, 40, 40);
	}
	
	public void update(){
//		MouseFacade mf = Controller.getMouseFacade();
//		if(mf.getMouseLeftPressCount() == 1) scfacade.sceneChange(SceneFactory.SCENE_ID.TITLE,0);
		
		switch(map.update()){
		case GAMEOVER:
			scfacade.sceneChange(SceneFactory.SCENE_ID.GAMEOVER);
			return;
		case NEXTSTAGE:
			this.level++;
			if(this.level >= levelDataTable.length) this.levelData = levelDataTable[levelDataTable.length-1];
			else this.levelData = levelDataTable[this.level];
			map = new Map(Map.BIG_BLOCK_SIZE, this.levelData.blockTypeNum, this.levelData.goalCompRate, 40, 40);
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