package ne;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ModelDb {
	
	Connection con = null;
	
	// 특정 컴퓨터를 찾기 위한 주소 체게
	// ip : 123.313.1313
	// url : "naver.com"
	// 특정 프로그램에 접근하기 위해 각 프로그램에 할당되는 세부 번지인 포트로 접근한다. 1-60000
	
	// 소켓 : ip + 포트. 특정 프로그램을 찾아가기 위한 주소체계가 포트이다.
	// 표준 프로토콜을 제공한다. 
	// http:// 아이피주소 : 포트 <- 포트는 따로 할당하지 않으면 자동으로 80이 됨
	
	// 즉, 특정 컴퓨터에 접근하기 위해서는 1.프로토콜이름 :// 2.아이피주소 3.포트    이 세 개가 필요하다
	
	// 프로토콜은 데이터의 형식이 무엇인지 알려줌. 데이터의 구조를 알려줌.
	// 어디에 어떤 정보가 있는지 미리 규약으로 정해 놓은 것. 어떤 내용이 들어가는지 등...
	// 메타정보는 값이 아닌 값을 설명하는 정보. 빠른 속도를 요구하는 서버는 프로토콜에 메타정보를 빼고 설계할 수 있다.
	
	private final String URL = "jdbc:mysql://localhost:3306/memo"; // Mysql 에는 테이블이 많기 때문에 마지막에 테이블까지 써 줘야 한다.
	private final String ID = "root";
	private final String PASSWORD = "mysql";
	
	public ModelDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
		}
	}

	// 생성
	public void create(Memo memo) {
		// 1. 데이터베이스 연결
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 쿼리를 실행
			String query = "INSERT INTO memo(name, content, datetime) values(?,?,?)";
			// 2.2 쿼리를 가능한 상태로 만들어 준다
			PreparedStatement pstmt = con.prepareStatement(query);
			// 2.3 물음표에 값을 세팅
			pstmt.setString(1, memo.title);
			pstmt.setString(2, memo.content);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			// 2.4 쿼리 실행
			pstmt.executeUpdate();
			System.out.println("저장되었습니다");
		} catch (Exception e) {
			
		}
	}
	
	// 업데이트
	public void update(Memo memo) {
		
	}
	
	// 삭제
	public void delete(int no) {
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 쿼리를 실행
			String query = "DELETE FROM memo WHERE no = "+no;
			// 2.2 쿼리를 가능한 상태로 만들어 준다
			Statement stmt = con.createStatement();
			// 2.4 쿼리 실행
			stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 조회
	public List<Memo> getList(){
		List<Memo> list = new ArrayList<>();
		// 1. 데이터베이스 연결
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 쿼리를 실행
			String query = "SELECT * FROM memo";
			// 2.2 쿼리를 가능한 상태로 만들어 준다
			Statement stmt = con.createStatement();
			// 2.3 쿼리를 실행-결과값을돌려받기 위해 쿼리를 실행
			ResultSet rs = stmt.executeQuery(query);
			// 3. 값 가져오기
			while(rs.next()) {
				Memo memo = new Memo();
				memo.no = rs.getInt("no");
				memo.title = rs.getString("name");
				memo.content = rs.getString("content");
				memo.date = rs.getLong("datetime");
				list.add(memo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 1개 조회
	public Memo read(int index) {
		Memo memo = new Memo();
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 쿼리를 실행
			String query = "SELECT * FROM memo where no = "+index;
			// 2.2 쿼리를 가능한 상태로 만들어 준다
			Statement stmt = con.createStatement();
			// 2.3 쿼리를 실행-결과값을돌려받기 위해 쿼리를 실행
			ResultSet rs = stmt.executeQuery(query);
			// 3. 값 가져오기
			if(rs.next()) {
				memo.no = rs.getInt("no");
				memo.title = rs.getString("name");
				memo.content = rs.getString("content");
				memo.date = rs.getLong("datetime");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memo;
	}

}
