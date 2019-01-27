<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form class="padding-small text-center" method="post" action="register">
                    <h1 class="text-color-darker">Rejestracja</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" id="first_name" name="first_name" placeholder="podaj imię">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="last_name" name="last_name"
                               placeholder="podaj nazwisko">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" name="email" placeholder="podaj email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password1" placeholder="podaj hasło">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password2" name="password2"
                               placeholder="powtórz hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                    <c:set var="register" value="${register}"/>
                    <c:choose>
                        <c:when test="${register == 1}">
                           <br> Uzupełnij wszystkie pola
                        </c:when>
                        <c:when test="${register == 2}">
                           <br> Hasła muszą się zgadzać
                        </c:when>
                        <c:when test="${register == 3}">
                           <br> Mail niepoprawny
                        </c:when>
                        <c:when test="${register == 5}">
                            <br> Istnieje użytkownik z takim mailem
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>
