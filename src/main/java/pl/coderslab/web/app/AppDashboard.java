package pl.coderslab.web.app;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/singnInUsr/appDashboard")
public class AppDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("user");
        //Recipes counter
        RecipeDao recipeDao = new RecipeDao();
        int recipesCount = recipeDao.countRecipesByAdmin(admin);
        request.setAttribute("recipesCount", recipesCount);
        //Plans counter
        PlanDao planDao = new PlanDao();
        int plansCount = planDao.countPlansByAdmin(admin);
        request.setAttribute("plansCount", plansCount);
        //Last added plan
        Plan lastAddedPlan = planDao.readLastAdded(admin);
        request.setAttribute("lastAddedPlan",lastAddedPlan);
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<RecipePlan> recipePlans = recipePlanDao.read(lastAddedPlan);
        request.setAttribute("recipePlans", recipePlans);
        getServletContext().getRequestDispatcher("/app/app-dashboard.jsp").forward(request, response);
    }
}
