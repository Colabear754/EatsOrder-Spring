<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<div class="row" >
		<div class="co col-sm-12">
			<div class="info-d" style="border: 1px solid grey; width:100%">
				<div id="info" class="info-list">
					<div class="info-item">
						<div class="info-item-title">사장님알림</div>
						<table class="info-images" style="margin: 0; width: 100%; ">
							<tbody>
								<tr>
									<td>
										<div>
											${restaurant.rst_notice}
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="info-item" style="display: block; border-bottom: 1px solid gray;">
					<div class="info-item-title">업체정보</div>
					<p>
						<i>영업시간</i>
						<span>${restaurant.hours}</span>
					</p>
					<p>
						<i>전화번호</i>
						<span>${restaurant.phone}</span>
					</p>
					<p>
						<i>주소</i>
						<span>${restaurant.address}</span>
					</p>
				</div>
				<div class="info-item" style="display: block; border-bottom: 1px solid gray;">
					<div class="info-item-title">결제정보</div>
					<p>
						<i>최소주문금액</i>
						<span><fmt:formatNumber value="${restaurant.min_order}" pattern="#,###"/>원</span>
					</p>
					<p>
						<i>결제수단</i>
						<span>${restaurant.payments}</span>
					</p>
				</div>
				<div class="info-item" style="display: block; border-bottom: 1px solid gray;">
					<div class="info-item-title">사업자정보</div>
					<p>
						<i>상호명</i>
						<span>${restaurant.bussiness_name}</span>
					</p>
					<p>
						<i>사업자등록번호</i>
						<span>${restaurant.bussiness_number}</span>
					</p>
				</div>
				<div class="info-item">
					<div class="info-item-title">원산지정보</div>
					<p></p>
					<pre>
${restaurant.origin}
								</pre>
				</div>
			</div>
		</div><!--col-sm-8-->
	</div>	<!-- row-->
</div> <!-- contaniner-->
