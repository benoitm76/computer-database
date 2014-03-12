<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="dashboard"> <spring:message code="app.name"
					text="Application - Computer Database" />
			</a>
		</h1>
	</header>

	<section id="internationalization">

		<c:url value="" var="url_en">
			<c:forEach items="${query_parameters}" var="entry">
				<c:if test="${entry.key!='lang'}">
					<c:param name="${entry.key}" value="${entry.value}" />
				</c:if>
			</c:forEach>
			<c:param name="lang" value="en" />
		</c:url>
		<c:url value="" var="url_fr">
			<c:forEach items="${query_parameters}" var="entry">
				<c:if test="${entry.key!='lang'}">
					<c:param name="${entry.key}" value="${entry.value}" />
				</c:if>
			</c:forEach>
			<c:param name="lang" value="fr" />
		</c:url>
		<a href="${url_en}"><img src="img/United_Kingdom.png" /></a>&nbsp; <a
			href="${url_fr}"><img src="img/France.png" /></a>
	</section>