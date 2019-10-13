package vo;

public class Book {
	private String bNum;				// 책 일련번호
	private String bName;				// 책 이름
	private String writer;				// 책 저자
	private String type;				// 책 종류
	private String state;				// 책 현재 상태

	
	public Book(String bNum, String bName, String writer, String type, String state) {
		super();
		this.bNum = bNum;
		this.bName = bName;
		this.writer = writer;
		this.type = type;
		this.state = state;
	
	}

	public String getbNum() {
		return bNum;
	}

	public String getbName() {
		return bName;
	}

	public String getWriter() {
		return writer;
	}

	public String getType() {
		return type;
	}

	public String getState() {
		return state;
	}

	public void setbNum(String bNum) {
		this.bNum = bNum;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		String temp = null;
		temp =  String.format("일련번호 : "+bNum+", 책이름 : "+bName+", 작가 : "+writer+
				", 종류 : "+type+", 상태  : "+state);
		return temp;
	}
	
	
}
