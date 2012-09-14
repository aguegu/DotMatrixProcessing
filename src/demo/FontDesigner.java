package demo;

import dotmatrix.DotMatrix;
import dotmatrix.DotMatrixDemo;
import dotmatrix.DotMatrixDisplay;
import dotmatrix.TextHelper;

import processing.core.PApplet;

public class FontDesigner extends PApplet
{
	private static final long serialVersionUID = 2480290622011449578L;

	private DotMatrix _dm;
	private DotMatrixDemo dmDemo;
	private DotMatrixDisplay _dmd;

	private int dotWidth = 12;
	private int margin = 10;

	public void setup()
	{
		dmDemo = new DotMatrixDemo(this, 24*1, 7, null); 
		dmDemo.SetDisplayStyle(dotWidth, margin);
		_dm = dmDemo.getDM();
		_dmd = dmDemo.getDotMatrixDisplay();
		this.size(width, height + 20);

		dmDemo.display();
	}

	public void draw()
	{

	}

	public void mousePressed()
	{
		if (mouseButton == LEFT)
		{
			int index = _dmd.getDotIndex(mouseX, mouseY);
			if (index != -1)
			{
				_dm.reverseDot(index);
			}
		}

		this.background(0x33);
		
		TextHelper.PrintText(this, calcPattern(), 0xff, 24f, 8f, height - 12f);
		dmDemo.display();

	}

	private String calcPattern()
	{
		String s = new String();
		for (int c = 0; c < 5; c++)
		{
			byte value = 0;
			for (int i = 0; i < 7; i++)
				if (_dm.getDot(i, c))
					value |= (0x01 << i);

			s = s.concat(hex(value)).concat(", ");
		}
		
		return s;
	}

}
