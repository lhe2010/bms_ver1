package com.bms.admin.goods.service;

import java.util.List;
import java.util.Map;

import com.bms.goods.vo.GoodsVO;
import com.bms.goods.vo.ImageFileVO;
import com.bms.order.vo.OrderVO;

public interface AdminGoodsService {
	public int  addNewGoods(Map newGoodsMap) throws Exception;
	public List<GoodsVO> listNewGoods(Map<String,Object> condMap) throws Exception;
	public Map<String,Object> goodsDetail(int goods_id) throws Exception;
	public List goodsImageFile(int goods_id) throws Exception;
	public void modifyGoodsInfo(Map goodsMap) throws Exception;
	public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;
	public List<OrderVO> listOrderGoods(Map condMap) throws Exception;
	public void modifyOrderGoods(Map orderMap) throws Exception;
	public void removeGoodsImage(int image_id) throws Exception;
	public void addNewGoodsImage(List<ImageFileVO>  imageFileList) throws Exception;
}