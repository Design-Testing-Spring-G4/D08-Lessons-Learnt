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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="user.list" var="list" />
<spring:message code="user.name" var="name" />
<spring:message code="user.surname" var="surname" />
<spring:message code="user.postalAddress" var="postalAddress" />
<spring:message code="user.phoneNumber" var="phoneNumber" />
<spring:message code="user.emailAddress" var="emailAddress" />
<spring:message code="user.answers" var="answers" />
<spring:message code="user.rendezvouses" var="rendezvouses" />
<spring:message code="user.display" var="msgDisplay" />
<spring:message code="user.return" var="return" />


<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="users" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>
	
	<display:column property="name" title="${name}" sortable="true" />
	
	<display:column property="surname" title="${surname}" sortable="true" />

	<display:column property="postalAddress" title="${postalAddress}" sortable="true" />
	
	<display:column property="phoneNumber" title="${phoneNumber}" sortable="true" />
	
	<display:column property="emailAddress" title="${emailAddress}" sortable="true" />
	
	<spring:url var="rendezvousUrl"
		value="rendezvous/list.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
	<a href="${rendezvousUrl}"><jstl:out value="${rendezvouses}" /></a>
	</display:column>
	
	<spring:url var="answersUrl"
		value="answer/user/list.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
	<a href="${answersUrl}"><jstl:out value="${answers}" /></a>
	</display:column>
	
	<spring:url var="displayUrl"
		value="user/display.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
	<a href="${displayUrl}"><jstl:out value="${msgDisplay}" /></a>
	</display:column>
	
	
	
</display:table>
<input type="button" name="return" value="${return}"
		onclick="javascript: relativeRedir('welcome/index.do');" />
		
