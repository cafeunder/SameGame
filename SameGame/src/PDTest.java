import java.awt.*;
import javax.swing.*;
import mvcModule.Controller;
import mvcModule.Viewer;
import system.ImageMgr;
import system.SoundMgr;

public class PDTest extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final int SIZE_X = 800;	//�A�v���b�g��x�T�C�Y
	private static final int SIZE_Y = 600;	//�A�v���b�g��y�T�C�Y
	
	public PDTest()
	{
		ImageMgr.loadImage(this);	//�摜�t�@�C�������[�h
		SoundMgr.loadSound();
		
		//�e�R���|�[�l���g�̏�����
		Viewer view = new Viewer();
		Controller controller = new Controller(view);
		//
		
		//�T�C�Y���Z�b�g
		this.setVisible(true); //����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//�E�B���h�E������ꂽ��A�v���Z�X�I��
		this.setBackground(Color.BLACK); //�o�b�N�O���E���h�̐F�ݒ�
		this.setResizable(false); //�T�C�Y�ύX�֎~
		
		Insets insets = this.getInsets(); //���p�\�̈�̌v�Z�Ɏg��
		this.setSize(SIZE_X+insets.right,SIZE_Y+insets.top);

		controller.setSize(new Dimension(SIZE_X,SIZE_Y));		
		//
		
		//�R���|�[�l���g�̃Z�b�g
		Container cont = new Container(); //�R���e�i�𐶐�
		
		//�R���|�[�l���g���R���e�i�ɃZ�b�g
		cont.add(controller);
		cont.add(view);
		this.setLocation(100, 100);
		//
		getContentPane().add(cont);	//�R���e�i���Z�b�g
		//		
	}
	
	public static void main(String[] args){
		new PDTest();
	}
}
