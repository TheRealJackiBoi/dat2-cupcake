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
        <form class="form"method="POST">

            <label for="email">Email</label>
            <input  id="email" type="text" name="email" value="${requestScope.edituser.username}" placeholder="${requestScope.edituser.username}" readonly>

            <label for="balance">Balance</label>
            <input id="balance" type="text" name="balance" value="${requestScope.edituser.balance}" placeholder="${requestScope.edituser.balance}">

            <div class="form-check form-switch">
                <label class="form-check-label" for="admin">Admin</label>
                <c:if test="${requestScope.edituser.admin == 1}">
                <input class="form-check-input" type="checkbox" id="admin" name="admin" checked>
                </c:if>
                <c:if test="${requestScope.edituser.admin == 0}">
                <input class="form-check-input" type="checkbox" id="admin" name="admin">
                </c:if>
            </div>
            <button formaction="editcustomer" name="balance"
                    class="align-baseline btn btn-outline-warning">Edit</button>
        </form>
    </jsp:body>

</t:pagetemplate>