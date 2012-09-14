package demo;

import java.util.ArrayList;

import dotmatrix.DotMatrix;
import dotmatrix.DotMatrixDemo;
import dotmatrix.Spark;
import processing.core.PApplet;

public class SparkDemo extends PApplet
{
	private static final long serialVersionUID = -3966570181465468437L;

	private DotMatrix _dm;
	private DotMatrixDemo dmDemo;	
	
	private ArrayList<Spark> alSparks;
	
	public void setup()
	{
		dmDemo = new DotMatrixDemo(this, 48, 7, null);
		_dm = dmDemo.getDM();		
		
		alSparks = new ArrayList<Spark>();
		
		Spark spark;
		for(int i=0; i<_dm.getColCount(); i++)
		{
			spark = new Spark(_dm);
			spark.moveTo(i,_dm.getRowCount()-1);
			spark.show();
			alSparks.add(spark);
		}		
	}
	
	public void draw()
	{
		_dm.clear(false);
		
		for (Spark spark : alSparks)
		{
			if (spark.getRow() < 1+(int)random(_dm.getRowCount()-1))
			{
				spark.moveTo(spark.getCol(), spark.getRow()+1);					
			}
			else
			{
				spark.moveTo(spark.getCol(), spark.getRow()-1);
			}
			
			for (int i=spark.getRow(); i< _dm.getRowCount(); i++)
				_dm.setDot(i, spark.getCol(), true);
			
			spark.show();					
		}

		dmDemo.display();		
	}
}
