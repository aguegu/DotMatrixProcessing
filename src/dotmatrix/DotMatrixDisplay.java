package dotmatrix;

import processing.core.PApplet;

public class DotMatrixDisplay
{
	private PApplet _parent;	
	
	private int _dotWidth = 20;
	private int _dotSpan = 2;
	private int _dotDistance;
	
	private int _margin = 10;
	
	private int _colorOn = 0xffff0000;
	private int _colorOff = 0xffffffff;
	
	private int _width, _height;
	
	private DotMatrix _dm;	
	
	public int getDotDistance()
	{
		return _dotDistance;
	}
	
	private void setStyle()
	{
		_dotDistance  = _dotWidth + _dotSpan;
		_width = _dotDistance * _dm.getColCount() + _margin * 2;
		_height = _dotDistance * _dm.getRowCount() + _margin * 2;		
		
		_parent.size(_width, _height);
		_parent.background(0x33);
	}
	
	public DotMatrixDisplay(PApplet p,DotMatrix dm)
	{
		_dm = dm;
		_parent = p;
		setStyle();
	}
	
	public DotMatrixDisplay(PApplet p, DotMatrix dm, int dotWidth, int margin)
	{
		this(p, dm);
		_dotWidth = dotWidth;
		_margin = margin;
		setStyle();
	}
	
	public void setColor(int colorOn, int colorOff)
	{
		_colorOn = colorOn;
		_colorOff = colorOff;
	}	
	
	public void display()
	{
		_parent.stroke(0);
		for(int r=0; r< _dm.getRowCount(); r++)
		{	
			for(int c=0; c<_dm.getColCount();c++)
			{			
				
				if(_dm.getDot(r,c))
				{
					_parent.fill(_colorOn);
				}
				else
				{
					_parent.fill(_colorOff);
				}
				
				_parent.pushMatrix();
				_parent.translate(_margin + c * _dotDistance, _margin + r * _dotDistance);
				_parent.rect(0, 0 , _dotWidth, _dotWidth);
				_parent.popMatrix();
			}
		}
	}	
	
	public int getDotIndex(int mouseX, int mouseY)
	{
		int index = -1;
		
		int c = (mouseX - _margin) / _dotDistance;
		int r = (mouseY - _margin) / _dotDistance;
		
		if (mouseX < _margin || mouseY < _margin)
			index = -1;
		else if (r >= _dm.getRowCount() || c >= _dm.getColCount())
			index = -1;
		else
			index = _dm.getIndx(r, c);
		
		return index;		
		
	}
}
