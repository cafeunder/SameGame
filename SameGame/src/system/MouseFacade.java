package system;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseFacade {
	private Point set_point;		//マウスリスナーから与えられた座標
	private Point update_point;		//タイマー呼び出し時に更新した座標
	private boolean left_press;			//タイマー呼び出しまでに左クリックされたかどうか
	private boolean right_press;		//タイマー呼び出しまでに右クリックされたかどうか
	private boolean moved;				//タイマー呼び出しまでにマウスが動いたかどうか
	private int left_count;				//左が押されているフレーム数
	private int right_count;			//右が押されているフレーム数
	
	public MouseFacade(){
		//各変数の初期化
		set_point = new Point(0,0);
		update_point = new Point(0,0);
		left_press = false;
		right_press = false;
		left_count = 0;
		right_count = 0;
		moved = false;
		//
	}
	
	/**
	* タイマーに呼び出される更新メソッド
	**/
	public void update(){
		if(left_press == true) left_count++;	//そのフレームで押されていたら、カウントを進める
		else left_count = 0;				//押されていないなら、カウントをリセット
		
		if(right_press == true) right_count++;
		else right_count = 0;
		
		update_point = set_point;	//位置を更新
	}
	
	/**
	*マウスが押されたとき、MouseListenerに呼び出されるメソッド
	**/
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1) left_press = true;
		if(e.getButton() == MouseEvent.BUTTON3) right_press = true;
	}

	/**
	*マウスが離されたとき、MouseListenerに呼び出されるメソッド
	**/
	public void mouseReleased(){
		left_press = false;
		right_press = false;
	}
	
	/**
	*マウスが動いたとき、MouseMotionListenerに呼び出されるメソッド
	**/
	public void mouseMoved(Point point){
		set_point = point;	//仮に値をセット（更新はタイマー呼び出し時）
		moved = true;
	}

	public boolean judgeMousePress(){
		return (left_count > 0 || right_count > 0);
	}
	
	public boolean judgeMouseMoved(){
		return moved;
	}
	public void resetMouseMoved(){
		moved = false;
	}
	
	/**
	*マウスの左ボタンが押入されているフレーム数を返す
	**/
	public int getMouseLeftPressCount(){
		return left_count;
	}
	/**
	*マウスの右ボタンが押入されているフレーム数を返す
	**/
	public int getMouseRightPressCount(){
		return right_count;
	}
	
	/**
	*マウスの現在xを返す
	**/
	public int getMouseX(){
		return update_point.x;
	}
	
	/**
	*マウスの現在yを返す
	**/
	public int getMouseY(){
		return update_point.y;
	}

	/**
	*マウスの現在位置を返す
	**/
	public Point getMousePoint(){
		return update_point;
	}
}
