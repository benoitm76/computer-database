<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<c:set var="computer" value="${requestScope['computer']}" />
	<h1>${!empty computer ? 'Update' : 'Add'}Computer</h1>
	<c:if
		test="${!empty requestScope['message'] && fn:length(requestScope['message']) != 0}">
		<div
			class="alert-message ${ requestScope['error'] ? 'error' : 'success'}">
			<c:forEach var="message" items="${requestScope['message']}">
				<p>${message}</p>
			</c:forEach>
		</div>
	</c:if>

	<form:form
		action="./addComputer${!empty computer ? '?update='.concat(computer.id) : ''}"
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
				<label for="introduced"><spring:message
						code="add_computer.intoduced_date" text="Introduced Date" />:</label>
				<div class="input">
					<form:input type="date" pattern="yyyy-MM-dd" path="introduced" />
					<span class="help-inline">YYYY-MM-DD</span>
					<form:errors path="introduced" />
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message
						code="add_computer.discontinued_date" text="Discontinued date" />:</label>
				<div class="input">
					<form:input type="date" pattern="yyyy-MM-dd" path="discontinued" />
					<span class="help-inline">YYYY-MM-DD</span>
					<form:errors path="discontinued" />
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
					<form:errors path="companyId" />
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<spring:message code="add_computer.update" var="update" />
			<spring:message code="add_computer.add" var="add" />
			<input type="submit" value="${!empty computer ? update : add}"
				class="btn primary"> or <a href="dashboard" class="btn"><spring:message code="add_computer.cancel" text="Cancel" /></a>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />