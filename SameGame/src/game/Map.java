package game;

import java.awt.Color;

import system.DrawLibrary;
import system.MouseFacade;
import mvcModule.Controller;

public class Map {
	public static final int MAP_WIDTH = 672;
	public static final int MAP_HEIGHT = 480;
	public static final int BIG_TIP_SIZE = 32;
	public static final int SMALL_TIP_SIZE = 24;
	
	private int xNum;
	private int yNum;
	private int offSetX;
	private int offSetY;
	private int gx;
	private int gy;
	private int[][] map;
	private final int tipSize;
	
	public Map(int tipSize, int typeNum, int offSetX, int offSetY){
		this.tipSize = tipSize;
		this.xNum = MAP_WIDTH/tipSize;
		this.yNum = MAP_HEIGHT/tipSize;
		this.offSetX = offSetX;
		this.offSetY = offSetY;
		this.map = new int[yNum][xNum];
		
		for(int y = 0; y < yNum; y++){
			for(int x = 0; x < xNum; x++){
				this.map[y][x] = Controller.getRand(typeNum);
			}
		}
		
		this.gx = 0;
		this.gy = 0;
	}

	public void update(){
		MouseFacade mf = Controller.getMouseFacade();
		int mx = mf.getMouseX();
		int my = mf.getMouseY();
		this.gx = (mx-this.offSetX)/this.tipSize;
		this.gy = (my-this.offSetY)/this.tipSize;
	}
	
	private Color[] DEBUG_COL = {new Color(255,0,0), new Color(0,255,0), new Color(0,0,255), new Color(255,200,0)};
	public void draw(){
		DrawLibrary dLib = DrawLibrary.getInstance();
		
		for(int y = 0; y < this.yNum; y++){
			for(int x = 0; x < this.xNum; x++){
				dLib.drawRect(this.offSetX + x*this.tipSize, this.offSetY + y*this.tipSize, this.tipSize, this.tipSize, DEBUG_COL[this.map[y][x]], true);
			}
		}
		if(this.mouseEntered()){
			dLib.drawRect(this.offSetX + this.gx*this.tipSize, this.offSetY + this.gy*this.tipSize, this.tipSize, this.tipSize, new Color(255,255,255,200), true);
		}
		
		dLib.drawRect(this.offSetX, this.offSetY, MAP_WIDTH, MAP_HEIGHT, new Color(255,255,255), false);
	}
	
	public boolean mouseEntered(){
		MouseFacade mf = Controller.getMouseFacade();
		int mx = mf.getMouseX(); int my = mf.getMouseY();
		return (mx >= this.offSetX && mx < this.offSetX+MAP_WIDTH && my >= this.offSetY && my < this.offSetY+MAP_HEIGHT);
	}
}