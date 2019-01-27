package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/singnInUsr/app/plan/add")
public class AddNewPlanForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Date date = new Date(new java.util.Date().getTime());
        Admin admin = (Admin) session.getAttribute("user");

        Plan newPlan = new Plan();
        newPlan.setName(request.getParameter("name"));
        newPlan.setDescription(request.getParameter("description"));
        newPlan.setCreated(date);
        newPlan.setAdmin(admin);

        PlanDao planDao = new PlanDao();
        planDao.create(newPlan);

        response.sendRedirect(request.getContextPath() + "/singnInUsr/planlist");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        getServletContext().getRequestDispatcher("/app/plan/app-addPlanForm.jsp").forward(request, response);
    }
}

