<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/templatestyle.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <div class ="header" style="text-align: center;">
                    <img src="${pageContext.request.contextPath}/images/cupcake3.png" width="700px" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbarstyle d-flex">


                        <!-- TODO: Split up the 4 buttons with 2 on each side -->
                        <!-- if not logged in-->
                        <c:if test="${sessionScope.user == null }">
                            <a class="nav-item nav-link text-light navfontright" href="${pageContext.request.contextPath}/login.jsp" id="">Login</a>
                        </c:if>

                        <!-- if logged in-->
                        <c:if test="${sessionScope.user != null }">
                            <a class="nav-item nav-link text-light navfontleft" href="${pageContext.request.contextPath}/orders" id="">Se ordrer</a>
                            <a class="nav-item nav-link text-light navfontleft" href="${pageContext.request.contextPath}/ordering" id="">Bestil cupcakes</a>
                            <c:if test="${sessionScope.user.admin == 1}">
                                <a class="nav-item nav-link text-light navfontleft" href="${pageContext.request.contextPath}/admincustomers">Kunder</a>
                            </c:if>
                            <a class=" nav-item nav-link text-light text-light float-right navfontright" href="${pageContext.request.contextPath}/index.jsp" id="navfontright-user">${sessionScope.user.username} ${sessionScope.user.balance}DKK</a>
                            <a class="nav-item nav-link float-right text-light navfontright" href="${pageContext.request.contextPath}/logout" id="navfontright-logout">Log out</a>
                        </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3">
    <hr/>
    <div class="row mt-4">
        <div class="col">
            Røvej 6<br/>
            3770 Olsker
        </div>
        <div class="col">
        </div>
        <div class="col">
            De bedste cupcakes på Bornholm!<br/>
            Det siger vores kunder i hvert fald
        </div>
    </div>
</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>