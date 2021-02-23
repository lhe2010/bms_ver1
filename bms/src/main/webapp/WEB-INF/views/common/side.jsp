<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
	
<nav>
 <ul>
  <c:choose>
	<c:when test="${side_menu=='admin_mode'}">
	   <li>
			<h3>주요기능</h3>
			<ul>
				<li><a href="${contextPath}/admin/goods/adminGoodsMain.do">상품관리</a></li>
				<li><a href="${contextPath}/admin/order/adminOrderMain.do">주문관리</a></li>
				<li><a href="${contextPath}/admin/member/adminMemberMain.do">회원관리</a></li>
				<li><a href="#">배송관리</a></li>
				<li><a href="#">게시판관리</a></li>
			</ul>
		</li>
	</c:when>
	<c:when test="${side_menu=='my_page'}">
		<li>
			<h3>주문내역</h3>
			<ul>
				<li><a href="${contextPath}/mypage/listMyOrderHistory.do">주문내역/배송 조회</a></li>
				<li><a href="#">반품/교환 신청 및 조회</a></li>
				<li><a href="#">취소 주문 내역</a></li>
				<li><a href="#">세금 계산서</a></li>
			</ul>
		</li>
		<li>
			<h3>정보내역</h3>
			<ul>
				<li><a href="${contextPath}/mypage/myDetailInfo.do">회원정보관리</a></li>
				<li><a href="#">나의 주소록</a></li>
				<li><a href="#">개인정보 동의내역</a></li>
				<li><a href="#">회원탈퇴</a></li>
			</ul>
		</li>
	</c:when>
	<c:otherwise>
		<li>
			<h3>&nbsp;국내도서</h3>
			<ul>
				<li><a href="#">Back end</a></li>
				<li><a href="#">Front end</a></li>
				<li><a href="#">Cloud</a></li>
				<li><a href="#">Server</a></li>
				<li><a href="#">Network</a></li>
				<li><a href="#">Security</a></li>
			</ul>
		</li>
		<li>
			<h3>&nbsp;외국도서</h3>
			<ul>
				<li><a href="#">Back end</a></li>
				<li><a href="#">Front end</a></li>
				<li><a href="#">Cloud</a></li>
				<li><a href="#">Server</a></li>
				<li><a href="#">Network</a></li>
				<li><a href="#">Security</a></li>
			</ul>
		</li>
	 </c:otherwise>
	</c:choose>	
  </ul>
</nav>
<div class="clear"></div>
<div id="banner">
	<a href="#"><img width="190" height="163" src="${contextPath}/resources/image/n-pay.jpg"> </a>
</div>
<div id="notice">
	<h2>공지사항</h2>
	<ul>
		<li><a href="#">BMS 개인정보 처리방침 개정 안내</a></li>
		<li><a href="#">BMS mobile 서비스 종료 안내 </a></li>
		<li><a href="#">마케팅정보 수신동의 확인 안내</a></li>
		<li><a href="#">북클럽 혜택 '커피 무료쿠폰' 종료 안내</a></li>
		<li><a href="#">BMS 이용약관 개정 안내</a></li>
	</ul>
</div>
<div id="banner">
	<a href="#"><img width="190" height="362" src="${contextPath}/resources/image/side_banner1.jpg"></a>
</div>
<div id="banner">
	<a href="#"><img width="190" height="104" src="${contextPath}/resources/image/call_center_logo.jpg"></a>
</div>
<div id="banner">
	<a href="#"><img width="190" height="69" src="${contextPath}/resources/image/QnA_logo.jpg"></a>
</div>
</html>