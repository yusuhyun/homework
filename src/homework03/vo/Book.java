package homework03.vo;

public class Book {
	private String bookKey;
	private String bookName;
	private String bookWriter;
	private String bookCategory;

	
	public Book() {}

	public Book(String bookKey, String bookName, String bookWriter, String bookCategory) {
		super();
		this.bookKey = bookKey;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookCategory = bookCategory;
	}

	public String getBookKey() {
		return bookKey;
	}

	public void setBookKey(String bookKey) {
		this.bookKey = bookKey;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}


	@Override
	public String toString() {
		return "도서코드 : " + bookKey + ", 책 제목 : " + bookName + ", 작가 : " + bookWriter + ", 카테고리 : "
				+ bookCategory;
	}
}

