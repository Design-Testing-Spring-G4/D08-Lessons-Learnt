<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="rendezvous.list" var="list" />
<spring:message code="rendezvous.name" var="name" />
<spring:message code="rendezvous.surname" var="surname" />
<spring:message code="rendezvous.moment" var="moment" />
<spring:message code="rendezvous.postalAddress" var="postalAddress" />
<spring:message code="rendezvous.phoneNumber" var="phoneNumber" />
<spring:message code="rendezvous.emailAddress" var="emailAddress" />
<spring:message code="rendezvous.announcements" var="announcements" />
<spring:message code="rendezvous.similar" var="similarRendezvouses" />
<spring:message code="rendezvous.createAnnouncement"
	var="createAnnouncement" />
<spring:message code="rendezvous.questions" var="questions" />
<spring:message code="rendezvous.users" var="users" />
<spring:message code="rendezvous.return" var="return" />
<spring:message code="rendezvous.dateint" var="formatDate" />


<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="rendezvouses" requestURI="${requestURI}" id="row">

	<%-- Attributes --%>

	<display:column property="name" title="${name}" sortable="true" />

	<display:column property="description" title="${surname}"
		sortable="true" />

	<display:column title="${moment}" sortable="true">
		<fmt:formatDate value="${row.moment}" pattern="${formatDate}" />
	</display:column>

	<spring:url var="displayUrl" value="rendezvous/display.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${displayUrl}"><jstl:out value="${details}" /></a>
	</display:column>

	<spring:url var="userUrl" value="user/list.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${userUrl}"><jstl:out value="${users}" /></a>
	</display:column>

	<spring:url var="announcementsUrl" value="announcement/list.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${announcementsUrl}"><jstl:out value="${announcements}" /></a>
	</display:column>

	<spring:url var="usersUrl" value="user/list.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${usersUrl}"><jstl:out value="${users}" /></a>
	</display:column>

	<security:authorize access="hasRole('USER')">

		<spring:url var="createAnnouncementUrl"
			value="announcement/user/create.do">
			<spring:param name="varId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${createAnnouncementUrl}"><jstl:out
					value="${createAnnouncement}" /></a>
		</display:column>

		<spring:url var="questionsUrl" value="question/list.do">
			<spring:param name="varId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${questionsUrl}"><jstl:out value="${questions}" /></a>
		</display:column>


		<spring:url var="similarRendezvousesUrl" value="rendezvous/list.do">
			<spring:param name="varId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${similarRendezvousesUrl}"><jstl:out
					value="${similarRendezvouses}" /></a>
		</display:column>

	</security:authorize>

</display:table>
<input type="button" name="return" value="${return}"
	onclick="javascript: relativeRedir('welcome/index.do');" />

