package com.sesoc.moneybook.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sesoc.moneybook.vo.MemberVO;
import com.sesoc.moneybook.vo.MoneybookVO;

@Repository
public class MoneybookDAO {

	@Autowired
	private SqlSession session;
	
	public ArrayList<MoneybookVO> mylist(MoneybookVO vo,HttpSession id) 
	{
		String userid = (String) id.getAttribute("userid");
		vo.setUserid(userid);
		MoneybookMapper mapper = session.getMapper(MoneybookMapper.class);
		return mapper.mylist(vo);
	}

	public void reglist(MoneybookVO vo, HttpSession hsession)
	{
		String userid = (String) hsession.getAttribute("userid");
		vo.setUserid(userid);
		MoneybookMapper mapper = session.getMapper(MoneybookMapper.class);
		mapper.reglist(vo);
	}

	public void dellist(MoneybookVO vo, HttpSession hsession) 
	{
		String userid = (String) hsession.getAttribute("userid");
		vo.setUserid(userid);
		MoneybookMapper mapper = session.getMapper(MoneybookMapper.class);
		mapper.dellist(vo);
	}

}
