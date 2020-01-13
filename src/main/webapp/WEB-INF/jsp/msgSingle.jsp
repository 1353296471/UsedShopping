<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<center >
		<br>
		<br>
		<br>
		<h3 id="msg">${msg }</h3>
		<br>
		<br>
		<br>
	</center>


	<script>
		var loc = beva.locationutil.attach('province', 'city', 'county',
				'address');

		// loc.setSelectedCodes([697,698]);
	</script>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
