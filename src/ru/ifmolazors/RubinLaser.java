package ru.ifmolazors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RubinLaser {
	private static BufferedImage rubImg1, rubImg2, rubImg3, rubImg4, buffImg;
	private int backWidth, backHeight;
	static public int cntOfSlide;
	private void setDrawImg() {
		if(cntOfSlide == 0)
			buffImg = rubImg1;
		if(cntOfSlide == 1)
			buffImg = rubImg2;
		if(cntOfSlide == 2)
			buffImg = rubImg3;
		if(cntOfSlide == 3)
			buffImg = rubImg4;
	}
	public RubinLaser() throws IOException {
		rubImg1 = ResourceLoader.getImage("rub0.jpg");
		rubImg2 = ResourceLoader.getImage("rub1.jpg");
		rubImg3 = ResourceLoader.getImage("rub2.jpg");
		rubImg4 = ResourceLoader.getImage("rub3.jpg");
		cntOfSlide = 0;
		backWidth = rubImg1.getWidth(null);
		backHeight = rubImg1.getHeight(null);
	}
	public void render(Graphics g) {
		setDrawImg();
		g.drawImage(buffImg, 0, 0, 800, 600, 0, 0, backWidth, backHeight, null);
	}
}
