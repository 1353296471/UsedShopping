<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>

 <!-- ${requestScope.describe } -->
 
 <c:forEach items="${requestScope.describeList }"  var="_des">
 <li>
  &nbsp; ${_des }<br>
</li> 
 </c:forEach>
