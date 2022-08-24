package homework02.run;

import homework02.view.BookMenu;

public class Run {
	public static void main(String[] args) {
		try {
			new BookMenu().mainMenu();
		} catch (NumberFormatException e) {
			new BookMenu().displayError("숫자를 입력해 주세요");
			new BookMenu().mainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
