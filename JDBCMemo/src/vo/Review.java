package vo;

public class Review {
	private String bNum;
	private String bName;
	private String writer;
	private String content;
	private String indate;
	
	public Review(String writer, String bName, String bNum, String content, String indate) {
		super();
		this.bNum = bNum;
		this.bName = bName;
		this.writer = writer;
		this.content = content;
		this.indate = indate;
	}
	
	public Review(String bNum, String writer, String content, String indate) {
		super();
		this.bNum = bNum;
		this.writer = writer;
		this.content = content;
		this.indate = indate;
	}


	public String getWriter() {
		return writer;
	}

	public String getContent() {
		return content;
	}

	public String getIndate() {
		return indate;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}
	
	public String getbNum() {
		return bNum;
	}

	public void setbNum(String bNum) {
		this.bNum = bNum;
	}
	
	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	@Override
	public String toString() {
		String temp = null;
		temp =  String.format("글쓴이 = "+writer+", 책이름 = "+bName+", 내용 = "+content+", 날짜 = "+indate);
		return temp;
	}
	
	
}
