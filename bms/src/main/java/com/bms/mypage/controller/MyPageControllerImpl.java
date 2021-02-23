package com.bms.mypage.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.base.BaseController;
import com.bms.member.vo.MemberVO;
import com.bms.mypage.service.MyPageService;
import com.bms.order.vo.OrderVO;

@Controller("myPageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl extends BaseController  implements MyPageController{
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/myPageMain.do" ,method = RequestMethod.GET)
	public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message, HttpServletRequest request)  throws Exception {
		
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "my_page");
		
		ModelAndView mv = new ModelAndView("/mypage/myPageMain");
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id = "";
		if (memberVO != null) 	member_id=memberVO.getMember_id();
		else 					mv.setViewName("redirect:/main/main.do");
		
		mv.addObject("message", message);
		mv.addObject("myOrderList", myPageService.listMyOrderGoods(member_id));

		return mv;
		
	}
	
	
	@Override
	@RequestMapping(value="/myOrderDetail.do" ,method = RequestMethod.GET)
	public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request)  throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/mypage/myOrderDetail");
		HttpSession session=request.getSession();
		
		mv.addObject("orderer", (MemberVO)session.getAttribute("memberInfo"));
		mv.addObject("myOrderList",myPageService.findMyOrderInfo(order_id));
		return mv;
	}
	
	
	@Override
	@RequestMapping(value="/listMyOrderHistory.do" ,method = RequestMethod.GET)
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap, HttpServletRequest request)  throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/mypage/listMyOrderHistory");
		
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
		
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String beginDate=null,endDate=null;
		
		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);
		dateMap.put("member_id", member_id);
		List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap);
		
		String beginDate1[]=beginDate.split("-");
		String endDate1[]=endDate.split("-");
		mv.addObject("beginYear",beginDate1[0]);
		mv.addObject("beginMonth",beginDate1[1]);
		mv.addObject("beginDay",beginDate1[2]);
		mv.addObject("endYear",endDate1[0]);
		mv.addObject("endMonth",endDate1[1]);
		mv.addObject("endDay",endDate1[2]);
		mv.addObject("myOrderHistList", myOrderHistList);
		return mv;
		
	}	
	
	
	@Override
	@RequestMapping(value="/cancelMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView cancelMyOrder(@RequestParam("order_id") String order_id)  throws Exception {
		ModelAndView mv = new ModelAndView();
		myPageService.cancelOrder(order_id);
		mv.addObject("message", "cancel_order");
		mv.setViewName("redirect:/mypage/myPageMain.do");
		return mv;
	}
	
	
	@Override
	@RequestMapping(value="/myDetailInfo.do" , method = RequestMethod.GET)
	public ModelAndView myDetailInfo() throws Exception {
		ModelAndView mv = new ModelAndView("/mypage/myDetailInfo");
		return mv;
	}	
	
	
	@Override
	@RequestMapping(value="/modifyMyInfo.do" , method = RequestMethod.POST)
	public ResponseEntity<String>  modifyMyInfo(@RequestParam("attribute")  String attribute,
			                 					@RequestParam("value")  String value,
			                 					HttpServletRequest request)  throws Exception {
		
		Map<String,String> memberMap=new HashMap<String,String>();
		String val[]=null;
		HttpSession session=request.getSession();
		
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
		
		if (attribute.equals("member_birth")){
			val=value.split(",");
			memberMap.put("member_birth_y",val[0]);
			memberMap.put("member_birth_m",val[1]);
			memberMap.put("member_birth_d",val[2]);
			memberMap.put("member_birth_gn",val[3]);
		}
		else if (attribute.equals("tel")){
			val=value.split(",");
			memberMap.put("tel1",val[0]);
			memberMap.put("tel2",val[1]);
			memberMap.put("tel3",val[2]);
		}
		else if (attribute.equals("hp")){
			val=value.split(",");
			memberMap.put("hp1",val[0]);
			memberMap.put("hp2",val[1]);
			memberMap.put("hp3",val[2]);
			memberMap.put("smssts_yn", val[3]);
		}
		else if (attribute.equals("email")){
			val=value.split(",");
			memberMap.put("email1",val[0]);
			memberMap.put("email2",val[1]);
			memberMap.put("emailsts_yn", val[2]);
		}
		else if (attribute.equals("address")){
			val=value.split(",");
			memberMap.put("zipcode",val[0]);
			memberMap.put("roadAddress",val[1]);
			memberMap.put("jibunAddress", val[2]);
			memberMap.put("namujiAddress", val[3]);
		}
		else {
			memberMap.put(attribute,value);	
		}
		
		memberMap.put("member_id", member_id);
		
		
		memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		return new ResponseEntity<String>("mod_success", new HttpHeaders(), HttpStatus.OK);
		
	}	
	
}
