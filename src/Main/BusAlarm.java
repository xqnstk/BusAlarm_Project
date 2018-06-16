package Main;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BusAlarmScreen.BusPanel;
import IntroScreen.FirstPanel;
import IntroScreen.IntroPanel;

public class BusAlarm extends JFrame {

	public static final int SCREEN_W = 1280;
	public static final int SCREEN_H = 720;
	public static int BUS_SPEED=2;
	public static final int SLEEP_TIME=5;

	private JPanel contentPane;

	public FirstPanel firstpanel = null;
	public IntroPanel intropanel = null;
	public BusPanel buspanel=null;

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
		else if(panelName.equals("buspanel")){
			getContentPane().removeAll();
			getContentPane().add(buspanel);
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
			        JFrame.setDefaultLookAndFeelDecorated(true); 
					BusAlarm frame = new BusAlarm();
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image img = toolkit.getImage("img/BusIcon.png");
					frame.setIconImage(img);
					frame.setTitle("버스 알림이");
					frame.firstpanel = new FirstPanel(frame);
					frame.intropanel = new IntroPanel(frame);
					frame.buspanel=new BusPanel(frame);
					
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
		
		//작업 표시줄의 닫기 버튼 누르면 종료됨.
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) { 
                    System.exit(0);
            }
    });

	}
}
