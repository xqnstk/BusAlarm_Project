package BusAlarmScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BusRoad extends JButton {
	Image icbusIcon=new ImageIcon(this.getClass().getResource("/BusIcon.png")).getImage();

	Point pos; //버스 좌표
	int busType;

	BusRoad(int x, int y,int busType) {
		pos = new Point(x, y); //버스의 좌표를 체크
		this.busType=busType;
	}

}
