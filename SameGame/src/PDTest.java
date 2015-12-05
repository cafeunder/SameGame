import java.awt.*;
import javax.swing.*;
import mvcModule.Controller;
import mvcModule.Viewer;
import system.ImageMgr;
import system.SoundMgr;

public class PDTest extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final int SIZE_X = 800;	//アプレットのxサイズ
	private static final int SIZE_Y = 600;	//アプレットのyサイズ
	
	public PDTest()
	{
		ImageMgr.loadImage(this);	//画像ファイルをロード
		SoundMgr.loadSound();
		
		//各コンポーネントの初期化
		Viewer view = new Viewer();
		Controller controller = new Controller(view);
		//
		
		//サイズをセット
		this.setVisible(true); //可視化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//ウィンドウが閉じられたら、プロセス終了
		this.setBackground(Color.BLACK); //バックグラウンドの色設定
		this.setResizable(false); //サイズ変更禁止
		
		Insets insets = this.getInsets(); //利用可能領域の計算に使う
		this.setSize(SIZE_X+insets.right,SIZE_Y+insets.top);

		controller.setSize(new Dimension(SIZE_X,SIZE_Y));		
		//
		
		//コンポーネントのセット
		Container cont = new Container(); //コンテナを生成
		
		//コンポーネントをコンテナにセット
		cont.add(controller);
		cont.add(view);
		this.setLocation(100, 100);
		//
		getContentPane().add(cont);	//コンテナをセット
		//		
	}
	
	public static void main(String[] args){
		new PDTest();
	}
}
