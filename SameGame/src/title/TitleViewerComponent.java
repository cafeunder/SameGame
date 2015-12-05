package title;

import java.awt.Color;
import system.DrawLibrary;
import system.FontMgr;
import mvcModule.Controller;
import mvcModule.ViewerComponent;

public class TitleViewerComponent extends ViewerComponent{
	public TitleViewerComponent(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}
	
	public void draw(){
		DrawLibrary drawlib = DrawLibrary.getInstance();
		drawlib.drawString(200, 240, "さめがめタイトル画面〜〜", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}
}
