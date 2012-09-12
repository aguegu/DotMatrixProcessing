/// Tetris Demo 
/// Author: Weihong Guan (weihong.guan@gmail.com)
/// Updated on Jan 15, 2011
/// Description: Tetris base on Processing, Arduino and M2407 LED Matrix Display
 
package tetris;

import java.util.ArrayList;

import dotmatrix.DotFont;
import dotmatrix.DotFont.FontDirection;
import dotmatrix.DotMatrix;
import dotmatrix.DotMatrixDemo;
import processing.core.PApplet;

public class TetrisDemo extends PApplet
{
	private static final long serialVersionUID = 87901132768588420L;

	private static final int[] SCORE = {0, 1, 3, 6, 10};
	
	private DotMatrixDemo dmDemo;
	private DotMatrix _dm;
	
	private TetrisBlock tb;
	private TetrisBlock tbNext;
	private TetrisStack ts;	
	
	private TetrisScore tscore;
	private int score = 0;
	private boolean playing; 
	
	private int[][] frame ={{0x7f},{0x41}};
	private int[][] frameLoaction = {{6,0}, {11, 0}};
	private ArrayList<DotFont> df;
	
	public void setup()
	{
		dmDemo = new DotMatrixDemo(this, 64, 7, null);
		dmDemo.SetDisplayStyle(12, 10);
		_dm = dmDemo.getDM();
		
		df = new ArrayList<DotFont>();
		
		for(int i=0; i<frame.length; i++)			
			df.add(new DotFont(_dm, frame[i], frameLoaction[i][0], frameLoaction[i][1], FontDirection.HORIZ0NTAL));
			
		tscore = new TetrisScore(_dm, 2, 0, 0, score);
		
		tbNext = new TetrisBlock(_dm, (int)random(TetrisBlock.getPatternCount()), (int)random(4));		
		initBlocks();	
		
		ts = new TetrisStack(_dm);			
		
		playing = false;
		
		noLoop();
	}
	
	public void keyPressed()
	{		
		if (!playing)
		{
			playing = true;
			loop();
		}
		
		TetrisDirection t = null;
		
		switch(key) 
		{	
			case CODED:					
				switch(keyCode)
				{
					case DOWN:
						//t = TetrisDirection.DOWN;
						t = TetrisDirection.RIGHT;
						break;					
					case UP:
						//t = TetrisDirection.UP;
						t = TetrisDirection.CLOCKWISE;
						break;
					case LEFT:
						//t = TetrisDirection.LEFT;
						t = TetrisDirection.DOWN;
						break;
					case RIGHT:
						//t = TetrisDirection.RIGHT;
						t = TetrisDirection.UP;
						break;
					default:
						break;
				}
				break;
			case ' ':
				t = TetrisDirection.ANTI_CLOCKWISE;
				break;
			default:
				break;
		}
		
		if (t != null)
			tb.change(t,ts);
	}
	
	public void draw()
	{		
		boolean b = tb.change(TetrisDirection.RIGHT, ts);	
		
		if (b)
		{				
			score += calcScore(ts.merge(tb));
			tscore = new TetrisScore(_dm, 2, 0, 0, score);
			initBlocks();
			
			if (ts.getHeight()<12)
			{
				playing = false;
				noLoop();				
			}
		}			
		
		this.display();		
	}
	
	private void initBlocks()
	{
		tb = tbNext;
		tb.moveTo(11, 2);
		
		tbNext = new TetrisBlock(_dm, (int)random(TetrisBlock.getPatternCount()), (int)random(4));
		tbNext.moveTo(8, 2);		
	}
	
	private int calcScore(int rowsSweeped)
	{
		if (rowsSweeped < SCORE.length && rowsSweeped >= 0)
			return SCORE[rowsSweeped];
		else
			return 0;
	}
	
	protected void display()
	{
		_dm.clear(false);
		
		for (DotFont dfont : df)
		{
			dfont.show();
		}
		
		tbNext.show();
		tb.show();
		
		ts.show();		
		tscore.show();			
		
		dmDemo.display();
				
		delay(100);		
	}
}
