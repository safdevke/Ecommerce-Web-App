package router;

import dto.GenericResponse;
import ejb.RegisterEjbI;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Router", value = {"/login","/register",""})
public class UrlRouter extends HttpServlet {

    @EJB
    private RegisterEjbI registerEjb;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String requestURI = req.getRequestURI().substring(req.getContextPath().length()); // remove /Ecommerce from the beginning
        PrintWriter writer = res.getWriter();

        switch (requestURI) {
            case "":
            case "/login":
                res.sendRedirect("./webstuff/login.html");
                return;
            case "/register":
                GenericResponse response;
                try {
                    response = registerEjb.validateRegistrationToken();
                } catch (Exception e) {
                    writer.println("An Error occurred :\\");
                    return;
                }
                if (response.isSuccess()) {
                    res.sendRedirect("./webstuff/confirm.html");
                    return;
                }
                writer.println("Your Token Expired :\\");
                return;
            default:
                writer.println("ERROR.PAGE NOT FOUND");
                res.sendRedirect("./webstuff/login.html");
        }
    }
}

