package com.bms.admin.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bms.admin.order.service.AdminOrderService;
import com.bms.common.base.BaseController;
import com.bms.order.vo.OrderVO;

@Controller("adminOrderController")
@RequestMapping(value="/admin/order")
public class AdminOrderControllerImpl extends BaseController  implements AdminOrderController{
	@Autowired
	private AdminOrderService adminOrderService;
	
	@Override
	@RequestMapping(value="/adminOrderMain.do")
	public ModelAndView adminOrderMain(@RequestParam Map<String, String> dateMap , HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/order/adminOrderMain");

		HttpSession session = request.getSession();
		session.setAttribute("side_menu", "admin_mode"); 
		
		String fixedSearchPeriod  = dateMap.get("fixedSearchPeriod");
		String section            = dateMap.get("section");
		String pageNum            = dateMap.get("pageNum");
		String search_type        = dateMap.get("search_type");
		String search_word        = dateMap.get("search_word");
		
		String [] tempDate = calcSearchPeriod(fixedSearchPeriod).split(",");
		String beginDate = tempDate[0];
		String endDate  = tempDate[1];
		
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);
		
		
		HashMap<String,Object> condMap=new HashMap<String,Object>();
		if (section== null) {
			section = "1";
		}
		condMap.put("section",section);
		if (pageNum== null) {
			pageNum = "1";
		}
		condMap.put("pageNum",pageNum);
		condMap.put("beginDate",beginDate);
		condMap.put("endDate", endDate);
		condMap.put("search_type", search_type);
		condMap.put("search_word", search_word);
		List<OrderVO> newOrderList = adminOrderService.listNewOrder(condMap);
		mv.addObject("newOrderList",newOrderList);
		
		String beginDate1[]=beginDate.split("-");
		String endDate2[]=endDate.split("-");
		mv.addObject("beginYear",beginDate1[0]);
		mv.addObject("beginMonth",beginDate1[1]);
		mv.addObject("beginDay",beginDate1[2]);
		mv.addObject("endYear",endDate2[0]);
		mv.addObject("endMonth",endDate2[1]);
		mv.addObject("endDay",endDate2[2]);
		
		mv.addObject("section", section);
		mv.addObject("pageNum", pageNum);
		return mv;
		
	}
	
	@Override
	@RequestMapping(value="/modifyDeliveryState.do" ,method={RequestMethod.POST})
	public ResponseEntity modifyDeliveryState(@RequestParam Map<String, String> deliveryMap)  throws Exception {
		adminOrderService.modifyDeliveryState(deliveryMap);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
		
	}
	
	@Override
	@RequestMapping(value="/orderDetail.do" ,method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView orderDetail(@RequestParam("order_id") int order_id)  throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/order/adminOrderMain");
		mv.addObject("orderMap", adminOrderService.orderDetail(order_id));
		return mv;
	}
	
}
