package system;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;


/*
	�`�擮���񋟂��郉�C�u����
 */
public class DrawLibrary{
	//�V���O���g���ŋL�q
	private static DrawLibrary instance = null;	//�C���X�^���X
	private DrawLibrary(JPanel jp, Graphics2D g2d){
		this.jp = jp;
		this.g2d = g2d;
	}
	public static DrawLibrary getInstance(){
		return instance;
	}
	public static void init(JPanel jp, Graphics2D g2d){
		instance = new DrawLibrary(jp, g2d);
	}
	
	private JPanel jp;	//�r���[
	private Graphics2D g2d;	//Graphics2D�I�u�W�F�N�g

	/**
	 * ������`�悷�郁�\�b�h
	 * @param x1 �n�_��x���W
	 * @param y1 �n�_��y���W
	 * @param x2 �I�_��x���W
	 * @param y2 �I�_��y���W
	 * @param c �F
	 */
	public void drawLine(int x1, int y1, int x2, int y2, Color c){
		g2d.setColor(c);
		g2d.drawLine(x1, y1, x2, y2);
	}
	/**
	* �摜��`�悷�郁�\�b�h
	* @param x �`��x���W
	* @param y �`��y���W
	* @param img Image�I�u�W�F�N�g
	**/
	public void drawImage(int x, int y, Image img){
		g2d.drawImage(img, x, y, jp);
	}
	/**
	* �A���t�@�u�����h�ŉ摜��`�悷�郁�\�b�h
	* @param x �`��x���W
	* @param y �`��y���W
	* @param img Image�I�u�W�F�N�g
	* @param param �A���t�@�p�����[�^(0.0~1.0)
	**/
	public void drawImageAlphaBlend(int x, int y, Image img, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//�A���t�@�`�����l���̒l���Z�b�g
		g2d.drawImage(img, x, y, jp);		//�摜��`��
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}

	/**
	* �~��`�悷�郁�\�b�h
	* @param x �`�撆�Sx���W
	* @param y �`�撆�Sy���W
	* @param radius ���a
	* @param color Color�I�u�W�F�N�g
	* @param fill �h��Ԃ����ǂ���
	**/
	public void drawCircle(int x, int y, int radius, Color color, boolean fill){
		int rx = x - radius;
		int ry = y - radius;
		
		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
	}
	
	/**
	* �~���A���t�@�u�����h�ŕ`�悷�郁�\�b�h
	* @param x �`�撆�Sx���W
	* @param y �`�撆�Sy���W
	* @param radius ���a
	* @param color Color�I�u�W�F�N�g
	* @param fill �h��Ԃ����ǂ���
	* @param param �A���t�@�p�����[�^(0.0~1.0)
	**/
	public void drawCircleAlphaBlend(int x, int y, int radius, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//�A���t�@�`�����l���̒l���Z�b�g

		int rx = x - radius;
		int ry = y - radius;                                                                                                                                                                                                                    
		
		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillOval(rx, ry, radius*2, radius*2);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawOval(rx, ry, radius*2, radius*2);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}
	
	/**
	* �l�p�`��`�悷�郁�\�b�h
	* @param x �`��x���W
	* @param y �`��y���W
	* @param width �l�p�`�̉���
	* @param height �l�p�`�̏c��
	* @param color Color�I�u�W�F�N�g
	* @param fill �h��Ԃ����ǂ���
	**/
	public void drawRect(int x, int y, int width, int height, Color color, boolean fill){
		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillRect(x, y, width, height);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawRect(x, y, width, height);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
	}
	
	/**
	* �A���t�@�u�����h�Ŏl�p�`��`�悷�郁�\�b�h
	* @param x �`��x���W
	* @param y �`��y���W
	* @param width �l�p�`�̉���
	* @param height �l�p�`�̏c��
	* @param fill �h��Ԃ����ǂ���
	* @param param �A���t�@�p�����[�^(0.0~1.0)
	**/
	public void drawRectAlphaBlend(int x, int y, int width, int height, Color color, boolean fill, float param){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, param));	//�A���t�@�`�����l���̒l���Z�b�g

		Color temp = g2d.getColor();	//�F���L��
		g2d.setColor(color);	//�����̐F���Z�b�g
		
		if(fill) g2d.fillRect(x, y, width, height);	//�ʂ�Ԃ��Ȃ�fillRect���\�b�h���A
		else g2d.drawRect(x, y, width, height);		//�ʂ�Ԃ��Ȃ��Ȃ�drawRect���\�b�h���Ăяo��

		g2d.setColor(temp);		//�F�����Z�b�g
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));		//�A���t�@�`�����l���̒l�����Z�b�g
	}
	
	/**
	* �摜����]���ĕ`�悷�郁�\�b�h
	* @param x �`�撆�Sx���W�i��]�̎x�_�j
	* @param y �`�撆�Sy���W�i��]�̎x�_�j
	* @param img Image�I�u�W�F�N�g
	* @param angle ��]���W�A���p
	**/
	public void drawImageRotate(int x, int y, Image img, float angle, boolean inter){
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.rotate(angle, x, y);	//���W����]
		g2d.drawImage(img, x-img.getWidth(jp)/2, y-img.getHeight(jp)/2, jp);	//�摜��`��
		g2d.setTransform(new AffineTransform());
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	/**
	* �摜���g�k���ĕ`�悷�郁�\�b�h
	* @param x �`�撆�Sx���W�i�g�k�̎x�_�j
	* @param y �`�撆�Sy���W�i�g�k�̎x�_�j
	* @param img Image�I�u�W�F�N�g
	* @param rate �g�k�{��
	* @param inter ��Ԃ��s�����ǂ���
	**/
	public void drawImageExtend(int x, int y, Image img, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(jp)*rate);
		int ex_h = (int)(img.getHeight(jp)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//�o�C���j�A��Ԃŕ`��
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jp);	//�摜���g��`��
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//�j�A���X�g��Ԃɖ߂�
		
	}

	/**
	* �摜���g�k�E��]���ĕ`�悷�郁�\�b�h
	* @param x �`�撆�Sx���W�i�g�k�E��]�̎x�_�j
	* @param y �`�撆�Sy���W�i�g�k�E��]�̎x�_�j
	* @param img Image�I�u�W�F�N�g
	* @param angle ��]���W�A���p
	* @param rate �g�k�{��
	* @param inter ��Ԃ��s�����ǂ���
	**/
	public void drawImageRotateExtend(int x, int y, Image img, float angle, float rate, boolean inter){
		int ex_w = (int)(img.getWidth(jp)*rate);
		int ex_h = (int)(img.getHeight(jp)*rate);
		
		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//�o�C���j�A��Ԃŕ`��
		g2d.rotate(angle, x, y);	//���W����]
		g2d.drawImage(img, x-ex_w/2, y-ex_h/2, ex_w, ex_h, jp);	//�摜���g��`��
		g2d.setTransform(new AffineTransform());

		if(inter) g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			//�j�A���X�g��Ԃɖ߂�		
	}
	
	/**
	 * �������`�悷�郁�\�b�h
	 * @param x �`��x���W
	 * @param y �`��y���W
	 * @param str ������
	 * @param color �F
	 * @param font �t�H���g�f�[�^
	 * @param antialias �A���`�G�C���A�X
	 */
	public void drawString(int x, int y, String str, Color color, Font font, boolean antialias){
		g2d.setColor(color);	//�F���Z�b�g
		g2d.setFont(font);		//�t�H���g���Z�b�g
		FontMetrics fm = g2d.getFontMetrics();	//�t�H���g���g���N�X���󂯎��
		y+=fm.getAscent();	//�A�Z���g�T�C�Y������
		
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString(str, x, y);
		if(antialias) g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
	
	/**
	* �t�H���g�̃��g���N�X��Ԃ����\�b�h
	* @param font Font�I�u�W�F�N�g
	**/	
	public FontMetrics getFontMetrics(Font font){
		return g2d.getFontMetrics(font);
	}
	
	public void SetPosition(int x, int y){
		g2d.translate(x , y);
	}
}
