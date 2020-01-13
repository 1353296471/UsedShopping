<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${types }" var="type" end="3" varStatus="s">
	<li class="grid">
		<a class="color${s.index + 1}" href="/searchType/${type.catalogId }">${type.catalogTypeOne }</a>
	</li>
</c:forEach>

<!-- Single button -->
<div class="btn-group">
	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		更多
		<span class="caret"></span>
	</button>
	<ul class="dropdown-menu">
		<c:forEach items="${types }" var="type" begin="4" varStatus="s">
			<li class="grid">
				<a  href="/searchType/${type.catalogId }">${type.catalogTypeOne }</a>
			</li>
		</c:forEach>
	</ul>
</div>