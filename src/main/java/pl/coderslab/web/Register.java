package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String emailPattern = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}";
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            request.setAttribute("register", 1);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        } else if (!password1.equals(password2)) {
            request.setAttribute("register", 2);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        } else if (!email.matches(emailPattern)) {
            request.setAttribute("register", 3);
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            AdminDao adminDao = new AdminDao();
            //sprawdzenie czy jest użytkownik z takim mailem
            if (adminDao.readByEmail(email) == null) {
                adminDao.create(new Admin(firstName, lastName, email, password1, 0));
                request.setAttribute("register", 4);
                response.sendRedirect("/login.jsp");
            } else {
                request.setAttribute("register", 5);
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            }


        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
