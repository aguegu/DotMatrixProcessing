package dotmatrix;

import processing.core.PVector;

public class Spark extends Dot
{
	private PVector _pv;
	private DotMatrix _dm;
	
	public Spark(DotMatrix dm)
	{
		this.turnOn();
		_dm = dm; 
		_pv = new PVector();
	}
	
	public void moveTo(int col, int row)
	{
		_pv.x = (float)col;
		_pv.y = (float)row;	
	}
	
	public int getCol()
	{
		return (int)_pv.x;
	}
	
	public int getRow()
	{
		return (int)_pv.y;
	}
		
	public void show()
	{
	
		if (getRow() <0 || getRow() >= _dm.getRowCount())return;
		if (getCol() <0 || getCol() >= _dm.getColCount())return;
		
		_dm.setDot(getRow(), getCol(), this.isOn());		
	}
	
	public int getID()
	{
		return (getRow() * _dm.getColCount() + getCol());
	}
}
