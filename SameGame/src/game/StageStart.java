package game;

import java.awt.Color;

import mvcModule.Controller;
import system.DrawLibrary;
import system.FontMgr;
import system.MouseFacade;

public class StageStart extends GameProcess {
	private Map map;
	public StageStart(Map map, GameModel model){
		super(model);
		this.map = map;
	}
	public GameProcess update(){
		MouseFacade mf = Controller.getMouseFacade();
		if(mf.getMouseLeftPressCount() == 1) {
			this.map.setEnable(true);
			return new GamePlay(this.map, this.model);
		}
		return null;
	}

	public void draw(){
		map.draw();

		DrawLibrary dLib = DrawLibrary.getInstance();
		dLib.fillRect(0, 0, 800, 600, new Color(255,255,255,128));
		dLib.drawString(200, 240, "ステージスタート！", new Color(0,0,0), FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
		dLib.drawString(200, 270, "クリックしてね", new Color(0,0,0), FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}
}
