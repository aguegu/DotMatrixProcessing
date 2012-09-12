package dotmatrix;

public class Dot
{
	private boolean _on;

	public Dot()
	{

	}
	
	public void turnOn()
	{
		_on = true;
	}
	
	public void turnOff()
	{
		_on = false;
	}
	
	public void set(boolean on)
	{
		_on = on;
	}
	
	public void reverse()
	{
		_on = !_on;
	}
	public boolean isOn()
	{
		return _on;
	}
}
