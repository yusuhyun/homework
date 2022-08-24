package homework02.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import homework02.view.BookMenu;

import java.util.Set;
import java.util.UUID;

public class BookController {
	private BookMenu menu = new BookMenu();
	public Map<String,Object> book = new HashMap<String, Object>();
	public Set<Entry<String,Object>> set = book.entrySet();
	
	// 신규 도서 등록
	public void insertBook() {
		
		String bKey = UUID.randomUUID().toString();// key값 랜덤 코드 생성
		List<String> bList = menu.insertBook();// 등록할 책 정보 받아오기
		
		book.put(bKey, bList);// key에 랜덤uuid, value에 받아온 값 넣어 추가
		menu.displaySuccess("신규 도서가 등록되었습니다.");
	}

	// 전체조회
	public void selectAll() {
		Iterator<Entry<String,Object>> keys = set.iterator();
		while(keys.hasNext()) {
			Entry<String, Object> entry = keys.next();
			menu.displaySuccess("도서코드 : " + entry.getKey() + " 도서정보 : " + entry.getValue()); // 도서 목록 비어있지 않으면 목록 표시
		}
		if(book.isEmpty()) {
			menu.displayError("도서 정보가 없습니다.");
		}
	}

	// 도서검색
	public void selectName() {
		int search = menu.selectBook();// 검색 조건 선택받기
		boolean sb = true; // 검색결과여부
		Iterator<Entry<String,Object>> keys = set.iterator();
		
		switch(search) {
		case 1:
			String bKey = menu.inputKey(); // key 받아오기
			
			while(keys.hasNext()) {
				Entry<String,Object> entry = keys.next();
				if(entry.getKey().equals(bKey)) {
					menu.displaySuccess("도서코드 : " + entry.getKey() + " 도서정보 : " + entry.getValue());
					sb = false;
				}
			}
			break;
			
		case 2:
			String bValue = menu.inputValue(); // 검색 단어 받아오기
			while(keys.hasNext()) {
				Entry<String,Object> entry = keys.next();
				String entryV = entry.getValue().toString();
				if(entryV.contains(bValue)) {
					menu.displaySuccess("도서코드 : " + entry.getKey() + " 도서정보 : " + entry.getValue());
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
		Iterator<Entry<String,Object>> keys = set.iterator();
		boolean sb = true; // 수정여부 체크
		while(keys.hasNext()) {
			Entry<String,Object> entry = keys.next();
			if(entry.getKey().equals(bKey)) {
				List<String> nList = menu.inputUpdate(); // 수정 정보 입력
				
				book.remove(bKey);
				book.put(bKey, nList);
				sb = false;
			}
		}
		if(sb) {
			menu.displayError("조회 결과가 없습니다.");
		} 
	}

	// 도서삭제
	public void deleteBook() {
		Iterator<Entry<String,Object>> keys = set.iterator();
		String bKey = menu.inputKey(); //재활용2
		boolean sb = true; // 삭제여부
		while(keys.hasNext()) {
			Entry<String,Object> entry = keys.next();
			if(entry.getKey().equals(bKey)) {
				char yn = menu.deleteBook("도서코드 : " + entry.getKey() + " 도서정보 : " + entry.getValue());
				if(yn == 'Y' ||  yn == 'y') {
					keys.remove();
					sb = false;
					menu.displaySuccess("도서정보가 삭제되었습니다.");
				} else if(yn == 'N' || yn == 'n') {
					menu.displayError("삭제가 취소되었습니다.");
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
