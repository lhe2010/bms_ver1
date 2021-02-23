package com.bms.admin.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bms.member.vo.MemberVO;

public interface AdminMemberDAO {
	
	public List<MemberVO> listMember(Map<String,Object> condMap) throws DataAccessException;
	public MemberVO memberDetail(String member_id) throws DataAccessException;
	public void modifyMemberInfo(Map<String,String> memberMap) throws DataAccessException;
	
}
