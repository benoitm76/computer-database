<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="url" type="java.lang.String" rtexprvalue="true"
	required="true"%>
<%@ attribute name="search" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="order" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="dir" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="curPage" type="java.lang.Integer" rtexprvalue="true"
	required="false"%>
<%@ attribute name="lang" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ attribute name="update" type="java.lang.Integer" rtexprvalue="true"
	required="false"%>
<%@ attribute name="onlyUrl" type="java.lang.Boolean"
	rtexprvalue="false" required="false"%>
<%@ attribute name="attrClass" type="java.lang.String" rtexprvalue="true"
	required="false"%>
<%@ tag trimDirectiveWhitespaces="true"%>


<c:url value="${url}" var="generateUrl">
	<c:if test="${not empty search}">
		<c:param name="search" value="${search}" />
	</c:if>
	<c:if test="${not empty order}">
		<c:param name="page.sort" value="${order}" />
	</c:if>
	<c:if test="${not empty dir}">
		<c:param name="page.sort.dir" value="${dir}" />
	</c:if>
	<c:if test="${not empty curPage && curPage != 1}">
		<c:param name="page.page" value="${curPage}" />
	</c:if>
	<c:if test="${not empty lang}">
		<c:param name="lang" value="${lang}" />
	</c:if>
	<c:if test="${not empty update && update != 0}">
		<c:param name="update" value="${update}" />
	</c:if>
</c:url>

<c:choose>
	<c:when test="${not empty onlyUrl && onlyUrl}">
		${generateUrl}
	</c:when>
	<c:otherwise>
		<a href="${generateUrl}" ${not empty attrClass ? 'class="'.concat(attrClass).concat('"') : ''}><jsp:doBody /></a>
	</c:otherwise>
</c:choose>