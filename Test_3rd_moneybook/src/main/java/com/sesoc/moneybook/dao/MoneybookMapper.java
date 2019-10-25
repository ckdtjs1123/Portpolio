package com.sesoc.moneybook.dao;

import java.util.ArrayList;

import com.sesoc.moneybook.vo.MemberVO;
import com.sesoc.moneybook.vo.MoneybookVO;

public interface MoneybookMapper {

	ArrayList<MoneybookVO> mylist(MoneybookVO vo);

	void reglist(MoneybookVO vo);

	void dellist(MoneybookVO vo);


}
