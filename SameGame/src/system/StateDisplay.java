package system;

import java.awt.Image;
import java.util.*;

/*
	�X�e�[�^�X�`��ɗp����N���X
*/
public class StateDisplay {
	//�V���O���g���ŋL�q
	private static final int CHARACTER_WIDTH = 3;
	private static final int CHARACTER_ADJX = -2;
	private static final int CHARACTER_ADJY = -2;
	
	private final Image[] imgs;	//�X�e�[�^�X�`��p�C���[�W�z��
	private static StateDisplay instance = null;	//�C���X�^���X
	
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
		//�������`�悷�郁�\�b�h
		ArrayList<Integer> draw_num = new ArrayList<Integer>(0);	//���ۂɕ`�悷�鐔���Z�b�g
		
		while(num != 0){
			draw_num.add(num%10);	//�P�̈ʂ����X�g�ɃZ�b�g
			num/=10;				//�ʂ��P������
		}
		//���̎��_�ŁAdraw_num�ɂ́A�`�悷��l���u�P�̈ʂ��珇�Ɂv�i�[����Ă���
		//�`�悷��ۂ́A�����Ƃ��傫���ʂ���`�悳��Ȃ���΂Ȃ�Ȃ��̂ŁA
		
		Collections.reverse(draw_num);	//���]

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
		//�������`�悷�郁�\�b�h
		ArrayList<Integer> draw_num = new ArrayList<Integer>(0);	//���ۂɕ`�悷�鐔���Z�b�g
		
		while(cost != 0){
			draw_num.add(cost%10);	//�P�̈ʂ����X�g�ɃZ�b�g
			cost/=10;				//�ʂ��P������
		}
		//���̎��_�ŁAdraw_num�ɂ́A�`�悷��l���u�P�̈ʂ��珇�Ɂv�i�[����Ă���
		//�`�悷��ۂ́A�����Ƃ��傫���ʂ���`�悳��Ȃ���΂Ȃ�Ȃ��̂ŁA
		
		Collections.reverse(draw_num);	//���]

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
