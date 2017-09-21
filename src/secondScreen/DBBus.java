package secondScreen;

import java.sql.DriverManager; //JDBC드라이버 클래스
import java.sql.ResultSet; //DB의 결과를 나타내는 클래스
import java.sql.SQLException; //DB에러 클래스
import java.sql.Connection; //DB와 연결하기 위한 클래스
import java.sql.Statement; //쿼리물 실행 후 결과 리턴 클래스

public class DBBus {
	Connection conn = null; //DB와 연결하기 위한 conn객체 생성
	Statement stmt = null; //연결된 포트로 쿼리문을 보내도록 도와주는 stmt객체 생성
	ResultSet rs = null; //쿼리문 실행 후 결과값 리턴해주는 rs객체 생성
	
	String driverName = "com.mysql.jdbc.Driver"; //DB드라이버 검색
	String url = "jdbc:mysql://localhost:3306/dbBus"; 
	String user = "dbuser";
	String password = "dbpass";
	
	public DBBus(){
		try {
			Class.forName(driverName); //로드
			conn = DriverManager.getConnection(url, user, password); //연결
			System.out.println("DB Connection OK");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		} catch(SQLException se) {
			se.printStackTrace();
			System.out.println("DB Connection Error!");
		}
		
		try {
			stmt = conn.createStatement(); //쿼리 실행을 위해 stmt생성
			rs = stmt.executeQuery("SELECT * FROM dbbus.bus_info"); //쿼리 전송 stmt객체를 이용하여 쿼리 실행 후 rs에 결과값 저장

			if (stmt.execute("SELECT * FROM dbbus.bus_info")) {
				rs = stmt.getResultSet(); //결과값 얻어오기
			}
		} catch(Exception ex) {
			// handle the error
			System.out.println("getresult error");
		}

		try{
			while (rs.next()) { //rs에 저장된 데이터들을 읽어 결과값을 변수에 저장
				int bus_num = rs.getInt("bus_num");
				boolean low_floor_bus = rs.getBoolean("low_floor_bus"); 
				int seat_num = rs.getInt("seat_num");
				System.out.println(bus_num + " " + low_floor_bus + " " +seat_num); //정보 출력
			} 
		}catch(Exception ex) {
			// handle the error
			System.out.println("print error");
		}
		
	} //DBBus

	public static void main(String[] args) {
		DBBus dbbus = new DBBus();
		
	}
}
