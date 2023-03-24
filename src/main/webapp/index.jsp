<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

        <jsp:attribute name="header">
            Olsker Cupcakes
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>

    <!-- TODO: Set image left-side and wrap text right-side + add proper index info -->
    <jsp:body>
        <div style="text-align: left;">
        <img src="${pageContext.request.contextPath}/images/cupcake4.png"
             width="500px" height="500px" class="img-fluid" alt="cupcake"/>
        </div>
        <div>
         <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.role}".</p>
         </c:if>

         <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="login.jsp">Login</a></p>
         </c:if>
        </div>

    </jsp:body>

</t:pagetemplate>