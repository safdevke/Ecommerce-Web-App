package router;

import dto.GenericResponse;
import ejb.TokenEjbI;
import utility.Consts;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Router", value = {"/login","/register"})
public class UrlRouter extends HttpServlet {

    @EJB
    private TokenEjbI tokenEjb;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String requestURI = req.getRequestURI().substring(req.getContextPath().length()); // remove /Ecommerce from the beginning
        PrintWriter writer = res.getWriter();
        req.setAttribute("token", Consts.token);

        switch (requestURI) {
            case "/login":
                res.sendRedirect("./webstuff/login.html");
                return;
            case "/register":
                GenericResponse response = tokenEjb.validateRegistrationToken();
                if (response.isSuccess()) {
                    res.sendRedirect("./webstuff/confirm.html");
                    return;
                }
        }

//        switch (requestURI) {
//            case "/app/login":
//                res.sendRedirect("./webstuff/login.html");
//                break;
//            case "/app/register/confirm":
//                GenericResponse response = new GenericResponse();
//                if (response.isSuccess()) {
//                    res.sendRedirect("./webstuff/confirm.html");
//                    break;
//                }
//                writer.println("Your Token Expired :\\");
                // if token validation succeeds redirect to page
                // else call ejb method that redirects to page for token generation via rest api
//        }
    }
}

