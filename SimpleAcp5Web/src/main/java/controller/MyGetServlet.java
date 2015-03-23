package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class MyGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("DO GET Method");
        System.out.printf("query %s, address %s",
                req.getQueryString(), req.getRemoteAddr());

        PrintWriter pw = resp.getWriter();
        pw.println("<h1>My Servlet</h1>");
        pw.flush();

        req.getServletContext().setAttribute("data", new Date());

    }
}
