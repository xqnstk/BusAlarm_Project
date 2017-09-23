package BusAlarmScreen;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.BusAlarm;

public class BusSeat extends JPanel {
	BusAlarm busalarm;
	ImageIcon icStartScreen = new ImageIcon(this.getClass().getResource("/MainScreen2.jpg"));
	JLabel bStartScreenImage = new JLabel(icStartScreen);
	
	public BusSeat(BusAlarm busalarm) {
		this.busalarm=busalarm;
		setLayout(null);
		bStartScreenImage.setSize(1280,720);
		bStartScreenImage.setLocation(0,0);
		add(bStartScreenImage);
	}
} //BusSeat
