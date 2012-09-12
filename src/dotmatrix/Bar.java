package dotmatrix;

import java.util.ArrayList;


public class Bar
{
	private ArrayList<Spark> _sparks;
	private DotMatrix _dm;
	private int _col;
	
	public Bar(DotMatrix dm, int col)
	{
		_sparks = new ArrayList<Spark>();
		_dm = dm;
		_col = col;
	}
	
	public void setHeight(int height)
	{
		_sparks.clear();
		for (int i=0; i<height; i++)
		{
			Spark spark = new Spark(_dm);
			spark.moveTo(_col, _dm.getRowCount()-1-i);
			_sparks.add(spark);
		}				
	}
	
	public void show()
	{
		for (Spark spark : _sparks)
		{
			spark.show();
		}
	}
}
