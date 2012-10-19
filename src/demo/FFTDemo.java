package demo;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.*;
import dotmatrix.TextHelper;
import processing.core.PApplet;

public class FFTDemo extends PApplet
{
	private static final long serialVersionUID = -9093215355069297598L;

	Minim minim;
	AudioPlayer ap;
	FFT fft;
	
	float fftMax = 0;
	
	public void setup()
	{
		minim = new Minim(this);  
		ap = minim.loadFile("sample.mp3", 512);
		ap.loop();	
		
		background(0x00);
		size(600, 100);
		
		fft = new FFT(ap.bufferSize(), ap.sampleRate());
		fft.logAverages(100, 3);
		rectMode(CORNERS);
		
	}
	
	public void draw()
	{
		
		fill(255);
		background(0x00);
		TextHelper.printText(this, Integer.toString(fft.avgSize()));
		TextHelper.printText(this, Float.toString(fftMax), 0xff, 2, 48);
		//fft.inverse(ap.mix);
		fft.forward(ap.mix);
		int w = (int)(width/fft.avgSize());
		for(int i = 0; i < fft.avgSize(); i++)
		{
			float f =  constrain((int)(2.5* log(fft.getAvg(i))), 0, 7) *10;
			if (fftMax < f) fftMax =  f;			
		    rect(i*w, height, i*w + w, height -f);
	    }
		
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
		ap.close();
		minim.stop();
		super.stop();
	}
}
