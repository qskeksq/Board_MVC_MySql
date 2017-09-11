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
	
	// Ư�� ��ǻ�͸� ã�� ���� �ּ� ü��
	// ip : 123.313.1313
	// url : "naver.com"
	// Ư�� ���α׷��� �����ϱ� ���� �� ���α׷��� �Ҵ�Ǵ� ���� ������ ��Ʈ�� �����Ѵ�. 1-60000
	
	// ���� : ip + ��Ʈ. Ư�� ���α׷��� ã�ư��� ���� �ּ�ü�谡 ��Ʈ�̴�.
	// ǥ�� ���������� �����Ѵ�. 
	// http:// �������ּ� : ��Ʈ <- ��Ʈ�� ���� �Ҵ����� ������ �ڵ����� 80�� ��
	
	// ��, Ư�� ��ǻ�Ϳ� �����ϱ� ���ؼ��� 1.���������̸� :// 2.�������ּ� 3.��Ʈ    �� �� ���� �ʿ��ϴ�
	
	// ���������� �������� ������ �������� �˷���. �������� ������ �˷���.
	// ��� � ������ �ִ��� �̸� �Ծ����� ���� ���� ��. � ������ ������ ��...
	// ��Ÿ������ ���� �ƴ� ���� �����ϴ� ����. ���� �ӵ��� �䱸�ϴ� ������ �������ݿ� ��Ÿ������ ���� ������ �� �ִ�.
	
	private final String URL = "jdbc:mysql://localhost:3306/memo"; // Mysql ���� ���̺��� ���� ������ �������� ���̺���� �� ��� �Ѵ�.
	private final String ID = "root";
	private final String PASSWORD = "mysql";
	
	public ModelDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
		}
	}

	// ����
	public void create(Memo memo) {
		// 1. �����ͺ��̽� ����
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 ������ ����
			String query = "INSERT INTO memo(name, content, datetime) values(?,?,?)";
			// 2.2 ������ ������ ���·� ����� �ش�
			PreparedStatement pstmt = con.prepareStatement(query);
			// 2.3 ����ǥ�� ���� ����
			pstmt.setString(1, memo.title);
			pstmt.setString(2, memo.content);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			// 2.4 ���� ����
			pstmt.executeUpdate();
			System.out.println("����Ǿ����ϴ�");
		} catch (Exception e) {
			
		}
	}
	
	// ������Ʈ
	public void update(Memo memo) {
		
	}
	
	// ����
	public void delete(int no) {
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 ������ ����
			String query = "DELETE FROM memo WHERE no = "+no;
			// 2.2 ������ ������ ���·� ����� �ش�
			Statement stmt = con.createStatement();
			// 2.4 ���� ����
			stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ü ��ȸ
	public List<Memo> getList(){
		List<Memo> list = new ArrayList<>();
		// 1. �����ͺ��̽� ����
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 ������ ����
			String query = "SELECT * FROM memo";
			// 2.2 ������ ������ ���·� ����� �ش�
			Statement stmt = con.createStatement();
			// 2.3 ������ ����-������������ޱ� ���� ������ ����
			ResultSet rs = stmt.executeQuery(query);
			// 3. �� ��������
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

	// 1�� ��ȸ
	public Memo read(int index) {
		Memo memo = new Memo();
		try (Connection con = DriverManager.getConnection(URL, ID, PASSWORD);) {
			// 2.1 ������ ����
			String query = "SELECT * FROM memo where no = "+index;
			// 2.2 ������ ������ ���·� ����� �ش�
			Statement stmt = con.createStatement();
			// 2.3 ������ ����-������������ޱ� ���� ������ ����
			ResultSet rs = stmt.executeQuery(query);
			// 3. �� ��������
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
