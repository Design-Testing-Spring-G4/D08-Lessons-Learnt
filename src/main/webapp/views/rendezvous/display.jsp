<%--
 * display.jsp
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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="rendezvous.display" var="msgDisplay" />
<spring:message code="rendezvous.name"  var="msgName" />
<spring:message code="rendezvous.description"  var="msgDescription"/>
<spring:message code="rendezvous.moment"  var="msgMoment"/>
<spring:message code="rendezvous.picture"  var="msgPicture"/>
<spring:message code="rendezvous.coordinates" var="msgCoordinates" />
<spring:message code="rendezvous.finalMode" var="msgFinalMode" />
<spring:message code="rendezvous.deleted" var="msgDeleted" />
<spring:message code="rendezvous.adultOnly" var="msgAdultOnly" />
<spring:message code="rendezvous.creator" var="msgcreator" />
<spring:message code="rendezvous.links" var="msgLinks" />
<spring:message code="rendezvous.announcements" var="msgannouncements" />
<spring:message code="rendezvous.comments" var="msgComments" />
<spring:message code="rendezvous.questions" var="msgQuestions" />
<spring:message code="rendezvous.return" var="msgReturn" />

	<%-- For the selected rendezvous in the list received as model, display the following information: --%>
	
	
	<jstl:out value="${msgName}" />:
	<jstl:out value="${rendezvous.name}" />
	<br />
	
		
	<jstl:out value="${msgDescription}" />:
	<jstl:out value="${rendezvous.description}" />
	<br />
	
	<jstl:out value="${msgMoment}" />:
	<jstl:out value="${rendezvous.moment}" />
	<br />
	
	<jstl:out value="${msgPicture}" />:
	<jstl:out value="${rendezvous.picture}" />
	<br />
	
	<jstl:out value="${msgCoordinates}" />:
	<fmt:formatDate value="${rendezvous.coordinates}" />
	<br />
	
	<jstl:out value="${msgFinalMode}" />:
	<fmt:formatDate value="${rendezvous.finalMode}" />
	<br />
	
	<jstl:out value="${msgDeleted}" />:
	<fmt:formatDate value="${rendezvous.deleted}" />
	<br />
	
	<jstl:out value="${msgAttendants}" />:
	<jstl:forEach var="attendants" items="${rendezvous.attendants}">
		<jstl:out value="${attendants.name}" />:<jstl:out value="${attendants.surname}" />,
	</jstl:forEach>
	<br />
	
	<jstl:out value="${msgLinks}" />:
	<jstl:out value="${rendezvous.links}" />
	<br />	
	
	<jstl:out value="${msgAnnouncements}" />:
	<jstl:out value="${rendezvous.announcements}" />
	<br />
	
	<jstl:out value="${msgComments}" />:
	<jstl:out value="${rendezvous.comments}" />
	<br />
	
	<jstl:out value="${msgAnnouncements}" />:
	<jstl:out value="${rendezvous.questions}" />
	<br />

<a href="rendezvous/list.do"><jstl:out value="${msgReturn}" /></a>