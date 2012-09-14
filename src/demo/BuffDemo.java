package demo;

import processing.core.PApplet;
import ddf.minim.*;
import dotmatrix.TextHelper;

public class BuffDemo extends PApplet
{
	private static final long serialVersionUID = 8598839469711789068L;
	Minim minim;
	AudioPlayer ap;

	float r =0f;
	
	int halfWidth, halfHeight;
	float sectionWidth;
	float buffMax, buffMin;	
	
	public void setup()
	{
		size(600, 600, P2D);	
		
		halfWidth = width / 2;
		halfHeight = height/ 2;
		
		minim = new Minim(this);	
		background(0x00);
  
		ap = minim.loadFile("sample.mp3", 512);
		ap.loop();		
		
		sectionWidth = (float)width / ap.bufferSize();		
		
		
	}
	
	public void draw()
	{	
		TextHelper.PrintText(this, Float.toString(buffMax));
		TextHelper.PrintText(this, Float.toString(buffMin), 0xff, 2f, 48f);
		
		r+=0.02;
		fade(20);		 
			
		stroke(0xffffffff);
		strokeWeight(2);  
		 
		drawWave(ap.left, r);  
		drawWave(ap.right, r+HALF_PI);	
		
  		delay(50);
	}
	
	private void drawWave(AudioBuffer ab, float angle)
	{
		pushMatrix();
		
		translate(halfWidth, halfHeight);
		rotate(angle);
		scale(1.4f);					    
		  
		for(int i = 1; i < ab.size(); i++)
		{
			if (ab.get(i)>buffMax) buffMax = ab.get(i);
			if (ab.get(i)<buffMin) buffMin = ab.get(i);
			
			line((i-1)*sectionWidth - halfWidth, ab.get(i-1)*100, i*sectionWidth - halfWidth, ab.get(i)*100);			
		}
		  
		popMatrix();
	}
	
	private void fade(int fadeValue)
	{
		fill(0x00, fadeValue);
		this.rect(0, 0, width, height);
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you finish with them
		ap.close();
		minim.stop();
		super.stop();
	}
}
