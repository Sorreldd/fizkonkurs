package ru.ifmolazors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DyeLaser {
	private static BufferedImage dyeImg1, dyeImg2, dyeImg3, dyeImg4, buffImg;
	private int backWidth, backHeight;
	static public int cntOfSlide, gamelvl;
	static public boolean gamemode;
	VideoClass sampleVideo;
	static private LazorsGame lasors;
	
	private void setDrawImg() {
		if(cntOfSlide == 0)
			buffImg = dyeImg1;
		if(cntOfSlide == 1)
			buffImg = dyeImg2;
		if(cntOfSlide == 2)
			buffImg = dyeImg3;
		if(cntOfSlide == 3)
			buffImg = dyeImg4;
	}
	public DyeLaser() throws IOException {
		dyeImg1 = ResourceLoader.getImage("dye0.jpg");
		dyeImg2 = ResourceLoader.getImage("dye1.jpg");
		dyeImg3 = ResourceLoader.getImage("dye2.jpg");
		dyeImg4 = ResourceLoader.getImage("dye3.jpg");
		cntOfSlide = 0;
		gamemode = false;
		gamelvl = 0;
		backWidth = dyeImg1.getWidth(null);
		backHeight = dyeImg1.getHeight(null);
	}
	public void render(Graphics g) throws Exception {
		if(!gamemode) {
			setDrawImg();
			g.drawImage(buffImg, 0, 0, 800, 600, 0, 0, backWidth, backHeight, null);
		} else {
			if(gamelvl == 0)
				lasors= new LazorsGame();
			lasors.render(g);
		}
	}
}
