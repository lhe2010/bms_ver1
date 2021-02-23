package com.bms.goods.controller;

import java.util.ArrayList;
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

import com.bms.common.base.BaseController;
import com.bms.goods.service.GoodsService;
import com.bms.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	
	@RequestMapping(value="/goodsDetail.do" ,method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		Map<String,Object> goodsMap=goodsService.goodsDetail(goods_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/goods/goodsDetail");
		mv.addObject("goodsMap", goodsMap);
		
		GoodsVO goodsVO=(GoodsVO)goodsMap.get("goodsVO");
		
		addGoodsInQuick(goods_id,goodsVO,session);
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/keywordSearch.do" , method = RequestMethod.GET , produces = "application/text; charset=utf8")
	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		if (keyword == null || keyword.equals(""))
		   return null ;
	
		keyword = keyword.toUpperCase();
	    List<String> keywordList =goodsService.keywordSearch(keyword);
	    
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		 		
	    String jsonInfo = jsonObject.toString();
	    return jsonInfo ;
	    
	}
	
	
	@RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/goods/searchGoods");
		mv.addObject("goodsList", goodsService.searchGoods(searchWord));
		
		return mv;
		
	}
	
	
	private void addGoodsInQuick(String goods_id,GoodsVO goodsVO,HttpSession session){
		
		boolean already_existed=false;
		List<GoodsVO> quickGoodsList =(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");
		
		if (quickGoodsList != null){
			if (quickGoodsList.size() < 4){ 
				for (int i=0; i<quickGoodsList.size();i++){
					GoodsVO _goodsBean = quickGoodsList.get(i);
					if (goods_id.equals(_goodsBean.getGoods_id())){
						already_existed=true;
						break;
					}
				}
				if (already_existed==false){
					quickGoodsList.add(goodsVO);
				}
			}
			
		}
		else {
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
			
		}
		session.setAttribute("quickGoodsList"    , quickGoodsList);
		session.setAttribute("quickGoodsListNum" , quickGoodsList.size());
	}
	
}
