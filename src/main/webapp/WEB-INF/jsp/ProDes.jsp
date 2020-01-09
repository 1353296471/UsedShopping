<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>

<!-- ${requestScope.describe } -->
<div style="white-space: pre-line;">
	<c:forEach items="${requestScope.describeList }" var="_des">
${_des }
 	</c:forEach>
</div>