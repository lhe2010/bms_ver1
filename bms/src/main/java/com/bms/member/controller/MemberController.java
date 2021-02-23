package com.bms.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bms.member.vo.MemberVO;

public interface MemberController {
	
	public ModelAndView login(@RequestParam Map<String, String> loginMap,HttpServletRequest request) throws Exception;
	public ModelAndView loginForm() throws Exception;
	public ModelAndView logout(HttpServletRequest request) throws Exception;
	public ModelAndView memberForm() throws Exception;
	public ResponseEntity  addMember(@ModelAttribute("member") MemberVO member,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity   overlapped(@RequestParam("id") String id) throws Exception;

}
