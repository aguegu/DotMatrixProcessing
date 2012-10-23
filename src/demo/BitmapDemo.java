package demo;

import processing.core.PApplet;

import java.util.Arrays;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.serial.*;

public class BitmapDemo extends PApplet
{
	private static final long serialVersionUID = -8270200464164013L;

	private byte[] _cache;
	private Serial _sp;

	Minim _minim;
	AudioPlayer _ap;

	public void setup()
	{
		_sp = new Serial(this, "/dev/ttyACM0", 115200);

		this.size(128, 64);
		this.background(0xff);

		_minim = new Minim(this);
		_ap = _minim.loadFile("sample.mp3", 128);
		_ap.loop();

		this.loadPixels();
		_cache = new byte[this.pixels.length / 8];

		this.stroke(0x00);
		this.line(0, 0, height - 1, height - 1);

		this.calcCache();
		this.printCache();
		
		this.frameRate(6);
	}

	public void draw()
	{
		background(0xff);
		for (int i = 0; i < _ap.mix.size(); i++)
		{
			point(i, height / 2 + _ap.mix.get(i) * height);
		}
		this.calcCache();
		_sp.write(0xf3);
		_sp.write(_cache);
	}

	public void stop()
	{
		// always close Minim audio classes when you finish with them
		_ap.close();
		_minim.stop();
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
