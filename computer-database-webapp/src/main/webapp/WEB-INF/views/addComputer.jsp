<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1>
					<spring:message
						code="${cDTO.id==0 ? 'add_computer.add_title' : 'add_computer.update_title'}"
						text="Add/Update title" />
				</h1>
				<c:if test="${!empty message && fn:length(message) != 0}">
					<div class="alert ${ error ? 'alert-error' : 'alert-success'}">
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
						<div class="form-group">
							<label for="name"><spring:message
									code="add_computer.computer_name" text="Computer Name" /> :</label>
							<div class="input">
								<form:input path="name" class="form-control" />
								<span class="help-block"><spring:message
										code="add_computer.required" text="Required" /></span>
								<form:errors path="name" class="error" />
							</div>
						</div>
						<div class="form-group">
							<spring:message code="date.pattern" text="yyyy-MM-dd"
								var="pattern" />
							<spring:message code="date.pattern_string" text="YYYY-MM-DD"
								var="pattern_string" />
							<label for="introduced"><spring:message
									code="add_computer.intoduced_date" text="Introduced Date" />:</label>
							<div class="input">
								<form:input type="text" path="introduced" class="form-control" />
								<span class="help-block">${pattern_string}</span>
								<form:errors path="introduced" class="error" />
							</div>
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message
									code="add_computer.discontinued_date" text="Discontinued date" />:</label>
							<div class="input">
								<form:input type="text" path="discontinued" class="form-control" />
								<span class="help-block">${pattern_string}</span>
								<form:errors path="discontinued" class="error" />
							</div>
						</div>
						<div class="form-group">
							<label for="company"><spring:message
									code="add_computer.company_name" text="Company Name" />:</label>
							<div class="input">
								<form:select path="companyId" class="form-control">
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
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-ok"></span> ${button_text}
						</button> or
						<a href="dashboard" class="btn btn-default"><spring:message
								code="add_computer.cancel" text="Cancel" /></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>

<jsp:include page="include/footer.jsp" />