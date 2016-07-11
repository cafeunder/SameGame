package gameOver;

import java.awt.Color;

import mvcModule.Controller;
import mvcModule.ViewerComponent;
import system.DrawLibrary;
import system.FontMgr;

public class GameOverViewerComponent extends ViewerComponent{
	public GameOverViewerComponent(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}

	public void draw(){
		DrawLibrary drawlib = DrawLibrary.getInstance();
		drawlib.drawString(200, 240, "ゲームオーバー画面", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
		drawlib.drawString(200, 280, "クリックでタイトルへ", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}
}