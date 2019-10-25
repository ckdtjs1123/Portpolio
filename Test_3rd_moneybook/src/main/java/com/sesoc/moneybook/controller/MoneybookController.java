package com.sesoc.moneybook.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesoc.moneybook.dao.MoneybookDAO;
import com.sesoc.moneybook.vo.MoneybookVO;

@Controller
@RequestMapping(value="/my")
public class MoneybookController {
	
	@Autowired
	private MoneybookDAO dao;
	
	@RequestMapping(value="moneybook", method=RequestMethod.GET)
	public String moneybooklist(HttpSession session)
	{
		return "moneybook";
	}
	
	@RequestMapping(value="mylist", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ArrayList<MoneybookVO> mylist(MoneybookVO vo,HttpSession session)
	{
		return dao.mylist(vo,session);
	}
	
	@RequestMapping(value="reglist", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void reglist(MoneybookVO vo,HttpSession session)
	{
		dao.reglist(vo,session);
	}
	
	@RequestMapping(value="dellist", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void dellist(MoneybookVO vo,HttpSession session)
	{
		dao.dellist(vo,session);
	}
}
