package ru.ifmolazors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CO2Laser {
	private static BufferedImage co2Img1, co2Img2, co2Img3, co2Img4, buffImg;
	private int backWidth, backHeight;
	static public int cntOfSlide;
	static public boolean videomode, vCreated;
	VideoClass sampleVideo;
	
	private void setDrawImg() {
		if(cntOfSlide == 0)
			buffImg = co2Img1;
		if(cntOfSlide == 1)
			buffImg = co2Img2;
		if(cntOfSlide == 2)
			buffImg = co2Img3;
		if(cntOfSlide == 3)
			buffImg = co2Img4;
	}
	public CO2Laser() throws IOException {
		co2Img1 = ResourceLoader.getImage("co20.jpg");
		co2Img2 = ResourceLoader.getImage("co21.jpg");
		co2Img3 = ResourceLoader.getImage("co22.jpg");
		co2Img4 = ResourceLoader.getImage("co23.jpg");
		cntOfSlide = 0;
		videomode = false;
		vCreated = false;
		backWidth = co2Img1.getWidth(null);
		backHeight = co2Img1.getHeight(null);
	}
	public void render(Graphics g) throws Exception {
		if(!videomode) {
			setDrawImg();
			g.drawImage(buffImg, 0, 0, 800, 600, 0, 0, backWidth, backHeight, null);
		} else {
			if(!vCreated) {
				sampleVideo = new VideoClass();
				vCreated = true;
			}
			sampleVideo.render(g);
		}
	}
}
