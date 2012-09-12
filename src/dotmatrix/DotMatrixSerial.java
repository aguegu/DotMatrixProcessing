package dotmatrix;

import processing.core.PApplet;
import processing.serial.Serial;

public class DotMatrixSerial extends Serial
{
	//private Serial _sp;
	private DotMatrix _dm;
	
	public DotMatrixSerial(PApplet p, String PortName, DotMatrix dm)
	{
		super(p, PortName, 9600);
		_dm = dm;
	}
	
	public void send()
	{
		this.write(0xf3);
		this.write(_dm.output());		
	}
}
