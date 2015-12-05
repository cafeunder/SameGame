package mvcModule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import system.DrawLibrary;
import system.FontMgr;

public class Viewer extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage bf;	//ダブルバッファリング用イメージ
	private ViewerComponent viewComponent;

	public Viewer(){
		bf = new BufferedImage(800,600,BufferedImage.TYPE_INT_ARGB);	//ダブルバッファの領域を確保
		
		setSize(new Dimension(800,600));
		
		DrawLibrary.init(this, (Graphics2D)bf.getGraphics());	//描画ライブラリの初期化
		FontMgr.init();			//フォントデータをロード
	}

    public void update(Graphics g){
    	//オーバーライドしたJPanelのupdateメソッド
    	paint(g);
    }
    
	public void paintComponent(Graphics g){
		//描画メソッド
		Graphics bg = bf.getGraphics();
		
		//背景の描画
		bg.setColor(Color.BLACK);
		bg.fillRect(0, 0, 800, 600);
		//
		
		//ここに本体を書く
		viewComponent.draw();
		//
		
		g.drawImage(bf, 0, 0, this);
	}

	public void setComponent(ViewerComponent viewComponent){
		this.viewComponent = viewComponent;
	}
}
