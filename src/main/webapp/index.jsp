<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/indexstyle.css">

<t:pagetemplate>

        <jsp:attribute name="header">
            Olsker Cupcakes
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <div class="containerbody">
            <div class="image">
            <img src="${pageContext.request.contextPath}/images/cupcake8.png"
                 class="img-fluid" alt="cupcake8.png"/>
                <img src="${pageContext.request.contextPath}/images/cupcake7.png"
                     class="img-fluid" alt="cupcake8.png"/>
                <div class="text">
                    <h3>Lækker sulten? Kig ind</h3>
                    <c:if test="${sessionScope.user != null}">
                        <button class="indexbutton" onclick="window.location.href='http://localhost:8080/Cupcake/ordering';">Bestil cupcakes</button>
                    </c:if>
                    <c:if test="${sessionScope.user == null}">
                        <button class="indexbutton" onclick="window.location.href='http://localhost:8080/Cupcake/login.jsp';">Bestil cupcakes</button>
                    </c:if>
                </div>
            </div>
        </div>
    </jsp:body>

</t:pagetemplate>