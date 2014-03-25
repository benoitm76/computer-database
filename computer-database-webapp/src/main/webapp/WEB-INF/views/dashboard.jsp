<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ page import="com.excilys.projet.domain.ComputerOrder"%>
<%@ taglib uri="tools" prefix="t"%>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">${page.totalElements}&nbsp;<spring:message
			code="dashboard.computers_found" text="Computers found" />
	</h1>
	<c:if test="${!empty message && fn:length(message) != 0}">
		<div class="alert-message ${ error ? 'error' : 'success'}">
			<c:forEach var="m" items="${message}">
				<spring:message code="${m}" text="${m}" />
			</c:forEach>
		</div>
	</c:if>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search"
				value="${!empty param.search ? param.search : ''}"
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer"><spring:message
				code="dashboard.add_computer" text="Ajouter un ordinateur" /></a>
	</div>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th><t:link url="dashboard" curPage="${page.number + 1}"
						search="${search}" order="name"
						dir="${ order == 'name' && dir == 'ASC' ? 'desc' : 'asc'}">
						<spring:message code="dashboard.computer_name"
							text="Computer Name" />
					</t:link>&nbsp;${ order == 'name' && dir == 'ASC' ? 'ASC' : ''}${ order == 'name' && dir == 'DESC' ? 'DESC' : ''}</th>
				<th><t:link url="dashboard" curPage="${page.number + 1}"
						search="${search}" order="introduced"
						dir="${ order == 'introduced' && dir == 'ASC' ? 'desc' : 'asc'}">
						<spring:message code="dashboard.introduced_date"
							text="Introduced Date" />
					</t:link>&nbsp;${ order == 'introduced' && dir == 'ASC' ? 'ASC' : ''}${ order == 'introduced' && dir == 'DESC' ? 'DESC' : ''}</th>
				<!-- Table header for Discontinued Date -->
				<th><t:link url="dashboard" curPage="${page.number + 1}"
						search="${search}" order="discontinued"
						dir="${ order == 'discontinued' && dir == 'ASC' ? 'desc' : 'asc'}">
						<spring:message code="dashboard.discontinued_date"
							text="Discontinued Date" />
					</t:link>&nbsp;${ order == 'discontinued' && dir == 'ASC' ? 'ASC' : ''}${ order == 'discontinued' && dir == 'DESC' ? 'DESC' : ''}</th>
				<!-- Table header for Company -->
				<th><t:link url="dashboard" curPage="${page.number + 1}"
						search="${search}" order="company.name"
						dir="${ order == 'company.name' && dir == 'ASC' ? 'desc' : 'asc'}">
						<spring:message code="dashboard.company" text="Company" />
					</t:link>&nbsp;${ order == 'company.name' && dir == 'ASC' ? 'ASC' : ''}${ order == 'company.name' && dir == 'DESC' ? 'DESC' : ''}
				</th>
				<th><spring:message code="dashboard.delete" text="Delete" /></th>
			</tr>
		</thead>
		<spring:message code="date.pattern"
							text="yyyy-MM-dd" var="datePattern" />
		<tbody>
			<c:forEach var="computer" items="${page.content}">
				<tr>
					<td><a href="./addComputer?update=${computer.id}" onclick="">${computer.name}</a></td>
					<td><joda:format value="${computer.introduced}"
							pattern="${datePattern}" /></td>
					<td><joda:format value="${computer.discontinued}"
							pattern="${datePattern}" /></td>
					<td>${computer.company.name}</td>
					<td>
						<form class="delete_form" action="./dashboard" method="POST">
							<input type="hidden" name="id" value="${computer.id}" /> <input
								type="submit" class="btn danger"
								value="<spring:message code="dashboard.delete" text="Delete" />" />
						</form>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<t:pagination url="dashboard" lastPage="${page.totalPages}"
		curPage="${page.number}" search="${search}" order="${order}"
		dir="${dir}" />
</section>

<jsp:include page="include/footer.jsp" />
