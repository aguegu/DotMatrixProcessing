package dotmatrix;

import processing.core.PApplet;

public class DotMatrixDemo
{
	private PApplet _parent;
	private DotMatrix _dm;
	private DotMatrixDisplay _dmd;
	private DotMatrixSerial _sp;
	private boolean _sp_enable = false;

	public DotMatrixDemo(PApplet pApple, int col, int row, String PortName)
	{
		_parent = pApple;
		_dm = new DotMatrix(col, row);
		_dmd = new DotMatrixDisplay(_parent, _dm);

		if (PortName == null)
			_sp_enable = false;
		else
		{
			_sp_enable = true;
			_sp = new DotMatrixSerial(_parent, PortName, _dm);
		}
	}

	public void SetDisplayStyle(int dotWidth, int margin)
	{
		_dmd = new DotMatrixDisplay(_parent, _dm, dotWidth, margin);
	}

	public DotMatrix getDM()
	{
		return _dm;
	}

	public DotMatrixDisplay getDotMatrixDisplay()
	{
		return _dmd;
	}

	public void display()
	{
		if (_sp_enable)
			_sp.send();

		_dmd.display();
	}
}
