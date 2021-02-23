package com.bms.goods.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bms.goods.vo.GoodsVO;
import com.bms.goods.vo.ImageFileVO;

@Repository("goodsDAO")
public class GoodsDAOImpl  implements GoodsDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException {
	   return sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);	
	}
	
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
	   return sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
	}
	
	@Override
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException{
		 return sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);
	}
	
	@Override
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException{
		return sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
	}
	
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException{
		return sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
	}
	
}
