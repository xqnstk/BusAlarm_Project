package BusAlarmScreen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Main.BusAlarm;

public class BusPanel extends JPanel implements Runnable, ActionListener {

	BusAlarm busalarm;

	Image imgbusicon = new ImageIcon(this.getClass().getResource("/BusIcon.png")).getImage();
	ImageIcon icmainScreen = new ImageIcon(this.getClass().getResource("/MainScreen2.jpg"));
	ImageIcon icbusIcon = new ImageIcon(this.getClass().getResource("/BusIcon.png"));
	ImageIcon icbusRoad_green = new ImageIcon(this.getClass().getResource("/busRoad_green.png"));
	ImageIcon icbusRoad_yellow = new ImageIcon(this.getClass().getResource("/busRoad_yelllow.png"));
	ImageIcon icbusRoad_red = new ImageIcon(this.getClass().getResource("/busRoad_red.png"));
	ImageIcon half_icbusRoad_green = new ImageIcon(this.getClass().getResource("/half_busRoad_green.png"));
	ImageIcon half_icbusRoad_yellow = new ImageIcon(this.getClass().getResource("/half_busRoad_yellow.png"));
	ImageIcon half_icbusRoad_red = new ImageIcon(this.getClass().getResource("/half_busRoad_red.png"));
	ImageIcon icbusStop = new ImageIcon(this.getClass().getResource("/busStopButton.png"));
	ImageIcon icbusStopSelect = new ImageIcon(this.getClass().getResource("/busStopButtonSelect.png"));
	ImageIcon icmainScreenBar = new ImageIcon(this.getClass().getResource("/MenuBar.png"));
	ImageIcon icfold = new ImageIcon(this.getClass().getResource("/foldbutton2.png"));
	ImageIcon icbusRoad;
	ImageIcon icbusSeat = new ImageIcon(this.getClass().getResource("/BusSeat.png"));
	ImageIcon icbusSeat2 = new ImageIcon(this.getClass().getResource("/BusSeat_26.png"));
	ImageIcon icseated = new ImageIcon(this.getClass().getResource("/seated.png"));
	ImageIcon icCongestion_green = new ImageIcon(this.getClass().getResource("/bus_Congestion_green.png"));
	ImageIcon icCongestion_yellow = new ImageIcon(this.getClass().getResource("/bus_Congestion_yellow.png"));
	ImageIcon icCongestion_red = new ImageIcon(this.getClass().getResource("/bus_Congestion_red.png"));

	JLabel lbbusInfo;
	JLabel label;
	JButton bbusStop[][] = new JButton[13][10];
	JLabel lbbusStop[][] = new JLabel[13][10];
	JLabel lbmainScreenBar = new JLabel(icmainScreenBar);
	JButton bfoldButton = new JButton(icfold);
	JLabel lbbusPassenger;
	JLabel lbcongestion;
	ImageIcon icCongestion;

	Calendar calendar1 = Calendar.getInstance();
	int year = calendar1.get(Calendar.YEAR);
	int month = calendar1.get(Calendar.MONTH);
	int day = calendar1.get(Calendar.DAY_OF_MONTH);
	int hour = calendar1.get(Calendar.HOUR_OF_DAY);
	int min = calendar1.get(Calendar.MINUTE);
	int sec = calendar1.get(Calendar.SECOND);
	Timer timer;
	JLabel lbPresent;

	boolean menubarVisible = true;

	int i = 0, j = 0, count = 0;
	BusAPI busapi = new BusAPI();

	int x, y;
	Thread th;

	ArrayList Bus_List = new ArrayList();
	ArrayList BusStop_List = new ArrayList();
	ArrayList BusRoad_List = new ArrayList();
	ArrayList BusPassenger_List = new ArrayList();
	ArrayList BusCongestion_List = new ArrayList();

	Bus bus; // 버스 접근 키
	BusStop busStop;
	BusRoad busRoad;

	static int time;
	static int busCnt;
	static int busStopCnt;

	int gap, linecnt = 0, bus_speed = 1, many; // 버스 개수, 홀 or짝수줄 인식, 버스 속도, 한 줄
												// 버스 개수
	int busgap[] = new int[3]; // 버스 간 간격
	int list1_x[][] = {{ 34, 211, 34, 211, 34, 211, 34, 211, 34, 34, 80, 170, 216, 34, 80, 170, 216, 34, 216, 34, 80, 126, 170, 216 }
	,{34, 211, 34, 211, 34, 211, 34, 211, 34, 34, 80, 170, 216, 34, 80, 170, 216, 34, 80, 170, 216, 34, 80, 126, 170, 216 }};
	int list1_y[][] = {{ 103, 103, 148, 148, 194, 194, 240, 240, 286, 353, 353, 353, 353, 397, 397, 397, 397, 440, 440, 484, 484, 484, 484, 484 }
	,{ 103, 103, 148, 148, 194, 194, 240, 240, 286, 353, 353, 353, 353, 397, 397, 397, 397, 440, 440, 440, 440, 484, 484, 484, 484, 484 }};
	int seat_max; //버스좌석 최대 수
	int anum=0; //list1_x에 쓸 24, 26구별하는 친구
	BusAlarmScreen.DBBus dbbus = new BusAlarmScreen.DBBus();

	ActionListener busStopListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			BusStop bs = (BusStop) e.getSource();
			if (e.getSource() instanceof BusStop) {
				final Frame frbusStop = new Frame("busStop");
				JPanel p = new JPanel();
				JLabel waiting_passenger;

				waiting_passenger = new JLabel("기다리는 승객 수 : " + bs.ride_passenger);
				p.add(waiting_passenger);

				System.out.println(bs.pos.x + " " + bs.pos.y);
				p.setBackground(Color.WHITE);

				frbusStop.add(p);
				frbusStop.setVisible(true);
				frbusStop.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						frbusStop.setVisible(false);
						frbusStop.dispose();
					}
				});
				frbusStop.setSize(400, 250);
				frbusStop.setLocation(200, 200);
				frbusStop.setResizable(false);
				frbusStop.setLocationRelativeTo(null);
			}

		}
	};
	ActionListener busListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Bus b = (Bus) e.getSource();
			if (e.getSource() instanceof Bus) {
				final Frame frbusStop = new Frame("Bus");
				String low_floor;
				int leftseat;
				JPanel p = new JPanel();
				p.setLayout(null);
				p.setBackground(Color.WHITE);

				JLabel lbseated[] = new JLabel[b.busPassenger];
				int cnt = 0;
				
				if (b.bseat == 24) {
					seat_max = 24;
					anum=0;
					JLabel lbbusSeat = new JLabel(icbusSeat);
					lbbusSeat.setBounds(15, 10, 263, 523);
					p.add(lbbusSeat);
				} else if (b.bseat == 26) {
					seat_max = 26;
					anum=1;
					JLabel lbbusSeat = new JLabel(icbusSeat2);
					lbbusSeat.setBounds(15, 10, 263, 523);
					p.add(lbbusSeat);
				}
				
				if (b.busPassenger > 23) {
					leftseat=0;
					for (int i = 0; i < 24; i++) {
						if (b.bseat == 24 && (i == 18 || i == 19)) {
							continue;
						} // 좌석 24개면 이 좌표
						leftseat=0;
						lbseated[i] = new JLabel(icseated);
						lbseated[i].setBounds(list1_x[anum][i], list1_y[anum][i],47,40);
						p.add(lbseated[i]);
						cnt++;
					}
				} else {
					leftseat = seat_max - b.busPassenger;
					for (int i = 0; i < b.busPassenger; i++) {
						if (b.bseat == 24 && (i == 18 || i == 19)) {
							continue;
						}
						lbseated[i] = new JLabel(icseated);
						lbseated[i].setBounds(list1_x[anum][b.busSeat_numbers[i]], list1_y[anum][b.busSeat_numbers[i]], 47, 40);
						p.add(lbseated[i]);
						cnt++;
					}
				}
				if (DBBus.low_floor_bus == 1) {
					low_floor = "저상버스";
				} else {
					low_floor = "지상버스";
				}


				JLabel passenger = new JLabel("현재 승객수 : " + Integer.toString(b.busPassenger));// +Integer.toString(bus.busPassenger)
				passenger.setFont(new Font("나눔고딕", Font.BOLD, 13));
				JLabel number = new JLabel("버스 번호 : " + b.bnum);// bus.bnum
				number.setFont(new Font("나눔고딕", Font.BOLD, 13));

				JLabel low_bus = new JLabel("저상 여부 : " + b.bfloor);// +bus.bfloor
				low_bus.setFont(new Font("나눔고딕", Font.BOLD, 13));

				JLabel cnt_seat = new JLabel("좌석 총 개수 : " + b.bseat + "개");// +
																			// bus.bseat+"개"
				cnt_seat.setFont(new Font("나눔고딕", Font.BOLD, 13));

				JLabel left_seat = new JLabel("남은 좌석 수 : " + Integer.toString(b.bseat - cnt));
				left_seat.setFont(new Font("나눔고딕", Font.BOLD, 13));

				passenger.setBounds(19, 540, 166, 30);
				number.setBounds(19, 570, 166, 30);
				low_bus.setBounds(19, 600, 166, 30);
				cnt_seat.setBounds(19, 630, 166, 30);
				left_seat.setBounds(160, 630, 166, 30);

				p.add(passenger);
				p.add(number);
				p.add(low_bus);
				p.add(cnt_seat);
				p.add(left_seat);
				frbusStop.add(p);
				frbusStop.setVisible(true);

				frbusStop.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						frbusStop.setVisible(false);
						frbusStop.dispose();
					}
				});
				frbusStop.setSize(300, 700);
				frbusStop.setLocation(200, 200);
				frbusStop.setResizable(false);
				frbusStop.setLocationRelativeTo(null);
			}
		}
	};

	public BusPanel(BusAlarm busalarm) {

		super(true);
		setLayout(null);
		setBackground(new Color(255, 243, 225));
		label = new JLabel();
		label.setBackground(Color.YELLOW);
		label.setBounds(0, 0, 1280, 720);

		JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 1000, 80, 0, 1500);
		vbar.setBounds(1245, 0, 26, 686);
		vbar.addAdjustmentListener(new MyAdjustmentListener());
		add(vbar);
		add(label);

		lbPresent = new JLabel(year + "-" + month + "-" + day + "   " + hour + ":" + min + ":" + sec,
				SwingConstants.RIGHT);
		lbPresent.setVerticalAlignment(SwingConstants.TOP);
		lbPresent.setBounds(1010, 5, 220, 40);
		lbPresent.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(lbPresent);

		timer = new Timer(1000, this);
		timer.setInitialDelay(0);
		timer.start();

		busapi.GetBusPassengerInfo(hour);// 현재 시각에 따른 버스 승객수를 api에서 받아오기

		for (i = 0; i < 13; i++) {
			// 버스정류장
			for (j = 0; j < 10; j++) {

				lbbusStop[i][j] = new JLabel();
				lbbusStop[i][j].setText(busapi.GetBusStopInfo(count));// busapi.GetBusStopInfo(count)
				count++;
				if (i % 2 != 0) {
					lbbusStop[i][j].setBounds(115 * (9 - j) + 77, 118 * i + 226, 110, 70);
					add(lbbusStop[i][j]);
					busStop = new BusStop(115 * (9 - j) + 90, 118 * i + 227, busStopCnt);
					busStopCnt++;
				} else {
					lbbusStop[i][j].setBounds(115 * j + 77, 118 * i + 226, 110, 60);
					add(lbbusStop[i][j]);
					busStop = new BusStop(115 * j + 90, 118 * i + 227, busStopCnt);
					busStopCnt++;
				}
				busStop.setIcon(icbusStop);
				busStop.setBounds(busStop.pos.x, busStop.pos.y, 16, 16);
				BusAlarm.setButton(busStop);
				BusStop_List.add(busStop);
				add(busStop);

				busStop.addActionListener(busStopListener);

			}
			// 버스 도로 정체 정보
			for (j = -1; j < 10; j++) {
				int randomRoad = (int) (Math.random() * 3);

				if (j == -1) {
					if (i != 0 && i % 2 != 0) {
						if (randomRoad == 0) {
							icbusRoad = half_icbusRoad_red;
						} else if (randomRoad == 1) {
							icbusRoad = half_icbusRoad_yellow;
						} else {
							icbusRoad = half_icbusRoad_green;
						}
						busRoad = new BusRoad(10, 118 * i + 228, randomRoad + 1);// 1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(10, 118 * i + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);

						busRoad = new BusRoad(10, 118 * (i + 1) + 228, randomRoad + 1);// 1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(10, 118 * (i + 1) + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);
					}
				} else if (j == 9) {
					if (i != 12 && i % 2 == 0) {
						if (randomRoad == 0) {
							icbusRoad = half_icbusRoad_red;
						} else if (randomRoad == 1) {
							icbusRoad = half_icbusRoad_yellow;
						} else {
							icbusRoad = half_icbusRoad_green;
						}
						busRoad = new BusRoad(1105, 118 * i + 228, randomRoad + 1);// 1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(1105, 118 * i + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);

						busRoad = new BusRoad(1105, 118 * (i + 1) + 228, randomRoad + 1);// 1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(1105, 118 * (i + 1) + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);
					}
				} else {
					if (randomRoad == 0) {
						icbusRoad = icbusRoad_red;
					} else if (randomRoad == 1) {
						icbusRoad = icbusRoad_yellow;
					} else {
						icbusRoad = icbusRoad_green;
					}
					busRoad = new BusRoad(115 * j + 95, 118 * i + 228, randomRoad + 1);// 1,2,3
					busRoad.setIcon(icbusRoad);
					busRoad.setBounds(115 * j + 95, 118 * i + 228, 120, 12);
					BusAlarm.setButton(busRoad);
					add(busRoad);
					BusRoad_List.add(busRoad);
				}

			}

		}
		addMenu();

		for (int i = 0; i < busapi.BusPassengerRide_List.size(); i++) {
			busStop = (BusStop) BusStop_List.get(i);
			busStop.setBusRidePassenger(busapi.BusPassengerRide_List.get(i));
			busStop.setBusAlightPassenger(busapi.BusPassengerAlight_List.get(i));
		}

		init();
		start();
	}

	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {

			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 10; j++) {
					if (i % 2 != 0) {
						lbbusStop[i][j].setLocation(115 * (9 - j) + 77, e.getValue() - (-118 * i + 774));
						add(lbbusStop[i][j]);
					} else {
						lbbusStop[i][j].setLocation(115 * j + 77, e.getValue() - (-118 * i + 774));
						add(lbbusStop[i][j]);
					}
				}
			}
			for (int i = 0; i < BusStop_List.size(); ++i) {
				busStop = (BusStop) (BusStop_List.get(i));
				busStop.setBounds(busStop.pos.x, busStop.pos.y + lbbusStop[1][1].getY() - 345, 16, 16);
				add(busStop);
			}
			for (int i = 0; i < BusRoad_List.size(); ++i) {
				busRoad = (BusRoad) (BusRoad_List.get(i));
				busRoad.setBounds(busRoad.pos.x, busRoad.pos.y + lbbusStop[1][1].getY() - 345, 120, 12);
				add(busRoad);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		++sec;
		Calendar calendar2 = Calendar.getInstance();
		year = calendar2.get(Calendar.YEAR);
		month = calendar2.get(Calendar.MONTH);
		day = calendar2.get(Calendar.DAY_OF_MONTH);
		hour = calendar2.get(Calendar.HOUR_OF_DAY);
		min = calendar2.get(Calendar.MINUTE);
		sec = calendar2.get(Calendar.SECOND);
		lbPresent.setText(year + "-" + month + "-" + day + "   " + hour + ":" + min + ":" + sec);
	}

	public void init() {
		x = 50;// 버스 초기위치
		y = 130;
	}

	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void BusGap() { // 시간대별로 버스 총 개수 정하기
		if ((7 <= hour && hour <= 9) || (12 <= hour && hour <= 14) || (18 <= hour && hour <= 20)) { // 버스가 많을 시간(아침,점심,저녁)
			gap = 40;
		} else if ((5 <= hour && hour <= 6) || (22 <= hour && hour <= 24)) { // 없음
			gap = 20;
		} else { // 평균
			gap = 30;
		}
	}

	public void changexy() { // 버스 초기위치 설정.
		x = 10;
		y += 118;

		many = (int) ((Math.random() * 2) + 2);
		for (i = 0; i < many; i++) { // 한 줄에 버스 2, 3개정도
			if (many == 3) {
				busgap[0] = (int) ((Math.random() * 300) + 100);
				busgap[1] = (int) ((Math.random() * 200) + 400);
				busgap[2] = (int) ((Math.random() * 300) + 700);
			} else if (many == 2) {
				busgap[0] = (int) ((Math.random() * 300) + 100);
				busgap[1] = (int) ((Math.random() * 500) + 500);
			}
			x = busgap[i];

			BusProcess();
			if (linecnt % 2 == 0) { // 뒤로 가기
				bus.busDir = -bus_speed;
			}
		}
		linecnt++;
	}

	public void run() {
		try {
			BusGap(); // 버스 몇 대 세팅해놀지 시간별로 정해줌
			for (int i = 0; i < gap; i++) {
				changexy(); // 버스의 좌표값 주기
			}

			x = 50;// 버스 초기위치 재설정
			y = 130;
			while (true) {
				BusProcess();
				for (int i = 0; i < Bus_List.size(); ++i) {
					bus = (Bus) (Bus_List.get(i));
					lbbusPassenger = (JLabel) (BusPassenger_List.get(i));
					lbcongestion = (JLabel) (BusCongestion_List.get(i));
					bus.move(); // 버스 움직이기
					bus.setBounds(bus.pos.x, bus.pos.y + lbbusStop[1][1].getY() - 315, 48, 65);
					lbbusPassenger.setBounds(bus.pos.x + 17, bus.pos.y + lbbusStop[1][1].getY() - 300, 50, 20);
					lbcongestion.setBounds(bus.pos.x + 50, bus.pos.y + lbbusStop[1][1].getY() - 310, 30, 30);

					for (int j = 0; j < BusRoad_List.size(); j++){ //버스 도로
						busRoad = (BusRoad) (BusRoad_List.get(j));

						if (bus.line % 2 == 0) {// 짝수줄
							if ((bus.pos.x - 115 == busRoad.pos.x) && (bus.pos.y + 98 == busRoad.pos.y)) {
								// bus.busSpeed=busRoad.busType;
							}
						} else {
							if ((bus.pos.x + 20 == busRoad.pos.x) && (bus.pos.y + 98 == busRoad.pos.y)) {
								// bus.busSpeed=busRoad.busType;
							}
						}

					}
					for (int j = 0; j < BusStop_List.size(); j++) {
						busStop = (BusStop) (BusStop_List.get(j));
						if ((bus.pos.x + 20 == busStop.pos.x) && bus.pos.y + 97 == busStop.pos.y) {
							if (bus.flag == true) {
								bus.flag = false;
								bus.arriveBus(busStop.ride_passenger, busStop.alight_passenger);// 버스 도착하면 정류장에 있던 사람들은 버스에 타고, 버스에서는 승객이 하차한다.
								
								lbbusPassenger.setText("" + bus.busPassenger);

								if (bus.busPassenger > 40) {
									icCongestion = icCongestion_red;
								} else if (bus.busPassenger > 25) {
									icCongestion = icCongestion_yellow;
								} else {
									icCongestion = icCongestion_green;
								}
								lbcongestion.setIcon(icCongestion);
								
								bus.seat(bus.busPassenger);

								if (busStop.ride_passenger != 0) {
									busStop.ride_passenger = (int) (Math.random() * busStop.ride_passenger)
											+ (busStop.ride_passenger - 2);// 시간에 따른 busStop의 탑승객수를 random으로 발생시켜 현실성 반영
									busStop.increaseBusPassenger(busStop.ride_passenger);
								}
							}
						}
						if (bus.pos.x == busStop.pos.x) {
							bus.flag = true;
						}
					}
					add(bus);
				}
				Thread.sleep(200);
				time++;
			}
		} 
		catch (Exception e) {
		}
	}

	public void BusProcess() { // 버스 처리 메소드

		if (time % 600 == 0) {
			DBBus.insertDB();
			bus = new Bus(x, y, busCnt); // 좌표 체크하여 넘기기
			busCnt++;
			bus.bnum = dbbus.bus_num;
			bus.bfloor = dbbus.low_floor_bus;
			bus.bseat = dbbus.seat_num;

			Bus_List.add(bus); // 버스 추가
			bus.setIcon(icbusIcon);
			bus.setBounds(bus.pos.x, bus.pos.y + lbbusStop[1][1].getY() - 315, 48, 65);
			BusAlarm.setButton(bus);
			bus.addActionListener(busListener);
			add(bus);

			lbbusPassenger = new JLabel("" + bus.busPassenger);
			lbbusPassenger.setBounds(bus.pos.x + 17, bus.pos.y + lbbusStop[1][1].getY() - 290, 50, 20);
			lbbusPassenger.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			add(lbbusPassenger);
			BusPassenger_List.add(lbbusPassenger);

			if (bus.busPassenger > 40) {
				icCongestion = icCongestion_red;
			} else if (bus.busPassenger > 25) {
				icCongestion = icCongestion_yellow;
			} else {
				icCongestion = icCongestion_green;
			}
			lbcongestion = new JLabel(icCongestion);
			lbcongestion.setBounds(bus.pos.x + 50, bus.pos.y + lbbusStop[1][1].getY() - 310, 30, 30);
			add(lbcongestion);
			BusCongestion_List.add(lbcongestion);
		}
	}

	public void addMenu() {
		// 메뉴바
		lbbusInfo = new JLabel();
		lbbusInfo.setFont(new Font("나눔고딕 Bold", Font.PLAIN, 18));
		lbbusInfo.setText(busapi.GetBusInfo());// busapi.GetBusInfo()
		lbbusInfo.setSize(500, 100);
		lbbusInfo.setLocation(0, 0);
		add(lbbusInfo);

		lbmainScreenBar.setSize(1280, 120);
		lbmainScreenBar.setLocation(0, 0);
		add(lbmainScreenBar);

		bfoldButton.setIcon(icfold);
		bfoldButton.setSize(90, 26);
		bfoldButton.setLocation(BusAlarm.SCREEN_W / 2 - 45, 120);
		BusAlarm.setButton(bfoldButton);
		bfoldButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (menubarVisible == false) {
					lbmainScreenBar.setVisible(true);
					lbbusInfo.setVisible(true);
					menubarVisible = true;
					bfoldButton.setLocation(BusAlarm.SCREEN_W / 2 - 45, 120);
				} else {
					lbmainScreenBar.setVisible(false);
					lbbusInfo.setVisible(false);
					menubarVisible = false;
					bfoldButton.setLocation(BusAlarm.SCREEN_W / 2 - 45, 0);
				}

			}
		});
		add(bfoldButton);
	}

}
