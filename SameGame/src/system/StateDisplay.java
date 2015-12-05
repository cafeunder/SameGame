package system;

import java.awt.Image;
import java.util.*;

/*
	ステータス描画に用いるクラス
*/
public class StateDisplay {
	//シングルトンで記述
	private static final int CHARACTER_WIDTH = 3;
	private static final int CHARACTER_ADJX = -2;
	private static final int CHARACTER_ADJY = -2;
	
	private final Image[] imgs;	//ステータス描画用イメージ配列
	private static StateDisplay instance = null;	//インスタンス
	
	private StateDisplay(){
		ImageMgr imgmgr = ImageMgr.getInstance();
		
		Image temp = imgmgr.getImageToId(ImageMgr.ImgId.SPOT_STATE);
		
		imgs = imgmgr.getDivImage(temp, 12, 1, 5, 7);
	}
	
	public static StateDisplay getInstance(){
		if(instance == null){
			instance = new StateDisplay();
		}
		
		return instance;
	}
	
	public void drawStateNum(int num, int px, int py, int space){
		//数字列を描画するメソッド
		ArrayList<Integer> draw_num = new ArrayList<Integer>(0);	//実際に描画する数字セット
		
		while(num != 0){
			draw_num.add(num%10);	//１の位をリストにセット
			num/=10;				//位を１つ下げる
		}
		//この時点で、draw_numには、描画する値が「１の位から順に」格納されている
		//描画する際は、もっとも大きい位から描画されなければならないので、
		
		Collections.reverse(draw_num);	//反転

		DrawLibrary imglib = DrawLibrary.getInstance();
		for(int value : draw_num){
			imglib.drawImage(px+CHARACTER_ADJX, py+CHARACTER_ADJY, imgs[value]);
			px+=space+CHARACTER_WIDTH;
		}
	}
	
	public void drawStateSign(int value, int px, int py){
		DrawLibrary imglib = DrawLibrary.getInstance();
		for(int i = 0; i < value; i++){
			imglib.drawImage(px+CHARACTER_ADJX, py+CHARACTER_ADJY, imgs[10]);
			px+=4;
		}
	}
	
	public void drawMoney(int cost, int px, int py, int space){
		//数字列を描画するメソッド
		ArrayList<Integer> draw_num = new ArrayList<Integer>(0);	//実際に描画する数字セット
		
		while(cost != 0){
			draw_num.add(cost%10);	//１の位をリストにセット
			cost/=10;				//位を１つ下げる
		}
		//この時点で、draw_numには、描画する値が「１の位から順に」格納されている
		//描画する際は、もっとも大きい位から描画されなければならないので、
		
		Collections.reverse(draw_num);	//反転

		DrawLibrary imglib = DrawLibrary.getInstance();
		
		imglib.drawImage(px+CHARACTER_ADJX, py+CHARACTER_ADJY, imgs[11]);
		px+=space+CHARACTER_WIDTH;
		
		if(draw_num.size() < 3) px+=space+CHARACTER_WIDTH;

		for(int value : draw_num){
			imglib.drawImage(px+CHARACTER_ADJX, py+CHARACTER_ADJY, imgs[value]);
			px+=space+CHARACTER_WIDTH;
		}
	}
}
