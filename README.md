# MVC 패턴으로 구현한 Board

- __Model__ : Mysql 영구저장소에 직접 접근하여 데이터를 create, update, delete, read 하는 역할을 한다.
- __View__ : 데이터를 입력받아 Controller 에 넘겨주며, Contrller 로부터 데이터를 넘겨받아 출력한다.
- __Controller__ : 데이터의 중간 역할을 한다. 입력 혹은 출력에서 어떠한 데이터의 변화가 있거나 데이터 추가할 경우가 생길 때 Controller 가 작업을 해 준다. Presenter 와 다른 점은 데이터 가공 이외에 어떠한 역할도 하지 않는다는 것이다.

### MySql 연동법

- 사전 작업 : Properties->java build path ->library -> jar 파일(MySQL\Connector.J 5.1) 추가

> url 을 통해 mysql 연결

```java
// Mysql 에는 테이블이 많기 때문에 마지막에 테이블까지 써 줘야 한다.
private final String URL = "jdbc:mysql://localhost:3306/memo";
private final String ID = "****";
private final String PASSWORD = "****";
```

> 생성(Create)

```java
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
```

> 조회(read)

```java
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
```
```java
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
```

> 삭제(delete)

```java
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
```
