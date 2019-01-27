
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../app-header.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="../app-left-panel.jsp" %>

        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <form action="/singnInUsr/app/plan/add" method="post">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">NOWY PLAN</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                            </button>
                        </div>
                    </div>

                    <div class="schedules-content">

                        <div class="form-group row">
                            <label for="planName" class="col-sm-2 label-size col-form-label">
                                Nazwa planu
                            </label>
                            <div class="col-sm-10">
                                <input type="text" name="name" class="form-control" id="planName"
                                       placeholder="Nazwa planu">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="planDescription" class="col-sm-2 label-size col-form-label">
                                Opis planu
                            </label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="description" rows="5" id="planDescription"
                                          placeholder="Opis plany"></textarea>
                            </div>
                        </div>


                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<%@ include file="../../footer.jsp" %>

</body>
</html>
