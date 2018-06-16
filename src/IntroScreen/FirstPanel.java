package IntroScreen;

import java.awt.event.*;

import javax.swing.*;

import Main.BusAlarm;

public class FirstPanel extends JPanel {

	BusAlarm busalarm;
	ImageIcon icStartScreen = new ImageIcon(this.getClass().getResource("/FirstScreen.jpg"));
	JButton bStartScreenImage = new JButton(icStartScreen);
	
	public FirstPanel(BusAlarm busalarm) {
		this.busalarm=busalarm;
		setLayout(null);
		bStartScreenImage.setSize(1280,720);
		bStartScreenImage.setLocation(0,0);
		BusAlarm.setButton(bStartScreenImage);
		add(bStartScreenImage);
		bStartScreenImage.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				busalarm.change("intropanel");
			}
		});
	}

}
