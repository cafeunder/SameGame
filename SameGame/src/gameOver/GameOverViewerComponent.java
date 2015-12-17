package gameOver;

import java.awt.Color;
import system.DrawLibrary;
import system.FontMgr;
import mvcModule.Controller;
import mvcModule.ViewerComponent;

public class GameOverViewerComponent extends ViewerComponent{
	public GameOverViewerComponent(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}
	
	public void draw(){
		DrawLibrary drawlib = DrawLibrary.getInstance();
		drawlib.drawString(200, 240, "�Q�[���I�[�o�[���", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
		drawlib.drawString(200, 280, "�N���b�N�Ń^�C�g����", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}
}