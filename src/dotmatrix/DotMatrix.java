package dotmatrix;

public class DotMatrix
{
	private int _colCount;
	private int _rowCount;
	private int _dotCount;
	private int _bytesPerRow;
	
	private Dot[] _dots;
	private byte[] _pScreen;
	
	public int getColCount()
	{
		return _colCount;		
	}
	
	public int getRowCount()
	{
		return _rowCount;		
	}
	
	public int getDotCount()
	{
		return _dotCount;
	}
	
	public int getBytePerRow()
	{
		return (_colCount / 8);		
	}
	
	public int getBytesPerRow()
	{
		return _bytesPerRow;		
	}
	
	public static byte bv(int c)
	{
		return (byte)(0x01<<c);
	}
	
	public static byte lv(int c)
	{
		return (byte)~(0x01<<c);
	}
		
	public DotMatrix(int colCount, int rowCount)
	{
		_colCount = colCount;
		_rowCount = rowCount;
		_dotCount = _colCount * _rowCount;
		_bytesPerRow = colCount / 8;
		
		_dots = new Dot[_dotCount];
		
		for (int i=0; i<_dots.length;i++)
		{
			_dots[i] = new Dot(); 			
		}				
		//_dots = new boolean[_dotCount];
		_pScreen = new byte[_dotCount / 8];
	}
	
	public void clear(boolean b)
	{		
		for (Dot dot : _dots)
		{
			dot.set(b);
		}
	}
	
	public int getIndx(int row, int col)
	{		
		int i = _colCount * row + col;
		return (i);
	}
	
	public void reverseDot(int row, int col)
	{		
		int i = getIndx(row, col);
		_dots[i].reverse();
	}
	
	public void reverseDot(int index)
	{		
		_dots[index].reverse();
	}
	
	public void setDot(int row, int col, boolean b)
	{
		int i = getIndx(row, col);
		this.setDot(i, b);
	}
	
	public void setDot(int index, boolean b)
	{
		_dots[index].set(b);
	}
	
	public boolean getDot(int row, int col)
	{
		int i = getIndx(row, col);
		//return _dots.get(i).isOn();
		return getDot(i);
	}
	
	public boolean getDot(int index)
	{
		return _dots[index].isOn();		
	}
	
	public byte[] output()
	{			
		for(int i=0; i<_dots.length; i++)
		{
			if (_dots[i].isOn())
			{
				_pScreen[i/8] |= bv(i%8);
			}
			else
			{
				_pScreen[i/8] &= lv(i%8);
			}
		}		
		return _pScreen;
	}
}
