<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.excilys.projet.model.ComputerOrder"%>
<%@ taglib uri="pagination" prefix="page" %>
<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">${requestScope['number_of_result']}
		Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer.jsp">Add
			Computer</a>
	</div>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th><a
					href="./dashboard?orderByName=${ requestScope['order'] == 'ORDER_BY_NAME_ASC' ? 'desc' : 'asc'}">Computer
						Name</a></th>
				<th><a
					href="./dashboard?orderByIntroducedDate=${ requestScope['order'] == 'ORDER_BY_INTRODUCED_DATE_ASC' ? 'desc' : 'asc'}">Introduced
						Date</a></th>
				<!-- Table header for Discontinued Date -->
				<th><a
					href="./dashboard?orderByDiscontinuedDate=${ requestScope['order'] == 'ORDER_BY_DISCONTINUED_DATE_ASC' ? 'desc' : 'asc'}">Discontinued
						Date</a></th>
				<!-- Table header for Company -->
				<th><a
					href="./dashboard?orderByCompanyName=${ requestScope['order'] == 'ORDER_BY_COMPANY_NAME_ASC' ? 'desc' : 'asc'}">Company</a></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="computer" items="${requestScope['list_computers']}">
				<tr>
					<td><a href="./addComputer?update=${computer.id}" onclick="">${computer.name}</a></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${computer.introduced}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${computer.discontinued}" /></td>
					<td>${computer.company.name}</td>
					<td>
						<form style="margin-bottom: 0px;" action="./dashboard"
							method="POST">
							<input type="hidden" name="id" value="${computer.id}" /> <input
								type="submit" class="btn danger" value="Delete" />
						</form>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<page:pagination lastPage="${requestScope['last_page']}" currentPage="${requestScope['current_page']}" queryParameters="${requestScope['query_parameters']}"/>
</section>

<jsp:include page="include/footer.jsp" />
