package demo;

import java.util.Arrays;

import processing.core.PApplet;

public class BitmapDemo extends PApplet
{
	private static final long serialVersionUID = -8270200464164013L;

	private byte[] _cache;

	public void setup()
	{
		this.size(128, 64);
		this.background(0xff);

		this.loadPixels();
		_cache = new byte[this.pixels.length / 8];

		this.stroke(0x00);
		this.line(0, 0, height - 1, height - 1);

		this.calcCache();
		this.printCache();
	}

	public void draw()
	{

	}

	public void stop()
	{
		// always close Minim audio classes when you finish with them
		// ap.close();
		// minim.stop();
		super.stop();
	}

	private void calcCache()
	{
		this.loadPixels();
		this.filter(THRESHOLD);

		Arrays.fill(_cache, (byte) 0x00);

		for (int i = 0; i < pixels.length; i++)
		{
			int row = i / width;
			int col = i % width;

			int index = row * 16 + col / 8;

			if (pixels[i] == 0xff000000)
			{
				_cache[index] |= 0x01 << (col % 8);
			}
		}

	}

	private void printCache()
	{
		for (int i = 0; i < _cache.length; i++)
		{
			print(String.format("0x%02x", _cache[i]) + ", ");
			if (i % 16 == 15)
				println();
		}
	}
}
