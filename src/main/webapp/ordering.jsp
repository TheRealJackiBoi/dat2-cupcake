<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Vi er klar til at tage din bestilling
    </jsp:attribute>

    <jsp:attribute name="footer">
        Olsker cupcake!
    </jsp:attribute>

    <jsp:body>
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
                                til ${cupcakebottom.price}</label>
                        </div>
                    </c:forEach>
                </div>

                <!-- this is showing the toplist-->
                <div class="col">
                    <p>Vælg din topping!</p>
                    <c:forEach var="cupcaketop" items="${requestScope.topCakeList}">
                        <div class="form-check">
                            <input type="radio" id="cupcaketop" name="topCake" value="${cupcaketop.topId}">
                            <label class="form-check-label" for="cupcaketop">${cupcaketop.name}
                                til ${cupcaketop.price}</label>
                        </div>
                    </c:forEach>
                </div>

                <div class="col">
                    <select class="form-select" aria-labelledby="numberofcakes" name="numberofcakes" id="numberofcakes">
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
                    <button type="submit" style="margin-top: 100px" class="btn btn-success" data-value1="bottomCake"
                            data-value2="topCake"
                            data-value3="numberofcakes" value="cake">Tilføj til kurv
                    </button>
                </div>

                <!-- this is meant to be the small area of the screen showing your curent order-->
                <div class="col-5" style="background-color: mediumpurple">
                    <h2>Din ordre</h2>
                    <!-- ordre table i mysql skal bruges her-->
                    <c:forEach var="cupcakesordre" items="${requestScope.ordrelist}">
                        Cupcake
                        <!--${cupcakesordre.bottomname} with ${cupcakesordre.topname}-->
                        <div>
                            <select class="form-select" aria-labelledby="changenumberofcakes"
                                    name="changenumberofcakes" id="changenumberofcakes">
                                <option selected> <!--BURDE VISE DET SAMME SOM BLEV VALGT FØRSTE GANG --></option>
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
                        </div>
                        <button class="btn btn-danger" value="remove">Fjern cupcake</button>
                    </c:forEach>

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

                </div>
            </div>
        </div>

    </jsp:body>

</t:pagetemplate>