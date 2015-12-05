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
	public enum ImgId{BACK_STAGE1, MYCHAR, MAP_STAGE1,
		SPOT_FRAME, SPOT_STATE, SPOT_FIRE, SPOT_WIND, SPOT_ICE, SPOT_THUNDER,
		ENEMY_MUMAR,ENEMY_MUMAG,ENEMY_MUMAB,ENEMY_MUMAY,ENEMY_MUMAW, WINDOW_SPOT,
		WINDOW_COMMENT,WINDOW_INFO,WINDOW_MYSTATE,WINDOW_WAVE,WINDOW_OPERATE,WINDOW_MAIN,GUIDE_DISABLE,
		BULLET,ENEMY_EVILFISHR,ENEMY_EVILFISHG,ENEMY_EVILFISHB, ENEMY_EVILFISHY, ENEMY_EVILFISHW, ENEMY_HITODAMAR,
		ENEMY_HITODAMAG, ENEMY_HITODAMAB, ENEMY_HITODAMAY, ENEMY_HITODAMAW, EX_MEMORY,EX_GUARD, EX_ATTACK,ENEMY_WHIRLWINDR, ENEMY_WHIRLWINDG, ENEMY_WHIRLWINDB,
		ENEMY_WHIRLWINDY, ENEMY_WHIRLWINDW,	STATUS_WEAKNESS, STATUS_SLOW, PARTS_NORMAL, PARTS_LETHAL, PARTS_BREAK, PARTS_GUARD,WINDOW_PARTS,
		STATUS_DOT, MAP_STAGE2, BACK_STAGE2, ENEMY_NIGHTWALKER2, LIFE_NUMBER, WINDOW_ENERGY, BIG_NUMBER
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
		img_data.add(new ImageData("img/mychar.png",ImgId.MYCHAR));
		img_data.add(new ImageData("img/stage1.png",ImgId.MAP_STAGE1));
		img_data.add(new ImageData("img/stage1_back.png",ImgId.BACK_STAGE1));
		img_data.add(new ImageData("img/stage2.png",ImgId.MAP_STAGE2));
		img_data.add(new ImageData("img/stage2_back.png",ImgId.BACK_STAGE2));
		img_data.add(new ImageData("img/sframe.png",ImgId.SPOT_FRAME));
		img_data.add(new ImageData("img/state.png",ImgId.SPOT_STATE));
		img_data.add(new ImageData("img/sfire.png",ImgId.SPOT_FIRE));
		img_data.add(new ImageData("img/swind.png",ImgId.SPOT_WIND));
		img_data.add(new ImageData("img/sice.png",ImgId.SPOT_ICE));
		img_data.add(new ImageData("img/sthunder.png",ImgId.SPOT_THUNDER));
		img_data.add(new ImageData("img/mumaR.png",ImgId.ENEMY_MUMAR));
		img_data.add(new ImageData("img/mumaG.png",ImgId.ENEMY_MUMAG));
		img_data.add(new ImageData("img/mumaB.png",ImgId.ENEMY_MUMAB));
		img_data.add(new ImageData("img/mumaY.png",ImgId.ENEMY_MUMAY));
		img_data.add(new ImageData("img/mumaW.png",ImgId.ENEMY_MUMAW));
		img_data.add(new ImageData("img/window_spot.png",ImgId.WINDOW_SPOT));
		img_data.add(new ImageData("img/window_message.png",ImgId.WINDOW_COMMENT));
		img_data.add(new ImageData("img/window_info.png",ImgId.WINDOW_INFO));
		img_data.add(new ImageData("img/window_mystate.png",ImgId.WINDOW_MYSTATE));
		img_data.add(new ImageData("img/window_operate.png",ImgId.WINDOW_OPERATE));
		img_data.add(new ImageData("img/window_wave.png",ImgId.WINDOW_WAVE));
		img_data.add(new ImageData("img/window_main.png",ImgId.WINDOW_MAIN));
		img_data.add(new ImageData("img/disable.png",ImgId.GUIDE_DISABLE));
		img_data.add(new ImageData("img/bullet.png",ImgId.BULLET));
		img_data.add(new ImageData("img/evilfishR.png",ImgId.ENEMY_EVILFISHR));
		img_data.add(new ImageData("img/evilfishG.png",ImgId.ENEMY_EVILFISHG));
		img_data.add(new ImageData("img/evilfishB.png",ImgId.ENEMY_EVILFISHB));
		img_data.add(new ImageData("img/evilfishY.png",ImgId.ENEMY_EVILFISHY));
		img_data.add(new ImageData("img/evilfishW.png",ImgId.ENEMY_EVILFISHW));
		img_data.add(new ImageData("img/hitodamaR.png",ImgId.ENEMY_HITODAMAR));
		img_data.add(new ImageData("img/hitodamaG.png",ImgId.ENEMY_HITODAMAG));
		img_data.add(new ImageData("img/hitodamaB.png",ImgId.ENEMY_HITODAMAB));
		img_data.add(new ImageData("img/hitodamaY.png",ImgId.ENEMY_HITODAMAY));
		img_data.add(new ImageData("img/hitodamaW.png",ImgId.ENEMY_HITODAMAW));
		img_data.add(new ImageData("img/ex_memory.png",ImgId.EX_MEMORY));
		img_data.add(new ImageData("img/ex_attack.png",ImgId.EX_ATTACK));
		img_data.add(new ImageData("img/ex_guard.png",ImgId.EX_GUARD));
		img_data.add(new ImageData("img/machmanR.png",ImgId.ENEMY_WHIRLWINDR));
		img_data.add(new ImageData("img/machmanG.png",ImgId.ENEMY_WHIRLWINDG));
		img_data.add(new ImageData("img/machmanB.png",ImgId.ENEMY_WHIRLWINDB));
		img_data.add(new ImageData("img/machmanY.png",ImgId.ENEMY_WHIRLWINDY));
		img_data.add(new ImageData("img/machmanW.png",ImgId.ENEMY_WHIRLWINDW));
		img_data.add(new ImageData("img/slow.png",ImgId.STATUS_SLOW));
		img_data.add(new ImageData("img/weakness.png",ImgId.STATUS_WEAKNESS));
		img_data.add(new ImageData("img/sbreak.png",ImgId.PARTS_BREAK));
		img_data.add(new ImageData("img/slethal.png",ImgId.PARTS_LETHAL));
		img_data.add(new ImageData("img/sguard.png",ImgId.PARTS_GUARD));
		img_data.add(new ImageData("img/snormal.png",ImgId.PARTS_NORMAL));
		img_data.add(new ImageData("img/window_parts.png",ImgId.WINDOW_PARTS));
		img_data.add(new ImageData("img/dot.png",ImgId.STATUS_DOT));
		img_data.add(new ImageData("img/number.png",ImgId.LIFE_NUMBER));
		img_data.add(new ImageData("img/window_energy.png",ImgId.WINDOW_ENERGY));
		img_data.add(new ImageData("img/BigNumber.png",ImgId.BIG_NUMBER));
		
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
