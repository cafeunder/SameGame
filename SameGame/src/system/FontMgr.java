package system;

import java.awt.Font;
import java.util.ArrayList;

/*
	�t�H���g�f�[�^���Ǘ�����}�l�[�W���N���X
 */
public class FontMgr {	
	public enum FontId{ POPMENU, COMMENT, INFOMATION, OPERATE_BUTTON, MYSTATE};
		//�t�H���g�f�[�^�I�u�W�F�N�g�����ʂ���ID
	
	private class FontData{
		//�t�H���g�f�[�^
		public final FontId id;		//�t�H���gID	
		public Font font;	//�t�H���g�I�u�W�F�N�g
		
		public FontData(Font font, FontId id){
			this.id = id;
			this.font = font;
		}
	}
		
	//�V���O���g���p�^�[���ŋL�q
	private FontMgr(){
		font_data.add(new FontData(new Font("���C���I",Font.BOLD,25),FontId.POPMENU));
		font_data.add(new FontData(new Font("�l�r �S�V�b�N",Font.PLAIN,14),FontId.COMMENT));
		font_data.add(new FontData(new Font("�l�r �S�V�b�N",Font.PLAIN,16),FontId.INFOMATION));
		font_data.add(new FontData(new Font("�l�r �S�V�b�N",Font.PLAIN,20),FontId.OPERATE_BUTTON));
		font_data.add(new FontData(new Font("���C���I",Font.PLAIN,14),FontId.MYSTATE));
	}
	
	private static FontMgr instance = null;	//���g�̃C���X�^���X(null������
	public static void init(){
		//�C���X�^���X�쐬�֐�
		if(instance == null){	//img_mgr��null�Ȃ�A
			instance = new FontMgr();
		}
	}
	public static FontMgr getInstance(){
		return instance;	//�����o�ł��鎩�g�̃C���X�^���X��Ԃ�
	}


	
	private ArrayList<FontData> font_data = new ArrayList<FontData>(0);	//�t�H���g�f�[�^���X�g
	public Font getFontToId(FontId fontid){
		//ID�Ƀ}�b�`����t�H���g�I�u�W�F�N�g��Ԃ������\�b�h
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
				sb.setCharAt(i, (char) (c - '0' + '�O'));
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
				sb.setCharAt(i, (char) (c - '0' + '�O'));
			}
		}
		return sb.toString();
	}	
}