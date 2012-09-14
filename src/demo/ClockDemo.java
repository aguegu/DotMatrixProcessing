package demo;

import java.util.ArrayList;

import dotmatrix.DotFont;
import dotmatrix.DotFont.FontDirection;
import dotmatrix.DotFont0706;
import dotmatrix.DotMatrix;
import dotmatrix.DotMatrixDemo;
import processing.core.PApplet;

public class ClockDemo extends PApplet
{
	private static final long serialVersionUID = -663453412567788630L;
	
	private DotMatrixDemo _dmd;
	private DotMatrix _dm;		

	private ArrayList<DotFont0706> alFonts;		
	
	private ArrayList<DotFont> alColons;
	private int[] font={0x14};
		
	public void setup()
	{
		_dmd = new DotMatrixDemo(this, 24*4, 7, null);
		_dmd.SetDisplayStyle(16, 8);
		_dm = _dmd.getDM();		
		
		alFonts = new ArrayList<DotFont0706>();
		alColons = new ArrayList<DotFont>();
		
		alColons.add(new DotFont(_dm, font, 16, 0, FontDirection.HORIZ0NTAL));
		alColons.add(new DotFont(_dm, font, 30, 0, FontDirection.HORIZ0NTAL));
	}
	
	public void draw()
	{
		alFonts.clear();
		
		alFonts.add(new DotFont0706(_dm, hour() / 10, 4, 0, FontDirection.HORIZ0NTAL));
		alFonts.add(new DotFont0706(_dm, hour() % 10, 10, 0, FontDirection.HORIZ0NTAL));
		
		alFonts.add(new DotFont0706(_dm, minute() / 10, 18, 0, FontDirection.HORIZ0NTAL));
		alFonts.add(new DotFont0706(_dm, minute() % 10, 24, 0, FontDirection.HORIZ0NTAL));
		
		alFonts.add(new DotFont0706(_dm, second() / 10, 32, 0, FontDirection.HORIZ0NTAL));
		alFonts.add(new DotFont0706(_dm, second() % 10, 38, 0, FontDirection.HORIZ0NTAL));		
		
		_dm.clear(false);
		
		for (DotFont0706 font : alFonts)
		{
			font.show();
		}
				
		for (DotFont font : alColons)
		{
			font.show();
		}
		
		_dmd.display();
	}
}
