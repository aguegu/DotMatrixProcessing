package dotmatrix;

import processing.core.PApplet;
import processing.core.PFont;

public class TextHelper
{
	static PFont _pf;
	static int _color = 0xff;
	
	public static void PrintText(PApplet parent, String s)
	{
		if (_pf == null )
			_pf = parent.loadFont("Calibri-24.vlw");
		
		parent.textFont(_pf);
		parent.fill(_color);
		parent.text(s, 2, 24);		
	}
	
	public static void PrintText(PApplet parent, String s, int fontColor)
	{
		if (_pf == null )
			_pf = parent.loadFont("Calibri-24.vlw");
		
		_color = fontColor;
		
		parent.textFont(_pf);
		parent.fill(_color);
		parent.text(s, 2, 24);		
	}
	
	public static void PrintText(PApplet parent, String s, int fontColor, float x, float y)
	{
		if (_pf == null )
			_pf = parent.loadFont("Calibri-24.vlw");
		
		_color = fontColor;
		
		parent.textFont(_pf, 12);
		parent.fill(_color);
		parent.text(s, x, y);		
	}
	
	public static void PrintText(PApplet parent, String s, int fontColor, float fontSize, float x, float y)
	{
		if (_pf == null )
			_pf = parent.loadFont("Calibri-24.vlw");
		
		_color = fontColor;
		
		parent.textFont(_pf, fontSize);
		parent.fill(_color);
		parent.text(s, x, y);		
	}
	
	public static void fade(PApplet parent, int fadeValue)
	{
		parent.fill(0x00, fadeValue);
		parent.rect(0, 0, parent.width, parent.height);
	}
	
	
}
