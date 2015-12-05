package system;
import java.applet.*;
import java.net.URL;
import java.util.ArrayList;

public class SoundMgr {
	public enum SoundId{Test};
		//�C���[�W�I�u�W�F�N�g�����ʂ���ID
	
	//�C���[�W�I�u�W�F�N�g�̊Ǘ��N���X
	private class SoundData{	//�C���[�W�f�[�^
		public final String pass;
		public final SoundId id;		//�C���[�WID
		public AudioClip ac;	//�C���[�W�I�u�W�F�N�g
		
		public SoundData(String pass, SoundId id){
			//�R���X�g���N�^
			this.id = id;
			this.pass = pass;
		}
	}
	
	//�V���O���g���p�^�[���ŋL�q
	private SoundMgr(){		
		snd_data.add(new SoundData("HeatCatch.wav", SoundId.Test));
		
		for(int i = 0; i < snd_data.size(); i++){
			URL url = getClass().getResource(snd_data.get(i).pass);
			if(snd_data.get(i).ac != null){
				snd_data.get(i).ac = Applet.newAudioClip(url);
			}
		}
	}
	
	private static SoundMgr instance = null;	//���g�̃C���X�^���X(null������
	public static void loadSound(){
		if(instance == null){	//img_mgr��null�Ȃ�A
			instance = new SoundMgr();
		}
	}
	public static SoundMgr getInstance(){
		return instance;	//�����o�ł��鎩�g�̃C���X�^���X��Ԃ�
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
