package BusAlarmScreen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class Bus extends JButton {

	Point pos; // 버스 좌표
	int busPassenger = (int) (Math.random() * 15) + 5;// 버스 초기 승객수
	String degreeOfCongestion = null;// 버스혼잡도
	String name;
	int busCnt = 0;
	boolean flag = true;

	int bnum, bfloor, bseat;
	int xy = 0;

	int busDir = 1, line = 1,busSpeed = 1;

	int busSeat_numbers[];

	Bus(int x, int y, int busCnt) {
		pos = new Point(x, y); // 버스의 좌표를 체크
		name = Integer.toString(busCnt);
		this.busCnt = busCnt;
	}

	public void move() { // 버스 이동을 위한 메소드

		if (pos.x > 1150 || pos.x < 0) {
			busDir = -busDir;
			pos.y += 118;
			line++;
		}
		pos.x += busDir * busSpeed;
	}

	public void countCongestion(int busPassenger) {
		if (busPassenger > 40) {
			degreeOfCongestion = "혼잡";
		} else if (busPassenger > 25) {
			degreeOfCongestion = "보통";
		} else {
			degreeOfCongestion = "여유";
		}
	}

	public void arriveBus(int busStop_ride_passenger, int busStop_alight_passenger) {

		busPassenger += busStop_ride_passenger;
		busPassenger -= busStop_alight_passenger;
		if (busPassenger < 0) {
			busPassenger = 0;
		}
		if (busPassenger > 45) {
			busPassenger = 45;
		}
		busSpeed = 0;
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			public void run() {
				busSpeed = 1;
			}
		};
		m_timer.schedule(m_task, 1000);

	}

	public void seat(int busPassenger) {
		if (busPassenger < 24) {
			busSeat_numbers = new int[busPassenger + 1];
			for (int k = 0; k < busSeat_numbers.length; k++) {
				busSeat_numbers[k] = k; //(int) (Math.random() * 24);
			}
		}

	}
}
