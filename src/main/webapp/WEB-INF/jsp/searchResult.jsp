<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:forEach items="${requestScope.searchPro }" var="_search" >
			<div class="col-md-3" style="height: 450px;">
				<div class="shop-holder">
					<div class="product-img">
						<a href="tosingle/${_search.id }">
							<img width="225px" height="265px" src="images/${_search.imgUrl }" class="img-responsive" alt="item4">
						</a>
						<a href="" class="hidden" ></a>
					</div>
				</div>
				<div class="shop-content" style="height: 120px;">
					<h3 >
						<a href="tosingle/${_search.id }" >${_search.proName }</a>
					</h3>
					<span>
						<span class="amount">¥${_search.price }</span>
					</span>
					
				</div>
				
			</div>
	</c:forEach>
