<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<spring:message code="error_page.exception_title"
			text="Error - A exception occured" />
	</h1>
	<p>
		<spring:message code="error_page.exception_body"
			text="This exception occured : " />${name} <c:if test="${message!=null}">--> ${message}</c:if>
	</p>

</section>

<jsp:include page="include/footer.jsp" />
