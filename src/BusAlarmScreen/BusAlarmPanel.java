package BusAlarmScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Main.BusAlarm;

public class BusAlarmPanel extends JPanel{

	BusAlarm busalarm;
	Bus bus;
	ImageIcon icMainScreen2 = new ImageIcon(this.getClass().getResource("/MainScreen2.jpg"));
	
	JLabel lbMainScreen=new JLabel(icMainScreen2);
	public BusAlarmPanel(BusAlarm busalarm) {
		this.busalarm=busalarm;
		setLayout(null);
		lbMainScreen.setSize(1280,720);
		lbMainScreen.setLocation(0,0);
		add(lbMainScreen);
	}

}
