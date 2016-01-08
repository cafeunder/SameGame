package system;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

import javax.swing.JFrame;

/*
	�C���[�W�f�[�^���Ǘ�����}�l�[�W���N���X
*/
public class ImageMgr {	
	public enum ImgId{
		TIP_RED, TIP_GREEN, TIP_BLUE, TIP_YELLOW, TIP_CYAN, TIP_VIOLET
	};
		//�C���[�W�I�u�W�F�N�g�����ʂ���ID
	
	//�C���[�W�I�u�W�F�N�g�̊Ǘ��N���X
	private class ImageData{	//�C���[�W�f�[�^
		public final String pass;
		public final ImgId id;		//�C���[�WID
		public Image img;	//�C���[�W�I�u�W�F�N�g
		
		public ImageData(String pass, ImgId id){
			//�R���X�g���N�^
			this.id = id;
			this.pass = pass;
		}
	}
	
	//�V���O���g���p�^�[���ŋL�q
	private ImageMgr(JFrame jap){
		this.jap = jap;
		
		//�C���[�W�\�[�X�ւ̃p�X����
		img_data.add(new ImageData("img/TEMP_RED.png",ImgId.TIP_RED));
		img_data.add(new ImageData("img/TEMP_GREEN.png",ImgId.TIP_GREEN));
		img_data.add(new ImageData("img/TEMP_BLUE.png",ImgId.TIP_BLUE));
		img_data.add(new ImageData("img/TEMP_YELLOW.png",ImgId.TIP_YELLOW));
		img_data.add(new ImageData("img/TEMP_CYAN.png",ImgId.TIP_CYAN));
		img_data.add(new ImageData("img/TEMP_VIOLET.png",ImgId.TIP_VIOLET));

		MediaTracker tracker = new MediaTracker(jap);	//���f�B�A�g���b�J�[
		
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
	
	private static ImageMgr instance = null;	//���g�̃C���X�^���X(null������
		//static�ŃC���X�^���X�����Ȃ��̂́A�g���b�J�[���󂯎��K�v�����邽��
	public static void loadImage(JFrame jap){
		//�C���X�^���X�쐬�֐�
		
		if(instance == null){	//img_mgr��null�Ȃ�A
			instance = new ImageMgr(jap);
				//�g���b�J�[��^���ăC���X�^���X����
		}
	}
	public static ImageMgr getInstance(){
		return instance;	//�����o�ł��鎩�g�̃C���X�^���X��Ԃ�
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
	* �摜�𕪊����Ĕz��փZ�b�g���郁�\�b�h
	* @param img Image�I�u�W�F�N�g
	* @param x_num x������
	* @param y_num y������
	* @param width �����C���[�W�̉���
	* @param height �����C���[�W�̏c��
	**/
	public Image[] getDivImage(Image img, int x_num, int y_num, int width, int height){
		Image[] divimg = new Image[x_num*y_num];
			//�C���[�W�z��𐶐�

		for(int x = 0; x < x_num; x++){
			for(int y = 0; y < y_num; y++){
				CropImageFilter cfilter = new CropImageFilter(x*width, y*height, width, height);
					//�؂���i=crop)�t�B���^�[�𐶐�
				FilteredImageSource producer = new FilteredImageSource(img.getSource(),cfilter);
					//�t�B���^�C���[�W�̃v���f���[�T�[�𐶐�
				divimg[y + y_num*x] = jap.createImage(producer);
					//�v���f���[�T�[�ɏ]���ĐV�����C���[�W�𐶐��A�z��փZ�b�g
			}
		}
		
		return divimg;
	}
}
