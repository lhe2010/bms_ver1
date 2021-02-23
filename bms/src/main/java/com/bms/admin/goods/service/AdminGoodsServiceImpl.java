package com.bms.admin.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.admin.goods.dao.AdminGoodsDAO;
import com.bms.goods.vo.GoodsVO;
import com.bms.goods.vo.ImageFileVO;
import com.bms.order.vo.OrderVO;


@Service("adminGoodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminGoodsServiceImpl implements AdminGoodsService {
	
	@Autowired
	private AdminGoodsDAO adminGoodsDAO;
	
	
	@Override
	public int addNewGoods(Map newGoodsMap) throws Exception{
		
		int goods_id = adminGoodsDAO.insertNewGoods(newGoodsMap);
		ArrayList<ImageFileVO> imageFileList = (ArrayList)newGoodsMap.get("imageFileList");
		
		for (ImageFileVO imageFileVO : imageFileList) {
			imageFileVO.setGoods_id(goods_id);
		}
		
		adminGoodsDAO.insertGoodsImageFile(imageFileList);
		
		return goods_id;
		
	}
	
	
	@Override
	public List<GoodsVO> listNewGoods(Map<String,Object> condMap) throws Exception{
		return adminGoodsDAO.selectNewGoodsList(condMap);
	}
	
	
	@Override
	public Map<String,Object> goodsDetail(int goods_id) throws Exception {
		
		Map<String,Object> goodsMap = new HashMap<String,Object>();
		goodsMap.put("goods", adminGoodsDAO.selectGoodsDetail(goods_id));
		goodsMap.put("imageFileList", adminGoodsDAO.selectGoodsImageFileList(goods_id));
		
		return goodsMap;
		
	}
	
	
	@Override
	public List goodsImageFile(int goods_id) throws Exception{
		return adminGoodsDAO.selectGoodsImageFileList(goods_id);
	}
	
	
	@Override
	public void modifyGoodsInfo(Map goodsMap) throws Exception{
		adminGoodsDAO.updateGoodsInfo(goodsMap);
		
	}	
	@Override
	public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception{
		adminGoodsDAO.updateGoodsImage(imageFileList); 
	}
	
	@Override
	public List<OrderVO> listOrderGoods(Map condMap) throws Exception{
		return adminGoodsDAO.selectOrderGoodsList(condMap);
	}
	@Override
	public void modifyOrderGoods(Map orderMap) throws Exception{
		adminGoodsDAO.updateOrderGoods(orderMap);
	}
	
	@Override
	public void removeGoodsImage(int image_id) throws Exception{
		adminGoodsDAO.deleteGoodsImage(image_id);
	}
	
	@Override
	public void addNewGoodsImage(List<ImageFileVO> imageFileList) throws Exception{
		adminGoodsDAO.insertGoodsImageFile(imageFileList);
	}
	

	
}
