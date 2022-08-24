package homework02.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import homework02.controller.BookController;

public class BookMenu {
	Scanner sc = new Scanner(System.in);
	public void mainMenu() {
		BookController bc = new BookController();
		
		int select = 0;
		
		do {
			System.out.println("\n === 도서 관리 프로그램.MapVer === \n");
			System.out.println("1. 전체 도서 조회");
			System.out.println("2. 도서 검색");
			System.out.println("3. 신규 도서 등록");
			System.out.println("4. 도서 정보 수정");
			System.out.println("5. 도서 정보 삭제");
			
			select = Integer.parseInt(sc.nextLine());
			
			switch(select) {
			case 1: bc.selectAll(); break; 
			case 2: bc.selectName(); break;
			case 3: bc.insertBook(); break;
			case 4: bc.updateBook(); break;
			case 5: bc.deleteBook(); break;
			default: displayError("잘못된 번호입니다. 다시 입력해 주세요.");
			}
			
		} while(select !=0);
	}

	
	// 신규 책 받아오기
	public List<String> insertBook() {
		List<String> bList = new ArrayList<String>();
		System.out.println("\n == 신규 도서 등록 == \n");
		System.out.println("도서 제목 : ");
		String bookName = sc.nextLine();
		System.out.println("작가이름 : ");
		String bookWriter = sc.nextLine();
		System.out.println("카테고리 : ");
		String bookCategory = sc.nextLine();
		
		bList.add(bookName);
		bList.add(bookWriter);
		bList.add(bookCategory);
		return bList;
	}


	// 성공 메세지
	public void displaySuccess(String string) {
		System.out.println("성공 : " + string);
	}
	
	// 실패 메세지
	public void displayError(String string) {
		System.out.println("실패 : " + string);
		
	}

	// 검색조건 입력
	public int selectBook() {
		int search = 0;
		while(true) {
			System.out.println("1. 도서코드로 검색");
			System.out.println("2. 도서정보로 검색");
			System.out.println("0. 메인으로 돌아가기");
			System.out.print("번호 선택 : ");
			search = Integer.parseInt(sc.nextLine());
			
			switch(search) {
			case 1:  case 2: case 3: case 0: return search;
			default: displayError("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		}
	}

	// 검색 도서 key 입력받기
	public String inputKey() {
		System.out.println("도서코드 : ");
		String bName = sc.nextLine();
		return bName;
	}

	// 검색도서 정보 입력받기
	public String inputValue() {
		System.out.println("도서 정보 : ");
		String bValue = sc.nextLine();
		return bValue;
	}

//	// 무엇을 수정할지
//	public int whatModify() {
//		System.out.println("\n == 도서 코드 확인 완료 == \n");
//		
//		int modi = 0;
//		while(true) {
//			System.out.println("1. 도서 제목 변경");
//			System.out.println("2. 도서 작가 변경");
//			System.out.println("3. 도서 카테고리 변경");
//			System.out.println("0. 메인메뉴로 돌아가기");
//			System.out.println("번호 선택 : ");
//			
//			modi = Integer.parseInt(sc.nextLine());	
//			
//			switch(modi) {
//			case 1: case 2: case 3: case 0: return modi;
//			default: System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
//			}
//		}
//	}

	// 수정정보 입력
	public List<String> inputUpdate() {
		List<String> bList = new ArrayList<String>();
		System.out.println("== 수정 정보 입력 ==");
		System.out.println("도서 제목 : ");
		String bookName = sc.nextLine();
		System.out.println("작가이름 : ");
		String bookWriter = sc.nextLine();
		System.out.println("카테고리 : ");
		String bookCategory = sc.nextLine();
		
		bList.add(bookName);
		bList.add(bookWriter);
		bList.add(bookCategory);
		return bList;
	}

	// 삭제 되묻기
	public char deleteBook(String string) {
		System.out.println(string);
		System.out.println("위 도서를 삭제 하시겠습니까(Y/N) : ");
		char yn = sc.nextLine().toUpperCase().charAt(0);
		return yn;
	}

}
