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
                                    <img src="" alt="Olsker logo" style="width: 100%; max-width: 300px" />
                                </td>

                                <td>
                                    Order Nummer: ${sessionScope.currentOrderId}<br />
                                    Bestilingsdato: ${sessionScope.orderDate}<br />
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
                    <td></td>
                    <td></td>
                    <td><b>Total Price:</b></td>
                    <td><b>${sessionScope.totalprice} kr.</b></td>
                </tr>
            </table>
        </div>
        </c:if>
    </jsp:body>

</t:pagetemplate>
