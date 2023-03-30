<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<link rel="stylesheet" href="css/style.css">

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:body>
        <c:if test="${sessionScope.user != null}">
        <h2>Tak for din bestilling hos Olsker Cupcakes!</h2>
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
                                    Order Nummer: ${sessionScope.orderConfirmOrder.orderId}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Bestilingsdato: ${sessionScope.orderConfirmOrder.orderDate}
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
                <c:forEach var="cupcake" items="${sessionScope.list}">
                <tr>
                   <td> ${cupcake.amount}stk.</td> <td> ${cupcake.bottomCake.name}</td> <td>${cupcake.topCake.name}</td> <td>${cupcake.price} kr.</td>
                </tr>


                </c:forEach>

                <tr class="total">
                    <td>
                        Status: <b><c:if test="${sessionScope.orderConfirmOrder.payed}">Betalt</c:if> <c:if test="${!sessionScope.orderConfirmOrder.payed}">Ikke Betalt</c:if></b>
                    </td>
                    <td></td>
                    <td><b>Total Price:</b></td>
                    <td><b> ${sessionScope.orderTotalPrice} kr.</b></td>
                </tr>
            </table>
        </div>
        </c:if>
    </jsp:body>

</t:pagetemplate>
