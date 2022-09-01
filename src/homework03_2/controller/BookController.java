package homework03_2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import homework03.vo.Book;
import homework03_2.view.BookMenu;


public class BookController {

	private BookMenu menu = new BookMenu();
	List<HashMap<String, Object>> bList = new ArrayList<HashMap<String, Object>>(); // 책 담을 객체
	
	// 신규도서 생성
	public void insertBook() {
		HashMap<String, Object> book = new HashMap<>();
		String bKey = UUID.randomUUID().toString();// key값 랜덤 코드 생성
		String[] bk = menu.insertBook();
		book.put("코드",bKey);
		book.put("제목",bk[0]);
		book.put("작가",bk[1]);
		book.put("카테고리",bk[2]);
		bList.add(book);
		
		menu.displaySuccess("신규 도서가 등록되었습니다.");
	}

	// 전체 목록 조회
	public void selectAll() {
		Stream<HashMap<String, Object>> bStr = bList.stream();  
		if(bStr.count() != 0) {
			Stream<HashMap<String, Object>> bStr2 = bList.stream();  
			bStr2.forEach(b -> System.out.println("도서코드 : " + b.get("코드") + " / 제목 : " + b.get("제목")
			+ " / 작가 : " +  b.get("작가") + " / 카테고리 : " + b.get("카테고리")));
		} else {
			menu.displayError("조회되는 도서가 없습니다.");
		}
	}

	// 책 검색
	public void selectBook() throws NumberFormatException{
		Stream<HashMap<String, Object>> bStr = bList.stream();
		int search = menu.selectBook();// 검색 조건 선택받기
		boolean sb = true; // 검색결과여부
		
		switch(search) {
		case 1:
			String bKey = menu.inputKey(); // key 받아오기
			bStr.filter(key -> key.get("코드").equals(bKey))
			.forEach(b -> System.out.println("도서코드 : " + b.get("코드") + " / 제목 : " + b.get("제목")
			+ " / 작가 : " +  b.get("작가") + " / 카테고리 : " + b.get("카테고리")));
			sb = false;
			break;
			
		case 2:
			String bValue1 = menu.inputValue(); // 검색 단어 받아오기
			bStr.filter(bVal -> bVal.get("제목").equals(bValue1))
			.forEach(b -> System.out.println("도서코드 : " + b.get("코드") + " / 제목 : " + b.get("제목")
			+ " / 작가 : " +  b.get("작가") + " / 카테고리 : " + b.get("카테고리")));
			sb = false;
			break;
			
		case 3:
			String bValue2 = menu.inputValue(); // 검색 단어 받아오기
			bStr.filter(bVal -> bVal.get("작가").equals(bValue2))
			.forEach(b -> System.out.println("도서코드 : " + b.get("코드") + " / 제목 : " + b.get("제목")
			+ " / 작가 : " +  b.get("작가") + " / 카테고리 : " + b.get("카테고리")));
			sb = false;
			break;
		
		case 4:
			String bValue3 = menu.inputValue(); // 검색 단어 받아오기
			bStr.filter(bVal -> bVal.get("카테고리").equals(bValue3))
			.forEach(b -> System.out.println("도서코드 : " + b.get("코드") + " / 제목 : " + b.get("제목")
			+ " / 작가 : " +  b.get("작가") + " / 카테고리 : " + b.get("카테고리")));
			sb = false;
			break;
			
		case 0: return ;
		}
		if(sb) {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	// 도서 정보 수정
	public void updateBook() {
		Stream<HashMap<String, Object>> bStr = bList.stream();
		boolean sb = true; // 수정여부 체크
		String bKey = menu.inputKey(); // 재활용
		boolean anyMatch = bStr.anyMatch(k -> k.get("코드").equals(bKey));// key 유무 체크
		
		if(anyMatch) {
			int changeB = menu.whatChange();
			Stream<HashMap<String, Object>> bStr2 = bList.stream();
			switch(changeB) {
			case 1:
				String bChange1 = menu.changeValue(); // 검색 단어 받아오기
				bStr2.filter(key -> key.get("코드").equals(bKey)).forEach(bV -> bV.put("제목",bChange1));
				break;
				
			case 2:
				String bChange2 = menu.changeValue(); // 검색 단어 받아오기
				bStr2.filter(key -> key.get("코드").equals(bKey)).forEach(bV -> bV.put("작가",bChange2));
				break;
			
			case 3:
				String bChange3 = menu.changeValue(); // 검색 단어 받아오기
				bStr2.filter(key -> key.get("코드").equals(bKey)).forEach(bV -> bV.put("카테고리",bChange3));
				break;
				
			case 0: return ;
			}
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	// 도서 삭제
	public void deleteBook() {
		Stream<HashMap<String, Object>> bStr = bList.stream();
		String bKey = menu.inputKey(); // 코드검색 재활용
		boolean anyMatch = bStr.anyMatch(k -> k.get("코드").equals(bKey));// key 유무 체크 재활용
		if(anyMatch) {
			Stream<HashMap<String, Object>> bStr2 = bList.stream();
			bStr2.filter(e -> e.get("코드").equals(bKey))
			.forEach(b -> System.out.println("도서코드 : " + b.get("코드") + " / 제목 : " + b.get("제목")
			+ " / 작가 : " +  b.get("작가") + " / 카테고리 : " + b.get("카테고리"))); // 책정보 확인
			char yn = menu.deleteBook(); // 정말 삭제할것인지 체크
			if(yn == 'Y' ||  yn == 'y') {
				Stream<HashMap<String, Object>> bStr3 = bList.stream();
				bStr3.filter(e -> e.get("코드").equals(bKey)).collect(Collectors.toList()).forEach(b -> {bList.remove(b);});
				menu.displaySuccess("도서정보가 삭제되었습니다.");
			} else if(yn == 'N' || yn == 'n') {
				menu.displaySuccess("삭제가 취소되었습니다.");
			} else {
				menu.displayError("잘못 입력하셨습니다.");
			}
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
		
	}
	
	
	
	
}