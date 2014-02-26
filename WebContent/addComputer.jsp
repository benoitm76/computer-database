<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<c:set var="computer" value="${requestScope['computer']}" />
	<h1>${!empty computer ? 'Update' : 'Add'} Computer</h1>
	<c:if test="${requestScope['error']}">
		Une erreur c'est produite !
	</c:if>
	
	<form
		action="./addComputer${!empty computer ? '?update='.concat(computer.id) : ''}"
		method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" value="${computer.name}" /> <span
						class="help-inline">Required</span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" pattern="yyyy-MM-dd"
						value="<fmt:formatDate pattern="yyyy-MM-dd"
							value="${computer.introduced}" />" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" pattern="yyyy-MM-dd"
						value="<fmt:formatDate pattern="yyyy-MM-dd"
							value="${computer.discontinued}" />" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>
						<c:forEach var="company" items="${requestScope['list_companies']}">
							<option value="${company.id}"
								${computer.company.id == company.id ? 'selected' : ''}>${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="${!empty computer ? 'Update' : 'Add'}" class="btn primary"> or <a
				href="dashboard" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />