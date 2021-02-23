package com.bms.cart.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bms.cart.service.CartService;
import com.bms.cart.vo.CartVO;
import com.bms.common.base.BaseController;
import com.bms.goods.vo.GoodsVO;
import com.bms.member.vo.MemberVO;

@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl extends BaseController implements CartController{
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartVO cartVO;
	
	@Autowired
	private MemberVO memberVO;
	
	@RequestMapping(value="/myCartList.do" , method = RequestMethod.GET)
	public ModelAndView myCartMain(HttpServletRequest request)  throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cart/myCartList");
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id="";
		if (memberVO != null)
			member_id = memberVO.getMember_id();
		cartVO.setMember_id(member_id);
		
		Map<String ,List> cartMap = cartService.myCartList(cartVO);
		
		session.setAttribute("cartMap", cartMap);
		//mav.addObject("cartMap", cartMap);
		return mv;
	
	}
	
	
	@RequestMapping(value="/addGoodsInCart.do" ,method = RequestMethod.POST,produces = "application/text; charset=utf8")
	public @ResponseBody String addGoodsInCart(@RequestParam("goods_id") int goods_id, HttpServletRequest request)  throws Exception{
		
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id="";
		if (memberVO != null)
			member_id = memberVO.getMember_id();
		
		cartVO.setMember_id(member_id);
		cartVO.setGoods_id(goods_id);
		cartVO.setMember_id(member_id);
		boolean isAreadyExisted=cartService.findCartGoods(cartVO);
		
		if (isAreadyExisted) {
			return "already_existed";
		}
		else {
			cartService.addGoodsInCart(cartVO);
			return "add_success";
		}
	}
	
	
	@RequestMapping(value="/modifyCartQty.do" ,method = RequestMethod.POST)
	public @ResponseBody String  modifyCartQty(@RequestParam("goods_id") int goods_id,
			                                   @RequestParam("cart_goods_qty") int cart_goods_qty,
			                                    HttpServletRequest request)  throws Exception{
		
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		String member_id="";
		if (memberVO != null)
			member_id = memberVO.getMember_id();
		
		cartVO.setGoods_id(goods_id);
		cartVO.setMember_id(member_id);
		cartVO.setCart_goods_qty(cart_goods_qty);
		boolean result=cartService.modifyCartQty(cartVO);
		
		if (result)	 return "modify_success";
		else		 return "modify_failed";	
		
		
	}
	
	
	@RequestMapping(value="/removeCartGoods.do" , method = RequestMethod.POST)
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id) throws Exception { 
		ModelAndView mv=new ModelAndView();
		cartService.removeCartGoods(cart_id);
		mv.setViewName("redirect:/cart/myCartList.do");
		return mv;
	}
}
