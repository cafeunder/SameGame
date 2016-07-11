package title;

import java.awt.Color;

import mvcModule.Controller;
import mvcModule.ViewerComponent;
import system.DrawLibrary;
import system.FontMgr;

public class TitleViewerComponent extends ViewerComponent{
	public TitleViewerComponent(Controller.SceneChangeFacade scfacade){
		super(scfacade);
	}

	public void draw(){
		DrawLibrary drawlib = DrawLibrary.getInstance();
		drawlib.drawString(200, 240, "さめがめタイトル画面～～", Color.WHITE, FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}
}