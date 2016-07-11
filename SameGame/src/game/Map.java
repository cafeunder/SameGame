package game;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

import mvcModule.Controller;
import system.DrawLibrary;
import system.FontMgr;
import system.ImageMgr;
import system.MouseFacade;

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

	public static class LevelData{
		public final int blockTypeNum;
		public final int goalCompRate;
		public LevelData(int blockTypeNum, int goalCompRate){
			this.blockTypeNum = blockTypeNum;
			this.goalCompRate = goalCompRate;
		}
	}
	public static LevelData[] levelDataTable = {
			new LevelData(3, 80),
			new LevelData(4, 80),
			new LevelData(5, 90),
			new LevelData(6, 80),
			new LevelData(6, 90),
			new LevelData(6, 95),
	};

	public static final int MAP_WIDTH = 640;
	public static final int MAP_HEIGHT = 480;
	public static final int BIG_BLOCK_SIZE = 32;
	public static final int SMALL_BLOCK_SIZE = 24;

	public final int level;
	private int init_xNum;
	private int xNum;
	private int yNum;
	public final int OFFSET_X = 40;
	public final int OFFSET_Y = 40;
	private Point mousePoint;
	private int[][] map;
	private final int BlockSize;
	private final int goalCompRate;
	private Point[] selectPointGroup;
	private final int startBlockNum;
	private double compRate;
	private boolean enable = false;

	private int[][] indexMap = null;
	private ArrayList<Point[]> groupAry = null;

	public Map(int level){
		this.level = level;
		this.BlockSize = BIG_BLOCK_SIZE;
		this.xNum = MAP_WIDTH/this.BlockSize;
		this.yNum = MAP_HEIGHT/this.BlockSize;
		this.init_xNum = this.xNum;

		// row X column
		// due to SAMEGAME's board
		this.map = new int[xNum][yNum];
		this.startBlockNum = this.xNum*this.yNum;

		LevelData levelData = (level >= Map.levelDataTable.length) ? levelDataTable[levelDataTable.length-1] : levelDataTable[this.level];
		ArrayList<Integer> blocks = new ArrayList<Integer>(this.startBlockNum);
		for(int i = 0; i < this.startBlockNum; i++){
			blocks.add(i%levelData.blockTypeNum);
		}
		Collections.shuffle(blocks);
		for(int i = 0; i < this.startBlockNum; i++){
			this.map[i%this.xNum][i/this.xNum] = blocks.get(i);
		}

		this.grouping();

		this.mousePoint = null;
		this.selectPointGroup = null;

		this.goalCompRate = levelData.goalCompRate;
		this.compRate = 0.0;
	}

	public enum State{
		CONTINUE,
		GAMEOVER,
		NEXTSTAGE,
	};
	public State update(){
		MouseFacade mf = Controller.getMouseFacade();
		State result = State.CONTINUE;

		//pressing mouse button => delete blocks
		//column check >> row check >> grouping
		if(mf.getMouseLeftPressCount() == 1 && this.selectPointGroup != null && this.selectPointGroup.length > 1){
			for(Point p : this.selectPointGroup){
				this.map[p.x][p.y] = -1;
			}

			//Column Check
			for(int x = 0; x < this.xNum; x++){
				for(int y = 0; y < this.yNum; y++){
					int index = this.yNum-1 - y;
					if(this.map[x][index] == -1){
						int searchIndex = index-1;
						while(true){
							if(searchIndex < 0) break;

							if(this.map[x][searchIndex] != -1){
								this.map[x][index] = this.map[x][searchIndex];
								this.map[x][searchIndex] = -1;
								break;
							}

							searchIndex--;
						}

						if(searchIndex < 0) break;
					}
				}
			}

			//Row Check
			for(int x = 0; x < this.xNum-1; x++){
				boolean exist = false;
				for(int y = 0; y < this.yNum; y++){
					if(this.map[x][y] != -1){
						exist = true;
						break;
					}
				}
				if(!exist){
					this.map[x] = null;
				}
			}

			for(int x = 0; x < this.xNum-1; x++){
				if(this.map[x] == null){
					int searchIndex = x+1;
					while(true){
						if(searchIndex >= this.xNum) break;

						if(this.map[searchIndex] != null){
							this.map[x] = this.map[searchIndex];
							this.map[searchIndex] = null;
							break;
						}

						searchIndex++;
					}
				}
			}

			int prevXNum = this.xNum;
			for(int x = 0; x < prevXNum; x++){
				if(this.map[x] == null) break;
				this.xNum = x;
			}
			this.xNum++;

			//calc complete rate
			int blockNum = 0;
			for(int[] a : this.map) if(a != null) for(int b : a) if(b != -1) blockNum++;

			BigDecimal bd = new BigDecimal((1 - (double)blockNum/this.startBlockNum)*100);
			this.compRate = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

			//grouping
			this.grouping();
			if(this.groupAry.size() == 0){
				if(this.compRate >= this.goalCompRate){
					result = State.NEXTSTAGE;
				} else {
					result = State.GAMEOVER;
				}
			}
		}


		//moving mouse => select blocks
		int mx = mf.getMouseX();
		int my = mf.getMouseY();
		if(mx >= this.OFFSET_X && mx < this.OFFSET_X+MAP_WIDTH && my >= this.OFFSET_Y && my < this.OFFSET_Y+MAP_HEIGHT){
			int mPx = (mx-this.OFFSET_X)/this.BlockSize;
			int mPy = (my-this.OFFSET_Y)/this.BlockSize;

			if(this.mousePoint == null || (mPx != this.mousePoint.x || mPy != this.mousePoint.y) || mf.getMouseLeftPressCount() == 1){
				this.mousePoint = new Point(mPx, mPy);
				if(this.indexMap[mPx][mPy] >= 0){
					this.selectPointGroup = this.groupAry.get(this.indexMap[mPx][mPy]);
				} else {
					this.selectPointGroup = null;
				}
			}
		} else {
			this.mousePoint = null;
			this.selectPointGroup = null;
		}

		return result;
	}

	private ImageMgr.ImgId[] tipImageAry = {ImageMgr.ImgId.TIP_RED, ImageMgr.ImgId.TIP_GREEN, ImageMgr.ImgId.TIP_BLUE, ImageMgr.ImgId.TIP_YELLOW, ImageMgr.ImgId.TIP_CYAN, ImageMgr.ImgId.TIP_VIOLET};
	public void draw(){
		DrawLibrary dLib = DrawLibrary.getInstance();
		ImageMgr imgMgr = ImageMgr.getInstance();

		for(int x = 0; x < this.xNum; x++){
			for(int y = 0; y < this.yNum; y++){
				if(this.map[x][y] != -1){
					dLib.drawImage(this.OFFSET_X + x*this.BlockSize, this.OFFSET_Y + y*this.BlockSize, imgMgr.getImageToId(tipImageAry[this.map[x][y]]));
					//dLib.drawString(this.offSetX + x*this.tipSize, this.offSetY + y*this.tipSize, this.indexMap[x][y]+"", new Color(255,255,255), FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
				}
			}
		}

		if(this.enable){
			if(this.mouseEntered()){
				if(this.selectPointGroup != null){
					for(Point p : this.selectPointGroup){
						dLib.fillRect(this.OFFSET_X + p.x*this.BlockSize, this.OFFSET_Y + p.y*this.BlockSize, this.BlockSize, this.BlockSize, new Color(255,255,255,200));
					}
				}

				if(this.mousePoint != null){
					dLib.drawRect(this.OFFSET_X + this.mousePoint.x*this.BlockSize, this.OFFSET_Y + this.mousePoint.y*this.BlockSize, this.BlockSize, this.BlockSize, new Color(0,255,255), 4);
				}
			}

		}

		dLib.drawRect(this.OFFSET_X, this.OFFSET_Y, MAP_WIDTH, MAP_HEIGHT, new Color(255,255,255), 1);
		dLib.drawString(10, 550, this.compRate+"%", new Color(255,255,255), FontMgr.getInstance().getFontToId(FontMgr.FontId.POPMENU), true);
	}

	public void grouping(){
		this.indexMap = new int[this.init_xNum][this.yNum];
		for(int[] a : this.indexMap) for(int i = 0; i < a.length; i++) a[i] = -1;
		this.groupAry = new ArrayList<Point[]>(0);

		int groupIndex = 0;
		for(int x = 0; x < this.xNum; x++){
			for(int y = 0; y < this.yNum; y++){
				if(this.indexMap[x][y] != -1) continue;
				this.indexMap[x][y] = -2;

				int type = this.map[x][y];
				if(type != -1){
					ArrayList<Point> group = new ArrayList<Point>(0);
					Queue<Point> queue = new ArrayDeque<Point>(0);
					queue.add(new Point(x,y));
					group.add(new Point(x,y));

					while(true){
						Point p = queue.poll();
						if(p == null) break;

						if(p.x-1 >= 0 && this.indexMap[p.x-1][p.y] == -1 && this.map[p.x-1][p.y] == type){
							group.add(new Point(p.x-1, p.y));
							queue.add(new Point(p.x-1, p.y));
							this.indexMap[p.x-1][p.y] = -2;
						}
						if(p.x+1 < this.xNum && this.indexMap[p.x+1][p.y] == -1 && this.map[p.x+1][p.y] == type){
							group.add(new Point(p.x+1, p.y));
							queue.add(new Point(p.x+1, p.y));
							this.indexMap[p.x+1][p.y] = -2;
						}
						if(p.y-1 >= 0 && this.indexMap[p.x][p.y-1] == -1 && this.map[p.x][p.y-1] == type){
							group.add(new Point(p.x, p.y-1));
							queue.add(new Point(p.x, p.y-1));
							this.indexMap[p.x][p.y-1] = -2;
						}
						if(p.y+1 < this.yNum && this.indexMap[p.x][p.y+1] == -1 && this.map[p.x][p.y+1] == type){
							group.add(new Point(p.x, p.y+1));
							queue.add(new Point(p.x, p.y+1));
							this.indexMap[p.x][p.y+1] = -2;
						}
					}

					if(group.size() > 1){
						for(Point p : group){
							this.indexMap[p.x][p.y] = groupIndex;
						}
						this.groupAry.add((Point[])group.toArray(new Point[0]));
						groupIndex++;
					}
				}
			}
		}
	}

	public Point[] calcNeighbor(Point op)
	{
		if(op.x < 0 || op.x >= this.xNum || op.y < 0 || op.y >= this.yNum) return null;

		final int type = this.map[op.x][op.y];
		if(type == -1){
			return null;
		}

		ArrayList<Point> result = new ArrayList<Point>(0);
		Queue<Point> queue = new ArrayDeque<Point>(0);
		int[][] tempMap = new int[this.xNum][this.yNum];
		queue.add(op.copy());
		tempMap[op.x][op.y] = 1;
		result.add(op.copy());

		while(true){
			Point p = queue.poll();
			if(p == null) break;

			if(p.x-1 >= 0 && tempMap[p.x-1][p.y] == 0 && this.map[p.x-1][p.y] == type){
				result.add(new Point(p.x-1, p.y));
				queue.add(new Point(p.x-1, p.y));
				tempMap[p.x-1][p.y] = 1;
			}
			if(p.x+1 < this.xNum && tempMap[p.x+1][p.y] == 0 && this.map[p.x+1][p.y] == type){
				result.add(new Point(p.x+1, p.y));
				queue.add(new Point(p.x+1, p.y));
				tempMap[p.x+1][p.y] = 1;
			}
			if(p.y-1 >= 0 && tempMap[p.x][p.y-1] == 0 && this.map[p.x][p.y-1] == type){
				result.add(new Point(p.x, p.y-1));
				queue.add(new Point(p.x, p.y-1));
				tempMap[p.x][p.y-1] = 1;
			}
			if(p.y+1 < this.yNum && tempMap[p.x][p.y+1] == 0 && this.map[p.x][p.y+1] == type){
				result.add(new Point(p.x, p.y+1));
				queue.add(new Point(p.x, p.y+1));
				tempMap[p.x][p.y+1] = 1;
			}
		}

		return (Point[])result.toArray(new Point[0]);
	}

	public boolean mouseEntered(){
		MouseFacade mf = Controller.getMouseFacade();
		int mx = mf.getMouseX(); int my = mf.getMouseY();
		return (mx >= this.OFFSET_X && mx < this.OFFSET_X+MAP_WIDTH && my >= this.OFFSET_Y && my < this.OFFSET_Y+MAP_HEIGHT);
	}

	public void setEnable(boolean enable){
		this.enable = enable;
	}
}