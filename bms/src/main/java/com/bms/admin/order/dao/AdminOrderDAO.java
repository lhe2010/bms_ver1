package com.bms.admin.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bms.member.vo.MemberVO;
import com.bms.order.vo.OrderVO;

public interface AdminOrderDAO {
	
	public List<OrderVO> selectNewOrderList(Map<String,Object> condMap) throws DataAccessException;
	public void updateDeliveryState(Map<String, String> deliveryMap) throws DataAccessException;
	public List<OrderVO> selectOrderDetail(int order_id) throws DataAccessException;
	public MemberVO selectOrderer(String member_id) throws DataAccessException;
	
}
