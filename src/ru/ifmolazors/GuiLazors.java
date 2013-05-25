package ru.ifmolazors;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GuiLazors extends JPanel {

	static boolean OnExec, menugame, rub, co2, dye;
	static private GameMenu gMenu;
	static private RubinLaser gRuby;
	static private CO2Laser gCO2;
	static private DyeLaser gDye;
	public GuiLazors() throws IOException {
		this.setPreferredSize(new Dimension(800, 600));
		OnExec = true;
		setGameMenu();
	}
	public void setGameMenu() throws IOException {
		gMenu = new GameMenu();
		menugame = true;
		rub = false;
		co2 = false;
		dye = false;
	}
	public void setRuby() throws IOException {
		gRuby = new RubinLaser();
		menugame = false;
		rub = true;
		co2 = false;
		dye = false;
	}
	public void setCO2() throws IOException {
		gCO2 = new CO2Laser();
		menugame = false;
		rub = false;
		co2 = true;
		dye = false;
	}
	public void setDye() throws IOException {
		gDye = new DyeLaser();
		menugame = false;
		rub = false;
		co2 = false;
		dye = true;
	}
	@Override
	protected void paintComponent(Graphics g) {
		boolean flag = false;
		if(menugame == true && flag == false) {
			gMenu.render(g);
			flag = true;
		}
		if(rub == true && flag == false) {
			try {
				gRuby.render(g);
			} catch (Exception e) {}
			flag = true;
		}
		if(co2 == true && flag == false) {
			try {
				gCO2.render(g);
			} catch (Exception e) {}
			flag = true;
		}
		if(dye == true && flag == false) {
			try {
				gDye.render(g);
			} catch (Exception e) {}
			flag = true;
		}
	}
}
