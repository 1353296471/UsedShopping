<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<li class="grid">
	<a class="color1" id="meun" href="search.html?searchdata=男">男装</a>
</li>
<li class="grid">
	<a class="color2" id="meun" href="search.html?searchdata=女">女装</a>
</li>

<!-- Single button -->
<div class="btn-group">
	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		更多
		<span class="caret"></span>
	</button>
	<ul class="dropdown-menu">
		<c:forEach items="${types }" var="type">
			<li class="grid">
				<a href="searchType/${type.catalogId }">${type.catalogTypeOne }</a>
			</li>
		</c:forEach>
	</ul>
</div>