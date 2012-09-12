package dotmatrix.Demo;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import dotmatrix.Bar;
import dotmatrix.DotMatrix;
import dotmatrix.DotMatrixDemo;
import processing.core.PApplet;

public class BarDemo extends PApplet
{
	private static final long serialVersionUID = -2935931867398743145L;

	private DotMatrix _dm;
	
	private DotMatrixDemo _dmDemo;
	
	private Bar bars[];

	Minim minim;
	AudioPlayer ap;
	FFT fft;

	public void setup()
	{
		_dmDemo = new DotMatrixDemo(this, 48, 7, null);
		_dm = _dmDemo.getDM();
		
		bars = new Bar[48];
		
		minim = new Minim(this);  
		ap = minim.loadFile("sample.mp3", 512);
		ap.loop();	
		
		fft = new FFT(ap.bufferSize(), ap.sampleRate());
		fft.logAverages(100, 3);
				
		for(int i=0; i<bars.length; i++)
		{	
			bars[i] = new Bar(_dm, i);
			bars[i].setHeight(i%(_dm.getRowCount()+1));
			bars[i].show();			
		}	
	}
	
	public void draw()
	{
		_dm.clear(false);
		fft.forward(ap.mix);
		
		for(int i = 0; i < _dm.getColCount() / 3 && i< fft.avgSize(); i++)
		{
			int h =  constrain((int)(2.5* log(fft.getAvg(i))), 0, 7);
			bars[3*i].setHeight(h);
			bars[3*i+1].setHeight(h);
			bars[3*i].show();
			bars[3*i+1].show();
	    }
		
		_dmDemo.display();
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
		ap.close();
		minim.stop();
		super.stop();
	}
}
