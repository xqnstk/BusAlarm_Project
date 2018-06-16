package BusAlarmScreen;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class BusStop extends JButton {

	Point pos; // 버스 좌표
	BusStop busStop;
	int ride_passenger;// 타기위해기다리는사람
	int alight_passenger;// 내릴사람
	int busStopCnt = 0;
	int tmp_passenger = 0;
	int i = 0;

	BusStop(int x, int y, int busStopCnt) {
		pos = new Point(x, y); // 버스의 좌표를 체크
		this.busStopCnt = busStopCnt;
	}

	void setPos(int x, int y) {
		pos.x = x;
		pos.y = y;
	}

	void setBusRidePassenger(int ride_passenger) {
		this.ride_passenger = ride_passenger;
	}

	void setBusAlightPassenger(int alight_passenger) {
		this.alight_passenger = alight_passenger;
	}

	void increaseBusPassenger(int passenger) { // 버스 정류장의 탑승객이 증가하는 것을 타이머를 사용하여 표현.
		ride_passenger = 0;
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			public void run() {
				ride_passenger = passenger / 3;
			}
		};
		m_timer.schedule(m_task, 2000);

		Timer m_timer1 = new Timer();
		TimerTask m_task1 = new TimerTask() {
			public void run() {
				ride_passenger = passenger / 2;
			}
		};
		m_timer1.schedule(m_task1, 4000);

		Timer m_timer2 = new Timer();
		TimerTask m_task2 = new TimerTask() {
			public void run() {
				ride_passenger = passenger;
			}
		};
		m_timer2.schedule(m_task2, 7000);

	}

}
