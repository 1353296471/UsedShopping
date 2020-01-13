<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
		$(function () {
			$("#pageNo").change(function () {
				
				var val = $("#pageNo").val();
				var maxPage = "${requestScope.page.maxPage }";
				if(checkPage(val,maxPage)){
					//3. 页面跳转
					toSearchPage(val);
				}
			})
		})
			
</script>

<!-- 搜索结果 -->
<div class="col-md-12">
	<c:forEach items="${requestScope.searchPro }" var="_search">
		<div class="col-md-3" style="height: 450px;">
			<div class="shop-holder">
				<div class="product-img">
					<a href="/tosingle/${_search.pro.id }">
						<img width="225px" height="265px" src="/images/${_search.mainImg.imgUrl }" class="img-responsive" alt="item4">
					</a>
					<a href="" class="hidden"></a>
				</div>
			</div>
			<div class="shop-content" style="height: 120px;">
				<h3>
					<a href="/tosingle/${_search.pro.id }">${_search.pro.proName }</a>
				</h3>
				<span>
					<span class="amount">¥${_search.pro.price }</span>
				</span>

			</div>

		</div>





	</c:forEach>

	<br>



</div>
<div>
	<center>
		<br>
		共${requestScope.page.maxPage }页，当前第${requestScope.page.pageNo }页
		<br>
		<br>
		<a href="#" onclick="toSearchPage(${requestScope.page.fristPage })">首页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toSearchPage(${requestScope.page.beforePage })">上一页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toSearchPage(${requestScope.page.nextPage })">下一页</a>
		&nbsp;&nbsp;
		<a href="#" onclick="toSearchPage(${requestScope.page.maxPage })">末页</a>
		&nbsp;&nbsp;转到<input type="text" size="1" id="pageNo">页
		<br>
	</center>
</div>
<div class="clearfix"></div>
<div class="clearfix"></div>
<div class="clearfix"></div>
