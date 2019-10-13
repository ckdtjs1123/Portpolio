package manager;

import java.util.ArrayList;

import dao.BookDaoManager;
import dao.MemoDaoManager;
import vo.Book;
import vo.Review;
import vo.UserAccount;
import vo.UserMemo;

public class MemoManager {

	private MemoDaoManager mdm = null;
	private String id;// 로그인한 ID
	private String name;// 로그인한 이용자 이름
	
	private BookDaoManager bdm= null;
	
	public MemoManager() {

		this.mdm = new MemoDaoManager();
		this.bdm = new BookDaoManager();
	}

	public boolean insertAccount(UserAccount user) {
		// TODO Auto-generated method stub
		boolean result = false;
		result = mdm.insertAccount(user);
		return result;
	}

	public UserAccount loginAccount(UserAccount user) {
		// TODO Auto-generated method stub
		UserAccount result = null;
		result = mdm.loginAccount(user);
		if (result != null) {
			this.id = result.getId();
			this.name = result.getName();
		}
		return result;
	}

	public boolean deleteAccount(UserAccount user) {
		// TODO Auto-generated method stub
		boolean result = false;
		result = mdm.deleteAccount(user);
		return result;
	}

	public boolean insertMemo(UserMemo memo) {
		// TODO Auto-generated method stub
		boolean result = false;
		memo.setId(this.id);
		result = mdm.insertMemo(memo);
		return result;
	}
	
	public ArrayList<UserMemo> getMemoList() {
		// TODO Auto-generated method stub
		ArrayList<UserMemo> result = null;
		result = mdm.getMemoList(this.id);
		return result;
	}

	public ArrayList<UserMemo> searchMemo(String searchword) {
		// TODO Auto-generated method stub

		ArrayList<UserMemo> result = null;
		result = mdm.searchMemo(searchword, this.id);
		return result;
	}

	public UserMemo searchOneMemo(String seq) {
		// TODO Auto-generated method stub
		UserMemo result = null;
		result = mdm.searchOneMemo(seq, this.id);
		return result;
	}
	
	public boolean updateMemo(String seq,UserMemo memo) {
		// TODO Auto-generated method stub
		boolean result = false;
		result = mdm.updateMemo(seq,memo);
		return result;
	}

	public boolean deleteMemo(String seq) {
		// TODO Auto-generated method stub
		boolean result = false;
		result = mdm.deleteMemo(seq);
		return result;
	}

	public boolean deleteAllMemo(String id) {
		// TODO Auto-generated method stub
		boolean result = false;
		result = mdm.deleteAllMemo(id);
		return result;
	}

	public boolean logout() {

		if (this.name == null) {
			return false;
		}

		this.name = null;
		this.id = null;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	//=======================================================
	

	public boolean bookRegist(Book book) {
		boolean result = false;
		result = bdm.bookRegist(book);
		return result;
	}
	

	public Book stateCheck(String bNum) {
		Book result = null;
		result = bdm.stateCheck(bNum);
		return result;
	}
	
	public ArrayList<Book> checkAll() {
		ArrayList<Book> result = null;
		result = bdm.checkAll();
		return result;
	}
	
	public boolean bookUpdate(String bNum, Book book) {
		boolean result = false;
		result = bdm.bookUpdate(bNum, book);
		return result;
	}
	
	public boolean bookDelete(String bNum) {
		// TODO Auto-generated method stub
		boolean result = false;
		result = bdm.bookDelete(bNum);
		return result;
	}
	
	public ArrayList<Book> searchBook(String word) {
		// TODO Auto-generated method stub
		ArrayList<Book> result = null;
		result = bdm.searchBook(word);
		return result;
	}
	
	public boolean changeState(String bNum, Book book) {
		boolean result = false;
		result = bdm.changeState(bNum, book);
		return result;
	}
	
	public boolean insertReview(Review view) {
		boolean result = false;
		result = bdm.insertReview(view);
		return result;
	} 
	
	public ArrayList<Review> bookReview(String bName) {
		ArrayList<Review> result = null;
		result = bdm.bookReview(bName);
		return result;
	}

	public boolean deleteReview(String writer) {
		boolean result = false;
		result = bdm.deleteReview(writer);
		return result;
		
	}
}
