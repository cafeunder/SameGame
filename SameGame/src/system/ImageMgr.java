package system;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

import javax.swing.JFrame;

/*
	イメージデータを管理するマネージャクラス
*/
public class ImageMgr {
	public enum ImgId{
		TIP_RED, TIP_GREEN, TIP_BLUE, TIP_YELLOW, TIP_CYAN, TIP_VIOLET
	};
		//イメージオブジェクトを識別するID

	//イメージオブジェクトの管理クラス
	private class ImageData{	//イメージデータ
		public final String pass;
		public final ImgId id;		//イメージID
		public Image img;	//イメージオブジェクト

		public ImageData(String pass, ImgId id){
			//コンストラクタ
			this.id = id;
			this.pass = pass;
		}
	}

	//シングルトンパターンで記述
	private ImageMgr(JFrame jap){
		this.jap = jap;

		//イメージソースへのパスを代入
		img_data.add(new ImageData("img/TEMP_RED.png",ImgId.TIP_RED));
		img_data.add(new ImageData("img/TEMP_GREEN.png",ImgId.TIP_GREEN));
		img_data.add(new ImageData("img/TEMP_BLUE.png",ImgId.TIP_BLUE));
		img_data.add(new ImageData("img/TEMP_YELLOW.png",ImgId.TIP_YELLOW));
		img_data.add(new ImageData("img/TEMP_CYAN.png",ImgId.TIP_CYAN));
		img_data.add(new ImageData("img/TEMP_VIOLET.png",ImgId.TIP_VIOLET));

		MediaTracker tracker = new MediaTracker(jap);	//メディアトラッカー

		Toolkit tk = Toolkit.getDefaultToolkit();
		int i = 1;
		for(ImageData data : img_data){
			data.img = tk.createImage(data.pass);
			tracker.addImage(data.img, i);

			i++;
		}

	    try {
	    	tracker.waitForAll();
	    } catch (InterruptedException e) {}
	}

	private static ImageMgr instance = null;	//自身のインスタンス(null初期化
		//staticでインスタンスを作らないのは、トラッカーを受け取る必要があるため
	public static void loadImage(JFrame jap){
		//インスタンス作成関数

		if(instance == null){	//img_mgrがnullなら、
			instance = new ImageMgr(jap);
				//トラッカーを与えてインスタンス生成
		}
	}
	public static ImageMgr getInstance(){
		return instance;	//メンバである自身のインスタンスを返す
	}

	private JFrame jap;
	private ArrayList<ImageData> img_data = new ArrayList<ImageData>(0);

	public Image getImageToId(ImgId imgid){
		for(ImageData data : img_data){
			if(data.id == imgid){
				return data.img;
			}
		}

		return null;
	}

	/**
	* 画像を分割して配列へセットするメソッド
	* @param img Imageオブジェクト
	* @param x_num x分割数
	* @param y_num y分割数
	* @param width 分割イメージの横幅
	* @param height 分割イメージの縦幅
	**/
	public Image[] getDivImage(Image img, int x_num, int y_num, int width, int height){
		Image[] divimg = new Image[x_num*y_num];
			//イメージ配列を生成

		for(int x = 0; x < x_num; x++){
			for(int y = 0; y < y_num; y++){
				CropImageFilter cfilter = new CropImageFilter(x*width, y*height, width, height);
					//切り取り（=crop)フィルターを生成
				FilteredImageSource producer = new FilteredImageSource(img.getSource(),cfilter);
					//フィルタイメージのプロデューサーを生成
				divimg[y + y_num*x] = jap.createImage(producer);
					//プロデューサーに従って新しいイメージを生成、配列へセット
			}
		}

		return divimg;
	}
}
