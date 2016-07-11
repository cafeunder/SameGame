package game;

import java.awt.Color;

import mvcModule.Controller;
import system.DrawLibrary;
import system.FontMgr;
import system.MouseFacade;

public class StageClear extends GameProcess {
	private Map map;
	public StageClear(Map map, GameModel model){
		super(model);
		this.map = map;
	}

	public GameProcess update() {
		MouseFacade mf = Controller.getMouseFacade();
		if(mf.getMouseLeftPressCount() == 1) {
			return new StageStart(new Map(map.level+1), this.model);
		}
		return null;
	}

	public void draw() {
		map.draw();

		DrawLibrary dLib = DrawLibrary.getInstance();
		dLib.fillRect(0, 0, 800, 600, new Color(255,255,255,128));
		dLib.drawString(200, 240, "ステージクリア！", new Color(0,0,0), FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
		dLib.drawString(200, 270, "クリックしてね", new Color(0,0,0), FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}
}
