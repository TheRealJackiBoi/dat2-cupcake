<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<link rel="stylesheet" href="css/style.css">

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:body>
        <h2>Tak for din bestilling hos Olsker Cupcakes!</h2>
        <div class="invoice-box">
            <table>
                <tr class="top">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td class="title">
                                    <img src="" alt="Company logo" style="width: 100%; max-width: 300px" />
                                </td>

                                <td>
                                    Order Nummer: ${requestScope.orderId}<br />
                                    Bestilingsdato: ${requestScope.bestilingsDato}<br />
                                    Hente dato:${requestScope.orderId}
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
                                    Kunde: ${requestScope.user}<br />
                                    Adresse: Olsker, Bornholm<br />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="heading">
                    <td>Items: </td>

                    <td>Price</td>
                </tr>

                <tr class="cupcake1">
                    <td>2stk vanilje + pistace</td>

                    <td>25kr.</td>
                </tr>
                <tr class="cupcake2">
                    <td>3stk karamel + jordbær</td>

                    <td>35kr.</td>
                </tr>
                <tr class="cupcake3">
                    <td>3stk chokolade + frosting</td>

                    <td>45kr.</td>
                </tr>



                <tr class="total">
                    <td></td>

                    <td>Total: </td>
                </tr>
            </table>
        </div>
    </jsp:body>

</t:pagetemplate>
