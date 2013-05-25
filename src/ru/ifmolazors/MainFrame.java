package ru.ifmolazors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class MainFrame extends JFrame{
	private Container root;
	private GuiLazors gamegui;
	static public Point cursorLoc = new Point(0, 0);
	private boolean inRub(Point loc) {
		if(loc.x >= 67 && loc.x <= 250 && loc.y >= 200 && loc.y <= 348) 
			return true;
		return false;
	}
	private boolean inCO2(Point loc) {
		if(loc.x >= 307 && loc.x <= 490 && loc.y >= 200 && loc.y <= 348) 
			return true;
		return false;
	}
	private boolean inDye(Point loc) {
		if(loc.x >= 550 && loc.x <= 733 && loc.y >= 200 && loc.y <= 348) 
			return true;
		return false;
	}
	private void ClickOnMenu(Point cc) throws IOException {
		if(inRub(cc) == true) {
			gamegui.setRuby();
		}
		if(inCO2(cc) == true) {
			gamegui.setCO2();
		}
		if(inDye(cc) == true) {
			gamegui.setDye();
		}
	}
	private void ClickOnRuby(Point cc) throws IOException {
		if(RubinLaser.videomode == false) {
			if(cc.x >= 50 && cc.x <= 200 && cc.y >= 525 && cc.y <= 600) {
				if(RubinLaser.cntOfSlide == 0) gamegui.setGameMenu();
				else RubinLaser.cntOfSlide--;
			}
			if(cc.x >= 600 && cc.x <= 750 && cc.y >= 525 && cc.y <= 600) {
				if(RubinLaser.cntOfSlide == 3) gamegui.setGameMenu();
				else RubinLaser.cntOfSlide++;
			}
			if(RubinLaser.cntOfSlide == 3) {
				if(cc.x >= 200 && cc.x <= 600 && cc.y >= 250 && cc.y <= 350) {
					RubinLaser.videomode = true;
				}
			}
		}
	}
	private void ClickOnCO2(Point cc) throws IOException {
		if(CO2Laser.videomode == false) {
			if(cc.x >= 50 && cc.x <= 200 && cc.y >= 525 && cc.y <= 600) {
				if(CO2Laser.cntOfSlide == 0) gamegui.setGameMenu();
				else CO2Laser.cntOfSlide--;
			}
			if(cc.x >= 600 && cc.x <= 750 && cc.y >= 525 && cc.y <= 600) {
				if(CO2Laser.cntOfSlide == 3) gamegui.setGameMenu();
				else CO2Laser.cntOfSlide++;
			}
			if(CO2Laser.cntOfSlide == 3) {
				if(cc.x >= 200 && cc.x <= 600 && cc.y >= 250 && cc.y <= 350) {
					CO2Laser.videomode = true;
				}
			}
		}
	}
	private void ClickOnDye(Point cc) throws IOException {
		if(DyeLaser.gamemode == false) {
			if(cc.x >= 50 && cc.x <= 200 && cc.y >= 525 && cc.y <= 600) {
				if(DyeLaser.cntOfSlide == 0) gamegui.setGameMenu();
				else DyeLaser.cntOfSlide--;
			}
			if(cc.x >= 600 && cc.x <= 750 && cc.y >= 525 && cc.y <= 600) {
				if(DyeLaser.cntOfSlide == 3) gamegui.setGameMenu();
				else DyeLaser.cntOfSlide++;
			}
			if(DyeLaser.cntOfSlide == 3) {
				if(cc.x >= 150 && cc.x <= 650 && cc.y >= 350 && cc.y <= 465) {
					DyeLaser.gamelvl = 0;
					DyeLaser.gamemode = true;
				}
			}
		} else {
			if(cc.x >= 200 && cc.x <= 600 && cc.y >= 525 && cc.y <= 600) {
				DyeLaser.gamemode = false;
			}
			if(cc.x <= 740 && cc.x >= 0 && cc.y <= 444 && cc.y >= 0) {
				int inX = Math.min(9, cc.x / 74), inY = Math.min(5, cc.y / 74);
				if(LazorsGame.isClicked) {
					if(LazorsGame.gameDesk[inX][inY] == 1 || LazorsGame.gameDesk[inX][inY] == 3) {
						Point p = LazorsGame.whereClk;
						LazorsGame.gameDesk[p.x][p.y] = 1;
						LazorsGame.gameDesk[inX][inY] = 2;
						LazorsGame.isClicked = false;
					}
				} else {
					if(LazorsGame.gameDesk[inX][inY] == 2) {
						LazorsGame.gameDesk[inX][inY] = 3;
						LazorsGame.whereClk = new Point(inX, inY);
						LazorsGame.isClicked = true;
					}
				}
			}
		}
	}
	
	
	
	public MainFrame() throws IOException {
		super("Lazors");
		root = getContentPane();
        gamegui = new GuiLazors();
        gamegui.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Point clk = new Point(e.getX(), e.getY());
				try {
					if(gamegui.menugame == true) ClickOnMenu(clk);
					if(gamegui.rub == true) ClickOnRuby(clk);
					if(gamegui.co2 == true) ClickOnCO2(clk);
					if(gamegui.dye == true) ClickOnDye(clk);
				} catch (IOException e1) {}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
        gamegui.addMouseMotionListener(new MouseMotionListener() {
        	@Override
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e) {
            	cursorLoc = new Point(e.getX(), e.getY()); 
            	if(inRub(cursorLoc)) 
            		GameMenu.bufferBack = GameMenu.backimgRub;
            	else if(inCO2(cursorLoc)) 
            			GameMenu.bufferBack = GameMenu.backimgCO2;
            	else if(inDye(cursorLoc))
            			GameMenu.bufferBack = GameMenu.backimgDye;
            	else GameMenu.bufferBack = GameMenu.backimg;
            }
         });
        root.add(gamegui);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        new Thread(new GuiThread()).start();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
					new MainFrame().setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
    }
    class GuiThread implements Runnable{
        @Override
        public void run() {
        	while(gamegui.OnExec) {
        		gamegui.repaint();
        	}
        }
    }
	
}
