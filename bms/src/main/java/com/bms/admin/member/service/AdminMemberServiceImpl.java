package com.bms.admin.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.admin.member.dao.AdminMemberDAO;
import com.bms.member.vo.MemberVO;


@Service("adminMemberService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminMemberServiceImpl implements AdminMemberService {
	
	@Autowired
	private AdminMemberDAO adminMemberDAO;
	
	
	public List<MemberVO> listMember(Map<String,Object> condMap) throws Exception{
		return adminMemberDAO.listMember(condMap);
	}

	
	public MemberVO memberDetail(String member_id) throws Exception{
		 return adminMemberDAO.memberDetail(member_id);
	}
	
	
	public void  modifyMemberInfo(Map<String,String> memberMap) throws Exception{
		 adminMemberDAO.modifyMemberInfo(memberMap);
	}
	
}
