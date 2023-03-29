<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">Ordrer</jsp:attribute>

    <jsp:attribute name="footer">Ordrer</jsp:attribute>

    <jsp:body>
        <c:if test="${sessionScope.user != null}">
            <h2>Dine ordrer</h2>
            <table class="table table-striped mt-4">
               <tr class="">
                    <th>Ordre Id</th>
                    <th>Dato</th>
                    <th>Total Pris</th>
                    <th>Betalt</th>
                    <th>Se Fulde Order</th>
                </tr>

                <c:forEach var="order" items="${sessionScope.userOrders}" varStatus="loop">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.orderDate}</td>
                        <td>${sessionScope.userOrdersPrice.get(loop.index)}</td>
                        <td>${order.payed}</td>
                        <td><button class="btn btn-info text-light" onclick="window.location.href='http://localhost:8080/cupcake/seeorder?orderid=${order.orderId}'">
                    Gå til ordren
                    </button></td>
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