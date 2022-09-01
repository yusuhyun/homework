package homework03_2.run;

import homework03_2.view.BookMenu;

public class Run {
	public static void main(String[] args) {
		try {
			new BookMenu().mainMenu();
		} catch(NumberFormatException e) {
			new BookMenu().displayError("숫자를 입력해 주세요.");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			new BookMenu().mainMenu();
		}
	}
}
