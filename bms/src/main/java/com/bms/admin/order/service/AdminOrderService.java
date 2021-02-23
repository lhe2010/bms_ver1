package com.bms.admin.order.service;

import java.util.List;
import java.util.Map;

import com.bms.order.vo.OrderVO;

public interface AdminOrderService {
	
	public List<OrderVO>listNewOrder(Map<String,Object> condMap) throws Exception;
	public void  modifyDeliveryState(Map<String, String> deliveryMap) throws Exception;
	public Map<String,Object> orderDetail(int order_id) throws Exception;
	
}
