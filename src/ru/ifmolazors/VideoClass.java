package ru.ifmolazors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VideoClass {
	private static BufferedImage cadrVideo;
	private static int numOfCadr;
	private int backWidth, backHeight;
	String getCadr(int x) {
		String res = "80W CO2 Laser - First test ";
		if(x < 10) res = res + "00" + x + ".jpg"; 
		if(x >= 10 && x < 100) res = res + "0" + x + ".jpg"; 
		if(x >= 100) res = res + x + ".jpg"; 
		return res;
	}
	public VideoClass() throws IOException {
		numOfCadr = 0;
		cadrVideo = ResourceLoader.getImage(getCadr(numOfCadr + 1));
		backWidth = cadrVideo.getWidth(null);
		backHeight = cadrVideo.getHeight(null);
	}
	public void render(Graphics g) throws Exception {
		g.drawImage(cadrVideo, 0, 0, 800, 600, 0, 0, backWidth, backHeight, null);
		if(numOfCadr >= 495) {
			CO2Laser.videomode = false;
			CO2Laser.vCreated = false;
		} else {
			numOfCadr++;
			cadrVideo = ResourceLoader.getImage(getCadr(numOfCadr + 1));
			Thread.sleep(15);
		}
	}
}
