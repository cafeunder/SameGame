package mvcModule;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import mvcModule.SceneFactory.SceneData;
import system.MouseFacade;

/*
	�R���g���[���[��\������N���X
 */
public class Controller extends Component implements ActionListener, MouseListener, MouseMotionListener
{
	public class SceneChangeFacade {
		private Controller controller;
		
		public SceneChangeFacade(Controller controller){
			this.controller = controller;
		}
		
		public void sceneChange(SceneFactory.SCENE_ID id){
			controller.sceneChange(id);
		}
		public void sceneChange(SceneFactory.SCENE_ID id, int stage_num){
			controller.sceneChange(id,stage_num);
		}
	}	
	
	private static final long serialVersionUID = 1L;

	private Model model;	//���f��
	private Viewer view;	//�r���[
	
	private Timer timer;		//�^�C�}�[
	private MouseFacade mfacade;//�}�E�X�t�@�T�[�h
	
	public Controller(Viewer view){
		//�e���W���[���̃C���X�^���X�쐬
		mfacade = new MouseFacade();
		this.view = view;
		//
		
		sceneChange(SceneFactory.SCENE_ID.TITLE);
		
		//�}�E�X���X�i�[�̏�����
		addMouseListener(this);
		addMouseMotionListener(this);		
		//
		
		timer = new Timer(17,this);	//�^�C�}�[�Z�b�g
		timer.start();				//�^�C�}�[�X�^�[�g
	}
	
	public void mousePressed(MouseEvent e){
		mfacade.mousePressed(e); //�}�E�X�{�^���������ꂽ�Ƃ�
	}
	public void mouseReleased(MouseEvent e){
		mfacade.mouseReleased();//�}�E�X�{�^���������ꂽ�Ƃ�
	}
	public void mouseMoved(MouseEvent e){
		mfacade.mouseMoved(e.getPoint());//�}�E�X���������Ƃ�
	}

	public void actionPerformed(ActionEvent e){	//�^�C�}�[�ɂ���ČĂяo�����X�V���\�b�h
		mfacade.update();	//�}�E�X�t�@�T�[�h�̍X�V

		//�^�C�}�[�Ăяo���܂łɁA�}�E�X���͂��������Ȃ�A�}�E�X����
		if(mfacade.judgeMousePress()) {
			model.mousePressUpdate(mfacade);
		}
		if(mfacade.judgeMouseMoved()) {
			model.mouseMoveUpdate(mfacade);
		}
		//
		
		model.timerUpdate();//���f���̍X�V		
		view.repaint();	//�^�C�}�[�ɂ��`��
	}
	
	public void sceneChange(SceneFactory.SCENE_ID id){
		SceneData scene = SceneFactory.getSceneData(new SceneChangeFacade(this), id, 0);
		
		this.model = scene.model;
		view.setComponent(scene.view);
	}
	public void sceneChange(SceneFactory.SCENE_ID id, int stage_num){
		SceneData scene = SceneFactory.getSceneData(new SceneChangeFacade(this), id, stage_num);
		
		this.model = scene.model;
		view.setComponent(scene.view);
	}
	
	public void mouseClicked(MouseEvent e){}  // �}�E�X�{�^�����Z���Ԃŉ����ė����ꂽ�Ƃ�
	public void mouseEntered(MouseEvent e){}  // �}�E�X�J�[�\����GUI���i���ɓ������Ƃ�
	public void mouseExited(MouseEvent e){}   // �}�E�X�J�[�\����GUI���i�O�ɏo���Ƃ�
	public void mouseDragged(MouseEvent e){} // �}�E�X���h���b�O���ꂽ�Ƃ�
}