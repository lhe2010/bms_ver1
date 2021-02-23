package com.bms.admin.goods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bms.goods.vo.GoodsVO;
import com.bms.goods.vo.ImageFileVO;
import com.bms.order.vo.OrderVO;

@Repository("adminGoodsDAO")
public class AdminGoodsDAOImpl  implements AdminGoodsDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertNewGoods(Map newGoodsMap) throws DataAccessException {
		sqlSession.insert("mapper.admin.goods.insertNewGoods",newGoodsMap);
		return Integer.parseInt((String)newGoodsMap.get("goods_id"));
	}
	
	@Override
	public void insertGoodsImageFile(List<ImageFileVO> fileList)  throws DataAccessException {
		for (int i=0; i<fileList.size();i++){
			sqlSession.insert("mapper.admin.goods.insertGoodsImageFile" , (ImageFileVO)fileList.get(i));
		}
	}
		
	@Override
	public List<GoodsVO>selectNewGoodsList(Map condMap) throws DataAccessException {
		return sqlSession.selectList("mapper.admin.goods.selectNewGoodsList",condMap);
	}
	
	@Override
	public GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException{
		return sqlSession.selectOne("mapper.admin.goods.selectGoodsDetail",goods_id);
	}
	
	@Override
	public List selectGoodsImageFileList(int goods_id) throws DataAccessException {
		return (ArrayList)sqlSession.selectList("mapper.admin.goods.selectGoodsImageFileList",goods_id);
	}
	
	@Override
	public void updateGoodsInfo(Map goodsMap) throws DataAccessException{
		sqlSession.update("mapper.admin.goods.updateGoodsInfo",goodsMap);
	}
	
	@Override
	public void deleteGoodsImage(int image_id) throws DataAccessException{
		sqlSession.delete("mapper.admin.goods.deleteGoodsImage",image_id);
	}
	
	@Override
	public void deleteGoodsImage(List fileList) throws DataAccessException{
		int image_id;
		for (int i=0; i<fileList.size();i++){
			ImageFileVO bean=(ImageFileVO) fileList.get(i);
			image_id=bean.getImage_id();
			sqlSession.delete("mapper.admin.goods.deleteGoodsImage",image_id);	
		}
	}

	@Override
	public List<OrderVO> selectOrderGoodsList(Map condMap) throws DataAccessException{
		return (ArrayList)sqlSession.selectList("mapper.admin.selectOrderGoodsList",condMap);
	}	
	
	@Override
	public void updateOrderGoods(Map orderMap) throws DataAccessException{
		sqlSession.update("mapper.admin.goods.updateOrderGoods",orderMap);
		
	}

	@Override
	public void updateGoodsImage(List<ImageFileVO> imageFileList) throws DataAccessException {
		
		for (int i=0; i<imageFileList.size();i++){
			ImageFileVO imageFileVO = imageFileList.get(i);
			sqlSession.update("mapper.admin.goods.updateGoodsImage",imageFileVO);	
		}
		
	}





	

}
