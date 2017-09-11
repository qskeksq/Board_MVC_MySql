package ne;

import java.util.List;
import java.util.Scanner;

/**
 * View 
 * �Է�, ���
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
	
	// �Է�â
	public void intro() {
		println("------------------��ɾ �Է��ϼ���------------------");
		println("|c : ����|r : �б�|u : ����|d : ����|l : ���|x : ����|");
		println("-------------------------------------------------");
		print("����>");
	}
	
	// �߰�
	public Memo create() {
		// �ϳ��� �޸� ��ü ����
		Memo memo = new Memo();
		print("������ �Է��ϼ���>");
		memo.title = scanner.nextLine();
		print("������ �Է��ϼ���>");
		memo.content = scanner.nextLine();
		return memo;
	}
	
	
	// �޸� �ϳ� �� ���
	public int inputMemoIndex() {
		print("�� ��ȣ�� �Է��ϼ���>");
		int index = Integer.parseInt(scanner.nextLine());
		return index;
	}
	
	public void showMemo(Memo memo) {
		print("Index : "+memo.no);
		print(" | Title : "+memo.title);
		println(" | Content : "+memo.content);
	}
	
	// ��ü ����Ʈ ���
	public void showList(List<Memo> data) {
		for(Memo memo : data) {
			print("| Index : "+memo.no);
			print("| Title : "+memo.title);
			print("| Content : "+memo.content);
			println("| Data : "+controller.dateFormat(memo.date));
		}
	}
//	
//	// ����
//	public void update(Scanner scanner){
//		print("�� ��ȣ�� �Է��ϼ���>");
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
	// ����
	public int delete(Scanner scanner) {
		print("�� ��ȣ�� �Է��ϼ���>");
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
