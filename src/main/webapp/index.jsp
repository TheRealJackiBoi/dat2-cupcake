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
                 width="300px" height="100px" class="img-fluid" alt="cupcake8.png"/>
                <div class="text">
                    <h3>Lækker sulten? Kig ind</h3>
                    <button onclick="window.location.href='http://localhost:8080/Cupcake/ordering';">Bestil cupcakes</button>
                </div>
            </div>
        </div>
    </jsp:body>

</t:pagetemplate>