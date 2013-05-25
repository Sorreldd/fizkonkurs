package ru.ifmolazors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LazorsGame {
	private static BufferedImage gamefield, boxes[] = new BufferedImage[3], laserrr, startp, lastp;
	private int backWidth, backHeight;
	private int boxWidth, boxHeight, ktop = 1, kside = 2;
	private boolean winlvl;
	private Point pointLaser, pointEnd;
	private int direct;
	final int X[] = {-1, 1, 1, -1};
	final int Y[] = {-1, -1, 1, 1};
	public static boolean isClicked;
	public static Point whereClk;
	public static int gameDesk[][] = new int[10][6];
	public LazorsGame() throws IOException {
		gamefield = ResourceLoader.getImage("gamefield.jpg");
		boxes[0] = ResourceLoader.getImage("kub00.png");
		boxes[1] = ResourceLoader.getImage("kub01.png");
		boxes[2] = ResourceLoader.getImage("kub02.png");
		laserrr = ResourceLoader.getImage("laserrr.png");
		startp = ResourceLoader.getImage("startp.png");
		lastp = ResourceLoader.getImage("lastp.png");
		backWidth = gamefield.getWidth(null);
		backHeight = gamefield.getHeight(null);
		boxWidth = boxes[0].getWidth(null);
		boxHeight = boxes[0].getHeight(null);
		winlvl = true;
		isClicked = false;
	}
	public void render(Graphics g) throws Exception {
		if(winlvl && DyeLaser.gamelvl != 0) 
			Thread.sleep(1500);
		if(winlvl && DyeLaser.gamelvl == 8) {
			DyeLaser.gamemode = false;
		} else {
			g.drawImage(gamefield, 0, 0, 800, 600, 0, 0, backWidth, backHeight, null);
			if(winlvl) initThisLvl();
			g.drawImage(lastp, pointEnd.x - 15, pointEnd.y - 15, pointEnd.x + 16, pointEnd.y + 16, 0, 0, 150, 150, null);
			g.drawString("Уровень " + DyeLaser.gamelvl, 380, 10);
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 6; j++) {
					if(gameDesk[i][j] == 3) 
						g.drawImage(boxes[1], i * 74 + 1, j * 74 + 1, (i + 1) * 74, (j + 1) * 74, 0, 0, boxWidth, boxHeight, null);
					if(gameDesk[i][j] == 2)
						g.drawImage(boxes[0], i * 74 + 1, j * 74 + 1, (i + 1) * 74, (j + 1) * 74, 0, 0, boxWidth, boxHeight, null);
					if(gameDesk[i][j] == 1)
						g.drawImage(boxes[2], i * 74 + 1, j * 74 + 1, (i + 1) * 74, (j + 1) * 74, 0, 0, boxWidth, boxHeight, null);
				}
			}
			g.setColor(Color.WHITE);
			g.drawImage(startp, pointLaser.x - 5, pointLaser.y - 5, pointLaser.x + 6, pointLaser.y + 6, 0, 0, 150, 150, null);
			drawLaser(g, pointLaser, direct, 0);
		}
	}
	private void drawLaser(Graphics g, Point now, int nowDir, int tt) {
		g.drawImage(laserrr, now.x - 1, now.y - 1, now.x + 2, now.y + 2, 0, 0, 11, 11, null);
		g.drawRect(now.x, now.y, 0, 0);
	
		if(now.x == pointEnd.x && now.y == pointEnd.y) winlvl = true;
		if(!winlvl && tt < 2000) {
			int newDir = nowDir;
			if(now.x % 74 == 0 && now.x < 740 && now.y < 444) {
				if(gameDesk[Math.min((now.x + 1) / 74, 9)][now.y / 74] >= 2) {
					if(nowDir == 1) newDir = 0;
					if(nowDir == 2) newDir = 3;
				}
				if(gameDesk[Math.max((now.x - 1) / 74, 0)][now.y / 74] >= 2) {
					if(nowDir == 0) newDir = 1;
					if(nowDir == 3) newDir = 2;
				}
			}
			if(now.y % 74 == 0 && now.x < 740  && now.y < 444) {
				if(gameDesk[now.x / 74][Math.min((now.y + 1) / 74, 6)] >= 2) {
					if(nowDir == 3) newDir = 0;
					if(nowDir == 2) newDir = 1;
				}
				if(gameDesk[now.x / 74][Math.max((now.y - 1) / 74, 0)] >= 2) {
					if(nowDir == 0) newDir = 3;
					if(nowDir == 1) newDir = 2;
				}
			}
			if(now.x < 800 && now.x > 0 && now.y < 600 && now.y > 0) {
				drawLaser(g, new Point(now.x + X[newDir], now.y + Y[newDir]), newDir, tt + 1);
			}
		}
	}
	private void initThisLvl() {
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 6; j++)
				gameDesk[i][j] = 0;
		if(DyeLaser.gamelvl == 0) setLvl6();
		if(DyeLaser.gamelvl == 1) setLvl7();
		if(DyeLaser.gamelvl == 2) setLvl2();
		if(DyeLaser.gamelvl == 3) setLvl3();
		if(DyeLaser.gamelvl == 4) setLvl4();
		if(DyeLaser.gamelvl == 5) setLvl5();
		if(DyeLaser.gamelvl == 6) setLvl0();
		if(DyeLaser.gamelvl == 7) setLvl1();
		DyeLaser.gamelvl++;
		winlvl = false;
	}
	private void setLvl0() {
		gameDesk[kside + 0][ktop + 2] = 2;
		gameDesk[kside + 2][ktop + 3] = 2;
		gameDesk[kside + 3][ktop + 1] = 2;
		gameDesk[kside + 5][ktop + 3] = 2;
		
		gameDesk[kside + 0][ktop + 3] = 1;
		gameDesk[kside + 3][ktop + 3] = 1;
		gameDesk[kside + 4][ktop + 0] = 1;
		gameDesk[kside + 5][ktop + 1] = 1;
		
		pointLaser = new Point(kside * 74 + 74, ktop * 74 + 37);
		pointEnd = new Point(kside * 74 + 74, ktop * 74 + 37 + 74);
		direct = 2;
	}
	private void setLvl1() {
		gameDesk[kside + 2][ktop + 1] = 2;
		gameDesk[kside + 2][ktop + 3] = 2;
		gameDesk[kside + 4][ktop + 2] = 2;
		
		gameDesk[kside + 2][ktop + 2] = 1;
		gameDesk[kside + 3][ktop + 1] = 1;
		gameDesk[kside + 3][ktop + 3] = 1;
		
		pointLaser = new Point(kside * 74 + 74 * 5, ktop * 74 + 37 + 74 * 3);
		pointEnd = new Point(kside * 74 + 74 * 5, ktop * 74 + 37 + 74 * 1);
		direct = 0;
	}
	
	private void setLvl2() {
		gameDesk[kside + 1][ktop + 2] = 2;
		gameDesk[kside + 2][ktop + 3] = 2;
		gameDesk[kside + 4][ktop + 1] = 2;
		
		gameDesk[kside + 2][ktop + 0] = 1;
		gameDesk[kside + 1][ktop + 1] = 1;
		gameDesk[kside + 2][ktop + 1] = 1;
		gameDesk[kside + 3][ktop + 1] = 1;
		gameDesk[kside + 4][ktop + 2] = 1;
		gameDesk[kside + 1][ktop + 3] = 1;
		gameDesk[kside + 3][ktop + 3] = 1;
		
		pointLaser = new Point(kside * 74 + 74 * 5, ktop * 74 + 37 + 74 * 3);
		pointEnd = new Point(kside * 74 + 74 * 4 + 37, ktop * 74 + 74 * 2);
		direct = 0;
	}
	
	private void setLvl3() {
		gameDesk[kside + 3][ktop + 1] = 2;
		gameDesk[kside + 4][ktop + 2] = 2;
		gameDesk[kside + 4][ktop + 3] = 2;
		gameDesk[kside + 2][ktop + 3] = 2;
		
		gameDesk[kside + 2][ktop + 1] = 1;
		gameDesk[kside + 4][ktop + 1] = 1;
		gameDesk[kside + 2][ktop + 2] = 1;
		gameDesk[kside + 3][ktop + 3] = 1;
		
		pointLaser = new Point(kside * 74 + 74 * 2, ktop * 74 + 37 + 74 * 2);
		pointEnd = new Point(kside * 74 + 74 * 2 + 37, ktop * 74 + 74 * 3);
		direct = 1;
	}
	private void setLvl4() {
		gameDesk[kside + 2][ktop + 0] = 2;
		gameDesk[kside + 5][ktop + 0] = 2;
		gameDesk[kside + 4][ktop + 1] = 2;
		gameDesk[kside + 3][ktop + 3] = 2;
		
		gameDesk[kside + 3][ktop + 0] = 1;
		gameDesk[kside + 4][ktop + 0] = 1;
		gameDesk[kside + 1][ktop + 1] = 1;
		gameDesk[kside + 2][ktop + 1] = 1;
		gameDesk[kside + 3][ktop + 1] = 1;
		gameDesk[kside + 5][ktop + 1] = 1;
		gameDesk[kside + 1][ktop + 2] = 1;
		gameDesk[kside + 2][ktop + 2] = 1;
		gameDesk[kside + 3][ktop + 2] = 1;
		gameDesk[kside + 5][ktop + 2] = 1;
		gameDesk[kside + 1][ktop + 3] = 1;
		gameDesk[kside + 2][ktop + 3] = 1;
		gameDesk[kside + 4][ktop + 3] = 1;

		pointLaser = new Point(kside * 74 + 74 * 4, ktop * 74 + 37 + 74 * 2);
		pointEnd = new Point(kside * 74 + 74 * 2 + 37, ktop * 74 + 74 * 3);
		direct = 2;
	}
	private void setLvl5() {
		gameDesk[kside + 0][ktop + 0] = 2;
		gameDesk[kside + 4][ktop + 0] = 2;
		gameDesk[kside + 4][ktop + 1] = 2;
		gameDesk[kside + 0][ktop + 2] = 2;
		
		gameDesk[kside + 1][ktop + 0] = 1;
		gameDesk[kside + 2][ktop + 0] = 1;
		gameDesk[kside + 3][ktop + 0] = 1;
		gameDesk[kside + 0][ktop + 1] = 1;
		gameDesk[kside + 3][ktop + 1] = 1;
		gameDesk[kside + 5][ktop + 1] = 1;
		gameDesk[kside + 1][ktop + 2] = 1;
		gameDesk[kside + 2][ktop + 2] = 1;
		gameDesk[kside + 3][ktop + 2] = 1;
		gameDesk[kside + 4][ktop + 2] = 1;
		gameDesk[kside + 5][ktop + 2] = 1;
		gameDesk[kside + 0][ktop + 3] = 1;
		gameDesk[kside + 1][ktop + 3] = 1;
		gameDesk[kside + 3][ktop + 3] = 1;
		gameDesk[kside + 4][ktop + 3] = 1;
		gameDesk[kside + 5][ktop + 3] = 1;

		pointLaser = new Point(kside * 74 + 74 * 3, ktop * 74 + 37 + 74 * 1);
		pointEnd = new Point(kside * 74 + 74 * 5, ktop * 74 + 74 * 2 + 37);
		direct = 3;
	}
	private void setLvl6() {
		gameDesk[kside + 3][ktop + 2] = 1;
		gameDesk[kside + 4][ktop + 1] = 2;
		pointLaser = new Point(kside * 74 + 74 * 2, ktop * 74 + 37 + 74 * 3);
		pointEnd = new Point(kside * 74 + 74 * 2, ktop * 74 + 74 * 1 + 37);
		direct = 1;
	}
	private void setLvl7() {
		gameDesk[kside + 4][ktop + 1] = 1;
		gameDesk[kside + 4][ktop + 2] = 1;
		gameDesk[kside + 2][ktop + 2] = 1;
		gameDesk[kside + 3][ktop + 3] = 1;
		
		gameDesk[kside + 3][ktop + 1] = 2;
		gameDesk[kside + 2][ktop + 3] = 2;
		pointLaser = new Point(kside * 74 + 74 * 5, ktop * 74 + 37 + 74 * 3);
		pointEnd = new Point(kside * 74 + 74 * 3 + 37, ktop * 74 + 74 * 3);
		direct = 0;
	}
}
