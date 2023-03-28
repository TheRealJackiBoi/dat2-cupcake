<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">Ordrer</jsp:attribute>

    <jsp:attribute name="footer">Ordrer</jsp:attribute>

    <jsp:body>

        <c:if test="sessionScope.user != null">
            <h3>Dine ordrer</h3>

            <table>
                <tr>
                    <th>Ordre Id</th>
                    <th>Total Pris</th>
                    <th>Se Fulde Order</th>
                </tr>

                <c:forEach var="order" items="requestScope.userOrders" varStatus="loop">
                    <tr>
                        <td>${order.getOrderId()}</td>
                       <!-- <td>${requestScope.userOrdersPrice.get(loop.index)}</td> -->
                        <td><button></button></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="../login.jsp">Login</a></p>
        </c:if>
    </jsp:body>
</t:pagetemplate>