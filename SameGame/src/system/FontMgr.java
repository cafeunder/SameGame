package system;

import java.awt.Font;
import java.util.ArrayList;

/*
	フォントデータを管理するマネージャクラス
 */
public class FontMgr {	
	public enum FontId{ POPMENU, COMMENT, INFOMATION, OPERATE_BUTTON, MYSTATE};
		//フォントデータオブジェクトを識別するID
	
	private class FontData{
		//フォントデータ
		public final FontId id;		//フォントID	
		public Font font;	//フォントオブジェクト
		
		public FontData(Font font, FontId id){
			this.id = id;
			this.font = font;
		}
	}
		
	//シングルトンパターンで記述
	private FontMgr(){
		font_data.add(new FontData(new Font("メイリオ",Font.BOLD,25),FontId.POPMENU));
		font_data.add(new FontData(new Font("ＭＳ ゴシック",Font.PLAIN,14),FontId.COMMENT));
		font_data.add(new FontData(new Font("ＭＳ ゴシック",Font.PLAIN,16),FontId.INFOMATION));
		font_data.add(new FontData(new Font("ＭＳ ゴシック",Font.PLAIN,20),FontId.OPERATE_BUTTON));
		font_data.add(new FontData(new Font("メイリオ",Font.PLAIN,14),FontId.MYSTATE));
	}
	
	private static FontMgr instance = null;	//自身のインスタンス(null初期化
	public static void init(){
		//インスタンス作成関数
		if(instance == null){	//img_mgrがnullなら、
			instance = new FontMgr();
		}
	}
	public static FontMgr getInstance(){
		return instance;	//メンバである自身のインスタンスを返す
	}


	
	private ArrayList<FontData> font_data = new ArrayList<FontData>(0);	//フォントデータリスト
	public Font getFontToId(FontId fontid){
		//IDにマッチするフォントオブジェクトを返すをメソッド
		for(FontData data : font_data){
			if(data.id == fontid){
				return data.font;
			}
		}
		
		return null;
	}
	
	public static String integerToZenkakuString(int integer) {
		String str = Integer.toString(integer);
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}
	
	public static String floatToZenkakuString(float integer) {
		String str = Float.toString(integer);
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}	
}