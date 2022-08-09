<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="reviewCount" value="${fn:length(reviewData)}" />
<c:set var="orderCount" value="${fn:length(orderList)}" />
<div class="content_box">
    <!-- 작성 전 리뷰 컨테이너 -->
    <div class="review_wrapper">
        <h1>리뷰관리</h1>
        <h2>작성 전 리뷰</h2>
        <div class="grid_box">
            <!-- 리뷰 form 1 -->
			<c:if test="${orderCount == 0}">
			<h3>작성 가능한 리뷰가 없습니다.</h3>
			</c:if>
			<c:if test="${orderCount > 0 }">
				<c:forEach var="orderList" items="${orderList}">
				<c:if test="${orderList.elapsed_time >= (1 / 60) * 25 && orderList.elapsed_time < 10080}">
				<form id="review_form" name="review_form" method="post" action="insertMyReview.do" enctype="multipart/form-data"> 
					<input type="hidden" name="pageNum" value="${pageNum}">
					<input type="hidden"  name="order_number" value="${orderList.order_number}">
					<input type="hidden" id="ratings" name="rating" value="${rating}">
					<table class="table_box">
						<tbody>
							<!-- 가게명, 삭제 -->
							<tr class="shop_name">
								<td>
									<h3 class="shop">
										<a href="/eatsorder/restaurant/rst?rst_id=${orderList.rst_id}">
										${orderList.rst_name}
										<span>></span>
										</a>
									</h3>
								</td>
							</tr>
							<!-- 별점 -->
							<tr>
								<td class="star_box">
									<p id="star">
										<button type="button" value="1" name="rating1"><i class="fa-solid fa-star"></i></button>
										<button type="button" value="2" name="rating2"><i class="fa-solid fa-star"></i></button>
										<button type="button" value="3" name="rating3"><i class="fa-solid fa-star"></i></button>
										<button type="button" value="4" name="rating4"><i class="fa-solid fa-star"></i></button>
										<button type="button" value="5" name="rating5"><i class="fa-solid fa-star"></i></button>
									<p>
								</td>
								<td></td>
							</tr>
							<!-- 파일 선택 1번째 줄-->
								<tr>
									<td>
										<input type="file" class="hidden_input" name="photo1"
											accept="image/png" />
									</td>
									<td>
										<input type="file" class="hidden_input" name="photo2"
											accept="image/png"/>
									</td>
								</tr>
								<!-- 파일 선택 2번째 줄-->
								<tr>
									<td>
										<input type="file" class="hidden_input" name="photo3" 
											accept="image/png"/>
									</td>
									<td>
										<input type="file" class="hidden_input" name="photo4" 
											accept="image/png"/>
									</td>
								</tr>
								<!-- 파일 선택 3번째 줄-->
								<tr>
									<td>
										<input type="file" class="hidden_input" name="photo5" 
											accept="image/png"/>
											
									</td>
									<td></td>
								</tr>
							<!-- 이미지, 글자수, 리뷰 -->
							<tr>
								<td class="shop_img">
									<img src="./img/review_food.jpg" alt="리뷰 예시 사진">
								</td>
								<td class="text_box">
									<span class="text_count">0자</span>
									<span class="text_total">/180자</span>
									<textarea class="review_write" name="content" cols="35"
										rows="6" maxlength="180" minlength="1" placeholder="리뷰를 작성해주세요"></textarea>
								</td>
							</tr>
							<!-- 작성버튼 -->
							<tr>
								<td></td>
								<td class="submit_btn_box">
									<input type="submit" id="submit_btn" name="submit_btn" value="작성">
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				</c:if>
				</c:forEach>
			</c:if>
        </div>
        <!-- 작성완료 리뷰 컨테이너-->
        <div class="review_wrapper">
            <hr>
            <h2>작성완료 리뷰</h2>
            <div class="grid_box2">
            <c:if test="${reviewCount == 0}">
				<h3>작성된 리뷰가 없습니다.</h3>
			</c:if>
			<c:if test="${reviewCount > 0 }">
                <!-- 작성완료 리뷰1 -->
                <c:forEach var="reviewData" items="${reviewData}">
                <div class="review_box2">
                <input type="hidden" class="email" value="${email}">
                <input type="hidden" class="review_number" value="${reviewData.review.review_number}">
                    <h3>
						<a href="/EatsOrder/restaurant/rst_form.do?rst_id=${reviewData.restaurant.rst_id}">
                        ${reviewData.restaurant.rst_name}
                        <span>></span>
						</a>
                    </h3>
                    <div class="star_box2">
                        <p class="star_off">
							<c:forEach var="i" begin="1" end="${reviewData.review.rating }">
							<i class="fa-solid fa-star" style="color:#FECB10"></i>
							</c:forEach>
							<c:forEach var="i" begin="1" end="${5-reviewData.review.rating }">
							<i class="fa-solid fa-star"></i>
							</c:forEach>
                        </p>
                    </div>
                    <div class="del_btn">
                        <a class="delete_btn">삭제</a>
                    </div>
                    <div class="review_img2">
						<c:if test="${reviewData.review.photo5 !=null }">
                        <img src="../reviewPhoto/${reviewData.review.photo5}" alt="리뷰사진">
                        </c:if>
                        <c:if test="${reviewData.review.photo5 ==null }">
                        <img src="./img/review_food.jpg" alt="리뷰 예시 사진">
                        </c:if>
                    </div>
                    <div class="review_text2">
                        <div>${reviewData.review.content}</div>
                    </div>
                </div>
                </c:forEach>
            </c:if>
            </div>
        </div>
    </div>
</div>