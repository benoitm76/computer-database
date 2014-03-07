<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		method="POST"
		commandName="cDTO">
		<form:hidden path="id" />
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<form:input path="name" />
					<span class="help-inline">Required</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<form:input type="date" pattern="yyyy-MM-dd" path="introduced" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<form:input type="date" pattern="yyyy-MM-dd" path="discontinued" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<form:select path="companyId">
						<form:option value="0">--</form:option>
						<form:options items="${list_companies}"
							itemValue="id" itemLabel="name" />
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="${!empty computer ? 'Update' : 'Add'}"
				class="btn primary"> or <a href="dashboard" class="btn">Cancel</a>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />