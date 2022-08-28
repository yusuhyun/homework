package homework02.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import homework02.view.BookMenu;

import java.util.Set;
import java.util.UUID;

public class BookController {
	private BookMenu menu = new BookMenu();
	public Set<HashMap<String, Object>> books = new HashSet<HashMap<String, Object>>();
	
	// 신규 도서 등록
	public void insertBook() {
		HashMap<String, Object> book = new HashMap<String, Object>();
		String bKey = UUID.randomUUID().toString();// key값 랜덤 코드 생성
		String[] bList = menu.insertBook();// 등록할 책 정보 받아오기
		book.put("key", bKey);
		book.put("bookName", bList[0]);
		book.put("bookWriter", bList[1]);
		book.put("category", bList[2]);
		System.out.println(book); // map에 책 정보 입력
		books.add(book);// set에 완성된 책 입력
		menu.displaySuccess("신규 도서가 등록되었습니다.");
	}

	// 전체조회
	public void selectAll() {
		Iterator<HashMap<String,Object>> keys = books.iterator();
		while(keys.hasNext()) {
			HashMap<String, Object> book = keys.next();
			menu.bookView(book); // 도서 목록 비어있지 않으면 목록 표시
		}
		if(books.isEmpty()) {
			menu.displayError("도서 정보가 없습니다.");
		}
	}

	// 도서검색
	public void selectName() {
		int search = menu.selectBook();// 검색 조건 선택받기
		boolean sb = true; // 검색결과여부
		Iterator<HashMap<String,Object>> keys = books.iterator();
		
		switch(search) {
		case 1:
			String bKey = menu.inputKey(); // key 받아오기
			
			while(keys.hasNext()) {
				HashMap<String,Object> book = keys.next(); //book 정보 가져오기
				String sKey = (String) book.get("key"); //key 가져오기
				if(sKey.equals(bKey)) { // 가져온 book의 key가 입력된 값과 같은경우
					menu.bookView(book);
					sb = false;
				}
			}
			break;
			
		case 2:
			String bValue = menu.inputValue(); // 검색 단어 받아오기
			while(keys.hasNext()) {
				HashMap<String,Object> book = keys.next();
				String sName = (String) book.get("bookName");
				String sWriter = (String) book.get("bookWriter");
				String sCate = (String) book.get("category");
				if(sName.equals(bValue) || sWriter.equals(bValue) || sCate.equals(bValue)) { // 제목, 작가, 장르중 하나라도 일치하는경우 
					menu.bookView(book);
					sb = false;
				} 
			}
			break;
		case 0: return ;
		}
		if(sb) {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	// 도서 수정
	public void updateBook() {
		String bKey = menu.inputKey(); // 재활용
		Iterator<HashMap<String,Object>> keys = books.iterator();
		boolean sb = true; // 수정여부 체크
		while(keys.hasNext()) {
			HashMap<String,Object> book = keys.next();
			String sKey = (String) book.get("key"); 
			if(sKey.equals(bKey)) {
				String[] nList = menu.inputUpdate(); // 수정 정보 입력
				book.put("key", bKey);
				book.put("bookName", nList[0]);
				book.put("bookWriter", nList[1]);
				book.put("category", nList[2]);
				sb = false;
			}
		}
		if(sb) {
			menu.displayError("조회 결과가 없습니다.");
		} 
	}

	// 도서삭제
	public void deleteBook() {
		Iterator<HashMap<String,Object>> keys = books.iterator();
		String bKey = menu.inputKey(); //재활용2
		boolean sb = true; // 삭제여부 체크용
		while(keys.hasNext()) {
			HashMap<String,Object> book = keys.next();
			if(book.get("key").equals(bKey)) {
				char yn = menu.deleteBook(book);
				if(yn == 'Y' ||  yn == 'y') {
					books.remove(book);
					sb = false;
					menu.displaySuccess("도서정보가 삭제되었습니다.");
				} else if(yn == 'N' || yn == 'n') {
					menu.displaySuccess("삭제가 취소되었습니다.");
					sb = false;
				} else {
					menu.displayError("잘못 입력하셨습니다.");
					sb = false;
				}
			}
		}
		if(sb) {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

}
