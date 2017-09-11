package ne;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Controller {

	private boolean RUN = true;
	
	View view;
	ModelDb model;
	Scanner scanner;
	String inputText = "";
	Memo memo;
	int index;
	
	public Controller() {
		view = new View(this);
		model = new ModelDb();
		scanner = new Scanner(System.in);
	}
	
	public void run() {
		while(RUN) {
			view.intro();
			inputText = scanner.nextLine();
			switch(inputText) {
				case "c": 
				 	memo = view.create();
				 	model.create(memo);
					break;
				case "r":
					int index = view.inputMemoIndex();
					Memo data = model.read(index);
					view.showMemo(data);
					break;
//				case "u":
//					view.update(scanner);
//					break;
				case "d":
					index = view.delete(scanner);
					model.delete(index);
					break;
				case "l":
					List<Memo> datas = model.getList();
					view.showList(datas);
					break;
				case "x":
					view.print("�ý��� ����");
					finish();
					break;
			}
		}
		scanner.close();
	}
	
	public int setIndex(List<Memo> list) {
		return list.size()+1;
	}

	// ����Ʈ format
	public String dateFormat(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� hh�� mm�� ss��");
		return sdf.format(date);
	}
	
	public void finish() {
		RUN = false;
	}
}
