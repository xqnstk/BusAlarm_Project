package BusAlarmScreen;

import java.sql.DriverManager; //JDBC드라이버 클래스
import java.sql.ResultSet; //DB의 결과를 나타내는 클래스
import java.sql.SQLException; //DB에러 클래스
import java.sql.Connection; //DB와 연결하기 위한 클래스
import java.sql.Statement; //쿼리물 실행 후 결과 리턴 클래스
import java.util.Random; //랜덤

public class DBBus {
	static Connection conn = null; // DB와 연결하기 위한 conn객체 생성
	static Statement stmt = null; // 연결된 포트로 쿼리문을 보내도록 도와주는 stmt객체 생성
	static ResultSet rs = null; // 쿼리문 실행 후 결과값 리턴해주는 rs객체 생성

	String driverName = "com.mysql.jdbc.Driver"; // DB드라이버 검색
	String url = "jdbc:mysql://localhost:3306/dbbus";
	String user = "root";
	String password = "mirim0316";

	static int bus_num;
	static int low_floor_bus;
	static int seat_num;
	static int cnt = 0;

	public DBBus() {
		try {
			Class.forName(driverName); // 로드
			conn = DriverManager.getConnection(url, user, password); // 연결
			System.out.println("DB Connection OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("DB Connection Error!");
		}
	} // DBBus

	public static void insertDB() {
		try {
			stmt = conn.createStatement(); // 쿼리 실행을 위해 stmt생성
			double randomValue = Math.random();
			bus_num = ((int) (randomValue * 8999)) + 1000;// 버스번호
			low_floor_bus = (int) (randomValue * 2) + 2;// 저상 여부

			if (low_floor_bus == 2) {// 좌석 개수
				low_floor_bus = 1;
				seat_num = 24;
			} else {
				low_floor_bus = 0;
				seat_num = 26;
			}

			String sql = "insert into bus_info (bus_num, low_floor_bus, seat_num) values(" + bus_num + ", "
					+ low_floor_bus + ", " + seat_num + ")";
			String selectsql = "SELECT * FROM dbbus.bus_info";

			stmt.executeUpdate(sql); // DB에 삽입

			rs = stmt.executeQuery(selectsql); // 쿼리 전송 stmt객체를 이용하여 쿼리 실행 후
												// rs에 결과값 저장
			if (stmt.execute(selectsql)) {
				rs = stmt.getResultSet(); // 결과값 얻어오기
			}
		} catch (Exception ex) {
			// handle the error
			System.out.println("getresult error");
		}
		cnt = 0;
		try {
			while (rs.next()) { // rs에 저장된 데이터들을 읽어 결과값을 변수에 저장
				cnt++;

				bus_num = rs.getInt("bus_num"); // 버스 번호
				low_floor_bus = rs.getInt("low_floor_bus"); // 저상버스
				seat_num = rs.getInt("seat_num"); // 좌석 수
				//System.out.println(bus_num + " " + low_floor_bus + " " + seat_num); // 정보출력
			}
		} catch (Exception ex) {
			// handle the error
			System.out.println("print error");
		}
	}
}
