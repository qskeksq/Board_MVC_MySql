package ne;
/**
 * POJO 데이터 클래스
 *
 */
public class Memo {
	int no;
	String title;
	String content;
	long date;
	
	public Memo() {
		date = System.currentTimeMillis();
	}
}
