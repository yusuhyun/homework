package homework03.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import homework03.view.BookMenu;
import homework03.vo.Book;

public class BookController {
	private BookMenu menu = new BookMenu();
	private Book b = new Book();
	List<Book> book = new ArrayList<Book>();
	
	// 신규도서 생성
	public void insertBook() {
		String bKey = UUID.randomUUID().toString();// key값 랜덤 코드 생성
		Book bInfo = menu.insertBook(bKey); // 도서정보 받기
		book.add(bInfo); // 도서정보 리스트에 add
		menu.displaySuccess("신규 도서가 등록되었습니다.");
	}

	// 전체 도서 조회
	public void selectAll() {
		Stream<Book> b = book.stream();
		b.forEach(s -> System.out.println(s));
	}

	// 도서 검색
	public void selectName() {
		int search = menu.selectBook();// 검색 조건 선택받기
		boolean sb = true; // 검색결과여부
		Stream<Book> b = book.stream();
		
		switch(search) {
		case 1:
			String bKey = menu.inputKey(); // key 받아오기
			b.filter(key -> key.getBookKey().equals(bKey)).forEach(s -> System.out.println(s));
			sb = false;
			break;
			
		case 2:
			String bValue1 = menu.inputValue(); // 검색 단어 받아오기
			b.filter(bVal -> bVal.getBookName().equals(bValue1)).forEach(s -> System.out.println(s));
			sb = false;
			break;
			
		case 3:
			String bValue2 = menu.inputValue(); // 검색 단어 받아오기
			b.filter(bVal -> bVal.getBookName().equals(bValue2)).forEach(s -> System.out.println(s));
			sb = false;
			break;
		
		case 4:
			String bValue3 = menu.inputValue(); // 검색 단어 받아오기
			b.filter(bVal -> bVal.getBookName().equals(bValue3)).forEach(s -> System.out.println(s));
			sb = false;
			break;
			
		case 0: return ;
		}
		if(sb) {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	// 도서 수정
	public void updateBook() {
		Stream<Book> b = book.stream();
		boolean sb = true; // 수정여부 체크
		
		String bKey = menu.inputKey(); // 재활용
		boolean anyMatch = b.anyMatch(k -> k.getBookKey().equals(bKey));// key 체크
		
		if(anyMatch) {
			int changeB = menu.whatChange();
			Stream<Book> bk = book.stream(); // 초기화되서 다시 선언
			switch(changeB) {
			
			case 1:
				String bChange1 = menu.changeValue(); // 검색 단어 받아오기
				bk.filter(key -> key.getBookKey().equals(bKey)).forEach(bVal -> bVal.setBookName(bChange1));
				sb = false;
				break;
				
			case 2:
				String bChange2 = menu.changeValue(); // 검색 단어 받아오기
				bk.filter(key -> key.getBookKey().equals(bKey)).forEach(bVal -> bVal.setBookName(bChange2));
				sb = false;
				break;
			
			case 3:
				String bChange3 = menu.changeValue(); // 검색 단어 받아오기
				bk.filter(key -> key.getBookKey().equals(bKey)).forEach(bVal -> bVal.setBookName(bChange3));
				sb = false;
				break;
				
			case 0: return ;
			}
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
		if(!sb) {
			menu.displaySuccess("변경이 완료되었습니다.");
		}
	}

	// 도서 삭제
	public void deleteBook() {
		Stream<Book> b = book.stream();
		String bKey = menu.inputKey(); // 재활용
		boolean sb = true; // 삭제여부 체크
		boolean anyMatch = b.anyMatch(k -> k.getBookKey().equals(bKey)); // key 체크
		if(anyMatch) {
			Stream<Book> b2 = book.stream();
			b2.filter(e -> e.getBookKey().equals(bKey)).forEach(e2 -> System.out.println(e2));
			char yn = menu.deleteBook(); // 정말 삭제할것인지 체크
			if(yn == 'Y' ||  yn == 'y') {
				Stream<Book> b3 = book.stream();
				b3.filter(e -> e.getBookKey().equals(bKey)).collect(Collectors.toList()).forEach(del ->{book.remove(del);});
				sb = false;
				menu.displaySuccess("도서정보가 삭제되었습니다.");
			} else if(yn == 'N' || yn == 'n') {
				menu.displaySuccess("삭제가 취소되었습니다.");
				sb = false;
			} else {
				menu.displayError("잘못 입력하셨습니다.");
				sb = false;
			}
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
		
	}
		
		
}


