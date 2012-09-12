package tetris;

import java.util.ArrayList;

import dotmatrix.DotMatrix;
import dotmatrix.Spark;
import dotmatrix.SparkArray;

public class TetrisStack extends SparkArray
{
	private ArrayList<ArrayList<Spark>> _sparksPerCol;
	
	public TetrisStack(DotMatrix dm)
	{		
		super(dm);
		
		_sparksPerCol = new ArrayList<ArrayList<Spark>>();
		
		for(int i=0; i<_dm.getColCount(); i++)
			_sparksPerCol.add(i, new ArrayList<Spark>());
	}
	
	public ArrayList<Spark> getSparks()
	{
		return _sparks;
	}
	
	public int merge(TetrisBlock tb)
	{		
		_sparks.addAll(tb.getSparks());		
		
		return sweep();
	}
	
	private void countSparkPerCol()
	{
		for (ArrayList<Spark> sparksPerCol : _sparksPerCol)
		{
			sparksPerCol.clear();
		}			
		
		for (Spark spark : _sparks)
		{
			_sparksPerCol.get(spark.getCol()).add(spark);
		}
	}
	
	private int sweep()
	{
		int score = 0;
		
		countSparkPerCol();
		
		for(int i=0; i<_sparksPerCol.size(); i++)
		{
			if(_sparksPerCol.get(i).size() == _dm.getRowCount())
			{
				score ++;
				_sparks.removeAll(_sparksPerCol.get(i));
				
				for(int j=0; j<i; j++)
				{
					for (Spark spark : _sparksPerCol.get(j))
					{
						spark.moveTo(spark.getCol()+1, spark.getRow());
					}
				}
			}			
		}
		
		return score;
	}
	
	public int getHeight()
	{
		int i;
		for(i=0; i<_sparksPerCol.size(); i++)
		{
			if (_sparksPerCol.get(i).size() != 0) break;
		}
		
		return i;
	}
}
