package system;
import java.applet.*;
import java.net.URL;
import java.util.ArrayList;

public class SoundMgr {
	public enum SoundId{Test};
		//イメージオブジェクトを識別するID
	
	//イメージオブジェクトの管理クラス
	private class SoundData{	//イメージデータ
		public final String pass;
		public final SoundId id;		//イメージID
		public AudioClip ac;	//イメージオブジェクト
		
		public SoundData(String pass, SoundId id){
			//コンストラクタ
			this.id = id;
			this.pass = pass;
		}
	}
	
	//シングルトンパターンで記述
	private SoundMgr(){		
		snd_data.add(new SoundData("HeatCatch.wav", SoundId.Test));
		
		for(int i = 0; i < snd_data.size(); i++){
			URL url = getClass().getResource(snd_data.get(i).pass);
			if(snd_data.get(i).ac != null){
				snd_data.get(i).ac = Applet.newAudioClip(url);
			}
		}
	}
	
	private static SoundMgr instance = null;	//自身のインスタンス(null初期化
	public static void loadSound(){
		if(instance == null){	//img_mgrがnullなら、
			instance = new SoundMgr();
		}
	}
	public static SoundMgr getInstance(){
		return instance;	//メンバである自身のインスタンスを返す
	}
	
	private ArrayList<SoundData> snd_data = new ArrayList<SoundData>(0);
	
	public AudioClip getSoundToId(SoundId id){
		for(SoundData data : snd_data){
			if(data.id == id){
				return data.ac;
			}
		}
		
		return null;
	}
}
