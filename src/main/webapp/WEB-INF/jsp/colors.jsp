<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>

<option value="1"></option>
<c:forEach items='${requestScope.colorList }' var="_color">
<c:choose>
<c:when test="${_color==1 }">
   <option value="2">米白色</option>
</c:when>
<c:when test="${_color==2 }">
   <option value="2">黑色</option>
   </c:when>  
   <c:when test="${_color==3 }">
   <option value="2">卡其色</option>
   </c:when> 
 <c:otherwise> 
 <option value="2">红色</option>
 </c:otherwise> 
</c:choose>
</c:forEach>