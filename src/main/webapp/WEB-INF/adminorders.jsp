<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>

        <jsp:attribute name="header">
            Admin orders
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <h3>Testing</h3>
            <table class="table table-striped mt-4">
                <tr>
                    <th>Email</th>
                    <th>Balance</th>
                    <th>Admin</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="user" items="${requestScope.userList}">
                        <tr>
                            <td>
                                    ${user.username}
                            </td>
                            <td>
                                    ${user.balance}DKK
                            </td>
                            <td>
                                <c:choose>
                                 <c:when test="${user.admin == 1}">
                                     Yes
                                 </c:when>
                                    <c:otherwise>
                                        No
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-outline-success btn-sm">Orders</button>
                                <button type="submit" class="btn btn-outline-warning btn-sm">Edit</button>
                            </td>
                        </tr>
                 </c:forEach>
            </table>
    </jsp:body>

</t:pagetemplate>