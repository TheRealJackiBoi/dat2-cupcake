<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Fedt, lad os få dig oprettet!
    </jsp:attribute>

  <jsp:body>

    <div class="container">
      <form action="signUpPage" name="signUpPage" method="POST"><br>
        <label for="email">Enter New Email:</label><br>
        <input type="text" id="email" name="email"><br>

        <label for="email">Repeat New Email:</label><br>
        <input type="text" id="email1" name="email1"><br>

        <label for="password">Enter New Password:</label><br>
        <input type="password" id="password" name="password"><br>

        <label for="password">Repat New Password:</label><br>
        <input type="password" id="password1" name="password1"><br>

        <button type="submit" class="btn btn-primary">Opret Bruger</button>

      </form>

    </div>

  </jsp:body>

</t:pagetemplate>
