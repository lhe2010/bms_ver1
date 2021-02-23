package com.bms.admin.member.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bms.admin.member.service.AdminMemberService;
import com.bms.common.base.BaseController;
import com.bms.member.vo.MemberVO;

@Controller("adminMemberController")
@RequestMapping(value="/admin/member")
public class AdminMemberControllerImpl extends BaseController  implements AdminMemberController{
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	
	@RequestMapping(value="/adminMemberMain.do")
	public ModelAndView adminMemberMain(@RequestParam Map<String, String> dateMap , HttpServletRequest request)  throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/member/adminMemberMain");
		
		HttpSession session = request.getSession();
		session.setAttribute("side_menu", "admin_mode");
		
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String section           = dateMap.get("section");
		String pageNum           = dateMap.get("pageNum");
		String search_word       = dateMap.get("search_word");
		String search_type       = dateMap.get("search_type");
		String chapter           = dateMap.get("chapter");
		String [] tempDate       = null; 
		String beginDate         = "";
		String endDate           = "";
		
		if (dateMap.get("beginDate") == null ) {
			tempDate = calcSearchPeriod(fixedSearchPeriod).split(",");
			beginDate = tempDate[0];
			endDate = tempDate[1];
		} 
		else {
			beginDate = dateMap.get("beginDate");
			endDate = dateMap.get("endDate");
		}
		
		if (chapter == null) chapter = "1";
		if (section== null)  section = "1";
		if (pageNum== null)  pageNum = "1";
		
		
		HashMap<String,Object> condMap=new HashMap<String,Object>();
		
		condMap.put("section"       , section);
		condMap.put("pageNum"       , pageNum);
		condMap.put("chapter"       , chapter);
		condMap.put("section"       , section);
		condMap.put("beginDate"     , beginDate);
		condMap.put("endDate"       , endDate);
		condMap.put("search_word"   , search_word);
		condMap.put("search_type"   , search_type);
		condMap.put("member_id"     , dateMap.get("member_id"));
		condMap.put("member_name"   , dateMap.get("member_name"));
		condMap.put("member_hp_num" , dateMap.get("member_hp_num"));

		
		String beginDate1[] = beginDate.split("-");
		String endDate2[]   = endDate.split("-");
		mv.addObject("member_list"  , adminMemberService.listMember(condMap));
		mv.addObject("beginYear"  , beginDate1[0]);
		mv.addObject("beginMonth" , beginDate1[1]);
		mv.addObject("beginDay"   , beginDate1[2]);
		mv.addObject("endYear"    , endDate2[0]);
		mv.addObject("endMonth"   , endDate2[1]);
		mv.addObject("endDay"     , endDate2[2]);
		
		mv.addObject("chapter" , chapter);
		mv.addObject("section" , section);
		mv.addObject("pageNum" , pageNum);
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/memberDetail.do")
	public ModelAndView memberDetail(@RequestParam("member_id")String member_id)  throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/member/memberDetail");
		mv.addObject("member_info" , adminMemberService.memberDetail(member_id));
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/modifyMemberInfo.do")
	public void modifyMemberInfo(@RequestParam("member_id") String member_id , 
								@RequestParam("mod_type") String mod_type , 
								@RequestParam("value") String value , HttpServletResponse response)  throws Exception{
		
		HashMap<String,String> memberMap = new HashMap<String,String>();
		String val[]     = null;
		PrintWriter pw   = response.getWriter();
		
		if (mod_type.equals("member_pw")) {
			memberMap.put("member_pw" , value);
		}
		else if (mod_type.equals("member_gender")) {
			memberMap.put("member_gender" , value);
		}
		else if (mod_type.equals("member_birth")){
			val=value.split(",");
			memberMap.put("member_birth_y",val[0]);
			memberMap.put("member_birth_m",val[1]);
			memberMap.put("member_birth_d",val[2]);
			memberMap.put("member_birth_gn",val[3]);
		}
		else if (mod_type.equals("tel")){
			val=value.split(",");
			memberMap.put("tel1",val[0]);
			memberMap.put("tel2",val[1]);
			memberMap.put("tel3",val[2]);
			
		}
		else if (mod_type.equals("hp")){
			val=value.split(",");
			memberMap.put("hp1",val[0]);
			memberMap.put("hp2",val[1]);
			memberMap.put("hp3",val[2]);
			memberMap.put("smssts_yn", val[3]);
		}
		else if (mod_type.equals("email")){
			val=value.split(",");
			memberMap.put("email1",val[0]);
			memberMap.put("email2",val[1]);
			memberMap.put("emailsts_yn", val[2]);
		}
		else if (mod_type.equals("address")){
			val=value.split(",");
			memberMap.put("zipcode",val[0]);
			memberMap.put("roadAddress",val[1]);
			memberMap.put("jibunAddress", val[2]);
			memberMap.put("namujiAddress", val[3]);
		}
		
		memberMap.put("member_id", member_id);
		
		adminMemberService.modifyMemberInfo(memberMap);
		pw.print("mod_success");
		pw.close();		
		
	}
	
	@RequestMapping(value="/deleteMember.do" , method=RequestMethod.POST)
	public ModelAndView deleteMember(@RequestParam("del_yn") String del_yn , @RequestParam("member_id") String member_id)  throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/member/adminMemberMain.do");
		
		HashMap<String,String> memberMap = new HashMap<String,String>();
		memberMap.put("del_yn"   , del_yn);
		memberMap.put("member_id", member_id);
		
		adminMemberService.modifyMemberInfo(memberMap);
		
		return mv;
		
	}
		
}
