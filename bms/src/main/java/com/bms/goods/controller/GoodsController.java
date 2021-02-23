package com.bms.goods.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface GoodsController  {
	
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id , HttpServletRequest request) throws Exception;
	public @ResponseBody String keywordSearch(@RequestParam("keyword") String keyword , HttpServletResponse response) throws Exception;
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord) throws Exception;

}
