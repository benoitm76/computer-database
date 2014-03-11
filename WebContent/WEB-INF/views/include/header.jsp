<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<a href="dashboard"> <spring:message code="app.name" text="Application - Computer Database" />
			</a>
		</h1>
	</header>
	<section id="internationalization">
		<a href="?lang=en"><img src="img/United_Kingdom.png" /></a>&nbsp;
		<a href="?lang=fr"><img src="img/France.png" /></a>
	</section>