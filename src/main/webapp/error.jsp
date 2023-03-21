<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="true" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Error page
    </jsp:attribute>

    <jsp:attribute name="footer">
            Error page
    </jsp:attribute>

    <jsp:body>

        <p>An error has occured. This is the best message we can come up
            with right now: </p>

        <c:if test="${pageContext.errorData.statusCode == 404 }">
            <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
        </c:if>

        <c:if test="${pageContext.errorData.statusCode == 500 }">
            <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
            <p>A serious error happened at the server.</p>
        </c:if>


        <c:if test="${requestScope.errormessage != null}">
            <p>${requestScope.errormessage}</p>
        </c:if>

        <c:if test="${requestScope.errormessage  == null}">
            <p>Abandon ship. We have no idea how you ended up here!</p>
        </c:if>

        <p>Jump back to the <a href="index.jsp">Frontpage</a>,
            or try <a href="login.jsp">logging</a> in again.</p>

    </jsp:body>
</t:pagetemplate>