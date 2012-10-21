package demo;

import processing.core.PApplet;

public class BitmapDemo extends PApplet
{
	private static final long serialVersionUID = -8270200464164013L;

	public void setup()
	{
		this.size(128, 64);
		this.background(0xff);

		this.stroke(0x00);
		this.point(0, 0);

		this.filter(THRESHOLD);

		this.loadPixels();

		for (int i = 0; i < 4; i++)
			PApplet.println(Integer.toHexString(this.pixels[i]));

	}

}
