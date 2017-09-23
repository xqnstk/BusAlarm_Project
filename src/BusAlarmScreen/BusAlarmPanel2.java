package BusAlarmScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Main.BusAlarm;

public class BusAlarmPanel2 extends JPanel{

	BusAlarm busalarm;
	Bus bus;
	ImageIcon icMainScreen2 = new ImageIcon(this.getClass().getResource("/MainScreen2.jpg"));
	JLabel lbMainScreen=new JLabel(icMainScreen2);
	
	ImageIcon icMenuBar = new ImageIcon(this.getClass().getResource("/menubarbutton.png"));
	JButton bMenuBar = new JButton(icMenuBar);
	
	
	public BusAlarmPanel2(BusAlarm busalarm) {
		this.busalarm=busalarm;
		setLayout(null);
		
		bMenuBar.setIcon(icMenuBar); //시작하기 버튼
		bMenuBar.setSize(500, 80);
		bMenuBar.setLocation(BusAlarm.SCREEN_W/2-250,-30);
		BusAlarm.setButton(bMenuBar);
		bMenuBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { //버튼을 눌렀을 때
				busalarm.change("busalarmpanel"); //화면 전환
			}
		});
		add(bMenuBar);
		
		lbMainScreen.setSize(1280,720);
		lbMainScreen.setLocation(0,0);
		add(lbMainScreen);
		//add(lbMenuBar);
		
	}
	

	
	
}
