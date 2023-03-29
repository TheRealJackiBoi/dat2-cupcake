<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Bestilling
    </jsp:attribute>

    <jsp:attribute name="footer">
        Olsker cupcake!
    </jsp:attribute>

    <jsp:body>

        <h1>Vi er klar til din bestilling</h1>
        <form name="currentorder" action="ordering" method="POST">
            <div class="container">
                <div class="row">
                    <!--this is showing the bottomlist-->
                    <div class="col">
                        <p>Vælg cupcake-bund!</p>
                        <c:forEach var="cupcakebottom" items="${requestScope.bottomCakeList}">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="buttomcake" name="bottomcake"
                                       value="${cupcakebottom.bottomId}">
                                <label class="form-check-label" for="buttomcake">${cupcakebottom.name}
                                    til ${cupcakebottom.price}Kr</label>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- this is showing the toplist-->
                    <div class="col">
                        <p>Vælg din topping!</p>
                        <c:forEach var="cupcaketop" items="${requestScope.topCakeList}">
                            <div class="form-check">
                                <input type="radio" id="cupcaketop" name="topcake" value="${cupcaketop.topId}">
                                <label class="form-check-label" for="cupcaketop">${cupcaketop.name}
                                    til ${cupcaketop.price}Kr</label>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="col">
                        <select class="form-select" aria-labelledby="numberofcakes" name="numberofcakes"
                                id="numberofcakes">
                            <option selected>Vælg antal cupcakes!</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>

                        <!-- når man trykker på botten skal det plottes ind i ordre i mysql-->
                        <button type="submit" style="margin-top: 100px" class="btn btn-success"
                                value="cake">Tilføj til kurv
                        </button>
                    </div>
                </div>
            </div>
        </form>


        <!-- this is meant to be the small area of the screen showing your curent order-->
        <div class="col-5" style="background-color: mediumpurple">
            <h2>Din ordre</h2>
            <!-- getting data from order table in mysql-->
            <c:forEach var="cupcakeordre" items="${sessionScope.currentOrderList}">
                <div>
                        ${cupcakeordre.amount}x ${cupcakeordre.bottomCake.name} med ${cupcakeordre.topCake.name}
                    til ${cupcakeordre.price}Kr
                    <form method="POST">
                        <button type="submit" formaction="removefromorder" class="btn btn-danger" name="removecupcake"
                                value="${cupcakeordre.cupCakeId}">Fjern cupcake
                        </button>
                    </form>
                </div>
            </c:forEach>
            <form name="confirmorder" action="confirmorder" method="POST">
                <fieldset style="margin-top: 50px">
                    <legend style="font-weight: bold; font-size: 20px">Vælg betalingsmetode:</legend>

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
            </form>
        </div>

    </jsp:body>

</t:pagetemplate>