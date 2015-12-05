package system;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;


/*
	描画動作を提供するライブラリ
 */
public class DrawLibrary{
	//シングルトンで記述
	private static DrawLibrary instance = null;	//インスタンス
	private DrawLibrary(JPanel jp, Graphics2D g2d){
		this.jp = jp;
		this.g2d = g2d;
	}
	public static DrawLibrary getInstance(){
		return instance;
	}
	public static void init(JPanel jp, Graphics2D g2d){
		instance = new DrawLibrary(jp, g2d);
	}
	
	private JPanel jp;	//ビュー
	private Graphics2D g2d;	//Graphics2Dオブジェクト

	/**
	 * 直線を描画するメソッド
	 * @param x1 始点のx座標
	 * @param y1 始点のy座標
	 * @param x2 終点のx座標
	 * @param y2 終点のy座標
	 * @param c 色
	 */
	public void drawLine(int x1, int y1, int x2, int y2, Color c){
		g2d.setColor(c);
		g2d.drawLine(x1, y1, x2, y2);
	}
	/**
	* 画像を描画するメソッド
	* @param x 描画x座標
	* @param y 描画y座標
	* @param img Imageオブジェクト
	**/
	public void drawImage(int x, int y, Image img){
		g2d.drawImage(img, x, y, jp);
	}
	/**
	* アルファブレンドで画像を描画するメソッド
	* @param x 描画x座標
	* @param y 描画y座標
	* @param img Imageオブジェクト
	* @param param アルファパラメータ(0.0~1.0)
	**/
	public void drawImageAlphaBlend(int x, int y, Image img, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//アルファチャンネルの値をセット
		g2d.drawImage(img, x, y, jp);		//画像を描画
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}

	/**
	* 円を描画するメソッド
	* @param x 描画中心x座標
	* @param y 描画中心y座標
	* @param radius 半径
	* @param color Colorオブジェクト
	* @param fill 塗りつぶすかどうか
	**/
	public void drawCircle(int x, int y, int radius, Color color, boolean fill){
		int rx = x - radius;
		int ry = y - radius;
		
		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
	}
	
	/**
	* 円をアルファブレンドで描画するメソッド
	* @param x 描画中心x座標
	* @param y 描画中心y座標
	* @param radius 半径
	* @param color Colorオブジェクト
	* @param fill 塗りつぶすかどうか
	* @param param アルファパラメータ(0.0~1.0)
	**/
	public void drawCircleAlphaBlend(int x, int y, int radius, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//アルファチャンネルの値をセット

		int rx = x - radius;
		int ry = y - radius;                                                                                                                                                                                                                    
		
		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}
	
	/**
	* 四角形を描画するメソッド
	* @param x 描画x座標
	* @param y 描画y座標
	* @param width 四角形の横幅
	* @param height 四角形の縦幅
	* @param color Colorオブジェクト
	* @param fill 塗りつぶすかどうか
	**/
	public void drawRect(int x, int y, int width, int height, Color color, boolean fill){
		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillRect(x, y, width, height);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawRect(x, y, width, height);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
	}
	
	/**
	* アルファブレンドで四角形を描画するメソッド
	* @param x 描画x座標
	* @param y 描画y座標
	* @param width 四角形の横幅
	* @param height 四角形の縦幅
	* @param fill 塗りつぶすかどうか
	* @param param アルファパラメータ(0.0~1.0)
	**/
	public void drawRectAlphaBlend(int x, int y, int width, int height, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//アルファチャンネルの値をセット

		Color temp = g2d.getColor();	//色を記憶
		g2d.setColor(color);	//引数の色をセット
		
		if(fill) g2d.fillRect(x, y, width, height);	//ぬりつぶすならfillRectメソッドを、
		else g2d.drawRect(x, y, width, height);		//ぬりつぶさないならdrawRectメソッドを呼び出す

		g2d.setColor(temp);		//色をリセット
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//アルファチャンネルの値をリセット
	}
	
	/**
	* 画像を回転して描画するメソッド
	* @param x 描画中心x座標（回転の支点）
	* @param y 描画中心y座標（回転の支点）
	* @param img Imageオブジェクト
	* @param angle 回転ラジアン角
	**/
	public void drawImageRotate(int x, int y, Image img, float angle, boolean inter){
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.rotate(angle, x, y);	//座標を回転
		g2d.drawImage(img, x-img.getWidth(jp)/2, y-img.getHeight(jp)/2, jp);	//画像を描画
		g2d.setTransform(new AffineTransform());
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	/**
	* 画像を拡縮して描画するメソッド
	* @param x 描画中心x座標（拡縮の支点）
	* @param y 描画中心y座標（拡縮の支点）
	* @param img Imageオブジェクト
	* @param rate 拡縮倍率
	* @param inter 補間を行うかどうか
	**/
	public void drawImageExtend(int x, int y, Image img, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(jp)*rate);
		int ex_h = (int)(img.getHeight(jp)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//バイリニア補間で描画
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jp);	//画像を拡大描画
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//ニアレスト補間に戻す
		
	}

	/**
	* 画像を拡縮・回転して描画するメソッド
	* @param x 描画中心x座標（拡縮・回転の支点）
	* @param y 描画中心y座標（拡縮・回転の支点）
	* @param img Imageオブジェクト
	* @param angle 回転ラジアン角
	* @param rate 拡縮倍率
	* @param inter 補間を行うかどうか
	**/
	public void drawImageRotateExtend(int x, int y, Image img, float angle, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(jp)*rate);
		int ex_h = (int)(img.getHeight(jp)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//バイリニア補間で描画
		g2d.rotate(angle, x, y);	//座標を回転
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jp);	//画像を拡大描画
		g2d.setTransform(new AffineTransform());

		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//ニアレスト補間に戻す		
	}
	
	/**
	 * 文字列を描画するメソッド
	 * @param x 描画x座標
	 * @param y 描画y座標
	 * @param str 文字列
	 * @param color 色
	 * @param font フォントデータ
	 * @param antialias アンチエイリアス
	 */
	public void drawString(int x, int y, String str, Color color, Font font, boolean antialias){
		g2d.setColor(color);	//色をセット
		g2d.setFont(font);		//フォントをセット
		FontMetrics fm = g2d.getFontMetrics();	//フォントメトリクスを受け取る
		y+=fm.getAscent();	//アセントサイズ分下へ
		
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString(str, x, y);
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
	
	/**
	* フォントのメトリクスを返すメソッド
	* @param font Fontオブジェクト
	**/	
	public FontMetrics getFontMetrics(Font font){
		return g2d.getFontMetrics(font);
	}
	
	public void SetPosition(int x, int y){
		g2d.translate(x , y);
	}
}
