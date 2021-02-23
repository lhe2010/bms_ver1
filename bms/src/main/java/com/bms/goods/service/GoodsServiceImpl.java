package com.bms.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.goods.dao.GoodsDAO;
import com.bms.goods.vo.GoodsVO;
import com.bms.goods.vo.ImageFileVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDAO goodsDAO;
	
	
	public Map<String,List<GoodsVO>> listGoods() throws Exception {
		
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		
		goodsMap.put("bestseller"   , goodsDAO.selectGoodsList("bestseller"));
		goodsMap.put("newbook"      , goodsDAO.selectGoodsList("newbook"));
		goodsMap.put("steadyseller" , goodsDAO.selectGoodsList("steadyseller"));
		
		return goodsMap;
		
	}
	
	
	public Map<String,Object> goodsDetail(String _goods_id) throws Exception {
		
		Map<String,Object> goodsMap=new HashMap<String,Object>();
		
		goodsMap.put("goodsVO"   ,  goodsDAO.selectGoodsDetail(_goods_id));
		goodsMap.put("imageList" , goodsDAO.selectGoodsDetailImage(_goods_id));
		
		return goodsMap;
		
	}
	
	
	
	public List<String> keywordSearch(String keyword) throws Exception {
		return goodsDAO.selectKeywordSearch(keyword);
	}
	
	
	public List<GoodsVO> searchGoods(String searchWord) throws Exception{
		return goodsDAO.selectGoodsBySearchWord(searchWord);
	}
	
	
}
