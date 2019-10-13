package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.UserAccount;
import vo.UserMemo;

public class MemoDaoManager implements MemoInterface {
	
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
	
	@Override
	public boolean insertAccount(UserAccount user) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO USERACCOUNT VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPassword());
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

	@Override
	public UserAccount loginAccount(UserAccount user) {
		UserAccount loginId = null;
		try {
			String id =null;
			String name = null;
			String pw =null;
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM USERACCOUNT WHERE ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getString(2);
				name = rs.getString(1);
				pw = rs.getString(3);
			}

			if(pw.equals(user.getPassword())) {
				loginId = new UserAccount(name,id,pw);
				return loginId;
			}
			
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB(); 
		}
		return loginId;
	}

	@Override
	public boolean deleteAccount(UserAccount user) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "DELETE USERACCOUNT WHERE ID=? AND PASSWORD=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			if(pstmt.executeUpdate()>0) {
				result=true;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB(); 
		}
		return result;
	}
	
	@Override
	public boolean insertMemo(UserMemo memo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO USERMEMO(MEMOSEQ,TITLE,CONTENT,ID) VALUES(memoseq.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memo.getTitle());
			pstmt.setString(2, memo.getContent()); 
			pstmt.setString(3, memo.getId());
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
	
	@Override
	public ArrayList<UserMemo> getMemoList(String id) {
		// TODO Auto-generated method stub
		ArrayList<UserMemo> list = new ArrayList<>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM USERMEMO WHERE ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String memoseq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				id = rs.getString(5);
				UserMemo um = new UserMemo(memoseq, title, content, indate, id);
				list.add(um);
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB(); 
		}
		return list;
	}

	@Override
	public ArrayList<UserMemo> searchMemo(String searchword, String id) {
		// TODO Auto-generated method stub
		ArrayList<UserMemo> list = new ArrayList<>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM USERMEMO WHERE (TITLE LIKE ? OR CONTENT LIKE ?) AND ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchword+"%");
			pstmt.setString(2, "%"+searchword+"%");
			pstmt.setString(3, id);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				String memoseq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				id =rs.getString(5);
				UserMemo um = new UserMemo(memoseq, title, content, indate, id);
				list.add(um);
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return list;
	}

	@Override
	public UserMemo searchOneMemo(String seq, String id) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT * FROM USERMEMO WHERE MEMOSEQ=? AND ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				seq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				id = rs.getString(5);
				UserMemo um = new UserMemo(seq, title, content, indate, id);
				
				return um;
			}
		} catch (Exception e) {
			System.out.println("***오류!! 다시 입력하세요...");
		} finally {
			closeDB();
		}
		return null;
	}

	@Override
	public boolean updateMemo(String seq, UserMemo memo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "UPDATE USERMEMO SET TITLE=?, CONTENT=?WHERE MEMOSEQ="+seq+"";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memo.getTitle());
			pstmt.setString(2, memo.getContent());
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

	@Override
	public boolean deleteMemo(String seq) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "DELETE USERMEMO WHERE MEMOSEQ=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
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

	@Override
	public boolean deleteAllMemo(String id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = ConnectionManager.getConnection();
			String sql = "DELETE USERMEMO WHERE ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
