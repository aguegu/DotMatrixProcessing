package dotmatrix;

public class DotFont extends SparkArray
{
	protected int[] _font;
	
	public enum FontDirection
	{
		HORIZ0NTAL, VERTICAL;
	}
	
	public DotFont(DotMatrix dm, int[] font, int col, int row, FontDirection direction)
	{
		super(dm);
		_font = font;
		
		switch(direction)
		{
			case VERTICAL:
				setSparksV(col, row);
				break;			
			case HORIZ0NTAL:
			default:
				setSparks(col, row);
				break;
			
		}
	}
	
	protected void setSparks(int col, int row)
	{
		_sparks.clear();	
		
		for (int c=0; c<_font.length; c++)
		{			
			for (int r=0; r<_dm.getRowCount(); r++)
			{
				if ((_font[c] & (0x01<<r)) == (0x01<<r))
				{
					Spark spark = new Spark(_dm);
					spark.moveTo(col + c, row + r);
					_sparks.add(spark);
				}
			}
		}		
	}
	
	protected void setSparksV(int col, int row)
	{
		_sparks.clear();	
		
		for (int c=0; c<_font.length; c++)
		{			
			for (int r=0; r<_dm.getRowCount(); r++)
			{
				if ((_font[c] & (0x01<<r)) == (0x01<<r))
				{
					Spark spark = new Spark(_dm);
					spark.moveTo(col + r, row + c);
					_sparks.add(spark);
				}
			}
		}		
	}
}
