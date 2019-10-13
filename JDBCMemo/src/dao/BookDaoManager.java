package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Book;
import vo.Review;

public class BookDaoManager {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void closeDB() {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public boolean bookRegist(Book book) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO BOOK(bnum,bname,writer,type,state) VALUES((SELECT NVL(MAX(BNUM),0)+1 FROM BOOK),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getbName());
			pstmt.setString(2, book.getWriter());
			pstmt.setString(3, book.getType());
			pstmt.setString(4, book.getState());
			int answer = pstmt.executeUpdate();
			if(answer>0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB(); 
		}
		return result;
	}
	
	public Book stateCheck(String bNum) {
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM BOOK WHERE BNUM=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bNum = rs.getString(1);
				String bName = rs.getString(2);
				String writer = rs.getString(3);
				String type = rs.getString(4);
				String state = rs.getString(5);
				Book book = new Book(bNum, bName, writer, type, state);
				
				return book;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return null;
	}
	
	public ArrayList<Book> checkAll() {
		ArrayList<Book> list = new ArrayList<>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM BOOK ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String bNum = rs.getString(1);
				String bName = rs.getString(2);
				String writer = rs.getString(3);
				String type = rs.getString(4);
				String state = rs.getString(5);
				Book book = new Book(bNum, bName, writer, type, state);
				list.add(book);
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB(); 
		}
		return list;
	}
	
	public boolean bookUpdate(String bNum, Book book) {
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "UPDATE BOOK SET BNAME=?, WRITER=?, TYPE=?, STATE=? WHERE BNUM="+bNum+"";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getbName());
			pstmt.setString(2, book.getWriter());
			pstmt.setString(3, book.getType());
			pstmt.setString(4, book.getState());
			int answer = pstmt.executeUpdate();
			if(answer>0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return result;
	}
	
	public boolean bookDelete(String bNum) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "DELETE BOOK WHERE BNUM=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bNum);
			int answer = pstmt.executeUpdate();
			if(answer>0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return result;
	}

	public ArrayList<Book> searchBook(String word) {
		// TODO Auto-generated method stub
		ArrayList<Book> list = new ArrayList<>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM BOOK WHERE (WRITER LIKE ? OR TYPE LIKE ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+word+"%");
			pstmt.setString(2, "%"+word+"%");
			rs= pstmt.executeQuery();
			while(rs.next()) {
				String bNum = rs.getString(1);
				String bName = rs.getString(2);
				String writer = rs.getString(3);
				String type = rs.getString(4);
				String state = rs.getString(5);
				Book book = new Book(bNum, bName, writer, type, state);
				list.add(book);
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return list;
	}

	public boolean changeState(String bNum, Book book) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "UPDATE BOOK SET STATE=? WHERE BNUM="+bNum+"";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getState());
			int answer = pstmt.executeUpdate();
			if(answer>0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return result;
	}

	public boolean insertReview(Review view) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO REVIEW(RID, RNUM, BNAME, REVIEWS) VALUES(?,?,(SELECT BNAME FROM BOOK WHERE BNUM = ?),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, view.getWriter());
			pstmt.setString(2, view.getbNum());
			pstmt.setString(3, view.getbNum());
			pstmt.setString(4, view.getContent());
			int answer = pstmt.executeUpdate();
			if(answer>0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB(); 
		}
		return result;
	}
	
	public ArrayList<Review> bookReview(String bName) {
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM REVIEW WHERE BNAME=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String bNum = rs.getString(1);
				bName = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				String indate = rs.getString(5);
				Review view = new Review(bNum, bName, writer, content, indate);
				list.add(view);
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return list;
	}

	public boolean deleteReview(String writer) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "DELETE REVIEW WHERE RID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			int answer = pstmt.executeUpdate();
			if(answer>0) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return result;
	}
}
	
