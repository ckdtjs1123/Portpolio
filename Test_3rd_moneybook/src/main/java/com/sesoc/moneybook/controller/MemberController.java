package com.sesoc.moneybook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sesoc.moneybook.dao.MemberDAO;
import com.sesoc.moneybook.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@RequestMapping(value="/signupForm", method=RequestMethod.GET)
	public String signupForm()
	{
		return "signupForm";
	}
	
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm()
	{
		return "loginForm";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public String signup(MemberVO vo,RedirectAttributes rttr)
	{
		int result = 0;
		result = dao.signup(vo);
		if(result>0)
		{
			return "loginForm";
		} else 
		{
			rttr.addFlashAttribute("signupresult", false);
			return "signupForm";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(MemberVO vo,RedirectAttributes rttr,HttpSession session)
	{
		MemberVO result = dao.login(vo);
		if(result!=null)
		{
			session.setAttribute("userid", result.getUserid());
			return "main";
		} else 
		{
			rttr.addFlashAttribute("loginresult", false);
			return "loginform";
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "main";
	}
}
