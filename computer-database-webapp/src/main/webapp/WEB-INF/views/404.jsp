<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="pagination" prefix="page"%>


<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<spring:message code="error_page.404_title"
			text="Error 404 - Page not found" />
	</h1>
	<p>
		<spring:message code="error_page.404_body"
			text="Request page can't be found !" />
	</p>

</section>

<jsp:include page="include/footer.jsp" />
