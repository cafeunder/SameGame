package game;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import system.DrawLibrary;
import system.MouseFacade;
import mvcModule.Controller;

public class Map {
	public class Point{
		public int x;
		public int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public boolean equal(Point p){
			return this.x == p.x && this.y == p.y;
		}
		public boolean equal(int px, int py){
			return this.x == px && this.y == py;
		}
		
		public Point copy(){
			return new Point(this.x, this.y);
		}
	}
	
	public static final int MAP_WIDTH = 672;
	public static final int MAP_HEIGHT = 480;
	public static final int BIG_TIP_SIZE = 32;
	public static final int SMALL_TIP_SIZE = 24;
	
	private int xNum;
	private int yNum;
	private int offSetX;
	private int offSetY;
	private Point mousePoint;
	private int[][] map;
	private final int tipSize;
	private Point[] selectPointGroup;
	
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
		
		this.mousePoint = null;
		this.selectPointGroup = null;
	}

	public void update(){
		MouseFacade mf = Controller.getMouseFacade();
		
		int mx = mf.getMouseX();
		int my = mf.getMouseY();
		if(mx >= this.offSetX && mx < this.offSetX+MAP_WIDTH && my >= this.offSetY && my < this.offSetY+MAP_HEIGHT){
			int mPx = (mx-this.offSetX)/this.tipSize;
			int mPy = (my-this.offSetY)/this.tipSize;
			
			if(this.mousePoint == null || (mPx != this.mousePoint.x || mPy != this.mousePoint.y)){
				this.mousePoint = new Point(mPx, mPy);
				this.selectPointGroup = calcNeighbor(this.mousePoint);
			}
		} else {
			this.mousePoint = null;
			this.selectPointGroup = null;
		}
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
			if(this.selectPointGroup != null){
				for(Point p : this.selectPointGroup){
					dLib.drawRect(this.offSetX + p.x*this.tipSize, this.offSetY + p.y*this.tipSize, this.tipSize, this.tipSize, new Color(255,255,255,200), true);
				}
			}
		}
		
		dLib.drawRect(this.offSetX, this.offSetY, MAP_WIDTH, MAP_HEIGHT, new Color(255,255,255), false);
	}

	public Point[] calcNeighbor(Point op){
		final int type = this.map[op.y][op.x];
		if(type == -1){
			return null;
		}
		
		ArrayList<Point> result = new ArrayList<Point>(0); 
		Queue<Point> queue = new ArrayDeque<Point>(0);
		int[][] tempMap = new int[this.yNum][this.xNum];
		queue.add(op.copy());
		tempMap[op.y][op.x] = 1;
		result.add(op.copy());
		
		while(true){
			Point p = queue.poll();
			if(p == null) break;
			
			if(p.x-1 >= 0 && tempMap[p.y][p.x-1] == 0 && this.map[p.y][p.x-1] == type){
				result.add(new Point(p.x-1, p.y));
				queue.add(new Point(p.x-1, p.y));
				tempMap[p.y][p.x-1] = 1;
			}
			if(p.x+1 < this.xNum && tempMap[p.y][p.x+1] == 0 && this.map[p.y][p.x+1] == type){
				result.add(new Point(p.x+1, p.y));
				queue.add(new Point(p.x+1, p.y));
				tempMap[p.y][p.x+1] = 1;
			}
			if(p.y-1 >= 0 && tempMap[p.y-1][p.x] == 0 && this.map[p.y-1][p.x] == type){
				result.add(new Point(p.x, p.y-1));
				queue.add(new Point(p.x, p.y-1));
				tempMap[p.y-1][p.x] = 1;
			}
			if(p.y+1 < this.yNum && tempMap[p.y+1][p.x] == 0 && this.map[p.y+1][p.x] == type){
				result.add(new Point(p.x, p.y+1));
				queue.add(new Point(p.x, p.y+1));
				tempMap[p.y+1][p.x] = 1;
			}			
		}
		
		return (Point[])result.toArray(new Point[0]);
	}
	
	public boolean mouseEntered(){
		MouseFacade mf = Controller.getMouseFacade();
		int mx = mf.getMouseX(); int my = mf.getMouseY();
		return (mx >= this.offSetX && mx < this.offSetX+MAP_WIDTH && my >= this.offSetY && my < this.offSetY+MAP_HEIGHT);
	}
}