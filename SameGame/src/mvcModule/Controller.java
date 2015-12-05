package mvcModule;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import mvcModule.SceneFactory.SceneData;
import system.MouseFacade;

/*
	コントローラーを表現するクラス
 */
public class Controller extends Component implements ActionListener, MouseListener, MouseMotionListener
{
	public class SceneChangeFacade {
		private Controller controller;
		
		public SceneChangeFacade(Controller controller){
			this.controller = controller;
		}
		
		public void sceneChange(SceneFactory.SCENE_ID id){
			controller.sceneChange(id);
		}
		public void sceneChange(SceneFactory.SCENE_ID id, int stage_num){
			controller.sceneChange(id,stage_num);
		}
	}	
	
	private static final long serialVersionUID = 1L;

	private Model model;	//モデル
	private Viewer view;	//ビュー
	
	private Timer timer;		//タイマー
	private MouseFacade mfacade;//マウスファサード
	
	public Controller(Viewer view){
		//各モジュールのインスタンス作成
		mfacade = new MouseFacade();
		this.view = view;
		//
		
		sceneChange(SceneFactory.SCENE_ID.TITLE);
		
		//マウスリスナーの初期化
		addMouseListener(this);
		addMouseMotionListener(this);		
		//
		
		timer = new Timer(17,this);	//タイマーセット
		timer.start();				//タイマースタート
	}
	
	public void mousePressed(MouseEvent e){
		mfacade.mousePressed(e); //マウスボタンが押されたとき
	}
	public void mouseReleased(MouseEvent e){
		mfacade.mouseReleased();//マウスボタンが離されたとき
	}
	public void mouseMoved(MouseEvent e){
		mfacade.mouseMoved(e.getPoint());//マウスが動いたとき
	}

	public void actionPerformed(ActionEvent e){	//タイマーによって呼び出される更新メソッド
		mfacade.update();	//マウスファサードの更新

		//タイマー呼び出しまでに、マウス入力があったなら、マウス処理
		if(mfacade.judgeMousePress()) {
			model.mousePressUpdate(mfacade);
		}
		if(mfacade.judgeMouseMoved()) {
			model.mouseMoveUpdate(mfacade);
		}
		//
		
		model.timerUpdate();//モデルの更新		
		view.repaint();	//タイマーによる描画
	}
	
	public void sceneChange(SceneFactory.SCENE_ID id){
		SceneData scene = SceneFactory.getSceneData(new SceneChangeFacade(this), id, 0);
		
		this.model = scene.model;
		view.setComponent(scene.view);
	}
	public void sceneChange(SceneFactory.SCENE_ID id, int stage_num){
		SceneData scene = SceneFactory.getSceneData(new SceneChangeFacade(this), id, stage_num);
		
		this.model = scene.model;
		view.setComponent(scene.view);
	}
	
	public void mouseClicked(MouseEvent e){}  // マウスボタンが短時間で押して離されたとき
	public void mouseEntered(MouseEvent e){}  // マウスカーソルがGUI部品内に入ったとき
	public void mouseExited(MouseEvent e){}   // マウスカーソルがGUI部品外に出たとき
	public void mouseDragged(MouseEvent e){} // マウスがドラッグされたとき
}