package mvcModule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import system.DrawLibrary;
import system.FontMgr;

public class Viewer extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage bf;	//�_�u���o�b�t�@�����O�p�C���[�W
	private ViewerComponent viewComponent;

	public Viewer(){
		bf = new BufferedImage(800,600,BufferedImage.TYPE_INT_ARGB);	//�_�u���o�b�t�@�̗̈���m��
		
		setSize(new Dimension(800,600));
		
		DrawLibrary.init(this, (Graphics2D)bf.getGraphics());	//�`�惉�C�u�����̏�����
		FontMgr.init();			//�t�H���g�f�[�^�����[�h
	}

    public void update(Graphics g){
    	//�I�[�o�[���C�h����JPanel��update���\�b�h
    	paint(g);
    }
    
	public void paintComponent(Graphics g){
		//�`�惁�\�b�h
		Graphics bg = bf.getGraphics();
		
		//�w�i�̕`��
		bg.setColor(Color.BLACK);
		bg.fillRect(0, 0, 800, 600);
		//
		
		//�����ɖ{�̂�����
		viewComponent.draw();
		//
		
		g.drawImage(bf, 0, 0, this);
	}

	public void setComponent(ViewerComponent viewComponent){
		this.viewComponent = viewComponent;
	}
}
