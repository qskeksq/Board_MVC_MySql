package ne;

import java.util.List;
import java.util.Scanner;

/**
 * View 
 * 입력, 출력
 */
public class View {
	
	ModelDb model;
	Controller controller;
	Scanner scanner;
	
	public View(Controller controller) {
		model = new ModelDb();
		this.controller = controller;
		scanner = new Scanner(System.in);
	}
	
	// 입력창
	public void intro() {
		println("------------------명령어를 입력하세요------------------");
		println("|c : 쓰기|r : 읽기|u : 수정|d : 삭제|l : 목록|x : 종료|");
		println("-------------------------------------------------");
		print("선택>");
	}
	
	// 추가
	public Memo create() {
		// 하나의 메모 객체 생성
		Memo memo = new Memo();
		print("제목을 입력하세요>");
		memo.title = scanner.nextLine();
		print("내용을 입력하세요>");
		memo.content = scanner.nextLine();
		return memo;
	}
	
	
	// 메모 하나 값 출력
	public int inputMemoIndex() {
		print("글 번호를 입력하세요>");
		int index = Integer.parseInt(scanner.nextLine());
		return index;
	}
	
	public void showMemo(Memo memo) {
		print("Index : "+memo.no);
		print(" | Title : "+memo.title);
		println(" | Content : "+memo.content);
	}
	
	// 전체 리스트 출력
	public void showList(List<Memo> data) {
		for(Memo memo : data) {
			print("| Index : "+memo.no);
			print("| Title : "+memo.title);
			print("| Content : "+memo.content);
			println("| Data : "+controller.dateFormat(memo.date));
		}
	}
//	
//	// 수정
//	public void update(Scanner scanner){
//		print("글 번호를 입력하세요>");
//		String inputNo = scanner.nextLine();
//		
//		int index = Integer.parseInt(inputNo);
//		Memo temp = model.getOne(index);
//		print("Title>");
//		temp.title = scanner.nextLine();
//		print("Content>");
//		temp.content = scanner.nextLine();
//	}
//	
	// 삭제
	public int delete(Scanner scanner) {
		print("글 번호를 입력하세요>");
		int index = Integer.parseInt(scanner.nextLine());
		return index;
	}
	
	public void print(String str) {
		System.out.print(str);
	}
	
	public void println(String str) {
		System.out.println(str);
	}	
}
