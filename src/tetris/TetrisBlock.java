package tetris;

import java.util.ArrayList;

import processing.core.PVector;

import dotmatrix.DotMatrix;
import dotmatrix.Spark;
import dotmatrix.SparkArray;

public class TetrisBlock extends SparkArray
{
    private final static int PATTERN_ALL[][][][] = {
	    // l
	    { { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 3, 1 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 3 } }, },

	    // O
	    { { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, 1 } },
	    // {{0,0}, {1,0}, {1,1}, {0,1}},
	    },

	    // T
	    { { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 1, 0 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 1 } },
		    { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 1, 2 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 1 } }, },

	    // J
	    { { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 2 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 2 } },
		    { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 0, 0 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 } }, },

	    // L
	    { { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 0 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 2 } },
		    { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 0, 2 } },
		    { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 0 } }, },

	    // Z
	    { { { 0, 2 }, { 0, 1 }, { 1, 1 }, { 1, 0 } },
		    { { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 2 } }, },

	    // S
	    { { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } },
		    { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, },

    };

    private int _patternID;
    private int _index;
    private PVector _location;

    public TetrisBlock(DotMatrix dm, int pattern, int index)
    {
	super(dm);

	_location = new PVector(2, 2);
	_patternID = pattern;
	_index = index % countIndex();

	setSparks();
    }

    public void moveTo(int col, int row)
    {
	_location = new PVector(col, row);
	setSparks();
    }

    public ArrayList<Spark> getSparks()
    {
	return _sparks;
    }

    public int countIndex()
    {
	return PATTERN_ALL[_patternID].length;
    }

    public static int getPatternCount()
    {
	return PATTERN_ALL.length;
    }

    private void tryChange(TetrisDirection t)
    {
	switch (t)
	{
	case UP:
	    _location.y--;
	    break;
	case DOWN:
	    _location.y++;
	    break;
	case LEFT:
	    _location.x--;
	    break;
	case RIGHT:
	    _location.x++;
	    break;
	case CLOCKWISE:
	    _index++;
	    if (_index == countIndex())
		_index = 0;
	    break;
	case ANTI_CLOCKWISE:
	    _index--;
	    if (_index == -1)
		_index = countIndex() - 1;
	    break;
	default:
	    break;
	}

	setSparks();
    }

    public boolean change(TetrisDirection direction, TetrisStack ts)
    {
	tryChange(direction);

	boolean b = isOutOfBorder() || isContacted(ts);
	if (b)
	{
	    tryChange(TetrisDirection.reverse(direction));
	}

	return b;
    }

    public boolean isContacted(TetrisStack ts)
    {
	boolean b = false;

	for (Spark sparkStack : ts.getSparks())
	{
	    for (Spark spark : _sparks)
	    {
		if (sparkStack.getID() == spark.getID())
		{
		    b = true;
		    break;
		}
	    }
	}
	return b;
    }

    public boolean isOutOfBorder()
    {
	boolean b = false;

	for (Spark spark : _sparks)
	{
	    if (spark.getCol() < 0 || spark.getCol() >= _dm.getColCount()
		    || spark.getRow() < 0
		    || spark.getRow() >= _dm.getRowCount())
	    {
		b = true;
		break;
	    }
	}

	return b;
    }

    private void setSparks()
    {
	_pattern = PATTERN_ALL[_patternID][_index];
	super.setSparks(locationCol(), locationRow());
    }

    private int locationCol()
    {
	return (int) _location.x;
    }

    private int locationRow()
    {
	return (int) _location.y;
    }

}
