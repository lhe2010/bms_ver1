package com.bms.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.base.BaseController;
import com.bms.goods.service.GoodsService;
import com.bms.goods.vo.GoodsVO;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value= "/")
	public ModelAndView home() throws Exception{
		return new ModelAndView("/main/main");
	}
	
	
	@RequestMapping(value= "/main/main.do")
	public ModelAndView main(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/main/main");
		
		HttpSession session = request.getSession();
		session.setAttribute("side_menu", "user");
		
		Map<String,List<GoodsVO>> goodsMap = goodsService.listGoods();
		mv.addObject("goodsMap", goodsMap);
		
		return mv;
		
	}
}
