<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <section>
        <div class="header_back">
            <div class="all_header">
                <div class="header_first">
                    <div class="header_text">
                        <h1>빠른 음식배달 잇츠오더</h1>
                        <h3>원하시는 음식을 검색해보세요.</h3>
                    </div>
                    <div id="address-modal">
						<div id="wrapper"></div>
					</div>
                    <form class="search_box" action="/eatsorder/restaurant/rstList" method="post">
                        <div>
                            <i class="fa-solid fa-location-dot"></i>
                            <input type="text" name="address" id="address" placeholder="클릭해서 주소를 선택해주세요 (필수입력)" readonly="readonly">
                            <input type="hidden" name="sido" id="sido">
                            <input type="hidden" name="sigungu" id="sigungu">
                            <input type="hidden" name="bname" id="bname">
                        </div>
                        <div class="food_search_bar">
                            <i class="fa-solid fa-magnifying-glass"></i>
                            <input type="text" name="searchText" id="searchText" placeholder="메뉴와 음식점을 검색하세요 (선택사항)">
                            <input type="submit" value="검색">
                        </div>
                    </form>
                </div>
                <div class="main_img_container">
                    <img src="/eatsorder/resources/main/img/main_food.png" alt="머릿글-장식-사진">
                </div>
            </div>
        </div>
    </section>
    <main>
        <div class="main_food_text" id="main_food_text"><h1>원하시는 음식 카테고리를 선택하세요</h1></div>
        <div class="food_select_container">
            <a href="#" class="notice">
                <img src="/eatsorder/resources/main/img/event.png" class="first_img" alt="이벤트-사진"><label></label>
                <span class="caption">이벤트/공지</span>
            </a>
            <a href="#" class="food_item" id="0">
                <img src="/eatsorder/resources/main/img/all.png" alt="전체보기-사진"><div>전체보기</div>
                <span class="caption">전체보기</span>
            </a>
            <a href="#" class="food_item" id="1">
                <img src="/eatsorder/resources/main/img/oneMeal_crop.png" alt="1인분-사진"><div>1인분 주문</div>
                <span class="caption">1인분 주문</span>
            </a>
            <a href="#" class="food_item" id="2">
                <img src="/eatsorder/resources/main/img/fran_crop.png" alt="프랜차이즈-사진"><div>프랜차이즈</div>
                <span class="caption">프랜차이즈</span>
            </a>
            <a href="#" class="food_item" id="3">
               <img src="/eatsorder/resources/main/img/chicken_crop.png" alt="치킨-사진"><div>치킨</div>
                <span class="caption">치킨</span>
            </a>
            <a href="#" class="food_item" id="4">
                <img src="/eatsorder/resources/main/img/pizza_crop.png" alt="피자-양식-사진"><div>피자/양식</div>
                <span class="caption">피자/양식</span>
            </a>
            <a href="#" class="food_item" id="5">
                <img src="/eatsorder/resources/main/img/china_crop.png" alt="중국집-사진"><div>중국집</div>
                <span class="caption">중국집</span>
            </a>
            <a href="#" class="food_item" id="6">
                <img src="/eatsorder/resources/main/img/korea_crop.png" alt="한식-사진"><div>한식</div>
                <span class="caption">한식</span>
            </a>
            <a href="#" class="food_item" id="7">
               <img src="/eatsorder/resources/main/img/japan_crop.png" alt="일식-돈까스-사진"><div>일식/돈까스</div>
                <span class="caption">일식/돈까스</span>
            </a>
            <a href="#" class="food_item" id="8">
                <img src="/eatsorder/resources/main/img/bossam_crop.png" alt="족발-보쌈-사진"><div>족발/보쌈</div>
                <span class="caption">족발/보쌈</span>
            </a>
            <a href="#" class="food_item" id="9">
                <img src="/eatsorder/resources/main/img/nighrMeal_crop.png" alt="야식-사진"><div>야식</div>
                <span class="caption">야식</span>
            </a>
            <a href="#" class="food_item" id="10">
                <img src="/eatsorder/resources/main/img/bun_crop.png" alt="분식-사진"><div>분식</div>
                <span class="caption">분식</span>
            </a>
            <a href="#" class="food_item" id="11">
                <img src="/eatsorder/resources/main/img/coffee_crop.png" alt="카페-디저트-사진"><div>카페/디저트</div>
                <span class="caption">카페/디저트</span>
            </a>
            <a href="#" class="food_item" id="12">
                <img src="/eatsorder/resources/main/img/ramen_crop.png" alt="편의점-마트-사진"><div>편의점/마트</div>
                <span class="caption">편의점/마트</span>
            </a>
        </div>
    </main>