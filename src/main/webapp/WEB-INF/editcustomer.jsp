<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Edit customer
    </jsp:attribute>

    <jsp:body>
        <h3>Edit customer</h3>
        <form method="POST">
            <label for="email">Email</label>
            <input type="text" id="email" value="${requestScope.edituser.username}"
            placeholder="${requestScope.edituser.username}" name="email" disabled>
            <label for="balance">Balance</label>
                <input id="balance" type="text" name="balance" value="${requestScope.edituser.balance}"
                placeholder="${requestScope.edituser.balance}">

            <button formaction="editcustomer" name="balance"
                    class="align-baseline btn btn-outline-warning">Edit</button>
        </form>
    </jsp:body>

</t:pagetemplate>