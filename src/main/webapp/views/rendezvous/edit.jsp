<%--
 * create.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%-- Stored message variables --%>

<spring:message code="rendezvous.create" var="create" />
<spring:message code="rendezvous.name" var="name" />
<spring:message code="rendezvous.description" var="description" />
<spring:message code="rendezvous.moment" var="moment" />
<spring:message code="rendezvous.picture" var="picture" />
<spring:message code="rendezvous.coordinates" var="coordinates" />
<spring:message code="rendezvous.finalMode" var="finalMode" />
<spring:message code="rendezvous.deleted" var="deleted" />
<spring:message code="rendezvous.adultOnly" var="adultOnly" />
<spring:message code="rendezvous.links" var="links" />
<spring:message code="rendezvous.creator" var="creator" />
<spring:message code="rendezvous.attendants" var="attendants" />
<spring:message code="rendezvous.announcements" var="announcements" />
<spring:message code="rendezvous.comments" var="comments" />
<spring:message code="rendezvous.questions" var="questions" />
<spring:message code="rendezvous.save" var="save" />
<spring:message code="rendezvous.cancel" var="cancel" />

<security:authorize access="hasRole('USER')">

<form:form id="form" action="${requestURI}" modelAttribute="rendezvous">

	<%-- Forms --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creator" />
	<form:hidden path="deleted" />
	
	
	
	
	<form:label path="name">
		<jstl:out value="${name}" />:
	</form:label>
			<form:input path="name" />
			<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="description">
		<jstl:out value="${description}" />:
	</form:label>
			<form:password path="description" />
			<form:errors cssClass="error" path="description" />
	<br />
		
	
	<form:label path="moment">
		<jstl:out value="${moment}" />:
	</form:label>
			<form:input path="moment" />
			<form:errors cssClass="error" path="moment" />
	<br />
	
	<form:label path="coordinates">
		<jstl:out value="${coordinates}" />:
	</form:label>
			<form:input path="coordinates" />
			<form:errors cssClass="error" path="coordinates" />
	<br />
	
	<form:label path="finalMode">
		<jstl:out value="${finalMode}" />:
	</form:label>
			<form:select path="finalMode" >
				<form:option
					label="NO"
					value="false" />
				<form:option
					label="YES"
					value="true" />
			</form:select>
	<br/><br/>
	
	<form:label path="adultOnly">
		<jstl:out value="${adultOnly}" />:
	</form:label>
			<form:select path="adultOnly" >
				<form:option
					label="NO"
					value="false" />
				<form:option
					label="YES"
					value="true" />
			</form:select>
	<br/><br/>
	
	<form:label path="attendants">
		<jstl:out value="${attendants}" />:
	</form:label>
			<form:input path="attendants" />
			<form:errors cssClass="error" path="attendants" />
	<br />
	
	<form:select path="links">
				<form:option label="----" value="0" />
				<form:options items="${rendezvouses}" itemLabel="name"/>
			</form:select>
			<form:errors cssClass="error" path="links" />
	<br />
	
	<form:label path="announcements">
		<jstl:out value="${announcements}" />:
	</form:label>
			<form:input path="announcements" />
			<form:errors cssClass="error" path="announcements" />
	<br />
	
	<form:label path="comments">
		<jstl:out value="${comments}" />:
	</form:label>
			<form:input path="comments" />
			<form:errors cssClass="error" path="comments" />
	<br />
	
	<form:label path="questions">
		<jstl:out value="${questions}" />:
	</form:label>
			<form:input path="questions" />
			<form:errors cssClass="error" path="questions" />
	<br />
	
	
	
	<%-- Buttons --%>
	<input type="submit" name="save" value="${save}" />&nbsp; 
	
	<input type="button" name="cancel" value="${cancel}"
		onclick="javascript: relativeRedir('welcome/index.do');" />
		
</form:form>
</security:authorize>
