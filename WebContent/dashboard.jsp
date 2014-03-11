<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="pagination" prefix="page"%>
<%@ page import="com.excilys.projet.model.ComputerOrder"%>


<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">${requestScope['number_of_result']}&nbsp;<spring:message
				code="dashboard.computers_found" text="Computers found"/></h1>
	<c:if
		test="${!empty requestScope['message'] && fn:length(requestScope['message']) != 0}">
		<div
			class="alert-message ${ requestScope['error'] ? 'error' : 'success'}">
			<c:forEach var="message" items="${requestScope['message']}">
				<p>${message}</p>
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
				<th><a
					href="./dashboard?order=${ requestScope['order'] == 'ORDER_BY_NAME_ASC' ? 'orderByNameDesc' : 'orderByNameAsc'}${!empty param.search ? '&search='.concat(param.search) : ''}"><spring:message
							code="dashboard.computer_name" text="Computer Name" /></a>&nbsp;${ requestScope['order'] == 'ORDER_BY_NAME_ASC' ? 'ASC' : ''}${ requestScope['order'] == 'ORDER_BY_NAME_DESC' ? 'DESC' : ''}</th>
				<th><a
					href="./dashboard?order=${ requestScope['order'] == 'ORDER_BY_INTRODUCED_DATE_ASC' ? 'orderByIntroducedDateDesc' : 'orderByIntroducedDateAsc'}${!empty param.search ? '&search='.concat(param.search) : ''}"><spring:message
							code="dashboard.introduced_date" text="Introduced Date" /></a>&nbsp;${ requestScope['order'] == 'ORDER_BY_INTRODUCED_DATE_ASC' ? 'ASC' : ''}${ requestScope['order'] == 'ORDER_BY_INTRODUCED_DATE_DESC' ? 'DESC' : ''}</th>
				<!-- Table header for Discontinued Date -->
				<th><a
					href="./dashboard?order=${ requestScope['order'] == 'ORDER_BY_DISCONTINUED_DATE_ASC' ? 'orderByDiscontinuedDateDesc' : 'orderByDiscontinuedDateAsc'}${!empty param.search ? '&search='.concat(param.search) : ''}"><spring:message
							code="dashboard.discontinued_date" text="Discontinued Date" /></a>&nbsp;${ requestScope['order'] == 'ORDER_BY_DISCONTINUED_DATE_ASC' ? 'ASC' : ''}${ requestScope['order'] == 'ORDER_BY_DISCONTINUED_DATE_DESC' ? 'DESC' : ''}</th>
				<!-- Table header for Company -->
				<th><a
					href="./dashboard?order=${ requestScope['order'] == 'ORDER_BY_COMPANY_NAME_ASC' ? 'orderByCompanyNameDesc' : 'orderByCompanyNameAsc'}${!empty param.search ? '&search='.concat(param.search) : ''}"><spring:message
							code="dashboard.company" text="Company" /></a>&nbsp;${ requestScope['order'] == 'ORDER_BY_COMPANY_NAME_ASC' ? 'ASC' : ''}${ requestScope['order'] == 'ORDER_BY_COMPANY_NAME_DESC' ? 'DESC' : ''}</th>
				<th><spring:message code="dashboard.delete" text="Delete" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="computer" items="${requestScope['list_computers']}">
				<tr>
					<td><a href="./addComputer?update=${computer.id}" onclick="">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.companyName}</td>
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
	<page:pagination lastPage="${requestScope['last_page']}"
		currentPage="${requestScope['current_page']}"
		queryParameters="${requestScope['query_parameters']}" />
</section>

<jsp:include page="include/footer.jsp" />
