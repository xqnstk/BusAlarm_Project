package Main;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import IntroScreen.*;
import BusAlarmScreen.BusAlarmPanel;
import BusAlarmScreen.BusAlarmPanel2;
import BusAlarmScreen.BusSeat;

public class BusAlarm extends JFrame {

	public static final int SCREEN_W = 1280;
	public static final int SCREEN_H = 720;

	private JPanel contentPane;

	public FirstPanel firstpanel = null;
	public IntroPanel intropanel = null;
	public BusAlarmPanel busalarmpanel=null;
	public BusAlarmPanel2 busalarmpanel2=null;
	public BusSeat busseat = null;


	public void change(String panelName) {
		if (panelName.equals("firstpanel")) {
			getContentPane().removeAll();
			getContentPane().add(firstpanel);
			revalidate();
			repaint();
		} else if(panelName.equals("intropanel")){
			getContentPane().removeAll();
			getContentPane().add(intropanel);
			revalidate();
			repaint();
		}
		else if(panelName.equals("busalarmpanel")){
			getContentPane().removeAll();
			getContentPane().add(busalarmpanel);
			revalidate();
			repaint();
		}
		else if(panelName.equals("busalarmpanel2")){
			getContentPane().removeAll();
			getContentPane().add(busalarmpanel2);
			revalidate();
			repaint();
		}
		else if(panelName.equals("busseat")){
			getContentPane().removeAll();
			getContentPane().add(busseat);
			revalidate();
			repaint();
		}
	}

	public static void setButton(JButton jb) {
		jb.setBorderPainted(false);
		jb.setContentAreaFilled(false);
		jb.setFocusPainted(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusAlarm frame = new BusAlarm();
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image img = toolkit.getImage("img/BusIcon.png");
					frame.setIconImage(img);
					frame.setTitle("버스 알림이");
					frame.firstpanel = new FirstPanel(frame);
					frame.intropanel = new IntroPanel(frame);
					frame.busalarmpanel = new BusAlarmPanel(frame);		
					frame.busalarmpanel2 = new BusAlarmPanel2(frame);		
					frame.busseat = new BusSeat(frame);
					
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setSize(SCREEN_W, SCREEN_H);
					frame.setResizable(false);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.getContentPane().add(frame.firstpanel);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusAlarm() {
		contentPane = new JPanel();
	}
}
