<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="tools" prefix="t"%>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="topbar">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="dashboard"> <spring:message
							code="app.name" text="Application - Computer Database" /></a>					
				</div>
				<ul id="list_lang" class="nav navbar-nav navbar-right">
						<li><t:link url="" curPage="${page.number + 1}"
								search="${search}" order="${order }" dir="${dir}"
								update="${update}" lang="en">
								<img src="img/United_Kingdom.png" />
							</t:link></li>
						<li><t:link url="" curPage="${page.number + 1}"
								search="${search}" order="${order }" dir="${dir}"
								update="${update}" lang="fr">
								<img src="img/France.png" />
							</t:link></li>
					</ul>
			</div>
		</div>
	</header>