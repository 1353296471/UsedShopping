<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="box_1">
	<div class="col-md-7">
		<div class="section group">
			<c:forEach items="${showProducts }" var="pro">
				<div class="col_1_of_3 span_1_of_3">
					<div class="shop-holder">
						<div class="product-img">
							<a href="tosingle/${pro.pro.id}"> <img width="225" height="265"
								src="images/${pro.mainImg.imgUrl }" class="img-responsive" alt="item4">
							</a> 
							<a href="" class="hidden" ></a>
							</div>
					</div>
					<div class="shop-content" style="height: 80px;">
					<!-- 
					<div>
							<a href="tosingle/${pro.pro.id}" rel="tag">${pro.pro.proName }</a>
						</div>
						<h3>
							<a href="tosingle/${pro.pro.id}">${pro.catalog.sex  }-${pro.catalog.catalogTypeOne }-${ pro.catalog.catalogTypeTwo }</a>
						</h3>
					 -->
						
						<h3>
							<a href="tosingle/${pro.pro.id}">${pro.pro.proName }</a>
						</h3>
						<span><span class="amount">￥${pro.pro.price }</span></span>
					</div>
				</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="col-md-5 row_3">
		<div class="about-block-content">
			<div class="border-add"></div>
			<h2>关于我们</h2>
			<p>这是我们的个人服装品牌BUYSHOP</p>
			<p>每一件衣服都是我们用心制作出来的，选自上好的布料</p>
			<p>希望你能在这里找到让你满意的衣服</p>
		</div>
		<img src="images/pic9.jpg" class="img-responsive" alt="" />
	</div>
	<div class="clearfix"></div>
</div>