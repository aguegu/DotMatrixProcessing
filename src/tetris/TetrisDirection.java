package tetris;

public enum TetrisDirection
{
    UP, DOWN, LEFT, RIGHT, CLOCKWISE, ANTI_CLOCKWISE;

    public static TetrisDirection reverse(TetrisDirection t)
    {
	TetrisDirection r = null;

	switch (t)
	{
	case UP:
	    r = DOWN;
	    break;
	case DOWN:
	    r = UP;
	    break;
	case LEFT:
	    r = RIGHT;
	    break;
	case RIGHT:
	    r = LEFT;
	    break;
	case CLOCKWISE:
	    r = ANTI_CLOCKWISE;
	    break;
	case ANTI_CLOCKWISE:
	    r = CLOCKWISE;
	    break;
	default:
	    break;
	}

	return r;
    }
}
