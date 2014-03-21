<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<h1>
		<spring:message
			code="${cDTO.id==0 ? 'add_computer.add_title' : 'add_computer.update_title'}"
			text="Add/Update title" />
	</h1>
	<c:if test="${!empty message && fn:length(message) != 0}">
		<div class="alert-message ${ error ? 'error' : 'success'}">
			<c:forEach var="m" items="${message}">
				<spring:message code="${m}" text="${m}" />
			</c:forEach>
		</div>
	</c:if>

	<form:form
		action="./addComputer${!empty cDTO && cDTO.id!=0 ? '?update='.concat(cDTO.id) : ''}"
		method="POST" commandName="cDTO">
		<form:hidden path="id" />
		<fieldset>
			<div class="clearfix">
				<label for="name"><spring:message
						code="add_computer.computer_name" text="Computer Name" />:</label>
				<div class="input">
					<form:input path="name" />
					<span class="help-inline"><spring:message
							code="add_computer.required" text="Required" /></span>
					<form:errors path="name" class="error" />
				</div>
			</div>
			<div class="clearfix">
				<spring:message code="date.pattern" text="yyyy-MM-dd" var="pattern" />
				<spring:message code="date.pattern_string" text="YYYY-MM-DD"
					var="pattern_string" />
				<label for="introduced"><spring:message
						code="add_computer.intoduced_date" text="Introduced Date" />:</label>
				<div class="input">
					<form:input type="text" path="introduced" />
					<span class="help-inline">${pattern_string}</span>
					<form:errors path="introduced" class="error" />
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message
						code="add_computer.discontinued_date" text="Discontinued date" />:</label>
				<div class="input">
					<form:input type="text" path="discontinued" />
					<span class="help-inline">${pattern_string}</span>
					<form:errors path="discontinued" class="error" />
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message
						code="add_computer.company_name" text="Company Name" />:</label>
				<div class="input">
					<form:select path="companyId">
						<form:option value="0">--</form:option>
						<form:options items="${list_companies}" itemValue="id"
							itemLabel="name" />
					</form:select>
					<form:errors path="companyId" class="error" />
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<spring:message
				code="${cDTO.id==0 ? 'add_computer.add' : 'add_computer.update'}"
				text="Add/Update title" var="button_text" />
			<input type="submit" value="${button_text}" class="btn primary">
			or <a href="dashboard" class="btn"><spring:message
					code="add_computer.cancel" text="Cancel" /></a>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />