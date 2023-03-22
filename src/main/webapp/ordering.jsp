<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Vi er klar til at tage din bestilling
    </jsp:attribute>

    <jsp:attribute name="footer">
        cyka blyat det bliver en fin kage!
    </jsp:attribute>

    <jsp:body>

        <!--this is showing the bottomlist-->
        <div>
            <p>Vælg cupcake bund!</p>
            <li>
                <ul>
                    <c:forEach var="cupcakebottom" items="${requestScope.bottomList}">
                        <input type="radio" id="${cupcakebottom.bottomId}" name="bottomCake"
                               value="${cupcakebottom.name}">
                    </c:forEach>
                </ul>
            </li>
        </div>

        <!-- this is showing the toplist-->
        <div>
            <p>Vælg din topping!</p>
            <li>
                <ul>
                    <c:forEach var="cupcaketop" items="${requestScope.topList}">
                        <input type="radio" id="${cupcaketop.topId}" name="topCake" value="${cupcaketop.name}">
                    </c:forEach>
                </ul>
            </li>
        </div>

        <!-- Default dropright button -->
        <div class="btn-group dropright">
            <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">
                Vælg antal cupcakes!
            </button>
            <div class="dropdown-menu">
                <!-- skulle lave en drop down med op til 10-->
                <form>
                    <input type="number" name="numberofcakes" min="1" max="10">
                </form>
            </div>
        </div>


        <!-- når man trykker på botten skal det plottes ind i ordre i mysql-->
        <button type="submit" class="btn btn-success" data-value1="bottomCake" data-value2="topCake"
                data-value3="numberofcakes" value="cake">Tilføj til kurv
        </button>

        <!-- this is meant to be the small area of the screen showing your curent order-->
        <div class="container-sm">
            <h2>Din ordre</h2>
            <!-- ordre table i mysql skal bruges her-->
            <c:forEach var="cupcakesordre" items="${requestScope.ordrelist}">
                Cupcake
                ${cupcakesordre.bottomname} with ${cupcakesordre.topname}
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <!-- ville gerne have det til at vise det valgte antal-->
                            ${numberofcakes}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <input type="number" name="numberofcakes" min="1" max="10">
                    </div>
                </div>
                <button class="btn btn-danger" value="remove">Fjern cupcake</button>
            </c:forEach>

            <fieldset>
                <legend>Vælg betalings methode:</legend>

                <div>
                    <input type="radio" id="pickup" name="paymentmethod" value="pickup"
                           checked>
                    <label for="pickup">Betal ved afhentning</label>
                </div>

                <div>
                    <input type="radio" id="kredit" name="paymentmethod" value="kredit">
                    <label for="kredit">Betal med kredit</label>
                </div>
            </fieldset>

        </div>


    </jsp:body>

</t:pagetemplate>