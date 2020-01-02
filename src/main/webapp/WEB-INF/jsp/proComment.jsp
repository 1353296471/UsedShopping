<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>

<c:forEach items="${requestScope.commentList }" var='_comment'>
<li><div>${_comment.commentTime }</div>
<div>${_comment.commentDes }</div>
<hr>
</li>
</c:forEach>
