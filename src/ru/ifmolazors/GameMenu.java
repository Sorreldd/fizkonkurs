package ru.ifmolazors;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameMenu {
	public static BufferedImage backimg, backimgRub, backimgCO2, backimgDye, bufferBack;
	private int backWidth, backHeight;
	public GameMenu() throws IOException {
		backimg = ResourceLoader.getImage("background.jpg");
		backimgRub = ResourceLoader.getImage("backgroundRub.jpg");
		backimgCO2 = ResourceLoader.getImage("backgroundCO2.jpg");
		backimgDye = ResourceLoader.getImage("backgroundDye.jpg");
		bufferBack = backimg;
		backWidth = backimg.getWidth(null);
		backHeight = backimg.getHeight(null);
	}

	public void render(Graphics g) {
		g.drawImage(bufferBack, 0, 0, 800, 600, 0, 0, backWidth, backHeight, null);
	}
}
