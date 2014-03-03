<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="lastPage" required="true" type="java.lang.Integer"
	rtexprvalue="true"%>
<%@ attribute name="currentPage" required="true"
	type="java.lang.Integer" rtexprvalue="true"%>
<%@ attribute name="queryParameters" required="true"
	type="java.util.Map" rtexprvalue="true"%>



<c:url value="./dashboard" var="variableURL">
	<c:forEach items="${queryParameters}" var="entry">
		<c:param name="${entry.key}" value="${entry.value}" />
	</c:forEach>
</c:url>


<c:if test="${currentPage > 4}">
	<a href="<c:url value="${variableURL}">
		c:param name="
		page" value="${currentPage - 4}" /></c:url>"><<</a>
</c:if>

<c:if test="${currentPage > 3}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="1" />
	</c:url>">1</a>
</c:if>

<c:if test="${currentPage > 4}">
	...
</c:if>

<c:if test="${currentPage > 2}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="${currentPage - 2}" />
	</c:url>">${currentPage - 2}</a>
</c:if>

<c:if test="${currentPage > 1}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="${currentPage - 1}" />
	</c:url>">${currentPage - 1}</a>
</c:if>

${currentPage}

<c:if test="${currentPage + 1 <= lastPage}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="${currentPage + 1}" />
	</c:url>">${currentPage + 1}</a>
</c:if>

<c:if test="${currentPage + 2 <= lastPage}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="${currentPage + 2}" />
	</c:url>">${currentPage + 2}</a>
</c:if>

<c:if test="${currentPage + 4 <= lastPage}">
	...
</c:if>

<c:if test="${currentPage + 3 <= lastPage}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="${lastPage}" />
	</c:url>">${lastPage}</a>
</c:if>

<c:if test="${currentPage + 3 <= lastPage}">
	<a
		href="<c:url value="${variableURL}">
		<c:param name="page" value="${currentPage + 4}" />
	</c:url>">>></a>
</c:if>


