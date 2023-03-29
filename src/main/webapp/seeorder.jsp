<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">Se Order</jsp:attribute>

    <jsp:attribute name="footer">Se Order</jsp:attribute>

    <jsp:body>
        <c:if test="${sessionScope.user != null}">
        <div class="Recceipt">
            <table>
                <tr class="top">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td class="title">
                                    Olsker's Cupcakes
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Order Nummer: ${requestScope.order.orderId}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Bestilingsdato: ${requestScope.order.orderDate}
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="information">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td>
                                    Kunde: ${sessionScope.user.username}<br />
                                    Adresse: Olsker, Bornholm<br />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>



                <tr class="heading">
                    <td>Items: </td>
                    <td>Buttom: </td>
                    <td>Top: </td>

                    <td>Price</td>
                </tr>
                <c:forEach var="cupcake" items="${requestScope.orderCupcakes}">
                    <tr>
                        <td> ${cupcake.amount}stk.</td> <td> ${cupcake.bottomCake.name}</td> <td>${cupcake.topCake.name}</td> <td>${cupcake.price} kr.</td>
                    </tr>


                </c:forEach>

                <tr class="total">
                    <td>
                        Status: <b><c:if test="${requestScope.order.payed}">Betalt</c:if> <c:if test="${!requestScope.order.payed}">Ikke Betalt</c:if></b>
                    </td>
                    <td></td>
                    <td><b>Total Price:</b></td>
                    <td><b>${requestScope.orderTotalPrice} kr.</b></td>
                </tr>
            </table>
        </div>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="../login.jsp">Login</a></p>
        </c:if>
    </jsp:body>
</t:pagetemplate>