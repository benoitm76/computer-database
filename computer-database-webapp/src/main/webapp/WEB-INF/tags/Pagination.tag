<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="tools" prefix="t"%>
<%@ attribute name="lastPage" required="true" type="java.lang.Integer"
	rtexprvalue="true"%>
<%@ attribute name="url" type="java.lang.String" rtexprvalue="true"
	required="true"%>
<%@ attribute name="search" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="order" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="dir" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="curPage" type="java.lang.Integer" rtexprvalue="true"
	required="true"%>

<div class="btn-group">
	<c:set var="currentPage" value="${curPage + 1}" />
	<c:if test="${currentPage > 4}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${currentPage - 4}" attrClass="btn btn-primary">&lt;&lt;</t:link>
	</c:if>

	<c:if test="${currentPage > 3}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="1" attrClass="btn btn-primary">1</t:link>
	</c:if>

	<c:if test="${currentPage > 4}">
	<button type="button" class="btn btn-primary" disabled="disabled">...</button>
	</c:if>

	<c:if test="${currentPage > 2}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${currentPage - 2}" attrClass="btn btn-primary">${currentPage - 2}</t:link>
	</c:if>

	<c:if test="${currentPage > 1}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${currentPage - 1}" attrClass="btn btn-primary">${currentPage - 1}</t:link>
	</c:if>

	<button type="button" class="btn btn-primary" disabled="disabled">${currentPage}</button>

	<c:if test="${currentPage + 1 <= lastPage}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${currentPage + 1}" attrClass="btn btn-primary">${currentPage + 1}</t:link>
	</c:if>

	<c:if test="${currentPage + 2 <= lastPage}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${currentPage + 2}" attrClass="btn btn-primary">${currentPage + 2}</t:link>
	</c:if>

	<c:if test="${currentPage + 4 <= lastPage}">
	<button type="button" class="btn btn-primary" disabled="disabled">...</button>
	</c:if>

	<c:if test="${currentPage + 3 <= lastPage}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${lastPage}" attrClass="btn btn-primary">${lastPage}</t:link>
	</c:if>

	<c:if test="${currentPage + 4 <= lastPage}">
		<t:link url="${url}" search="${search}" order="${order}" dir="${dir}"
			curPage="${currentPage + 4}" attrClass="btn btn-primary">&gt;&gt;</t:link>
	</c:if>
</div>


