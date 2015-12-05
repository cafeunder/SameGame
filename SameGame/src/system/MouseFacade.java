package system;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseFacade {
	private Point set_point;		//�}�E�X���X�i�[����^����ꂽ���W
	private Point update_point;		//�^�C�}�[�Ăяo�����ɍX�V�������W
	private boolean left_press;			//�^�C�}�[�Ăяo���܂łɍ��N���b�N���ꂽ���ǂ���
	private boolean right_press;		//�^�C�}�[�Ăяo���܂łɉE�N���b�N���ꂽ���ǂ���
	private boolean moved;				//�^�C�}�[�Ăяo���܂łɃ}�E�X�����������ǂ���
	private int left_count;				//����������Ă���t���[����
	private int right_count;			//�E��������Ă���t���[����
	
	public MouseFacade(){
		//�e�ϐ��̏�����
		set_point = new Point(0,0);
		update_point = new Point(0,0);
		left_press = false;
		right_press = false;
		left_count = 0;
		right_count = 0;
		moved = false;
		//
	}
	
	/**
	* �^�C�}�[�ɌĂяo�����X�V���\�b�h
	**/
	public void update(){
		if(left_press == true) left_count++;	//���̃t���[���ŉ�����Ă�����A�J�E���g��i�߂�
		else left_count = 0;				//������Ă��Ȃ��Ȃ�A�J�E���g�����Z�b�g
		
		if(right_press == true) right_count++;
		else right_count = 0;
		
		update_point = set_point;	//�ʒu���X�V
	}
	
	/**
	*�}�E�X�������ꂽ�Ƃ��AMouseListener�ɌĂяo����郁�\�b�h
	**/
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1) left_press = true;
		if(e.getButton() == MouseEvent.BUTTON3) right_press = true;
	}

	/**
	*�}�E�X�������ꂽ�Ƃ��AMouseListener�ɌĂяo����郁�\�b�h
	**/
	public void mouseReleased(){
		left_press = false;
		right_press = false;
	}
	
	/**
	*�}�E�X���������Ƃ��AMouseMotionListener�ɌĂяo����郁�\�b�h
	**/
	public void mouseMoved(Point point){
		set_point = point;	//���ɒl���Z�b�g�i�X�V�̓^�C�}�[�Ăяo�����j
		moved = true;
	}

	public boolean judgeMousePress(){
		return (left_count > 0 || right_count > 0);
	}
	
	public boolean judgeMouseMoved(){
		return moved;
	}
	public void resetMouseMoved(){
		moved = false;
	}
	
	/**
	*�}�E�X�̍��{�^������������Ă���t���[������Ԃ�
	**/
	public int getMouseLeftPressCount(){
		return left_count;
	}
	/**
	*�}�E�X�̉E�{�^������������Ă���t���[������Ԃ�
	**/
	public int getMouseRightPressCount(){
		return right_count;
	}
	
	/**
	*�}�E�X�̌���x��Ԃ�
	**/
	public int getMouseX(){
		return update_point.x;
	}
	
	/**
	*�}�E�X�̌���y��Ԃ�
	**/
	public int getMouseY(){
		return update_point.y;
	}

	/**
	*�}�E�X�̌��݈ʒu��Ԃ�
	**/
	public Point getMousePoint(){
		return update_point;
	}
}
