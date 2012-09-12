package tetris;

import java.util.ArrayList;

import dotmatrix.DotFont.FontDirection;
import dotmatrix.DotMatrix;
import dotmatrix.DotFont0503;

public class TetrisScore
{
	ArrayList<DotFont0503> _digitals;
	DotMatrix _dm;
	
	public TetrisScore(DotMatrix dm, int digiCount, int col, int row, int value)
	{
		_dm = dm;
		_digitals = new ArrayList<DotFont0503>();
		
		for(int i=0; i<digiCount; i++)
		{
			int ten = 1;
			
			for (int j=0; j<i; j++)
				ten *= 10;			
			
			_digitals.add(new DotFont0503(_dm, (value / ten) % 10 , col, row + 4*i, FontDirection.VERTICAL));
		}		
	}
	
	public void show()
	{
		for (DotFont0503 digi : _digitals)
		{
			digi.show();
		}
	}
	
}
