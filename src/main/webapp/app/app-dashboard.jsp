<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%@ include file="app-header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="app-left-panel.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="/singnInUsr/addrecipe">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/singnInUsr/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${recipesCount}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${plansCount}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${lastAddedPlan.name}
                </h2>
                <c:forEach var="plan" items="${recipePlans}" varStatus="loop">
                    <table class="table">
                        <c:choose>
                            <c:when test="${loop.first}">
                                <thead>
                                <tr class="d-flex">
                                    <th class="col-2">${fn:toUpperCase(fn:substring(plan.dayName.name, 0, 1))}${fn:toLowerCase(fn:substring(plan.dayName.name, 1,fn:length(plan.dayName.name)))}</th>
                                    <th class="col-8"></th>
                                    <th class="col-2"></th>
                                </tr>
                                </thead>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${recipePlans[loop.index-1].dayName.id != plan.dayName.id}">
                                    <thead>
                                    <tr class="d-flex">
                                        <th class="col-2">${fn:toUpperCase(fn:substring(plan.dayName.name, 0, 1))}${fn:toLowerCase(fn:substring(plan.dayName.name, 1,fn:length(plan.dayName.name)))}</th>
                                        <th class="col-8"></th>
                                        <th class="col-2"></th>
                                    </tr>
                                    </thead>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <tbody>
                        <tr class="d-flex">
                            <td class="col-2">${plan.mealName}</td>
                            <td class="col-8">${plan.recipe.name}</td>
                            <td class="col-2">
                                <a href="/recipedetails?id=${plan.recipe.id}"
                                   class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<%@ include file="../footer.jsp" %>

</body>
</html>
